package com.nexos.inventario.integration;

import com.nexos.inventario.dto.MercanciaDTO;
import com.nexos.inventario.entity.Usuario;
import com.nexos.inventario.repository.MercanciaRepository;
import com.nexos.inventario.repository.UsuarioRepository;
import com.nexos.inventario.service.MercanciaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.nexos.inventario.repository.CargoRepository;
import com.nexos.inventario.entity.Cargo;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class MercanciaIntegrationTest {

    @Autowired
    private MercanciaService mercanciaService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private MercanciaRepository mercanciaRepository;

    @Autowired
    private CargoRepository cargoRepository;

    private Long usuarioId;

    @BeforeEach
    public void prepararDatos() {
        mercanciaRepository.deleteAll();
        usuarioRepository.deleteAll();
        cargoRepository.deleteAll();

        // Crear y guardar un cargo válido
        var cargo = new com.nexos.inventario.entity.Cargo();
        cargo.setNombre("Vendedor");
        cargo = cargoRepository.save(cargo);

        // Crear y guardar un usuario con cargo y fecha de ingreso
        var usuario = new Usuario();
        usuario.setNombre("Juan Pérez");
        usuario.setEdad(30);
        usuario.setFechaIngreso(LocalDate.now());
        usuario.setCargo(cargo);
        usuario = usuarioRepository.save(usuario);

        usuarioId = usuario.getId();
    }

    @Test
    public void testRegistroCompletoMercancia() {
        // Crear DTO
        MercanciaDTO dto = new MercanciaDTO();
        dto.setNombre("Aceite");
        dto.setCantidad(5);
        dto.setFechaIngreso(LocalDate.now());
        dto.setUsuarioRegistraId(usuarioId);

        // Llamar al servicio real
        MercanciaDTO guardada = mercanciaService.registrar(dto);

        // Verificar resultado
        assertThat(guardada).isNotNull();
        assertThat(guardada.getId()).isNotNull();
        assertThat(guardada.getNombre()).isEqualTo("Aceite");
        assertThat(guardada.getCantidad()).isEqualTo(5);
        assertThat(guardada.getUsuarioRegistraId()).isEqualTo(usuarioId);
    }
}
