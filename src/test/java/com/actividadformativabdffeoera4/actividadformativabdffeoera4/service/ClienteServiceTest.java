package com.actividadformativabdffeoera4.actividadformativabdffeoera4.service;

import com.actividadformativabdffeoera4.actividadformativabdffeoera4.entity.Cliente;
import com.actividadformativabdffeoera4.actividadformativabdffeoera4.repository.ClienteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {

    @Mock
    private ClienteRepository repository;

    @InjectMocks
    private ClienteService service;

    @Test
    void listar_devuelveClientes() {
        Cliente c = new Cliente();
        c.setNombre("Cliente Test");
        when(repository.findAll()).thenReturn(List.of(c));

        List<Cliente> resultado = service.listar();

        assertEquals(1, resultado.size());
        assertEquals("Cliente Test", resultado.get(0).getNombre());
        verify(repository).findAll();
    }

    @Test
    void actualizar_modificaCamposYGuarda() {
        Cliente actual = new Cliente();
        actual.setId(1L);
        actual.setNombre("Anterior");

        Cliente cambios = new Cliente();
        cambios.setNombre("Nuevo");
        cambios.setEmpresa("Empresa X");
        cambios.setEmail("nuevo@test.com");
        cambios.setTelefono("600000000");

        when(repository.findById(1L)).thenReturn(Optional.of(actual));
        when(repository.save(actual)).thenReturn(actual);

        Cliente actualizado = service.actualizar(1L, cambios);

        assertEquals("Nuevo", actualizado.getNombre());
        assertEquals("Empresa X", actualizado.getEmpresa());
        assertEquals("nuevo@test.com", actualizado.getEmail());
        assertEquals("600000000", actualizado.getTelefono());
        verify(repository).save(actual);
    }
}
