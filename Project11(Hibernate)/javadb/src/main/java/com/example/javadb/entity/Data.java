package com.example.javadb.entity;



import java.io.Serializable;

public class Data implements Serializable {
    private Long id;
    private String nazwa;
    private String opis;

    public Data()
    {
    }

    public Data(Long id, String nazwa, String opis)
    {
        this.id = id;
        this.nazwa = nazwa;
        this.opis = opis;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
