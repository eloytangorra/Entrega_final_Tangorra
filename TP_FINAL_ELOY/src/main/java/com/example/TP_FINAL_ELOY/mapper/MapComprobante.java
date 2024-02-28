package com.example.TP_FINAL_ELOY.mapper;

import com.example.TP_FINAL_ELOY.dto.ComprobanteDto;
import com.example.TP_FINAL_ELOY.models.ComprobanteVenta;

public class MapComprobante {

    public static ComprobanteDto mapComprobante(ComprobanteVenta comprobanteVenta){
        ComprobanteDto comprobanteDto = new ComprobanteDto();
        comprobanteDto.setId(comprobanteVenta.getId());
        comprobanteDto.setCliente(comprobanteVenta.getCliente());
        comprobanteDto.setFecha(comprobanteVenta.getFecha());
        comprobanteDto.setLinea(comprobanteVenta.getLinea());
        return  comprobanteDto;
    }
}
