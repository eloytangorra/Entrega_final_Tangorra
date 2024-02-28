package com.example.TP_FINAL_ELOY.dto;

import com.example.TP_FINAL_ELOY.models.Producto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
public class LineaDto {
    private Integer lineaId;
    private Integer cantidad;
    private String descripcion;
    private Double precio;
    private Producto producto;


}
