package com.example.auto_park.hibernate.entity.veicolo;

import com.example.auto_park.hibernate.entity.prenotazione.Prenotazione;

import java.io.Serializable;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "veicolo")
public class Veicolo implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "casa_costruttrice")
    private String casa_costruttrice;

    @Column(name = "modello")
    private String modello;

    @Column(name = "anno_immatricolazione")
    private int anno_immatricolazione;

    @Column(name = "tipo")
    private int tipo;

    @OneToMany(mappedBy = "veicolo")
    private Set<Prenotazione> prenotazioni = new HashSet<>();


}
