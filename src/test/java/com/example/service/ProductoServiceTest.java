package com.example.service;

import com.example.model.Producto;
import com.example.repository.ProductoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProductoServiceTest {

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoService productoService;

    private Producto producto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        producto = new Producto("1", "Producto Test", 100.0, 10);
    }

    @Test
    void listarProductos() {
        when(productoRepository.findAll())
                .thenReturn(Flux.just(producto));

        StepVerifier.create(productoService.listarProductos())
                .expectNext(producto)
                .verifyComplete();

        verify(productoRepository).findAll();
    }

    @Test
    void obtenerProductoPorId() {
        when(productoRepository.findById("1"))
                .thenReturn(Mono.just(producto));

        StepVerifier.create(productoService.obtenerProductoPorId("1"))
                .expectNext(producto)
                .verifyComplete();

        verify(productoRepository).findById("1");
    }

    @Test
    void crearProducto() {
        when(productoRepository.save(any(Producto.class)))
                .thenReturn(Mono.just(producto));

        StepVerifier.create(productoService.crearProducto(producto))
                .expectNext(producto)
                .verifyComplete();

        verify(productoRepository).save(any(Producto.class));
    }
} 