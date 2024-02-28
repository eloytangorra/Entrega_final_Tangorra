package com.example.TP_FINAL_ELOY.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class ComprobanteVenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comprobante_id")
    private    Integer id;
    @Column(name = "fecha")
    private LocalDate fecha;
    @Column
    @OneToMany(mappedBy="comprobanteVenta", fetch=FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Linea> Linea;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @PrePersist
    public void prePersist() {
        this.fecha = LocalDate.now();
    }



    //METODO
    public void addLinea(Linea l){
        Linea.add(l);
    }


    }

