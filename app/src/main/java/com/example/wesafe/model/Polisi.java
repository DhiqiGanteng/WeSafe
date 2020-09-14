package com.example.wesafe.model;

import java.io.Serializable;

public class Polisi implements Serializable {
    private int id;
    private String nama;
    private String alamat;
    private String noTelp;
    private String Key;
    private double latitude;
    private double longtitude;
    private int jarak;

    public Polisi(){

    }
    public Polisi(int id, String nama, String alamat, String noTelp, double latitude, double longtitude, int jarak) {
        this.id = id;
        this.nama = nama;
        this.alamat = alamat;
        this.noTelp = noTelp;
        this.latitude = latitude;
        this.longtitude = longtitude;
        this.jarak = jarak;
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

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }

    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(double longtitude) {
        this.longtitude = longtitude;
    }

    public int getJarak() {
        return jarak;
    }

    public void setJarak(int jarak) {
        this.jarak = jarak;
    }
}

