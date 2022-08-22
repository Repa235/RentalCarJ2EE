package com.example.auto_park.hibernate.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "prenotazione")
public class Prenotazione implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idUtente")
    private Utente utente;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idVeicolo")
    private Veicolo veicolo;

    @Column(name = "dataInizio")
    private LocalDate dataInizio;

    @Column(name = "dataFine")
    private LocalDate dataFine;

    @Column(name = "isApprovato")
    private boolean isApprovato;

    public Prenotazione(Long id, Utente utente, Veicolo veicolo, LocalDate dataInizio, LocalDate dataFine, boolean isApprovato) {
        this.id = id;
        this.utente = utente;
        this.veicolo = veicolo;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.isApprovato = isApprovato;
    }

    public Prenotazione(Utente utente, Veicolo veicolo, LocalDate dataInizio, LocalDate dataFine, boolean isApprovato) {
        this.utente = utente;
        this.veicolo = veicolo;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.isApprovato = isApprovato;
    }

    public Prenotazione(Long id) {
        this.id = id;
    }

    public Prenotazione() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public Veicolo getVeicolo() {
        return veicolo;
    }

    public void setVeicolo(Veicolo veicolo) {
        this.veicolo = veicolo;
    }

    public LocalDate getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(LocalDate dataInizio) {
        this.dataInizio = dataInizio;
    }

    public LocalDate getDataFine() {
        return dataFine;
    }

    public void setDataFine(LocalDate dataFine) {
        this.dataFine = dataFine;
    }

    public boolean isApprovato() {
        return isApprovato;
    }

    public void setApprovato(boolean approvato) {
        isApprovato = approvato;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Prenotazione)) return false;
        Prenotazione that = (Prenotazione) o;
        return isApprovato() == that.isApprovato() && Objects.equals(getId(), that.getId()) && Objects.equals(getUtente(), that.getUtente()) && Objects.equals(getVeicolo(), that.getVeicolo()) && Objects.equals(getDataInizio(), that.getDataInizio()) && Objects.equals(getDataFine(), that.getDataFine());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUtente(), getVeicolo(), getDataInizio(), getDataFine(), isApprovato());
    }
}
