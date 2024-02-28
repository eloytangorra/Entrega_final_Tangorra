package com.example.TP_FINAL_ELOY.mapper;

import com.example.TP_FINAL_ELOY.dto.LineaDto;
import com.example.TP_FINAL_ELOY.models.Linea;

public class MapLinea {
    public static LineaDto mapLinea(Linea linea) {
        LineaDto lineaDto = new LineaDto();
        lineaDto.setLineaId(linea.getLineaId());
        lineaDto.setCantidad(linea.getCantidad());
        lineaDto.setDescripcion(linea.getDescripcion());
        lineaDto.setProducto(linea.getProducto());
        return lineaDto;
    }
}


