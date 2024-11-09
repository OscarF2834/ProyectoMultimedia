package com.example.mongodb.proyecto.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.mongodb.proyecto.entity.Pedidos;


public interface PedidosRepository extends MongoRepository<Pedidos, String> {

}
