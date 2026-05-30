package com.actividadformativabdffeoera4.actividadformativabdffeoera4.controller;

import com.actividadformativabdffeoera4.actividadformativabdffeoera4.entity.Tecnico;
import com.actividadformativabdffeoera4.actividadformativabdffeoera4.service.TecnicoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tecnicos")
public class TecnicoController {

    private final TecnicoService service;

    public TecnicoController(TecnicoService service) { this.service = service; }

    @GetMapping
    public List<Tecnico> listar() { return service.listar(); }

    @GetMapping("/{id}")
    public Tecnico obtenerPorId(@PathVariable Long id) { return service.obtenerPorId(id); }

    @PostMapping
    public Tecnico crear(@RequestBody Tecnico tecnico) { return service.crear(tecnico); }

    @PutMapping("/{id}")
    public Tecnico actualizar(@PathVariable Long id, @RequestBody Tecnico tecnico) { return service.actualizar(id, tecnico); }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) { service.eliminar(id); }
}
