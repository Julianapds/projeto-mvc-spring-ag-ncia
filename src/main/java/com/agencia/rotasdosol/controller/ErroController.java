package com.agencia.rotasdosol.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ErroController {
    @GetMapping("/erro")
    public String erro(@RequestParam(required = false) String mensagem, Model model) {
        if (mensagem != null && !mensagem.isEmpty()) {
            model.addAttribute("mensagemErro", mensagem);
        } else {
            model.addAttribute("mensagemErro", "Ocorreu um erro desconhecido.");
        }
        return "erro";
    }
}
