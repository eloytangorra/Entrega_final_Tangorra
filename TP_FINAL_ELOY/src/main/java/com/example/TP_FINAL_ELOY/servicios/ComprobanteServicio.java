package com.example.TP_FINAL_ELOY.servicios;

import com.example.TP_FINAL_ELOY.dto.ComprobanteDto;
import com.example.TP_FINAL_ELOY.dto.LineaDto;
import com.example.TP_FINAL_ELOY.mapper.MapComprobante;
import com.example.TP_FINAL_ELOY.mapper.MapLinea;
import com.example.TP_FINAL_ELOY.models.Cliente;
import com.example.TP_FINAL_ELOY.models.ComprobanteVenta;
import com.example.TP_FINAL_ELOY.models.Linea;
import com.example.TP_FINAL_ELOY.models.Producto;
import com.example.TP_FINAL_ELOY.repository.Repository;
import com.example.TP_FINAL_ELOY.repository.RepositoryLinea;
import com.example.TP_FINAL_ELOY.repository.RepositoryProducto;
import com.example.TP_FINAL_ELOY.repository.RepositoryVenta;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ComprobanteServicio {

@Autowired
    RepositoryProducto repositoryProducto;
    @Autowired
    Repository clienteRepositorio;
    @Autowired
    RepositoryVenta ComprobanteRepositorio;
@Autowired
    RepositoryLinea repositoryLinea;


    private static final HttpClient httpClient = HttpClient.newBuilder().build();

    // GET ALL

    public ResponseEntity<?> getAll() {
        Map<String,Object> mensaje = new HashMap<>();
        List<ComprobanteVenta> comprobantes = ComprobanteRepositorio.findAll();
        if(comprobantes.isEmpty()){
            mensaje.put("success",Boolean.FALSE);
            mensaje.put("mensaje",String.format("La lista esta vacia "));
            return ResponseEntity.badRequest().body(mensaje);
        }
        List<ComprobanteDto> comprobanteDto = comprobantes.stream()
                .map(MapComprobante::mapComprobante).collect(Collectors.toList());
        mensaje.put("success",Boolean.TRUE);
        mensaje.put("data",comprobanteDto);
        return ResponseEntity.ok(mensaje);
    }

// GET BY ID
    public ResponseEntity<?> getComprobanteid(Integer id) {
        Map<String, Object>mensaje = new HashMap<>();
        Optional<ComprobanteVenta>optionalcomprobante = ComprobanteRepositorio.findById(id);
        if (!optionalcomprobante.isPresent()){
            mensaje.put("succes",Boolean.FALSE);
            mensaje.put("mensaje",String.format("el comprobante con la id %d no existe",id));
            return  ResponseEntity.badRequest().body(mensaje);
        }
        ComprobanteVenta comprobanteVenta = optionalcomprobante.get();
        ComprobanteDto comprobanteDto = MapComprobante.mapComprobante(comprobanteVenta);
        mensaje.put("success",Boolean.TRUE);
        mensaje.put("data",comprobanteDto);
        return (ResponseEntity<?>) ResponseEntity.ok(mensaje);
    }




    public ResponseEntity<?> delete(Integer id) {
        Map<String,Object> mensaje = new HashMap<>();
        Optional<ComprobanteVenta>opComprobante = ComprobanteRepositorio.findById(id);
        if (!opComprobante.isPresent()){
            mensaje.put("succes",Boolean.FALSE);
            mensaje.put("mensaje",String.format("el comprobante con la id %d no existe",id));
            return  ResponseEntity.badRequest().body(mensaje);
        }
        ComprobanteRepositorio.deleteById(id);
        mensaje.put("success",Boolean.TRUE);
        return ResponseEntity.ok(mensaje);

    }






// ADD LINEA - COMPROBANTE
    public ResponseEntity addLineaToComprobante(Integer comprobanteId, Linea linea) {
        Map<String,Object> mensaje = new HashMap<>();
        Optional<ComprobanteVenta> optionalComprobante = ComprobanteRepositorio.findById(comprobanteId);
        if (optionalComprobante.isPresent()) {
            ComprobanteVenta comprobanteVenta = optionalComprobante.get();
            comprobanteVenta.addLinea(linea);
            ComprobanteRepositorio.save(comprobanteVenta);
            mensaje.put("success",Boolean.TRUE);
            mensaje.put("mensaje",String.format("el comprobante se actualizo correctamente"));
            return ResponseEntity.ok(mensaje);
        } else {
            mensaje.put("succes",Boolean.FALSE);
            mensaje.put("mensaje",String.format("error al actualizar"));
            return ResponseEntity.badRequest().body(mensaje);
        }
    }


// GET TOTAL PRECIO COMPROBANTE
    public ResponseEntity getTotalPriceById(Integer comprobanteId) {
       Double resultado = 0.0;
        Map<String,Object> mensaje = new HashMap<>();
        Optional<ComprobanteVenta> optionalComprobante = ComprobanteRepositorio.findById(comprobanteId);
       if (optionalComprobante.isPresent()){
       ComprobanteVenta comprobanteVenta = optionalComprobante.get();
       for (Linea l :comprobanteVenta.getLinea()){
           resultado +=l.getPrecio();
       }
           mensaje.put("success",Boolean.TRUE);
           mensaje.put("el precio es", resultado);
           return ResponseEntity.ok(mensaje);
       }
        mensaje.put("succes",Boolean.FALSE);
        mensaje.put("el precio es", String.format("no existe ese comrpobante"));
        return ResponseEntity.ok(mensaje);
    }
    public ResponseEntity getLineasById(Integer comprobanteId) {
        Map<String,Object> mensaje = new HashMap<>();
        Optional<ComprobanteVenta> optionalComprobante = ComprobanteRepositorio.findById(comprobanteId);
        if (optionalComprobante.isPresent()){
            ComprobanteVenta comprobanteVenta = optionalComprobante.get();
            Set<Linea>lineas = (Set<Linea>) comprobanteVenta.getLinea();
            List<LineaDto> lineasDTO = lineas.stream()
                    .map(MapLinea::mapLinea).collect(Collectors.toList());
            mensaje.put("success",Boolean.TRUE);
            mensaje.put("data",lineasDTO);
            return ResponseEntity.ok(mensaje);
        }
        mensaje.put("success",Boolean.FALSE);
        mensaje.put("mensaje",String.format("La lista esta vacia "));
        return ResponseEntity.badRequest().body(mensaje);

    }

    //post modificar
    public ResponseEntity<?>saveComprobante(ComprobanteVenta comprobanteVenta) {
        Map<String,Object> mensaje = new HashMap<>();
        ComprobanteVenta comprobanteGuardar = new ComprobanteVenta();

        try {
        Integer idCliente = comprobanteVenta.getCliente().getId();
Optional<Cliente>cliente = clienteRepositorio.findById(idCliente);

if (cliente.isEmpty()){
    mensaje.put("success",Boolean.TRUE);
    mensaje.put("mensaje",String.format("No se encontro el cliente"));
    return  ResponseEntity.badRequest().body(mensaje);
}

comprobanteGuardar.setCliente(cliente.get());
comprobanteGuardar.setFecha(this.getCurrentDate());
Set<Linea> lineasComprobante = new HashSet<>();
comprobanteGuardar.setId(comprobanteVenta.getId());

for (Linea linea : comprobanteVenta.getLinea()){
    int idProducto = linea.getProducto().getId();
    Optional<Producto> optionalProducto = repositoryProducto.findById(idProducto);

    if(optionalProducto.isEmpty()){
        mensaje.put("success", Boolean.FALSE);
        mensaje.put("mensaje", String.format("El producto con ID %d no existe", idProducto));
        return ResponseEntity.badRequest().body(mensaje);
    }
    Producto producto = optionalProducto.get();
    int cantidadSolicitada = linea.getCantidad();
    int stockDisponible = producto.getStock();

    if (cantidadSolicitada <= stockDisponible){
        Linea lineaAuxiliar = new Linea();
        lineaAuxiliar.setComprobanteVenta(comprobanteGuardar);
        lineaAuxiliar.setProducto(producto);
        lineaAuxiliar.setCantidad(cantidadSolicitada);
        lineaAuxiliar.setDescripcion(linea.getDescripcion());
        lineasComprobante.add(lineaAuxiliar);

        // actualizar stock
        int stockNuevo = stockDisponible - cantidadSolicitada;
        producto.setStock(stockNuevo);
    }else {
        mensaje.put("success", Boolean.FALSE);
        mensaje.put("mensaje", String.format("No hay suficiente stock para el producto '%s'", producto.getNombre()));
        return ResponseEntity.badRequest().body(mensaje);
    }
}
comprobanteGuardar.setLinea(lineasComprobante);
            ComprobanteRepositorio.save(comprobanteGuardar);
            mensaje.put("success", Boolean.TRUE);
            mensaje.put("mensaje", "Factura correctamente agregada");
            return ResponseEntity.ok(mensaje);
        }catch (Exception e){
            mensaje.put("succes",Boolean.FALSE);
            mensaje.put("mensaje",String.format("error al guardar la factura"));
            return ResponseEntity.badRequest().body(mensaje);
        }
    }
    private static LocalDate getCurrentDate() {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://worldclockapi.com/api/json/utc/now"))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) { // Verificar si la respuesta es exitosa
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(response.body());
                String currentDateString = jsonNode.get("currentDateTime").asText();
                ZonedDateTime zonedDateTime = ZonedDateTime.parse(currentDateString);
                return zonedDateTime.toLocalDate();
            } else {
                System.err.println("Error al obtener la fecha del servidor: " + response.statusCode());
                return LocalDate.now();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return LocalDate.now();
        }
    }


}
