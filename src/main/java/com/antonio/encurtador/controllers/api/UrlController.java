package com.antonio.encurtador.controllers.api;

import com.antonio.encurtador.model.Url;
import com.antonio.encurtador.repository.UrlRepository;
import com.antonio.encurtador.services.EncurtaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@Slf4j
@RestController
public class UrlController {

    // http://localhost:8080/linkcurto
    // redireciona para o valor

    @Autowired
    private EncurtaService encurtaService;

    @GetMapping("/{urlCurta}")
    public ResponseEntity<Void> direcionar(@PathVariable String urlCurta) {
        String urlAntiga = encurtaService.buscaUrl(urlCurta);

        if (urlAntiga == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .location(URI.create("/erro"))
                    .build();
        } else {
            return ResponseEntity
                    .status(HttpStatus.FOUND)
                    .location(URI.create(urlAntiga))
                    .build();
        }
    }

}
