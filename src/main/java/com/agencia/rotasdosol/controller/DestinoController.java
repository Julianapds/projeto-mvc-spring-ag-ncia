package com.agencia.rotasdosol.controller;

import com.agencia.rotasdosol.model.DestinoModel;
import com.agencia.rotasdosol.repository.DestinoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class DestinoController {

    private final DestinoRepository destinoRepository;

    public DestinoController(DestinoRepository destinoRepository) {
        this.destinoRepository = destinoRepository;
    }

    @GetMapping("/dashboard_admin/destinos")
    public String listarDestinos(Model model) {
        List<DestinoModel> destinos = destinoRepository.findAll();
        model.addAttribute("destinos", destinos);
        return "lista_destinos";
    }

    @GetMapping("/dashboard_admin/destinos/cadastrar")
    public String cadastrarDestino(Model model) {
        model.addAttribute("destino", new DestinoModel());
        return "cadastro_destino";
    }

    @PostMapping("/dashboard_admin/destinos/cadastrar")
    public String cadastrarDestino(DestinoModel destinoModel) {
        destinoRepository.save(destinoModel);
        return "redirect:/dashboard_admin/destinos";
    }

    @GetMapping("/dashboard_admin/destinos/editar")
    public String editarDestino(Model model, @RequestParam("id") Integer id) {
        Optional<DestinoModel> destinoModelOptional = destinoRepository.findById(id);

        if(destinoModelOptional.isEmpty()){
            return "redirect:/dashboard_admin/destinos";
        }

        model.addAttribute("destino", destinoModelOptional.get());
        return "editar_destino";
    }

    @PostMapping("/dashboard_admin/destinos/editar")
    public String editarDestino(@RequestParam("id") Integer id, DestinoModel destinoModel) {
        Optional<DestinoModel> destinoModelOptional = destinoRepository.findById(id);

        if(destinoModelOptional.isEmpty()){
            return "redirect:/dashboard_admin/destinos";
        }

        DestinoModel destinoModel1 = destinoModelOptional.get();

        destinoModel1.setDescricao(destinoModel.getDescricao());
        destinoModel1.setPais(destinoModel.getPais());
        destinoModel1.setCidade(destinoModel.getCidade());

        destinoRepository.save(destinoModel1);

        return "redirect:/dashboard_admin/destinos";
    }

    @GetMapping("/dashboard_admin/destinos/excluir")
    public String excluirDestino(@RequestParam("id") Integer id) {
        Optional<DestinoModel> destinoModelOptional = destinoRepository.findById(id);

        if(destinoModelOptional.isEmpty()){
            return "redirect:/dashboard_admin/destinos";
        }

        destinoRepository.delete(destinoModelOptional.get());

        return "redirect:/dashboard_admin/destinos";
    }
}