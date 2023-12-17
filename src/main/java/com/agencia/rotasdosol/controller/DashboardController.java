package com.agencia.rotasdosol.controller;

import com.agencia.rotasdosol.model.ReservaModel;
import com.agencia.rotasdosol.repository.ReservaRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class DashboardController {

    private final ReservaRepository reservaRepository;

    public DashboardController(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    @GetMapping("/dashboard_admin")
    public String dashboardAdmin(Model model, HttpSession session) {
        String nomeUsuario = (String) session.getAttribute("nomeUsuario");
        model.addAttribute("nomeUsuario", nomeUsuario);
        return "dashboard_admin";
    }

    @GetMapping("/dashboard_cliente")
    public String dashboardCliente(Model model, HttpSession session) {
        String nomeUsuario = (String) session.getAttribute("nomeUsuario");
        Integer idUsuario = (Integer) session.getAttribute("idUsuario");

        List<ReservaModel> reservas = reservaRepository.findByClienteIdUsuario(idUsuario);

        model.addAttribute("nomeUsuario", nomeUsuario);
        model.addAttribute("reservas", reservas);

        return "dashboard_cliente";
    }
}
