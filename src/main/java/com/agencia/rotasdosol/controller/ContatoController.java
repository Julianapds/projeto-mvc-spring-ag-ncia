package com.agencia.rotasdosol.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContatoController {

    @GetMapping("/contatos")
    public String contatos() {
        return "contatos";
    }
}
