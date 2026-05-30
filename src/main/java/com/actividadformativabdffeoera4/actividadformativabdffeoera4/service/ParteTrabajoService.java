package com.actividadformativabdffeoera4.actividadformativabdffeoera4.service;

import com.actividadformativabdffeoera4.actividadformativabdffeoera4.entity.ParteTrabajo;
import com.actividadformativabdffeoera4.actividadformativabdffeoera4.repository.ParteTrabajoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParteTrabajoService {

    private final ParteTrabajoRepository repository;

    public ParteTrabajoService(ParteTrabajoRepository repository) {
        this.repository = repository;
    }

    public List<ParteTrabajo> listar() { return repository.findAll(); }
    public ParteTrabajo obtenerPorId(Long id) { return repository.findById(id).orElseThrow(); }
    public ParteTrabajo crear(ParteTrabajo parteTrabajo) { return repository.save(parteTrabajo); }
    public ParteTrabajo actualizar(Long id, ParteTrabajo parteTrabajo) {
        ParteTrabajo actual = obtenerPorId(id);
        actual.setTitulo(parteTrabajo.getTitulo());
        actual.setDescripcion(parteTrabajo.getDescripcion());
        actual.setFecha(parteTrabajo.getFecha());
        actual.setHorasTrabajadas(parteTrabajo.getHorasTrabajadas());
        actual.setEstado(parteTrabajo.getEstado());
        actual.setCliente(parteTrabajo.getCliente());
        actual.setTecnico(parteTrabajo.getTecnico());
        return repository.save(actual);
    }
    public void eliminar(Long id) { repository.deleteById(id); }
}
