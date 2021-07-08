package com.example.jwt.service;

import com.example.jwt.DB.Banco;
import com.example.jwt.DB.UsuarioRepository;
import com.example.jwt.model.UsuarioModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private Banco db;

    @Autowired
    UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var x = repository.findById(username).orElse(new UsuarioModel());
        return new User(x.getUsername(), x.getPassword(),new ArrayList<>());
    }

    public ResponseEntity cadastrar(UsuarioModel user) {
        var usuario = repository.findById(user.getUsername()).orElse(null);
        if( usuario == null){
            repository.save(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("Usuario Cadastrado");
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Usuario Existente");
    }


    public List<UsuarioModel> todos() {
        return repository.findAll();
    }
}
