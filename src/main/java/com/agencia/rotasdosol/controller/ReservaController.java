package com.agencia.rotasdosol.controller;

import com.agencia.rotasdosol.model.*;
import com.agencia.rotasdosol.model.usuario.UsuarioModel;
import com.agencia.rotasdosol.repository.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ReservaController {

    private final HospedagemRepository hospedagemRepository;
    private final VooRepository vooRepository;
    private final PacoteRepository pacoteRepository;
    private final ReservaRepository reservaRepository;

    private final UsuarioRepository usuarioRepository;

    public ReservaController(HospedagemRepository hospedagemRepository, VooRepository vooRepository, PacoteRepository pacoteRepository, ReservaRepository reservaRepository, UsuarioRepository clienteService) {
        this.hospedagemRepository = hospedagemRepository;
        this.vooRepository = vooRepository;
        this.pacoteRepository = pacoteRepository;
        this.reservaRepository = reservaRepository;
        this.usuarioRepository = clienteService;
    }

    @GetMapping("/dashboard_cliente/criar_reserva")
    public String mostrarFormularioDeReserva(Model model, HttpSession session) {
        Integer idUsuario = (Integer) session.getAttribute("idUsuario");
        String nomeUsuario = (String) session.getAttribute("nomeUsuario");
        List<HospedagemModel> hospedagens = hospedagemRepository.findAll();
        List<VooModel> voos = vooRepository.findAll();
        List<PacoteModel> pacotes = pacoteRepository.findAll();

        model.addAttribute("idUsuario", idUsuario);
        model.addAttribute("nomeUsuario", nomeUsuario);
        model.addAttribute("hospedagens", hospedagens);
        model.addAttribute("voos", voos);
        model.addAttribute("pacotes", pacotes);

        return "cadastro_reserva";
    }

    @PostMapping("/dashboard_cliente/criar_reserva")
    public String salvarReserva(@ModelAttribute ReservaModel reserva, HttpSession session) {

        StatusReservaModel statusReserva = new StatusReservaModel();
        statusReserva.setIdStatus(1);
        reserva.setStatus(statusReserva);

        Integer idUsuario = (Integer) session.getAttribute("idUsuario");

        UsuarioModel cliente = usuarioRepository.findById(idUsuario).orElseThrow();

        reserva.setCliente(cliente);
        reserva.setDataReserva(new java.util.Date());
        reserva.setStatus(statusReserva);

        reservaRepository.save(reserva);

        return "redirect:/dashboard_cliente";
    }
}