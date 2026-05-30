package com.actividadformativabdffeoera4.actividadformativabdffeoera4.service;

import com.actividadformativabdffeoera4.actividadformativabdffeoera4.entity.Material;
import com.actividadformativabdffeoera4.actividadformativabdffeoera4.repository.MaterialRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialService {

    private final MaterialRepository repository;

    public MaterialService(MaterialRepository repository) {
        this.repository = repository;
    }

    public List<Material> listar() { return repository.findAll(); }
    public Material obtenerPorId(Long id) { return repository.findById(id).orElseThrow(); }
    public Material crear(Material material) { return repository.save(material); }
    public Material actualizar(Long id, Material material) {
        Material actual = obtenerPorId(id);
        actual.setNombre(material.getNombre());
        actual.setDescripcion(material.getDescripcion());
        actual.setPrecioUnitario(material.getPrecioUnitario());
        actual.setStock(material.getStock());
        return repository.save(actual);
    }
    public void eliminar(Long id) { repository.deleteById(id); }
}
