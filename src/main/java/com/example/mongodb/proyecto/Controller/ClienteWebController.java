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
        // Si el cliente no tiene id, MongoDB generará uno automáticamente.
        if (cliente.getId() == null || cliente.getId().isEmpty()) {
            clienteRepository.save(cliente);  // Guarda el cliente sin el id explícito
        } else {
            clienteRepository.save(cliente);  // Si el id ya está presente, actualiza el cliente
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
        Reservas reserva = reservasRepository.findById(id).orElseThrow(() -> new NotFoundException("Reserva no encontrada"));
        model.addAttribute("reserva", reserva);
        return "reservas/detalle"; // Retorna la vista reservas/detalle.html
    }

    @PostMapping("/reservas/save")
    public String saveReservas(@ModelAttribute Reservas reserva, Model model) {
        if (reserva.getId() == null || reserva.getId().isEmpty()) {
            // Si no tiene id, MongoDB genera uno automáticamente
            reservasRepository.save(reserva);
        } else {
            // Si tiene id (es una edición), se actualiza
            reservasRepository.save(reserva);
        }
        return "redirect:/clientes/reservas"; // Redirige a la lista de reservas
    }



    @GetMapping("/reservas/edit/{id}")
    public String editReservas(@PathVariable String id, Model model) {
        Reservas reserva = reservasRepository.findById(id).orElseThrow(() -> new NotFoundException("Reserva no encontrada"));
        model.addAttribute("reserva", reserva);
        return "reservas/form"; // Devuelve la vista del formulario para editar la reserva
    }

    @GetMapping("/reservas/delete/{id}")
    public String deleteReservas(@PathVariable String id) {
        reservasRepository.deleteById(id);
        return "redirect:/clientes/reservas"; // Redirige a la lista de reservas después de eliminar
    }

    @GetMapping("/reservas/new")
    public String newReserva(Model model) {
        model.addAttribute("reserva", new Reservas()); // Crear una nueva reserva vacía
        return "reservas/form"; // Devuelve la vista del formulario para agregar una nueva reserva
    }

}
