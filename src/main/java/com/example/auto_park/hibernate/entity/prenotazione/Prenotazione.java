package com.example.auto_park.hibernate.entity.prenotazione;
import com.example.auto_park.hibernate.entity.utente.Utente;
import com.example.auto_park.hibernate.entity.veicolo.Veicolo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name="prenotazione")
public class Prenotazione implements Serializable{

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_utente")
    private Utente utente;

    @ManyToOne
    @JoinColumn(name = "id_veicolo")
    private Veicolo veicolo;

    @Column(name = "data_inizio")
    private Date data_inizio;

    @Column(name = "data_fine")
    private Date data_fine;

    @Column(name = "isApprovato")
    private boolean isApprovato;

    public Prenotazione(Long id, Utente utente, Veicolo veicolo, Date data_inizio, Date data_fine, boolean isApprovato) {
        this.id = id;
        this.utente = utente;
        this.veicolo = veicolo;
        this.data_inizio = data_inizio;
        this.data_fine = data_fine;
        this.isApprovato = isApprovato;
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

    public Date getData_inizio() {
        return data_inizio;
    }

    public void setData_inizio(Date data_inizio) {
        this.data_inizio = data_inizio;
    }

    public Date getData_fine() {
        return data_fine;
    }

    public void setData_fine(Date data_fine) {
        this.data_fine = data_fine;
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
        return isApprovato() == that.isApprovato() && Objects.equals(getId(), that.getId()) && Objects.equals(getUtente(), that.getUtente()) && Objects.equals(getVeicolo(), that.getVeicolo()) && Objects.equals(getData_inizio(), that.getData_inizio()) && Objects.equals(getData_fine(), that.getData_fine());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUtente(), getVeicolo(), getData_inizio(), getData_fine(), isApprovato());
    }
}
