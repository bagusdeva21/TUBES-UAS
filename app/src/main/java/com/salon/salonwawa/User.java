package com.salon.salonwawa;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class User extends BaseObservable {
    String nama;
    String umur;
    String noTelp;
    String email;
    String username;
    String password;

    public User(String nama, String umur, String noTelp, String email, String username, String password) {
        this.nama = nama;
        this.umur = umur;
        this.noTelp = noTelp;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    @Bindable
    public String getNama() {
        return nama;
    }
    @Bindable
    public String getUmur() {
        return umur;
    }
    @Bindable
    public String getNoTelp() {
        return noTelp;
    }
    @Bindable
    public String getEmail() {
        return email;
    }
    @Bindable
    public String getUsername() {
        return username;
    }
    @Bindable
    public String getPassword() {
        return password;
    }

}
