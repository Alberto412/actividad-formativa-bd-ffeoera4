package com.actividadformativabdffeoera4.actividadformativabdffeoera4.controller.web;

import com.actividadformativabdffeoera4.actividadformativabdffeoera4.entity.Cliente;
import com.actividadformativabdffeoera4.actividadformativabdffeoera4.service.ClienteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/clientes")
public class ClienteWebController {

    private final ClienteService clienteService;

    public ClienteWebController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("clientes", clienteService.listar());
        return "clientes/lista";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("cliente", new Cliente());
        model.addAttribute("accion", "/clientes");
        model.addAttribute("titulo", "Nuevo cliente");
        return "clientes/form";
    }

    @PostMapping
    public String crear(@ModelAttribute Cliente cliente) {
        clienteService.crear(cliente);
        return "redirect:/clientes";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("cliente", clienteService.obtenerPorId(id));
        model.addAttribute("accion", "/clientes/editar/" + id);
        model.addAttribute("titulo", "Editar cliente");
        return "clientes/form";
    }

    @PostMapping("/editar/{id}")
    public String actualizar(@PathVariable Long id, @ModelAttribute Cliente cliente) {
        clienteService.actualizar(id, cliente);
        return "redirect:/clientes";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        clienteService.eliminar(id);
        return "redirect:/clientes";
    }
}
