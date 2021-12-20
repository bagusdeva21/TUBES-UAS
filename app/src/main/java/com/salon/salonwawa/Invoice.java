package com.salon.salonwawa;

import androidx.databinding.BaseObservable;

public class Invoice extends BaseObservable {
    String hari;
    String tanggal;
    String tukangCukur;
    String pembayaran;

    public Invoice(String hari, String tanggal, String tukangCukur, String pembayaran) {
        this.hari = hari;
        this.tanggal = tanggal;
        this.tukangCukur = tukangCukur;
        this.pembayaran = pembayaran;
    }

    public String getHari() {
        return hari;
    }

    public String getTanggal() {
        return tanggal;
    }

    public String getTukangCukur() {
        return tukangCukur;
    }

    public String getPembayaran() {
        return pembayaran;
    }
}
