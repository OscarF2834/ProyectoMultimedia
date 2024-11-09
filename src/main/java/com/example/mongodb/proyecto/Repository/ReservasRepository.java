package com.example.mongodb.proyecto.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.mongodb.proyecto.entity.Reservas;


public interface ReservasRepository extends MongoRepository<Reservas, String> {

}
