package com.example.TP_FINAL_ELOY.dto;

import com.example.TP_FINAL_ELOY.models.Cliente;
import com.example.TP_FINAL_ELOY.models.Linea;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
public class ComprobanteDto {
    private  Integer id;
    private LocalDate fecha;
    private Set<Linea> Linea;
    private Cliente cliente;
}
