package com.example.javadb.entity;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "samochod")
public class Samochod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_samochod")
    private long idSamochod;

    @NotNull
    private String nazwa;

    private String opis;

    @Column(name = "id_producent")
    private Long idProducent;

    @Column(name = "id_model")
    private Long idModel;

    public Samochod(){ }

    public Samochod(long idSamochod)
    {
        this.idSamochod = idSamochod;
    }

    public Samochod(String nazwa, String opis, Long idProducent, Long idModel){
        this.nazwa = nazwa;
        if(opis.isEmpty())
            this.opis=null;
        else
            this.opis = opis;
        this.idProducent = idProducent;
        this.idModel = idModel;
    }


    public String getnazwa() {
        return nazwa;
    }

    public void setnazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getopis() {
        return opis;
    }

    public void setopis(String opis) {
        this.opis = opis;
    }


    public long getIdSamochod() {
        return idSamochod;
    }

    public void setIdSamochod(long idSamochod) {
        this.idSamochod = idSamochod;
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

    public Long getIdProducent() {
        return idProducent;
    }

    public void setIdProducent(Long idProducent) {
        this.idProducent = idProducent;
    }

    public Long getIdModel() {
        return idModel;
    }

    public void setIdModel(Long idModel) {
        this.idModel = idModel;
    }
}
