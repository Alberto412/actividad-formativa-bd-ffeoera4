package com.actividadformativabdffeoera4.actividadformativabdffeoera4.controller;

import com.actividadformativabdffeoera4.actividadformativabdffeoera4.entity.Material;
import com.actividadformativabdffeoera4.actividadformativabdffeoera4.service.MaterialService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/materiales")
public class MaterialController {

    private final MaterialService service;

    public MaterialController(MaterialService service) { this.service = service; }

    @GetMapping
    public List<Material> listar() { return service.listar(); }

    @GetMapping("/{id}")
    public Material obtenerPorId(@PathVariable Long id) { return service.obtenerPorId(id); }

    @PostMapping
    public Material crear(@RequestBody Material material) { return service.crear(material); }

    @PutMapping("/{id}")
    public Material actualizar(@PathVariable Long id, @RequestBody Material material) { return service.actualizar(id, material); }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) { service.eliminar(id); }
}
