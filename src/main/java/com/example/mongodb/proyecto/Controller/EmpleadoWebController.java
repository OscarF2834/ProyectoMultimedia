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

import com.example.mongodb.proyecto.Repository.ProductoRepository;
import com.example.mongodb.proyecto.Repository.ReservasRepository;
import com.example.mongodb.proyecto.entity.Producto;
import com.example.mongodb.proyecto.entity.Reservas;
import com.example.mongodb.proyecto.exception.NotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/empleados")
public class EmpleadoWebController {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ReservasRepository reservasRepository;

    // Métodos para Producto
    @GetMapping("/productos")
    public String getAllProductos(Model model) {
        List<Producto> productos = productoRepository.findAll();
        model.addAttribute("productos", productos);
        return "productos/list"; // Retorna la vista productos/list.html
    }

    @GetMapping("/productos/{id}")
    public String getProductoById(@PathVariable String id, Model model) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Producto no encontrado"));
        model.addAttribute("producto", producto);
        return "productos/detalle"; // Retorna la vista productos/detalle.html
    }

    @PostMapping("/productos/save")
    public String saveProducto(@RequestBody Map<String, Object> body, Model model) {
        ObjectMapper mapper = new ObjectMapper();
        Producto producto = mapper.convertValue(body, Producto.class);
        productoRepository.save(producto);
        return "redirect:/empleados/productos"; // Redirige a la lista de productos
    }

    // Métodos para Reservas
    @GetMapping("/reservas")
    public String getAllReservas(Model model) {
        List<Reservas> reservas = reservasRepository.findAll();
        model.addAttribute("reservas", reservas);
        return "reservas/list"; // Retorna la vista reservas/list.html
    }

    @GetMapping("/reservas/{id}")
    public String getReservasById(@PathVariable String id, Model model) {
        Reservas reservas = reservasRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Reserva no encontrada"));
        model.addAttribute("reserva", reservas);
        return "reservas/detalle"; // Retorna la vista reservas/detalle.html
    }

    @PostMapping("/reservas/save")
    public String saveReservas(@RequestBody Map<String, Object> body, Model model) {
        ObjectMapper mapper = new ObjectMapper();
        Reservas reservas = mapper.convertValue(body, Reservas.class);
        reservasRepository.save(reservas);
        return "redirect:/empleados/reservas"; // Redirige a la lista de reservas
    }
}
