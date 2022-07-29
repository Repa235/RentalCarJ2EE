package com.example.auto_park.hibernate.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "veicolo")
public class Veicolo implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "casaCostruttrice")
    private String casaCostruttrice;

    @Column(name = "modello")
    private String modello;

    @Column(name = "annoImmatricolazione")
    private int annoImmatricolazione;

    @Column(name = "tipo")
    private String tipo;

    @OneToMany(mappedBy = "veicolo", fetch = FetchType.EAGER)
    private Set<Prenotazione> prenotazioni = new HashSet<>();

    public Veicolo(String casaCostruttrice, String modello, int annoImmatricolazione, String tipo) {
        this.casaCostruttrice = casaCostruttrice;
        this.modello = modello;
        this.annoImmatricolazione = annoImmatricolazione;
        this.tipo = tipo;
    }

    public Veicolo(Long id, String casaCostruttrice, String modello, int annoImmatricolazione, String tipo, Set<Prenotazione> prenotazioni) {
        this.id = id;
        this.casaCostruttrice = casaCostruttrice;
        this.modello = modello;
        this.annoImmatricolazione = annoImmatricolazione;
        this.tipo = tipo;
        this.prenotazioni = prenotazioni;
    }

    public Veicolo() {
    }

    public Veicolo(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCasaCostruttrice() {
        return casaCostruttrice;
    }

    public void setCasaCostruttrice(String casaCostruttrice) {
        this.casaCostruttrice = casaCostruttrice;
    }

    public String getModello() {
        return modello;
    }

    public void setModello(String modello) {
        this.modello = modello;
    }

    public int getAnnoImmatricolazione() {
        return annoImmatricolazione;
    }

    public void setAnnoImmatricolazione(int annoImmatricolazione) {
        this.annoImmatricolazione = annoImmatricolazione;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Set<Prenotazione> getPrenotazioni() {
        return prenotazioni;
    }

    public void setPrenotazioni(Set<Prenotazione> prenotazioni) {
        this.prenotazioni = prenotazioni;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Veicolo)) return false;
        Veicolo veicolo = (Veicolo) o;
        return getAnnoImmatricolazione() == veicolo.getAnnoImmatricolazione() && Objects.equals(getId(), veicolo.getId()) && Objects.equals(getCasaCostruttrice(), veicolo.getCasaCostruttrice()) && Objects.equals(getModello(), veicolo.getModello()) && Objects.equals(getTipo(), veicolo.getTipo()) && Objects.equals(getPrenotazioni(), veicolo.getPrenotazioni());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCasaCostruttrice(), getModello(), getAnnoImmatricolazione(), getTipo());
    }
}

