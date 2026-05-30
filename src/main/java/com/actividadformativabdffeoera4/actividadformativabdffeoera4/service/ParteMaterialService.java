package com.actividadformativabdffeoera4.actividadformativabdffeoera4.service;

import com.actividadformativabdffeoera4.actividadformativabdffeoera4.entity.ParteMaterial;
import com.actividadformativabdffeoera4.actividadformativabdffeoera4.repository.ParteMaterialRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParteMaterialService {

    private final ParteMaterialRepository repository;

    public ParteMaterialService(ParteMaterialRepository repository) {
        this.repository = repository;
    }

    public List<ParteMaterial> listar() { return repository.findAll(); }
    public ParteMaterial obtenerPorId(Long id) { return repository.findById(id).orElseThrow(); }
    public ParteMaterial crear(ParteMaterial parteMaterial) { return repository.save(parteMaterial); }
    public ParteMaterial actualizar(Long id, ParteMaterial parteMaterial) {
        ParteMaterial actual = obtenerPorId(id);
        actual.setCantidad(parteMaterial.getCantidad());
        actual.setParteTrabajo(parteMaterial.getParteTrabajo());
        actual.setMaterial(parteMaterial.getMaterial());
        return repository.save(actual);
    }
    public void eliminar(Long id) { repository.deleteById(id); }
}
