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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.mongodb.proyecto.entity.Empleado;
import com.example.mongodb.proyecto.exception.NotFoundException;
import com.example.mongodb.proyecto.Repository.EmpleadoRepository;
import com.example.mongodb.proyecto.Repository.ProductoRepository;
import com.example.mongodb.proyecto.entity.Producto;


@RestController
@RequestMapping(value = "/api/administrador")
public class AdministradorRestController {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private ProductoRepository productoRepository;

    // Métodos para Empleado 
    @GetMapping("/empleados/")
    public List<Empleado> getAllEmpleados() {
        return empleadoRepository.findAll();
    }

    @GetMapping("/empleados/{id}")
    public Empleado getEmpleadoById(@PathVariable String id) {
        return empleadoRepository.findById(id).orElseThrow(() -> new NotFoundException("Empleado no encontrado"));
    }

    @PostMapping("/save")
    public Empleado saveEmpleado(@RequestBody Map<String, Object> body) {
        ObjectMapper mapper = new ObjectMapper();
        Empleado empleado = mapper.convertValue(body, Empleado.class);
        return empleadoRepository.save(empleado);
    }

    @PutMapping("/empleados/{id}")
    public Empleado updateEmpleado(@PathVariable String id, @RequestBody Map<String, Object> body) {
        ObjectMapper mapper = new ObjectMapper();
        Empleado empleado = mapper.convertValue(body, Empleado.class);
        empleado.setId(id);
        return empleadoRepository.save(empleado);
    }

    @DeleteMapping("/empleados/{id}")
    public Empleado deleteEmpleado(@PathVariable String id) {
        Empleado empleado = empleadoRepository.findById(id).orElseThrow(() -> new NotFoundException("Empleado no encontrado"));
        empleadoRepository.deleteById(id);
        return empleado;
    }

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
}


