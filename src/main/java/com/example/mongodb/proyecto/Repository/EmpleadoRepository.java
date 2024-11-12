package com.example.mongodb.proyecto.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.mongodb.proyecto.entity.Empleado;


public interface EmpleadoRepository extends MongoRepository<Empleado, String> {
	Empleado findByUser(String user);
}
 