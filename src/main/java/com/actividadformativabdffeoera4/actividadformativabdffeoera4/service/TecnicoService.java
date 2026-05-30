package com.actividadformativabdffeoera4.actividadformativabdffeoera4.service;

import com.actividadformativabdffeoera4.actividadformativabdffeoera4.entity.Tecnico;
import com.actividadformativabdffeoera4.actividadformativabdffeoera4.repository.TecnicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TecnicoService {

    private final TecnicoRepository repository;

    public TecnicoService(TecnicoRepository repository) {
        this.repository = repository;
    }

    public List<Tecnico> listar() { return repository.findAll(); }
    public Tecnico obtenerPorId(Long id) { return repository.findById(id).orElseThrow(); }
    public Tecnico crear(Tecnico tecnico) { return repository.save(tecnico); }
    public Tecnico actualizar(Long id, Tecnico tecnico) {
        Tecnico actual = obtenerPorId(id);
        actual.setNombre(tecnico.getNombre());
        actual.setEmail(tecnico.getEmail());
        actual.setEspecialidad(tecnico.getEspecialidad());
        return repository.save(actual);
    }
    public void eliminar(Long id) { repository.deleteById(id); }
}
