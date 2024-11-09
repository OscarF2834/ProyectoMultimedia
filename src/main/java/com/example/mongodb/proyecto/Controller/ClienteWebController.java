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
    @GetMapping("/clientes")
    public String getAllClientes(Model model) {
        List<Cliente> clientes = clienteRepository.findAll();
        model.addAttribute("clientes", clientes);
        return "clientes/list"; // Retorna la vista clientes/list.html
    }

    @GetMapping("/clientes/{id}")
    public String getClienteById(@PathVariable String id, Model model) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new NotFoundException("Cliente no encontrado"));
        model.addAttribute("cliente", cliente);
        return "clientes/detalle"; // Retorna la vista clientes/detalle.html
    }

    @PostMapping("/clientes/save")
    public String saveCliente(@RequestBody Map<String, Object> body, Model model) {
        ObjectMapper mapper = new ObjectMapper();
        Cliente cliente = mapper.convertValue(body, Cliente.class);
        clienteRepository.save(cliente);
        return "redirect:/clientes"; // Redirige a la lista de clientes
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
