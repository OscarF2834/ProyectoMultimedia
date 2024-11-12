package com.example.mongodb.proyecto.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.mongodb.proyecto.entity.Cliente;


public interface ClienteRepository extends MongoRepository<Cliente, String> {
	Cliente findByUser(String user);
}
