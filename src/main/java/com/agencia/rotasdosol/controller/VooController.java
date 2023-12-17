package com.agencia.rotasdosol.controller;

import com.agencia.rotasdosol.model.VooModel;
import com.agencia.rotasdosol.repository.DestinoRepository;
import com.agencia.rotasdosol.repository.VooRepository;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Controller
public class VooController {

    private final VooRepository vooRepository;
    private final DestinoRepository destinoRepository;

    public VooController(VooRepository vooRepository, DestinoRepository destinoRepository) {
        this.vooRepository = vooRepository;
        this.destinoRepository = destinoRepository;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @GetMapping("/voos")
    public String listarVoosPublico(Model model) {
        model.addAttribute("voos", vooRepository.findAll());
        return "voos";
    }

    @GetMapping("/dashboard_admin/voos")
    public String listarVoos(Model model) {
        model.addAttribute("voos", vooRepository.findAll());
        return "lista_voos";
    }

    @GetMapping("/dashboard_admin/voos/cadastrar")
    public String mostrarFormularioDeCadastro(Model model) {
        model.addAttribute("voo", new VooModel());
        model.addAttribute("destinos", destinoRepository.findAll());
        return "cadastro_voo";
    }

    @PostMapping("/dashboard_admin/voos/cadastrar")
    public String cadastrarVoo(VooModel voo) {
        vooRepository.save(voo);
        return "redirect:/dashboard_admin/voos";
    }

    @GetMapping("/dashboard_admin/voos/editar")
    public String mostrarFormularioDeEdicao(@RequestParam("id") Integer id, Model model) {
        Optional<VooModel> voo = vooRepository.findById(id);
        if (voo.isPresent()) {
            model.addAttribute("voo", voo.get());
            model.addAttribute("destinos", destinoRepository.findAll());
            return "editar_voo";
        } else {
            return "redirect:/dashboard_admin/voos";
        }
    }

    @PostMapping("/dashboard_admin/voos/editar")
    public String editarVoo(@RequestParam("id") Integer id, VooModel vooAtualizado) {
        Optional<VooModel> vooData = vooRepository.findById(id);
        if (vooData.isPresent()) {
            VooModel voo = vooData.get();
            voo.setCompanhiaAerea(vooAtualizado.getCompanhiaAerea());
            voo.setDataPartida(vooAtualizado.getDataPartida());
            voo.setDataChegada(vooAtualizado.getDataChegada());
            voo.setValorPreco(vooAtualizado.getValorPreco());
            voo.setDestino(vooAtualizado.getDestino());
            vooRepository.save(voo);
        }
        return "redirect:/dashboard_admin/voos";
    }

    @GetMapping("/dashboard_admin/voos/excluir")
    public String excluirVoo(@RequestParam("id") Integer id) {
        vooRepository.deleteById(id);
        return "redirect:/dashboard_admin/voos";
    }
}