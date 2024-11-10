package com.example.mongodb.proyecto.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.mongodb.proyecto.Repository.ClienteRepository;
import com.example.mongodb.proyecto.Repository.ReservasRepository;
import com.example.mongodb.proyecto.entity.Cliente;
import com.example.mongodb.proyecto.entity.Reservas;
import com.example.mongodb.proyecto.exception.NotFoundException;

@Controller
@RequestMapping(value = "clientes")
public class ClienteWebController {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ReservasRepository reservasRepository;
    

    // Métodos para clientes
    @GetMapping("")
    public String getAllClientes(Model model) {
        List<Cliente> clientes = clienteRepository.findAll();
        model.addAttribute("clientes", clientes);
        return "clientes/list"; // Retorna la vista clientes/list.html
    }
    
    @GetMapping("/new")
    public String newCliente(Model model) {
        model.addAttribute("cliente", new Cliente()); // Crear un nuevo objeto cliente vacío
        return "clientes/form"; // Devuelve la vista del formulario para agregar un nuevo cliente
    }

    

    @GetMapping("/{id}")
    public String getClienteById(@PathVariable String id, Model model) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new NotFoundException("Cliente no encontrado"));
        model.addAttribute("cliente", cliente);
        return "clientes/detalle"; // Retorna la vista clientes/detalle.html
    }

    @PostMapping("/save")
    public String saveCliente(@ModelAttribute Cliente cliente, Model model) {
        // Si el cliente tiene un id, significa que estamos actualizando un cliente existente.
        if (cliente.getId() != null) {
            // Actualizamos el cliente
            Cliente existingCliente = clienteRepository.findById(cliente.getId())
                    .orElseThrow(() -> new NotFoundException("Cliente no encontrado"));
            
            // Actualizamos los campos del cliente
            existingCliente.setNombre(cliente.getNombre());
            existingCliente.setApellido(cliente.getApellido());
            existingCliente.setTelefono(cliente.getTelefono());
            existingCliente.setDireccion(cliente.getDireccion());

            // Guardamos la entidad actualizada
            clienteRepository.save(existingCliente);
        } else {
            // Si el cliente no tiene id, significa que es un nuevo cliente
            clienteRepository.save(cliente);  // MongoDB generará el id automáticamente
        }
        return "redirect:/clientes"; // Redirige a la lista de clientes
    }

    
    @GetMapping("/edit/{id}")
    public String editCliente(@PathVariable String id, Model model) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new NotFoundException("Cliente no encontrado"));
        model.addAttribute("cliente", cliente);
        return "clientes/form"; // Devuelve la vista del formulario para editar el cliente
    }
    
    @GetMapping("/delete/{id}")
    public String deleteCliente(@PathVariable String id) {
        clienteRepository.deleteById(id);
        return "redirect:/clientes"; // Redirige a la lista de clientes después de eliminar
    }

    // Métodos para reservas
    @GetMapping("/reservas")
    public String getAllReservas(Model model) {
        List<Reservas> reservas = reservasRepository.findAll();
        model.addAttribute("reservas", reservas);
        return "reservas/list"; // Retorna la vista reservas/list.html
    }

    @GetMapping("/reservas/{id}")
    public String getReservasById(@PathVariable String id, Model model) {
        Reservas reservas = reservasRepository.findById(id).orElseThrow(() -> new NotFoundException("Reserva no encontrada"));
        model.addAttribute("reserva", reservas);
        return "reservas/detalle"; // Retorna la vista reservas/detalle.html
    }

    @PostMapping("/reservas/save")
    public String saveReservas(@RequestBody Map<String, Object> body, Model model) {
        ObjectMapper mapper = new ObjectMapper();
        Reservas reservas = mapper.convertValue(body, Reservas.class);
        reservasRepository.save(reservas);
        return "redirect:/reservas"; // Redirige a la lista de reservas
    }
}
