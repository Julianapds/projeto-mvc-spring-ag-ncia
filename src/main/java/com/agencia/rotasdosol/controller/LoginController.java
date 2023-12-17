package com.agencia.rotasdosol.controller;

import com.agencia.rotasdosol.model.usuario.UsuarioModel;
import com.agencia.rotasdosol.repository.UsuarioRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

import static com.agencia.rotasdosol.model.usuario.TipoUsuarioEnum.ADMIN;
import static com.agencia.rotasdosol.model.usuario.TipoUsuarioEnum.CLIENTE;

@Controller
@RequestMapping("/login")
public class LoginController {

    private final UsuarioRepository usuarioRepository;

    public LoginController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping
    public String mostrarFormularioLogin(Model model) {
        model.addAttribute("usuario", new UsuarioModel());
        return "login";
    }

    @PostMapping
    public String processarLogin(@ModelAttribute UsuarioModel usuario, RedirectAttributes redirectAttributes, HttpSession session) {

        Optional<UsuarioModel> usuarioEncontrado = usuarioRepository.findByEmail(usuario.getEmail());

        if (usuarioEncontrado.isPresent() && usuarioEncontrado.get().getSenha().equals(usuario.getSenha())) {

            session.setAttribute("idUsuario", usuarioEncontrado.get().getIdUsuario());
            session.setAttribute("nomeUsuario", usuarioEncontrado.get().getNome());

            if (ADMIN.equals(usuarioEncontrado.get().getTipoUsuario())) {
                return "redirect:/dashboard_admin";
            } else if (CLIENTE.equals(usuarioEncontrado.get().getTipoUsuario())) {
                return "redirect:/dashboard_cliente";
            } else {
                redirectAttributes.addFlashAttribute("mensagemErro", "Tipo de usuário desconhecido.");
                return "redirect:/login";
            }
        } else {
            redirectAttributes.addFlashAttribute("mensagemErro", "Email ou senha inválidos.");
            return "redirect:/login";
        }
    }
}