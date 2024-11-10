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
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.mongodb.proyecto.entity.Empleado;
import com.example.mongodb.proyecto.entity.Producto;
import com.example.mongodb.proyecto.exception.NotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    
    // Formulario de creación de nuevo empleado
    @GetMapping("/empleados/new")
    public String newEmpleadoForm(Model model) {
        model.addAttribute("empleado", new Empleado()); // Se agrega un empleado vacío
        return "empleados/form"; // Retorna la vista empleados/form.html
    }

    @GetMapping("/empleados/{id}")
    public String getEmpleadoById(@PathVariable String id, Model model) {
        Empleado empleado = empleadoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Empleado no encontrado"));
        model.addAttribute("empleado", empleado);
        return "empleados/detalle"; // Retorna la vista empleados/detalle.html
    }

    // Método para guardar el empleado - Usando @ModelAttribute en lugar de @RequestBody
    @PostMapping("/empleados/save")
    public String saveEmpleado(@ModelAttribute Empleado empleado, Model model) {
        // El objeto "empleado" se recibe del formulario y se guarda
        empleadoRepository.save(empleado);
        return "redirect:/administrador/empleados"; // Redirecciona a la lista de empleados
    }
    
    // Método para editar un empleado
    @GetMapping("/empleados/editar/{id}")
    public String editEmpleado(@PathVariable String id, Model model) {
        Empleado empleado = empleadoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Empleado no encontrado"));
        model.addAttribute("empleado", empleado);
        return "empleados/form"; // Usa la vista empleados/form.html para editar
    }
    
 // Controlador para eliminar empleado
    @PostMapping("/empleados/delete/{id}")
    public String deleteEmpleado(@PathVariable String id) {
        Empleado empleado = empleadoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Empleado no encontrado"));
        empleadoRepository.delete(empleado);
        return "redirect:/administrador/empleados"; // Redirecciona a la lista de empleados después de eliminar
    }
    
    // Controladores para Productos
    @GetMapping("/productos")
    public String getAllProductos(Model model) {
        List<Producto> productos = productoRepository.findAll();
        model.addAttribute("productos", productos);
        return "productos/list"; // Retorna la vista productos/list.html
    }
    
    @GetMapping("/productos/new")
    public String newProductoForm(Model model) {
        model.addAttribute("producto", new Producto()); // Se agrega un producto vacío
        return "productos/form"; // Retorna la vista productos/form.html
    }

    @GetMapping("/productos/{id}")
    public String getProductoById(@PathVariable String id, Model model) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Producto no encontrado con el ID: " + id));
        model.addAttribute("producto", producto);
        return "productos/detalle"; // Redirige a la vista de detalles del producto
    }


    @GetMapping("/productos/editar/{id}")
    public String editProducto(@PathVariable String id, Model model) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Producto no encontrado con el ID: " + id));
        model.addAttribute("producto", producto);
        return "productos/form"; // Usa la vista productos/form.html para editar
    }

    @PostMapping("/productos/save")
    public String saveProducto(@ModelAttribute Producto producto, Model model) {
        productoRepository.save(producto);
        return "redirect:/administrador/productos"; // Redirecciona a la lista de productos
    }

    @PostMapping("/productos/delete/{id}")
    public String deleteProducto(@PathVariable String id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Producto no encontrado con el ID: " + id));
        productoRepository.delete(producto);
        return "redirect:/administrador/productos"; // Redirecciona a la lista de productos después de eliminar
    }
}
