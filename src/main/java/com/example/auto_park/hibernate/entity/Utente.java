package com.example.auto_park.hibernate.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "utente")
public class Utente implements Serializable {


    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "cognome")
    private String cognome;

    @Column(name = "dataNascita")
    private LocalDate dataNascita;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "utente", fetch = FetchType.EAGER)
    private Set<Prenotazione> prenotazioni = new HashSet<>();

    public Utente() {
    }

    public Utente(Long id) {
        this.id = id;
    }

    public Utente(Long id, String nome, String cognome, LocalDate dataNascita, String tipo, String username, String password, Set<Prenotazione> prenotazioni) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = dataNascita;
        this.tipo = tipo;
        this.username = username;
        this.password = password;
        this.prenotazioni = prenotazioni;
    }

    public Utente(String nome, String cognome, LocalDate dataNascita, String tipo, String username, String password, Set<Prenotazione> prenotazioni) {
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = dataNascita;
        this.tipo = tipo;
        this.username = username;
        this.password = password;
        this.prenotazioni = prenotazioni;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public LocalDate getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(LocalDate dataNascita) {
        this.dataNascita = dataNascita;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
        if (!(o instanceof Utente)) return false;
        Utente utente = (Utente) o;
        return Objects.equals(getId(), utente.getId()) && Objects.equals(getNome(), utente.getNome()) && Objects.equals(getCognome(), utente.getCognome()) && Objects.equals(getDataNascita(), utente.getDataNascita()) && Objects.equals(getTipo(), utente.getTipo()) && Objects.equals(getUsername(), utente.getUsername()) && Objects.equals(getPassword(), utente.getPassword()) && Objects.equals(getPrenotazioni(), utente.getPrenotazioni());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNome(), getCognome(), getDataNascita(), getTipo(), getUsername(), getPassword());
    }
}
