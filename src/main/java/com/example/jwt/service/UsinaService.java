package com.example.jwt.service;

import com.example.jwt.DB.UsinaRepository;
import com.example.jwt.DB.UsuarioRepository;
import com.example.jwt.model.UsinaModel;
import com.example.jwt.model.UsuarioModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsinaService {

    @Autowired
    UsinaRepository usinaRepository;


    public ResponseEntity cadastrar_usina(UsinaModel usinaModel){
        var usina = usinaRepository.findById(usinaModel.getHash()).orElse(null);
        if(usina == null){
            usinaRepository.save(usinaModel);
            return ResponseEntity.status(HttpStatus.CREATED).body("Usina criada com sucesso");
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Usina " + usinaModel.getHash()+ " já existe");
    }

    public List<UsinaModel> listar_usinas(String username) {
        return usinaRepository.findAll().stream().filter(usinaModel -> usinaModel.getPeople().equals(username)).collect(Collectors.toList());
    }

    public ResponseEntity remover_usina(UsinaModel usina) {
        var usinas = usinaRepository.findById(usina.getHash()).orElse(null);
        if(usina != null){
            usinaRepository.deleteById(usina.getHash());
            return ResponseEntity.status(HttpStatus.OK).body("Usina "+ usina.getHash() +" deletada com sucesso");
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Usina " + usina.getHash()+ " nao existe");
    }

    public void atualizar_usina(UsinaModel usina_atualizada){
        var usina = usinaRepository.findById(usina_atualizada.getHash()).orElse(null);
        if (usina != null){
            usinaRepository.save(usina_atualizada);
        }

    }

    public List<UsinaModel> pegar_todas(){
        return usinaRepository.findAll();
    }
}
