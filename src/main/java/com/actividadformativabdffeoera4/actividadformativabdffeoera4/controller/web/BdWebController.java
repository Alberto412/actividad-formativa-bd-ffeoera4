package com.actividadformativabdffeoera4.actividadformativabdffeoera4.controller.web;

import com.actividadformativabdffeoera4.actividadformativabdffeoera4.service.ClienteService;
import com.actividadformativabdffeoera4.actividadformativabdffeoera4.service.MaterialService;
import com.actividadformativabdffeoera4.actividadformativabdffeoera4.service.ParteMaterialService;
import com.actividadformativabdffeoera4.actividadformativabdffeoera4.service.ParteTrabajoService;
import com.actividadformativabdffeoera4.actividadformativabdffeoera4.service.TecnicoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class BdWebController {

    private final ClienteService clienteService;
    private final TecnicoService tecnicoService;
    private final MaterialService materialService;
    private final ParteTrabajoService parteTrabajoService;
    private final ParteMaterialService parteMaterialService;

    public BdWebController(ClienteService clienteService,
                           TecnicoService tecnicoService,
                           MaterialService materialService,
                           ParteTrabajoService parteTrabajoService,
                           ParteMaterialService parteMaterialService) {
        this.clienteService = clienteService;
        this.tecnicoService = tecnicoService;
        this.materialService = materialService;
        this.parteTrabajoService = parteTrabajoService;
        this.parteMaterialService = parteMaterialService;
    }

    @GetMapping("/bd")
    public String resumenBaseDatos(Model model) {
        var clientes = clienteService.listar();
        var tecnicos = tecnicoService.listar();
        var materiales = materialService.listar();
        var partes = parteTrabajoService.listar();
        var parteMateriales = parteMaterialService.listar();

        model.addAttribute("clientes", clientes);
        model.addAttribute("tecnicos", tecnicos);
        model.addAttribute("materiales", materiales);
        model.addAttribute("partes", partes);
        model.addAttribute("parteMateriales", parteMateriales);

        model.addAttribute("totalClientes", clientes.size());
        model.addAttribute("totalTecnicos", tecnicos.size());
        model.addAttribute("totalMateriales", materiales.size());
        model.addAttribute("totalPartes", partes.size());
        model.addAttribute("totalParteMateriales", parteMateriales.size());

        return "bd";
    }
}
