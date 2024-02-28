package com.example.TP_FINAL_ELOY.servicios;

import com.example.TP_FINAL_ELOY.dto.LineaDto;
import com.example.TP_FINAL_ELOY.mapper.MapLinea;
import com.example.TP_FINAL_ELOY.models.Linea;
import com.example.TP_FINAL_ELOY.repository.RepositoryLinea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LineaServicio {
    @Autowired
    RepositoryLinea repositoryLinea;

    public ResponseEntity<?> getAll() {

        Map<String,Object> mensaje = new HashMap<>();
        List<Linea> lineas = repositoryLinea.findAll();
        if(lineas.isEmpty()){
            mensaje.put("success",Boolean.FALSE);
            mensaje.put("mensaje",String.format("La lista esta vacia "));
            return ResponseEntity.badRequest().body(mensaje);
        }
        List<LineaDto> lineasDTO = lineas.stream()
                .map(MapLinea::mapLinea).collect(Collectors.toList());
        mensaje.put("success",Boolean.TRUE);
        mensaje.put("data",lineasDTO);
        return ResponseEntity.ok(mensaje);
    }


    public ResponseEntity<?> getLineaid(Integer id) {

        Optional<Linea>optionalLinea = repositoryLinea.findById(id);
        Map<String, Object>mensaje = new HashMap<>();
        if (!optionalLinea.isPresent()){
            mensaje.put("succes",Boolean.FALSE);
            mensaje.put("mensaje",String.format("la linea con la id %d no existe",id));
            return  ResponseEntity.badRequest().body(mensaje);
        }
        Linea linea = optionalLinea.get();
        LineaDto lineaDTO = MapLinea.mapLinea(linea);

        mensaje.put("success",Boolean.TRUE);
        mensaje.put("data",lineaDTO);
        return (ResponseEntity<?>) ResponseEntity.ok(mensaje);
    }

    public ResponseEntity<?> delete(Integer id) {
        Map<String,Object> mensaje = new HashMap<>();
        Optional<Linea>opLinea = repositoryLinea.findById(id);
        if (!opLinea.isPresent()){
            mensaje.put("succes",Boolean.FALSE);
            mensaje.put("mensaje",String.format("la linea con la id %d no existe",id));
            return  ResponseEntity.badRequest().body(mensaje);
        }
        repositoryLinea.deleteById(id);
        mensaje.put("success",Boolean.TRUE);
        return ResponseEntity.ok(mensaje);

    }


    public ResponseEntity<?> modificarLinea(Integer id, Linea linea) {
        Map<String, Object> mensaje = new HashMap<>();
        Optional<Linea> oLinea = repositoryLinea.findById(id);
        if (oLinea != null) {
            Linea linea2 = oLinea.get();
             linea2.setCantidad(linea.getCantidad());
            linea2.setDescripcion(linea.getDescripcion());
            repositoryLinea.save(linea2);

            mensaje.put("success", Boolean.TRUE);

            return ResponseEntity.ok(mensaje);
        }

        mensaje.put("succes", Boolean.FALSE);
        mensaje.put("mensaje", String.format("la linea con la id %d no existe", id));
        return ResponseEntity.badRequest().body(mensaje);

    }
}