package com.example.mongodb.proyecto.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.mongodb.proyecto.entity.Administrador;


public interface AdministradorRepository extends MongoRepository<Administrador, String> {
	Administrador findByUser(String user);
}