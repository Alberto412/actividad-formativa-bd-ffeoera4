package com.actividadformativabdffeoera4.actividadformativabdffeoera4.controller.web;

import com.actividadformativabdffeoera4.actividadformativabdffeoera4.entity.Cliente;
import com.actividadformativabdffeoera4.actividadformativabdffeoera4.entity.ParteTrabajo;
import com.actividadformativabdffeoera4.actividadformativabdffeoera4.entity.Tecnico;
import com.actividadformativabdffeoera4.actividadformativabdffeoera4.service.ClienteService;
import com.actividadformativabdffeoera4.actividadformativabdffeoera4.service.ParteTrabajoService;
import com.actividadformativabdffeoera4.actividadformativabdffeoera4.service.TecnicoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/partes")
public class ParteTrabajoWebController {

    private final ParteTrabajoService parteTrabajoService;
    private final ClienteService clienteService;
    private final TecnicoService tecnicoService;

    public ParteTrabajoWebController(ParteTrabajoService parteTrabajoService,
                                     ClienteService clienteService,
                                     TecnicoService tecnicoService) {
        this.parteTrabajoService = parteTrabajoService;
        this.clienteService = clienteService;
        this.tecnicoService = tecnicoService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("partes", parteTrabajoService.listar());
        return "partes/lista";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        ParteTrabajo parteTrabajo = new ParteTrabajo();
        parteTrabajo.setCliente(new Cliente());
        parteTrabajo.setTecnico(new Tecnico());
        model.addAttribute("parte", parteTrabajo);
        model.addAttribute("accion", "/partes");
        model.addAttribute("titulo", "Nuevo parte de trabajo");
        cargarCatalogos(model);
        return "partes/form";
    }

    @PostMapping
    public String crear(@ModelAttribute("parte") ParteTrabajo parteTrabajo) {
        if (parteTrabajo.getCliente() != null && parteTrabajo.getCliente().getId() != null) {
            parteTrabajo.setCliente(clienteService.obtenerPorId(parteTrabajo.getCliente().getId()));
        }
        if (parteTrabajo.getTecnico() != null && parteTrabajo.getTecnico().getId() != null) {
            parteTrabajo.setTecnico(tecnicoService.obtenerPorId(parteTrabajo.getTecnico().getId()));
        }
        parteTrabajoService.crear(parteTrabajo);
        return "redirect:/partes";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("parte", parteTrabajoService.obtenerPorId(id));
        model.addAttribute("accion", "/partes/editar/" + id);
        model.addAttribute("titulo", "Editar parte de trabajo");
        cargarCatalogos(model);
        return "partes/form";
    }

    @PostMapping("/editar/{id}")
    public String actualizar(@PathVariable Long id, @ModelAttribute("parte") ParteTrabajo parteTrabajo) {
        if (parteTrabajo.getCliente() != null && parteTrabajo.getCliente().getId() != null) {
            parteTrabajo.setCliente(clienteService.obtenerPorId(parteTrabajo.getCliente().getId()));
        }
        if (parteTrabajo.getTecnico() != null && parteTrabajo.getTecnico().getId() != null) {
            parteTrabajo.setTecnico(tecnicoService.obtenerPorId(parteTrabajo.getTecnico().getId()));
        }
        parteTrabajoService.actualizar(id, parteTrabajo);
        return "redirect:/partes";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        parteTrabajoService.eliminar(id);
        return "redirect:/partes";
    }

    private void cargarCatalogos(Model model) {
        model.addAttribute("clientes", clienteService.listar());
        model.addAttribute("tecnicos", tecnicoService.listar());
    }
}
