package com.agencia.rotasdosol.controller;

import com.agencia.rotasdosol.model.HospedagemModel;
import com.agencia.rotasdosol.repository.DestinoRepository;
import com.agencia.rotasdosol.repository.HospedagemRepository;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Controller
public class HospedagemController {

    private final HospedagemRepository hospedagemRepository;
    private final DestinoRepository destinoRepository;

    public HospedagemController(HospedagemRepository hospedagemRepository, DestinoRepository destinoRepository) {
        this.hospedagemRepository = hospedagemRepository;
        this.destinoRepository = destinoRepository;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @GetMapping("/hospedagens")
    public String listarHospedagensPublico(Model model) {
        model.addAttribute("hospedagens", hospedagemRepository.findAll());
        return "hospedagens";
    }

    @GetMapping("/dashboard_admin/hospedagens")
    public String listarHospedagens(Model model) {
        model.addAttribute("hospedagens", hospedagemRepository.findAll());
        return "lista_hospedagens";
    }

    @GetMapping("/dashboard_admin/hospedagens/cadastrar")
    public String mostrarFormularioDeCadastro(Model model) {
        model.addAttribute("hospedagem", new HospedagemModel());
        model.addAttribute("destinos", destinoRepository.findAll()); // Adiciona os destinos ao modelo
        return "cadastro_hospedagem";
    }

    @PostMapping("/dashboard_admin/hospedagens/cadastrar")
    public String cadastrarHospedagem(HospedagemModel hospedagem) {
        hospedagemRepository.save(hospedagem);
        return "redirect:/dashboard_admin/hospedagens";
    }

    @GetMapping("/dashboard_admin/hospedagens/editar")
    public String mostrarFormularioDeEdicao(@RequestParam("id") Integer id, Model model) {
        Optional<HospedagemModel> hospedagem = hospedagemRepository.findById(id);
        if (hospedagem.isPresent()) {
            model.addAttribute("hospedagem", hospedagem.get());
            model.addAttribute("destinos", destinoRepository.findAll()); // Adiciona os destinos ao modelo
            return "editar_hospedagem";
        } else {
            return "redirect:/dashboard_admin/hospedagens";
        }
    }

    @PostMapping("/dashboard_admin/hospedagens/editar")
    public String editarHospedagem(@RequestParam("id") Integer id, HospedagemModel hospedagemAtualizada) {
        Optional<HospedagemModel> hospedagemData = hospedagemRepository.findById(id);
        if (hospedagemData.isPresent()) {
            HospedagemModel hospedagem = hospedagemData.get();
            hospedagem.setNomeHotel(hospedagemAtualizada.getNomeHotel());
            hospedagem.setTipoQuarto(hospedagemAtualizada.getTipoQuarto());
            hospedagem.setDataCheckin(hospedagemAtualizada.getDataCheckin());
            hospedagem.setDataCheckout(hospedagemAtualizada.getDataCheckout());
            hospedagem.setValorPernoite(hospedagemAtualizada.getValorPernoite());
            hospedagem.setDestino(hospedagemAtualizada.getDestino());
            hospedagemRepository.save(hospedagem);
        }
        return "redirect:/dashboard_admin/hospedagens";
    }

    @GetMapping("/dashboard_admin/hospedagens/excluir")
    public String excluirHospedagem(@RequestParam("id") Integer id) {
        hospedagemRepository.deleteById(id);
        return "redirect:/dashboard_admin/hospedagens";
    }
}