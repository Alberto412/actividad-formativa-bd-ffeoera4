package com.actividadformativabdffeoera4.actividadformativabdffeoera4.controller.web;

import com.actividadformativabdffeoera4.actividadformativabdffeoera4.entity.Tecnico;
import com.actividadformativabdffeoera4.actividadformativabdffeoera4.service.TecnicoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tecnicos")
public class TecnicoWebController {

    private final TecnicoService tecnicoService;

    public TecnicoWebController(TecnicoService tecnicoService) {
        this.tecnicoService = tecnicoService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("tecnicos", tecnicoService.listar());
        return "tecnicos/lista";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("tecnico", new Tecnico());
        model.addAttribute("accion", "/tecnicos");
        model.addAttribute("titulo", "Nuevo técnico");
        return "tecnicos/form";
    }

    @PostMapping
    public String crear(@ModelAttribute Tecnico tecnico) {
        tecnicoService.crear(tecnico);
        return "redirect:/tecnicos";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("tecnico", tecnicoService.obtenerPorId(id));
        model.addAttribute("accion", "/tecnicos/editar/" + id);
        model.addAttribute("titulo", "Editar técnico");
        return "tecnicos/form";
    }

    @PostMapping("/editar/{id}")
    public String actualizar(@PathVariable Long id, @ModelAttribute Tecnico tecnico) {
        tecnicoService.actualizar(id, tecnico);
        return "redirect:/tecnicos";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        tecnicoService.eliminar(id);
        return "redirect:/tecnicos";
    }
}
