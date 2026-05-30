package com.actividadformativabdffeoera4.actividadformativabdffeoera4.service;

import com.actividadformativabdffeoera4.actividadformativabdffeoera4.entity.Cliente;
import com.actividadformativabdffeoera4.actividadformativabdffeoera4.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public List<Cliente> listar() { return repository.findAll(); }
    public Cliente obtenerPorId(Long id) { return repository.findById(id).orElseThrow(); }
    public Cliente crear(Cliente cliente) { return repository.save(cliente); }
    public Cliente actualizar(Long id, Cliente cliente) {
        Cliente actual = obtenerPorId(id);
        actual.setNombre(cliente.getNombre());
        actual.setEmpresa(cliente.getEmpresa());
        actual.setEmail(cliente.getEmail());
        actual.setTelefono(cliente.getTelefono());
        return repository.save(actual);
    }
    public void eliminar(Long id) { repository.deleteById(id); }
}
