package com.example.TP_FINAL_ELOY.controllers;

import com.example.TP_FINAL_ELOY.models.Cliente;
import com.example.TP_FINAL_ELOY.repository.Repository;
import com.example.TP_FINAL_ELOY.servicios.ClienteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("/clientes")
public class controller {

    ;
    @Autowired
   ClienteServicio servicioCliente;

    // GET ALL
    @GetMapping()
    public ResponseEntity<?> getClientes() {
        return servicioCliente.getClientes();
    }
    // GET POR ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getClienteById(@PathVariable Integer id) {
        return servicioCliente.getClienteById(id);
    }
    // POST
    @PostMapping("/alta")
    public ResponseEntity<?> saveCliente(@RequestBody Cliente cliente) {
        return servicioCliente.saveCliente(cliente);
    }
    // PUT
    @PutMapping("/modificar/{id}")
    public ResponseEntity<?> updateCliente(@PathVariable Integer id, @RequestBody Cliente cliente) {
        return servicioCliente.updateCliente(id, cliente);
    }
    // DELETE
    @DeleteMapping("/baja/{id}")
    public ResponseEntity<?> deleteClienteById(@PathVariable Integer id) {
        return servicioCliente.deleteClienteById(id);
    }
    // DELETE ALL
    @DeleteMapping("/borrarTodos")
    public ResponseEntity<?> deleteAllClientes() {
        return servicioCliente.deleteAllClientes();
    }


}
