package com.example.jwt.model;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity(name="usuario_model")
public class UsuarioModel {

    @Id
    @Column(unique = true)
    private String username;
    private String password;

    public UsuarioModel(){}
    public UsuarioModel(String username, String password) {
        this.username = username;
        this.password = password;
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
}
