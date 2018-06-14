package com.example.javadb.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "producent")
public class Producent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producent")
    private Long idProducent;

    @NotNull
    private String nazwa;

    private String opis;

    public Producent(){}

    public Producent(long idProducent)
    {
        this.idProducent = idProducent;
    }

    public Producent(String nazwa, String opis)
    {
        this.nazwa = nazwa;
        this.opis = opis;
    }

    public Long getIdProducent() {
        return idProducent;
    }

    public void setIdProducent(Long idProducent) {
        this.idProducent = idProducent;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }
}