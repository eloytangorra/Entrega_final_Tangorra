package com.example.TP_FINAL_ELOY.repository;

import com.example.TP_FINAL_ELOY.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Repository extends JpaRepository<Cliente, Integer> {
}
