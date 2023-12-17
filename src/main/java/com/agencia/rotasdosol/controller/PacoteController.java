package com.agencia.rotasdosol.controller;

import com.agencia.rotasdosol.model.PacoteModel;
import com.agencia.rotasdosol.repository.HospedagemRepository;
import com.agencia.rotasdosol.repository.PacoteRepository;
import com.agencia.rotasdosol.repository.VooRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class PacoteController {

    private final PacoteRepository pacoteRepository;
    private final HospedagemRepository hospedagemRepository;
    private final VooRepository vooRepository;

    public PacoteController(PacoteRepository pacoteRepository, HospedagemRepository hospedagemRepository, VooRepository vooRepository) {
        this.pacoteRepository = pacoteRepository;
        this.hospedagemRepository = hospedagemRepository;
        this.vooRepository = vooRepository;
    }

    @GetMapping("/pacotes")
    public String listarPacotesPublico(Model model) {
        model.addAttribute("pacotes", pacoteRepository.findAll());
        return "pacotes";
    }

    @GetMapping("/dashboard_admin/pacotes")
    public String listarPacotes(Model model) {
        model.addAttribute("pacotes", pacoteRepository.findAll());
        return "lista_pacotes";
    }

    @GetMapping("/dashboard_admin/pacotes/cadastrar")
    public String mostrarFormularioDeCadastro(Model model) {
        model.addAttribute("pacote", new PacoteModel());
        model.addAttribute("hospedagens", hospedagemRepository.findAll());
        model.addAttribute("voos", vooRepository.findAll());
        return "cadastro_pacote";
    }

    @PostMapping("/dashboard_admin/pacotes/cadastrar")
    public String cadastrarPacote(PacoteModel pacote) {
        pacoteRepository.save(pacote);
        return "redirect:/dashboard_admin/pacotes";
    }

    @GetMapping("/dashboard_admin/pacotes/editar")
    public String mostrarFormularioDeEdicao(@RequestParam("id") Integer id, Model model) {
        Optional<PacoteModel> pacote = pacoteRepository.findById(id);
        if (pacote.isPresent()) {
            model.addAttribute("pacote", pacote.get());
            model.addAttribute("hospedagens", hospedagemRepository.findAll());
            model.addAttribute("voos", vooRepository.findAll());
            return "editar_pacote";
        } else {
            return "redirect:/dashboard_admin/pacotes";
        }
    }

    @PostMapping("/dashboard_admin/pacotes/editar")
    public String editarPacote(@RequestParam("id") Integer id, PacoteModel pacoteAtualizado) {
        Optional<PacoteModel> pacoteData = pacoteRepository.findById(id);
        if (pacoteData.isPresent()) {
            PacoteModel pacote = pacoteData.get();
            pacote.setValorPreco(pacoteAtualizado.getValorPreco());
            pacote.setHospedagem(pacoteAtualizado.getHospedagem());
            pacote.setVoo(pacoteAtualizado.getVoo());
            pacoteRepository.save(pacote);
        }
        return "redirect:/dashboard_admin/pacotes";
    }

    @GetMapping("/dashboard_admin/pacotes/excluir")
    public String excluirPacote(@RequestParam("id") Integer id) {
        pacoteRepository.deleteById(id);
        return "redirect:/dashboard_admin/pacotes";
    }
}