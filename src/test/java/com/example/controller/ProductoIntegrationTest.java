package com.example.controller;

import com.example.model.Producto;
import com.example.repository.ProductoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductoIntegrationTest {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private ProductoRepository productoRepository;

    private Producto producto;

    @BeforeEach
    void setUp() {
        productoRepository.deleteAll().block();
        producto = new Producto(null, "Producto Test", 100.0, 10);
    }

    @Test
    void crearProducto() {
        webTestClient.post()
                .uri("/api/productos")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(producto), Producto.class)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Producto.class)
                .value(p -> {
                    assert p.getNombre().equals(producto.getNombre());
                    assert p.getPrecio() == producto.getPrecio();
                    assert p.getCantidad() == producto.getCantidad();
                });
    }

    @Test
    void obtenerProductoPorId() {
        Producto productoGuardado = productoRepository.save(producto).block();

        webTestClient.get()
                .uri("/api/productos/{id}", productoGuardado.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody(Producto.class)
                .value(p -> {
                    assert p.getId().equals(productoGuardado.getId());
                    assert p.getNombre().equals(producto.getNombre());
                });
    }

    @Test
    void eliminarProducto() {
        Producto productoGuardado = productoRepository.save(producto).block();

        webTestClient.delete()
                .uri("/api/productos/{id}", productoGuardado.getId())
                .exchange()
                .expectStatus().isNoContent();

        webTestClient.get()
                .uri("/api/productos/{id}", productoGuardado.getId())
                .exchange()
                .expectStatus().isNotFound();
    }
} 