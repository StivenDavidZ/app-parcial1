package com.example.app_parcial1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductoServiceTest {

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoService productoService;

    @Test
    public void testListarProductos() {
        // Arrange
        List<Producto> productos = Arrays.asList(
            new Producto("1", "Producto 1", 100.0),
            new Producto("2", "Producto 2", 200.0)
        );
        when(productoRepository.findAll()).thenReturn(Flux.fromIterable(productos));

        // Act
        Flux<Producto> resultado = productoService.listarProductos();

        // Assert
        StepVerifier.create(resultado)
            .expectNextCount(2)
            .verifyComplete();
        verify(productoRepository).findAll();
    }

    @Test
    public void testObtenerProductoPorId() {
        // Arrange
        Producto producto = new Producto("1", "Producto 1", 100.0);
        when(productoRepository.findById("1")).thenReturn(Mono.just(producto));

        // Act
        Mono<Producto> resultado = productoService.obtenerProductoPorId("1");

        // Assert
        StepVerifier.create(resultado)
            .expectNext(producto)
            .verifyComplete();
        verify(productoRepository).findById("1");
    }

    @Test
    public void testCrearProducto() {
        // Arrange
        Producto producto = new Producto("1", "Producto 1", 100.0);
        when(productoRepository.save(any(Producto.class))).thenReturn(Mono.just(producto));

        // Act
        Mono<Producto> resultado = productoService.crearProducto(producto);

        // Assert
        StepVerifier.create(resultado)
            .expectNext(producto)
            .verifyComplete();
        verify(productoRepository).save(any(Producto.class));
    }

    @Test
    public void testEliminarProducto() {
        // Arrange
        String id = "1";
        when(productoRepository.deleteById(id)).thenReturn(Mono.empty());

        // Act
        Mono<Void> resultado = productoService.eliminarProducto(id);

        // Assert
        StepVerifier.create(resultado)
            .verifyComplete();
        verify(productoRepository).deleteById(id);
    }
} 