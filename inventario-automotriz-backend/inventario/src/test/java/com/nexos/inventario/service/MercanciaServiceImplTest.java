package com.nexos.inventario.service;

import com.nexos.inventario.dto.MercanciaDTO;
import com.nexos.inventario.entity.Mercancia;
import com.nexos.inventario.entity.Usuario;
import com.nexos.inventario.repository.MercanciaRepository;
import com.nexos.inventario.repository.UsuarioRepository;
import com.nexos.inventario.service.impl.MercanciaServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class MercanciaServiceImplTest {

    @Mock
    private MercanciaRepository mercanciaRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private MercanciaServiceImpl mercanciaService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegistrarMercancia() {
        // Crear DTO de entrada
        MercanciaDTO dto = new MercanciaDTO();
        dto.setNombre("Batería");
        dto.setCantidad(3);
        dto.setFechaIngreso(LocalDate.now());
        dto.setUsuarioRegistraId(1L);

        // Simular usuario retornado por el repositorio
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNombre("Juan");

        // Simular mercancía guardada
        Mercancia entidadGuardada = new Mercancia();
        entidadGuardada.setId(1L);
        entidadGuardada.setNombre("Batería");
        entidadGuardada.setCantidad(3);
        entidadGuardada.setFechaIngreso(dto.getFechaIngreso());
        entidadGuardada.setUsuarioRegistra(usuario);

        // Configurar mocks
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        when(mercanciaRepository.existsByNombre("Batería")).thenReturn(false);
        when(mercanciaRepository.save(any(Mercancia.class))).thenReturn(entidadGuardada);

        // Ejecutar el método
        MercanciaDTO resultado = mercanciaService.registrar(dto);

        // Verificar resultado
        assertThat(resultado).isNotNull();
        assertThat(resultado.getId()).isEqualTo(1L);
        assertThat(resultado.getNombre()).isEqualTo("Batería");
        assertThat(resultado.getUsuarioRegistraId()).isEqualTo(1L);

        // Verificar interacciones
        verify(usuarioRepository, times(1)).findById(1L);
        verify(mercanciaRepository, times(1)).existsByNombre("Batería");
        verify(mercanciaRepository, times(1)).save(any(Mercancia.class));
    }
}
