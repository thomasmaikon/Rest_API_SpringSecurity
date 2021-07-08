package com.example.jwt.DB;

import com.example.jwt.model.UsuarioModel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class Banco {

    private List<UsuarioModel> usuarioModelList = new ArrayList<>();

    public UsuarioModel pegar_usuario(String nome){
        inserir_usuarios();
        var x = usuarioModelList.stream().filter(usuarioModel -> usuarioModel.getUsername().equals(nome)).findFirst();
        return x.orElse(new UsuarioModel("",""));
    }

    private void inserir_usuarios(){
        usuarioModelList.add(new UsuarioModel("thomas","123"));
        usuarioModelList.add(new UsuarioModel("jose","1563"));
        usuarioModelList.add(new UsuarioModel("samuel","2319"));
    }

    public List<UsuarioModel> lista_usuarios(){

        return usuarioModelList;
    }
}
