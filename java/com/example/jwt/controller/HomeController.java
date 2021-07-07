package com.example.jwt.controller;


import com.example.jwt.model.UsinaModel;
import com.example.jwt.model.UsuarioModel;
import com.example.jwt.model.JWTResponse;
import com.example.jwt.service.UserService;
import com.example.jwt.service.UsinaService;
import com.example.jwt.utility.JWTUtility;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
public class HomeController {

    @Autowired
    private JWTUtility jwtUtility;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsinaService usinaService;

    @Autowired
    private UserService userService;


    @GetMapping("/home")
    public String home(){
        return "Benvindo ao video do indiano";
    }

    @PostMapping("/logar")
    public JWTResponse autheticate(@RequestBody UsuarioModel usuarioModel) throws Exception {
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            usuarioModel.getUsername(),
                            usuarioModel.getPassword()
                    )
            );
        }catch (BadCredentialsException e){
            throw  new Exception("Credenciais invalidas", e);
        }

        final UserDetails userDetails = userService.loadUserByUsername(usuarioModel.getUsername());
        final String token = jwtUtility.generateToken(userDetails);

        return new JWTResponse(token);
    }

    @GetMapping("/listar")
    public List<UsuarioModel> usuarios(){
        return userService.todos();
    }


    @PostMapping("/cadastrar")
    public ResponseEntity cadastro_usuario(@RequestBody UsuarioModel user){
        String nova_senha = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(nova_senha);
        return userService.cadastrar(user);
    }
/*
    @GetMapping("/nome")
    public ResponseEntity meu_nome(HttpServletRequest request){
        final String requestTokenHeader = request.getHeader("Authorization");

        String username = null;
        String jwtToken = null;

        // JWT Token está no form "Bearer token". Remova a palavra Bearer e pegue somente o Token
        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            jwtToken = requestTokenHeader.substring(7);
            try {
                username = jwtUtility.getUsernameFromToken(jwtToken);
            } catch (IllegalArgumentException e) {
                System.out.println("Unable to get JWT Token");
            } catch (ExpiredJwtException e) {
                System.out.println("JWT Token has expired");
            }
        }

        return ResponseEntity.ok().body("Seu token é: "+ username +", DAAAAAAAAAAAALE");
    }*/

    @PostMapping("/novausina")
    public ResponseEntity nova_usina(@RequestBody Map<String, String> usina, HttpServletRequest request){

        final String requestTokenHeader = request.getHeader("Authorization");

        String username = null;
        String jwtToken = null;

        // JWT Token está no form "Bearer token". Remova a palavra Bearer e pegue somente o Token
        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            jwtToken = requestTokenHeader.substring(7);
            try {
                username = jwtUtility.getUsernameFromToken(jwtToken);
            } catch (IllegalArgumentException e) {
                System.out.println("Unable to get JWT Token");
            } catch (ExpiredJwtException e) {
                System.out.println("JWT Token has expired");
            }
        }

        return usinaService.cadastrar_usina(new UsinaModel(username, usina.get("hash")));

    }

    @GetMapping("/listausinas")
    public ResponseEntity lista_usinas(HttpServletRequest request){

        final String requestTokenHeader = request.getHeader("Authorization");

        String username = null;
        String jwtToken = null;

        // JWT Token está no form "Bearer token". Remova a palavra Bearer e pegue somente o Token
        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            jwtToken = requestTokenHeader.substring(7);
            try {
                username = jwtUtility.getUsernameFromToken(jwtToken);
            } catch (IllegalArgumentException e) {
                System.out.println("Unable to get JWT Token");
            } catch (ExpiredJwtException e) {
                System.out.println("JWT Token has expired");
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(usinaService.listar_usinas(username));
    }

    @PostMapping("/removerusina")
    public ResponseEntity remover_usina(@RequestBody Map<String, String> usina,HttpServletRequest request){
        final String requestTokenHeader = request.getHeader("Authorization");

        String username = null;
        String jwtToken = null;

        // JWT Token está no form "Bearer token". Remova a palavra Bearer e pegue somente o Token
        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            jwtToken = requestTokenHeader.substring(7);
            try {
                username = jwtUtility.getUsernameFromToken(jwtToken);
            } catch (IllegalArgumentException e) {
                System.out.println("Unable to get JWT Token");
            } catch (ExpiredJwtException e) {
                System.out.println("JWT Token has expired");
            }
        }
        return usinaService.remover_usina(new UsinaModel(username, usina.get("hash")));
    }

}
