package com.actividadformativabdffeoera4.actividadformativabdffeoera4.service;

import com.actividadformativabdffeoera4.actividadformativabdffeoera4.entity.Material;
import com.actividadformativabdffeoera4.actividadformativabdffeoera4.repository.MaterialRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MaterialServiceTest {

    @Mock
    private MaterialRepository repository;

    @InjectMocks
    private MaterialService service;

    @Test
    void crear_guardaMaterial() {
        Material m = new Material();
        m.setNombre("Cable");
        when(repository.save(m)).thenReturn(m);

        Material creado = service.crear(m);

        assertEquals("Cable", creado.getNombre());
        verify(repository).save(m);
    }

    @Test
    void actualizar_modificaCamposYGuarda() {
        Material actual = new Material();
        actual.setId(2L);

        Material cambios = new Material();
        cambios.setNombre("Switch");
        cambios.setDescripcion("24 puertos");
        cambios.setPrecioUnitario(120.0);
        cambios.setStock(5);

        when(repository.findById(2L)).thenReturn(Optional.of(actual));
        when(repository.save(actual)).thenReturn(actual);

        Material actualizado = service.actualizar(2L, cambios);

        assertEquals("Switch", actualizado.getNombre());
        assertEquals("24 puertos", actualizado.getDescripcion());
        assertEquals(120.0, actualizado.getPrecioUnitario());
        assertEquals(5, actualizado.getStock());
        verify(repository).save(actual);
    }
}
