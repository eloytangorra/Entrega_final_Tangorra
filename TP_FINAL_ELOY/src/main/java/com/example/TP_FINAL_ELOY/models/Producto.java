package com.example.TP_FINAL_ELOY.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "producto_id")
    private Integer id;
    @Column(name = "stock")
    private Integer stock;
    @Column()
    private String nombre;
    @Column()
    private Double precio;



}
