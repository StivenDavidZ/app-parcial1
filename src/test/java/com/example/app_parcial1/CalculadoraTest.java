package com.example.app_parcial1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculadoraTest {

    @Test
    public void testSumarDosNumerosPositivos() {
        // 1. Configuración
        Calculadora Calculadora = new Calculadora();
        int a = 2;
        int b = 3;

        // 2. Ejecución
        int resultado = Calculadora.sumar(a, b);

        // 3. Verificación
        assertEquals(5, resultado, "2 + 3 debe ser 5");
    }

    @Test
    public void testSumarPositivoYNegativo() {
        Calculadora alculadora = new Calculadora();
        assertEquals(-1, alculadora.sumar(2, -3), "2 + (-3) debe ser -1");
    }

    @Test
    public void testSumarConCero() {
        Calculadora calculadora = new Calculadora();
        assertEquals(5, calculadora.sumar(0, 5), "0 + 5 debe ser 5");
    }
}