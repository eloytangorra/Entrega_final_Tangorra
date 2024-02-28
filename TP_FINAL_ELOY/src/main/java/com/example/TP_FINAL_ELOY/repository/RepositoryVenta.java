package com.example.TP_FINAL_ELOY.repository;

import com.example.TP_FINAL_ELOY.models.Cliente;
import com.example.TP_FINAL_ELOY.models.ComprobanteVenta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryVenta extends JpaRepository<ComprobanteVenta, Integer> {
}
