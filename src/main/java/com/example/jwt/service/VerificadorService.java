package com.example.jwt.service;


import com.example.jwt.DB.UsinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class VerificadorService{

    @Autowired
    private UsinaService usinaService;

    @Scheduled(fixedRate = 1000*10) // atualiza a cada um minuto
    public void atualiza_status_usina(){

        System.out.println("Verificando status da usina");
        usinaService.pegar_todas().stream().forEach(usinaModel -> {
            usinaModel.setStatus("ON");
            /** inserir um valor que provavelmente é pego ou
             * enviado por outro locar para ser inserido,
             * foi deixado como ON apenas para fins de treino
             * */
           usinaService.atualizar_usina(usinaModel);
        });

    }
}
