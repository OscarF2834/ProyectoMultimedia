package com.example.mongodb.proyecto.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.mongodb.proyecto.entity.Producto;

public interface ProductoRepository extends MongoRepository<Producto, String> {

}
