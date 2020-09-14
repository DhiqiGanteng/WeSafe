package com.example.wesafe.model;

import java.io.Serializable;

public class Gejala implements Serializable {
    private String key;
    private int id;
    private String nama;
    private String namaKejadian;
    private Boolean isChecked;

    public Gejala() {
    }

    public Gejala(int id,String nama, String namaKejadian) {
        this.id=id;
        this.nama = nama;
        this.namaKejadian = namaKejadian;
    }
    public Boolean getChecked() {
        return isChecked;
    }

    public void setChecked(Boolean checked) {
        isChecked = checked;
    }
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNamaKejadian(){
        return namaKejadian;
    }
    public void setNamaKejadian(String namaKejadian){
        this.namaKejadian = namaKejadian;
    }
}