package com.example.app_parcial1;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class holamundo {

    @GetMapping("/hola")
    public String holaMundo() {
        return "¡Hola, Mundo!";
    }
}