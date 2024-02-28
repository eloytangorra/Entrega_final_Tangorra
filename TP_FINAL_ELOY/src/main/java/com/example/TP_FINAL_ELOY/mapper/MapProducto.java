package com.example.TP_FINAL_ELOY.mapper;

import com.example.TP_FINAL_ELOY.dto.ProductoDto;
import com.example.TP_FINAL_ELOY.models.Producto;

public class MapProducto {
    public static ProductoDto mapperProducto(Producto producto){
        ProductoDto dto = new ProductoDto();
        dto.setId_producto(producto.getId());
        dto.setPrecio(producto.getPrecio());
        dto.setNombre(producto.getNombre());
        dto.setStock(producto.getStock());
        return dto;
    }
}
