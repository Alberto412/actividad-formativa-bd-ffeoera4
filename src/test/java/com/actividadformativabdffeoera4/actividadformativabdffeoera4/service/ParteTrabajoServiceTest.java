package com.actividadformativabdffeoera4.actividadformativabdffeoera4.service;

import com.actividadformativabdffeoera4.actividadformativabdffeoera4.entity.Cliente;
import com.actividadformativabdffeoera4.actividadformativabdffeoera4.entity.ParteTrabajo;
import com.actividadformativabdffeoera4.actividadformativabdffeoera4.entity.Tecnico;
import com.actividadformativabdffeoera4.actividadformativabdffeoera4.repository.ParteTrabajoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ParteTrabajoServiceTest {

    @Mock
    private ParteTrabajoRepository repository;

    @InjectMocks
    private ParteTrabajoService service;

    @Test
    void actualizar_modificaCamposYRelaciones() {
        ParteTrabajo actual = new ParteTrabajo();
        actual.setId(3L);

        Cliente cliente = new Cliente();
        cliente.setId(1L);
        Tecnico tecnico = new Tecnico();
        tecnico.setId(1L);

        ParteTrabajo cambios = new ParteTrabajo();
        cambios.setTitulo("Incidencia red");
        cambios.setDescripcion("Se revisa router");
        cambios.setFecha(LocalDate.of(2026, 5, 30));
        cambios.setHorasTrabajadas(2.5);
        cambios.setEstado("EN PROCESO");
        cambios.setCliente(cliente);
        cambios.setTecnico(tecnico);

        when(repository.findById(3L)).thenReturn(Optional.of(actual));
        when(repository.save(actual)).thenReturn(actual);

        ParteTrabajo actualizado = service.actualizar(3L, cambios);

        assertEquals("Incidencia red", actualizado.getTitulo());
        assertEquals("Se revisa router", actualizado.getDescripcion());
        assertEquals(LocalDate.of(2026, 5, 30), actualizado.getFecha());
        assertEquals(2.5, actualizado.getHorasTrabajadas());
        assertEquals("EN PROCESO", actualizado.getEstado());
        assertEquals(1L, actualizado.getCliente().getId());
        assertEquals(1L, actualizado.getTecnico().getId());
        verify(repository).save(actual);
    }
}
