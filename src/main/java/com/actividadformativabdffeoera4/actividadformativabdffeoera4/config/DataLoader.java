package com.actividadformativabdffeoera4.actividadformativabdffeoera4.config;

import com.actividadformativabdffeoera4.actividadformativabdffeoera4.entity.*;
import com.actividadformativabdffeoera4.actividadformativabdffeoera4.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner loadData(
            ClienteRepository clienteRepository,
            TecnicoRepository tecnicoRepository,
            MaterialRepository materialRepository,
            ParteTrabajoRepository parteTrabajoRepository,
            ParteMaterialRepository parteMaterialRepository
    ) {
        return args -> {
            if (clienteRepository.count() > 0) {
                return;
            }

            Cliente smartFenix = new Cliente();
            smartFenix.setNombre("SmartFenix");
            smartFenix.setEmpresa("SmartFenix");
            smartFenix.setEmail("contacto@smartfenix.com");
            smartFenix.setTelefono("600111222");
            smartFenix = clienteRepository.save(smartFenix);

            Cliente oficinaCentral = new Cliente();
            oficinaCentral.setNombre("Oficina Central");
            oficinaCentral.setEmpresa("Oficina Central");
            oficinaCentral.setEmail("info@oficinacentral.com");
            oficinaCentral.setTelefono("600333444");
            oficinaCentral = clienteRepository.save(oficinaCentral);

            Tecnico alberto = new Tecnico();
            alberto.setNombre("Alberto Navarro");
            alberto.setEmail("alberto.navarro@empresa.com");
            alberto.setEspecialidad("Desarrollo / soporte");
            alberto = tecnicoRepository.save(alberto);

            Tecnico redes = new Tecnico();
            redes.setNombre("Técnico de redes");
            redes.setEmail("tecnico.redes@empresa.com");
            redes.setEspecialidad("Redes y mantenimiento");
            redes = tecnicoRepository.save(redes);

            Material cable = new Material();
            cable.setNombre("Cable Ethernet Cat6");
            cable.setDescripcion("Cable de red categoría 6");
            cable.setPrecioUnitario(2.50);
            cable.setStock(50);
            cable = materialRepository.save(cable);

            Material router = new Material();
            router.setNombre("Router");
            router.setDescripcion("Router empresarial");
            router.setPrecioUnitario(45.00);
            router.setStock(10);
            router = materialRepository.save(router);

            Material switch8 = new Material();
            switch8.setNombre("Switch 8 puertos");
            switch8.setDescripcion("Switch para red local");
            switch8.setPrecioUnitario(35.00);
            switch8.setStock(8);
            switch8 = materialRepository.save(switch8);

            Material rj45 = new Material();
            rj45.setNombre("Conector RJ45");
            rj45.setDescripcion("Conector para cable Ethernet");
            rj45.setPrecioUnitario(0.25);
            rj45.setStock(100);
            rj45 = materialRepository.save(rj45);

            ParteTrabajo parte1 = new ParteTrabajo();
            parte1.setTitulo("Revisión de red interna");
            parte1.setDescripcion("Diagnóstico y revisión de conectividad interna");
            parte1.setFecha(LocalDate.now());
            parte1.setHorasTrabajadas(2.5);
            parte1.setEstado("FINALIZADO");
            parte1.setCliente(smartFenix);
            parte1.setTecnico(alberto);
            parte1 = parteTrabajoRepository.save(parte1);

            ParteTrabajo parte2 = new ParteTrabajo();
            parte2.setTitulo("Sustitución de router");
            parte2.setDescripcion("Reemplazo de router averiado");
            parte2.setFecha(LocalDate.now());
            parte2.setHorasTrabajadas(1.5);
            parte2.setEstado("EN PROCESO");
            parte2.setCliente(oficinaCentral);
            parte2.setTecnico(redes);
            parte2 = parteTrabajoRepository.save(parte2);

            ParteMaterial pm1 = new ParteMaterial();
            pm1.setParteTrabajo(parte1);
            pm1.setMaterial(cable);
            pm1.setCantidad(5);
            parteMaterialRepository.save(pm1);

            ParteMaterial pm2 = new ParteMaterial();
            pm2.setParteTrabajo(parte1);
            pm2.setMaterial(rj45);
            pm2.setCantidad(10);
            parteMaterialRepository.save(pm2);

            ParteMaterial pm3 = new ParteMaterial();
            pm3.setParteTrabajo(parte2);
            pm3.setMaterial(router);
            pm3.setCantidad(1);
            parteMaterialRepository.save(pm3);

            ParteMaterial pm4 = new ParteMaterial();
            pm4.setParteTrabajo(parte2);
            pm4.setMaterial(switch8);
            pm4.setCantidad(1);
            parteMaterialRepository.save(pm4);
        };
    }
}
