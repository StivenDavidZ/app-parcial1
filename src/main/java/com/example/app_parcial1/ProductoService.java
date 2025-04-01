package com.example.app_parcial1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public Flux<Producto> listarProductos() {
        return productoRepository.findAll();
    }

    public Mono<Producto> obtenerProductoPorId(String id) {
        return productoRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Producto no encontrado")));
    }

    public Mono<Producto> crearProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    public Mono<Void> eliminarProducto(String id) {
        return productoRepository.deleteById(id);
    }
} 