package com.antonio.encurtador.services;

import com.antonio.encurtador.model.Url;
import com.antonio.encurtador.repository.UrlRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class EncurtaService {
    private final UrlRepository urlRepository; // banco de dados h2-database

    public EncurtaService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    // gera link curto e faz o link com o (longo)
    public String encurtar(String urlAntiga, HttpServletRequest request) {
        String urlEncurtada = encurtar();

        // Adiciona o protocolo se estiver faltando
        if (!urlAntiga.startsWith("http://") && !urlAntiga.startsWith("https://")) {
            urlAntiga = "http://" + urlAntiga;
        }
        LocalDate now = LocalDate.now();

        Url url = new Url(null,urlAntiga, urlEncurtada, now);

        urlRepository.save(url);

        return  baseUrlServer(request) + "/" + urlEncurtada;
    }

    // retorna o caminho do servidor
    public String baseUrlServer(HttpServletRequest request) {
        String baseUrl = request.getScheme() + "://" +
                request.getServerName() +
                ":" + request.getServerPort();

        return baseUrl;
    }

    // gera um link curto
    public String encurtar() {
        String id;
        do {
            id = UUID.randomUUID().toString().substring(0, 6); // Gere um novo ID
        } while (urlRepository.findByUrlCurta(id).isPresent()); // Enquanto houver um ID parecido

        return id;
    }

    // busca a url para redirecionar
    public String buscaUrl(String urlCurta) {
        return urlRepository.findByUrlCurta(urlCurta)
                .map(Url::getUrlLonga)
                .orElse(null);
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void limparUrlsAntigas() {
        LocalDateTime limite = LocalDateTime.now().minusHours(24);
        urlRepository.deleteByCreatedAtBefore(limite);
    }
}
