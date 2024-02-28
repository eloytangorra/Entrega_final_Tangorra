package com.example.TP_FINAL_ELOY.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ProductoDto {

    private Integer id_producto;
    private String nombre;
    private double precio;
    private int stock;

}


