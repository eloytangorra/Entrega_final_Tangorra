package com.example.TP_FINAL_ELOY.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class Linea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer lineaId;
    private Integer cantidad;
    @Column
    private String descripcion;
    @ManyToOne()
    @JoinColumn(name = "comprobante_id")
    ComprobanteVenta comprobanteVenta;
@ManyToOne()
@JoinColumn(name = "producto_id")
    Producto producto;
    public double getPrecio() {
        if (producto != null && cantidad != null) {
            return producto.getPrecio() * cantidad;
        }
        return 0.0;
    }

    @Override
    public String toString() {
        return "Linea{" +
                "lineaId=" + lineaId +
                ", descripcion='" + descripcion + '\'' +

                ", comprobanteVenta=" + comprobanteVenta +
                ", producto=" + producto +
                '}';
    }
}
