package com.example.mongodb.proyecto.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.mongodb.proyecto.entity.Empleado;
import com.example.mongodb.proyecto.entity.Producto;
import com.example.mongodb.proyecto.exception.NotFoundException;
import com.example.mongodb.proyecto.Repository.EmpleadoRepository;
import com.example.mongodb.proyecto.Repository.ProductoRepository;

@Controller
@RequestMapping(value = "administrador")
public class AdministradorWebController {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private ProductoRepository productoRepository;

    // Controlador para Empleados
    @GetMapping("/empleados")
    public String getAllEmpleados(Model model) {
        List<Empleado> empleados = empleadoRepository.findAll();
        model.addAttribute("empleados", empleados);
        return "empleados/list"; // Retorna la vista empleados/list.html
    }

    @GetMapping("/empleados/{id}")
    public String getEmpleadoById(@PathVariable String id, Model model) {
        Empleado empleado = empleadoRepository.findById(id).orElseThrow(() -> new NotFoundException("Empleado no encontrado"));
        model.addAttribute("empleado", empleado);
        return "empleados/detalle"; // Retorna la vista empleados/detalle.html
    }

    @PostMapping("/empleados/save")
    public String saveEmpleado(@RequestBody Map<String, Object> body, Model model) {
        ObjectMapper mapper = new ObjectMapper();
        Empleado empleado = mapper.convertValue(body, Empleado.class);
        empleadoRepository.save(empleado);
        return "redirect:/empleados"; // Redirecciona a la lista de empleados
    }

    // Controlador para Productos
    @GetMapping("/productos")
    public String getAllProductos(Model model) {
        List<Producto> productos = productoRepository.findAll();
        model.addAttribute("productos", productos);
        return "productos/list"; // Retorna la vista productos/list.html
    }

    @GetMapping("/productos/{id}")
    public String getProductoById(@PathVariable String id, Model model) {
        Producto producto = productoRepository.findById(id).orElseThrow(() -> new NotFoundException("Producto no encontrado"));
        model.addAttribute("producto", producto);
        return "productos/detalle"; // Retorna la vista productos/detalle.html
    }

    @PostMapping("/productos/save")
    public String saveProducto(@RequestBody Map<String, Object> body, Model model) {
        ObjectMapper mapper = new ObjectMapper();
        Producto producto = mapper.convertValue(body, Producto.class);
        productoRepository.save(producto);
        return "redirect:/productos"; // Redirecciona a la lista de productos
    }
}
