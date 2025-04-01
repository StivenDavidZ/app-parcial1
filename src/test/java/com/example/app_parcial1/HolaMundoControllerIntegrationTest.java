package com.example.app_parcial1;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // Inicia la aplicación con puerto aleatorio
public class HolaMundoControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate; // Cliente para hacer peticiones HTTP

    @Test
    public void testSaludoEndpoint() {
        // 1. Realiza una petición GET al endpoint "/hola"
        ResponseEntity<String> respuesta = restTemplate.getForEntity("/hola", String.class);

        // 2. Verifica que la respuesta sea HTTP 200 (OK)
        assertEquals(200, respuesta.getStatusCode().value());

        // 3. Verifica que el cuerpo de la respuesta sea "¡Hola, Mundo!"
        assertEquals("¡Hola, Mundo!", respuesta.getBody());
    }
}