package com.example.TP_FINAL_ELOY.mapper;


import com.example.TP_FINAL_ELOY.dto.ClienteDto;
import com.example.TP_FINAL_ELOY.models.Cliente;

public class MapCliente {
    public static ClienteDto mapperCliente(Cliente cliente){
        ClienteDto dto = new ClienteDto();

        dto.setId_cliente(cliente.getId());
        dto.setDni(cliente.getDni());
        dto.setApellido(cliente.getApellido());
        dto.setNombre(cliente.getNombre());

        return dto;
    }
}

