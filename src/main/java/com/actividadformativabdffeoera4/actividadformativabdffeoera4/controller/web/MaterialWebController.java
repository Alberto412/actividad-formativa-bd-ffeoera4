package com.actividadformativabdffeoera4.actividadformativabdffeoera4.controller.web;

import com.actividadformativabdffeoera4.actividadformativabdffeoera4.entity.Material;
import com.actividadformativabdffeoera4.actividadformativabdffeoera4.service.MaterialService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/materiales")
public class MaterialWebController {

    private final MaterialService materialService;

    public MaterialWebController(MaterialService materialService) {
        this.materialService = materialService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("materiales", materialService.listar());
        return "materiales/lista";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("material", new Material());
        model.addAttribute("accion", "/materiales");
        model.addAttribute("titulo", "Nuevo material");
        return "materiales/form";
    }

    @PostMapping
    public String crear(@ModelAttribute Material material) {
        materialService.crear(material);
        return "redirect:/materiales";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("material", materialService.obtenerPorId(id));
        model.addAttribute("accion", "/materiales/editar/" + id);
        model.addAttribute("titulo", "Editar material");
        return "materiales/form";
    }

    @PostMapping("/editar/{id}")
    public String actualizar(@PathVariable Long id, @ModelAttribute Material material) {
        materialService.actualizar(id, material);
        return "redirect:/materiales";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        materialService.eliminar(id);
        return "redirect:/materiales";
    }
}
