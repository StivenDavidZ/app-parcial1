package com.example.app_parcial1;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class ProductoIntegrationTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void testCrearProducto() {
        Producto producto = new Producto("1", "Producto Test", 100.0);

        webTestClient.post()
                .uri("/api/productos")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(producto), Producto.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Producto.class)
                .isEqualTo(producto);
    }

    @Test
    public void testObtenerProducto() {
        String id = "1";
        
        webTestClient.get()
                .uri("/api/productos/{id}", id)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Producto.class)
                .value(producto -> {
                    assert producto.getId().equals(id);
                });
    }

    @Test
    public void testEliminarProducto() {
        String id = "1";

        webTestClient.delete()
                .uri("/api/productos/{id}", id)
                .exchange()
                .expectStatus().isNoContent();
    }
} 