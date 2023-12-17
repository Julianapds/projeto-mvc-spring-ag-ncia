package com.agencia.rotasdosol.controller;

import com.agencia.rotasdosol.model.usuario.TipoUsuarioEnum;
import com.agencia.rotasdosol.model.usuario.UsuarioModel;
import com.agencia.rotasdosol.repository.UsuarioRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class ClienteController {

    private final UsuarioRepository usuarioRepository;

    public ClienteController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping("/dashboard_admin/clientes")
    public String listarClientes(Model model) {

        List<UsuarioModel> usuarios = usuarioRepository.findAllByTipoUsuario(TipoUsuarioEnum.CLIENTE);
        model.addAttribute("usuarios", usuarios);
        return "lista_clientes";
    }

    @GetMapping("/dashboard_admin/clientes/cadastrar")
    public String cadastrarCliente(Model model) {
        model.addAttribute("usuario", new UsuarioModel());
        return "cadastro_usuario_admin";
    }

    @GetMapping("/dashboard_admin/clientes/editar")
    public String editarCliente(Model model, @RequestParam("id") Integer id) {

        Optional<UsuarioModel> usuarioModelOptional =  usuarioRepository.findById(id);

        if(usuarioModelOptional.isEmpty()){
            return "redirect:/dashboard_admin/clientes";
        }

        model.addAttribute("usuario", usuarioModelOptional.get());
        return "editar_cliente";
    }

    @PostMapping("/dashboard_admin/clientes/editar")
    public String editarCliente(@RequestParam("id") Integer id, UsuarioModel usuarioModel) {

        Optional<UsuarioModel> usuarioModelOptional =  usuarioRepository.findById(id);

        if(usuarioModelOptional.isEmpty()){
            return "redirect:/dashboard_admin/clientes";
        }

        UsuarioModel usuarioModel1 = usuarioModelOptional.get();

        usuarioModel1.setNome(usuarioModel.getNome());
        usuarioModel1.setCpf(usuarioModel.getCpf());
        usuarioModel1.setEmail(usuarioModel.getEmail());
        usuarioModel1.setEndereco(usuarioModel.getEndereco());
        usuarioModel1.setTelefone(usuarioModel.getTelefone());

        usuarioRepository.save(usuarioModel1);

        return "redirect:/dashboard_admin/clientes";
    }

    @GetMapping("/dashboard_admin/clientes/excluir")
    public String excluirCliente(@RequestParam("id") Integer id) {

        Optional<UsuarioModel> usuarioModelOptional =  usuarioRepository.findById(id);

        if(usuarioModelOptional.isEmpty()){
            return "redirect:/dashboard_admin/clientes";
        }

        usuarioRepository.delete(usuarioModelOptional.get());

        return "redirect:/dashboard_admin/clientes";
    }

}
