package com.actividadformativabdffeoera4.actividadformativabdffeoera4.controller;

import com.actividadformativabdffeoera4.actividadformativabdffeoera4.entity.ParteTrabajo;
import com.actividadformativabdffeoera4.actividadformativabdffeoera4.service.ParteTrabajoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/partes")
public class ParteTrabajoController {

    private final ParteTrabajoService service;

    public ParteTrabajoController(ParteTrabajoService service) { this.service = service; }

    @GetMapping
    public List<ParteTrabajo> listar() { return service.listar(); }

    @GetMapping("/{id}")
    public ParteTrabajo obtenerPorId(@PathVariable Long id) { return service.obtenerPorId(id); }

    @PostMapping
    public ParteTrabajo crear(@RequestBody ParteTrabajo parteTrabajo) { return service.crear(parteTrabajo); }

    @PutMapping("/{id}")
    public ParteTrabajo actualizar(@PathVariable Long id, @RequestBody ParteTrabajo parteTrabajo) { return service.actualizar(id, parteTrabajo); }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) { service.eliminar(id); }
}
