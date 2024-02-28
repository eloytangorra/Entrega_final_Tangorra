package com.example.TP_FINAL_ELOY.repository;

import com.example.TP_FINAL_ELOY.models.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryProducto extends JpaRepository<Producto,Integer> {
}
