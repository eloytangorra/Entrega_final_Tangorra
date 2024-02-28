package com.example.TP_FINAL_ELOY.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ClienteDto {
    private String nombre;
    private String apellido;
    private String dni;
    private Integer id_cliente;
}
