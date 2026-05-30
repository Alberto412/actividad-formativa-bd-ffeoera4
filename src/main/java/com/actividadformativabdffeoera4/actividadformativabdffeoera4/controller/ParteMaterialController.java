package com.actividadformativabdffeoera4.actividadformativabdffeoera4.controller;

import com.actividadformativabdffeoera4.actividadformativabdffeoera4.entity.ParteMaterial;
import com.actividadformativabdffeoera4.actividadformativabdffeoera4.service.ParteMaterialService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parte-materiales")
public class ParteMaterialController {

    private final ParteMaterialService service;

    public ParteMaterialController(ParteMaterialService service) { this.service = service; }

    @GetMapping
    public List<ParteMaterial> listar() { return service.listar(); }

    @GetMapping("/{id}")
    public ParteMaterial obtenerPorId(@PathVariable Long id) { return service.obtenerPorId(id); }

    @PostMapping
    public ParteMaterial crear(@RequestBody ParteMaterial parteMaterial) { return service.crear(parteMaterial); }

    @PutMapping("/{id}")
    public ParteMaterial actualizar(@PathVariable Long id, @RequestBody ParteMaterial parteMaterial) { return service.actualizar(id, parteMaterial); }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) { service.eliminar(id); }
}
