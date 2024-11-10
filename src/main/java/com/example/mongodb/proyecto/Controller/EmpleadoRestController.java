package com.example.mongodb.proyecto.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mongodb.proyecto.Repository.ProductoRepository;
import com.example.mongodb.proyecto.Repository.ReservasRepository;
import com.example.mongodb.proyecto.entity.Producto;
import com.example.mongodb.proyecto.entity.Reservas;
import com.example.mongodb.proyecto.exception.NotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
@RequestMapping(value = "/api/empleado")
public class EmpleadoRestController {
	
	@Autowired
    private ProductoRepository productoRepository;
	
	@Autowired
    private ReservasRepository reservasRepository;
	
	// Métodos para Producto
    @GetMapping("/productos/")
    public List<Producto> getAllProductos() {
        return productoRepository.findAll();
    }

    @GetMapping("/productos/{id}")
    public Producto getProductoById(@PathVariable String id) {
        return productoRepository.findById(id).orElseThrow(() -> new NotFoundException("Producto no encontrado"));
    }

    @PostMapping("/productos/")
    public Producto saveProducto(@RequestBody Map<String, Object> body) {
        ObjectMapper mapper = new ObjectMapper();
        Producto producto = mapper.convertValue(body, Producto.class);
        return productoRepository.save(producto);
    }

    @PutMapping("/productos/{id}")
    public Producto updateProducto(@PathVariable String id, @RequestBody Map<String, Object> body) {
        ObjectMapper mapper = new ObjectMapper();
        Producto producto = mapper.convertValue(body, Producto.class);
        producto.setId(id);
        return productoRepository.save(producto);
    }

    @DeleteMapping("/productos/{id}")
    public Producto deleteProducto(@PathVariable String id) {
        Producto producto = productoRepository.findById(id).orElseThrow(() -> new NotFoundException("Producto no encontrado"));
        productoRepository.deleteById(id);
        return producto;
    }
    
 // Métodos para reservas
    @GetMapping("/reservas/")
    public List<Reservas> getAllReservas() {
        return reservasRepository.findAll();
    }

    @GetMapping("/reservas/{id}")
    public Reservas getReservasById(@PathVariable String id) {
        return reservasRepository.findById(id).orElseThrow(() -> new NotFoundException("Producto no encontrado"));
    }

    @PostMapping("/reservas/")
    public Reservas saveReservas(@RequestBody Map<String, Object> body) {
        ObjectMapper mapper = new ObjectMapper();
        Reservas reservas = mapper.convertValue(body, Reservas.class);
        return reservasRepository.save(reservas);
    }

    @PutMapping("/reservas/{id}")
    public Reservas updateReservas(@PathVariable String id, @RequestBody Map<String, Object> body) {
        ObjectMapper mapper = new ObjectMapper();
        Reservas reservas = mapper.convertValue(body, Reservas.class);
        reservas.setId(id);
        return reservasRepository.save(reservas);
    }

    @DeleteMapping("/reservas/{id}")
    public Reservas deleteReservas(@PathVariable String id) {
    	Reservas reservas = reservasRepository.findById(id).orElseThrow(() -> new NotFoundException("reservas no encontrado"));
    	reservasRepository.deleteById(id);
        return reservas;
    }

}
