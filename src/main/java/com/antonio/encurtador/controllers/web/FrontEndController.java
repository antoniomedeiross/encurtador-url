package com.antonio.encurtador.controllers.web;

import com.antonio.encurtador.services.EncurtaService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FrontEndController {
    private final EncurtaService encurtaService;

    public FrontEndController(EncurtaService encurtaService) {
        this.encurtaService = encurtaService;
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/erro")
    public String erro() {
        return "erro";
    }

    @PostMapping("/encurtar")
    private String encurtarUrl (@RequestParam("urlOriginal") String urlOriginal,
                                HttpServletRequest request, Model model) {


        String urlEncurtada = encurtaService.encurtar(urlOriginal, request);

        System.out.println(urlEncurtada);

        int reducaoPercentual = 100 - (urlEncurtada.length() * 100 / urlOriginal.length());



        model.addAttribute("urlEncurtada", urlEncurtada);
        model.addAttribute("reducaoPercentual", reducaoPercentual);
        return "index";
    }



}
