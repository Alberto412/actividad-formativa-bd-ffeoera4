package com.actividadformativabdffeoera4.actividadformativabdffeoera4.controller.web;

import com.actividadformativabdffeoera4.actividadformativabdffeoera4.entity.Material;
import com.actividadformativabdffeoera4.actividadformativabdffeoera4.entity.ParteMaterial;
import com.actividadformativabdffeoera4.actividadformativabdffeoera4.entity.ParteTrabajo;
import com.actividadformativabdffeoera4.actividadformativabdffeoera4.service.MaterialService;
import com.actividadformativabdffeoera4.actividadformativabdffeoera4.service.ParteMaterialService;
import com.actividadformativabdffeoera4.actividadformativabdffeoera4.service.ParteTrabajoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/parte-materiales")
public class ParteMaterialWebController {

    private final ParteMaterialService parteMaterialService;
    private final ParteTrabajoService parteTrabajoService;
    private final MaterialService materialService;

    public ParteMaterialWebController(ParteMaterialService parteMaterialService,
                                      ParteTrabajoService parteTrabajoService,
                                      MaterialService materialService) {
        this.parteMaterialService = parteMaterialService;
        this.parteTrabajoService = parteTrabajoService;
        this.materialService = materialService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("parteMateriales", parteMaterialService.listar());
        return "parte-materiales/lista";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        ParteMaterial parteMaterial = new ParteMaterial();
        parteMaterial.setParteTrabajo(new ParteTrabajo());
        parteMaterial.setMaterial(new Material());
        model.addAttribute("parteMaterial", parteMaterial);
        model.addAttribute("accion", "/parte-materiales");
        model.addAttribute("partes", parteTrabajoService.listar());
        model.addAttribute("materiales", materialService.listar());
        return "parte-materiales/form";
    }

    @PostMapping
    public String crear(@ModelAttribute ParteMaterial parteMaterial) {
        if (parteMaterial.getParteTrabajo() != null && parteMaterial.getParteTrabajo().getId() != null) {
            parteMaterial.setParteTrabajo(parteTrabajoService.obtenerPorId(parteMaterial.getParteTrabajo().getId()));
        }
        if (parteMaterial.getMaterial() != null && parteMaterial.getMaterial().getId() != null) {
            parteMaterial.setMaterial(materialService.obtenerPorId(parteMaterial.getMaterial().getId()));
        }
        parteMaterialService.crear(parteMaterial);
        return "redirect:/parte-materiales";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        parteMaterialService.eliminar(id);
        return "redirect:/parte-materiales";
    }
}
