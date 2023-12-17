package com.agencia.rotasdosol.controller;

import com.agencia.rotasdosol.model.usuario.TipoUsuarioEnum;
import com.agencia.rotasdosol.model.usuario.UsuarioModel;
import com.agencia.rotasdosol.repository.UsuarioRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Controller
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping("/cadastro")
    public String cadastro(@RequestParam("tipo") String tipoUsuario, Model model, RedirectAttributes redirectAttributes) {
        model.addAttribute("usuario", new UsuarioModel());

        if ("ADMIN".equals(tipoUsuario)) {
            return "cadastro_usuario_admin";
        } else if ("CLIENTE".equals(tipoUsuario)) {
            return "cadastro_usuario_cliente";
        } else {
            String mensagemErro = "Tipo de usuário inválido: " + tipoUsuario;
            redirectAttributes.addAttribute("mensagem", URLEncoder.encode(mensagemErro, StandardCharsets.UTF_8));
            return "redirect:/erro";
        }
    }

    @PostMapping("/cadastro")
    public String cadastrarUsuario(@ModelAttribute UsuarioModel usuario, @RequestParam("tipo") String tipoUsuario, RedirectAttributes redirectAttributes) {

        String redirect;
        usuario.setTipoUsuario(TipoUsuarioEnum.CLIENTE);

        if ("ADMIN".equals(tipoUsuario)) {
            redirect = "redirect:/dashboard_admin/clientes";
        } else if ("CLIENTE".equals(tipoUsuario)) {
            redirect = "redirect:/";
        } else {
            String mensagemErro = "Tipo de usuário inválido: " + tipoUsuario;
            redirectAttributes.addAttribute("mensagem", URLEncoder.encode(mensagemErro, StandardCharsets.UTF_8));
            return "redirect:/erro";
        }

        usuarioRepository.save(usuario);

        return redirect;
    }

}
