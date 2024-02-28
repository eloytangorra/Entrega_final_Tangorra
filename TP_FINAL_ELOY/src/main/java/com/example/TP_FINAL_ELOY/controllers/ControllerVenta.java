package com.example.TP_FINAL_ELOY.controllers;

import com.example.TP_FINAL_ELOY.models.ComprobanteVenta;
import com.example.TP_FINAL_ELOY.models.Linea;
import com.example.TP_FINAL_ELOY.models.Producto;
import com.example.TP_FINAL_ELOY.repository.RepositoryProducto;
import com.example.TP_FINAL_ELOY.repository.RepositoryVenta;
import com.example.TP_FINAL_ELOY.servicios.ComprobanteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Vector;
@RestController
@RequestMapping("/comprobante")
public class ControllerVenta {
    @Autowired
    private ComprobanteServicio servicio;




// GET ALL
    @GetMapping
    public ResponseEntity<?> get(){
        return servicio. getAll();
    }

    //GET POR ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getComprobante(@PathVariable Integer id) {
        return servicio.getComprobanteid(id);
    }

    //POST
    @PostMapping("/alta")
    public ResponseEntity<?> saveComprobante(@RequestBody ComprobanteVenta comprobanteVenta) {
        return servicio.saveComprobante(comprobanteVenta);
    }


    //METODO DELETE
    @DeleteMapping("/baja/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Integer id) {
        return servicio.delete(id);
    }

    //TRAER PRECIO
    @GetMapping("/precioComprobante/{id}")
    public ResponseEntity<?> getTotalPriceById(@PathVariable Integer id) {
        return servicio.getTotalPriceById(id);
    }


    //ADD
    @PutMapping("/put/{id}")
    public  ResponseEntity<?> addLinea(@PathVariable Integer id, @RequestBody Linea linea) {
        return servicio.addLineaToComprobante(id, linea);
    }


    // TRAER TODOS LAS LINEAS DE UN COMPROBANTE
    @GetMapping("/comprobante/productos/{id}")
    public ResponseEntity<?> getProductosById(@PathVariable Integer id) {
        return servicio.getLineasById(id);
    }



}
