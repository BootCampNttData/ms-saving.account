package com.bootcamp.savingaccount.controller;

import com.bootcamp.savingaccount.model.SavingAccountMovement;
import com.bootcamp.savingaccount.service.Impl.SavingAccountMovementService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/savingSavingAccountMovement")
@RequiredArgsConstructor
public class SavingAccountMovementController {
//    @Value("${passiveproducts.server.url}")
//    private String passPrdUrl;
//
    public final SavingAccountMovementService service;
    @GetMapping
    public Flux<SavingAccountMovement> getAll(){
        return service.findAll();
    }

    @GetMapping("/find/{num}")
    public Flux<SavingAccountMovement> getByAccountNumber(@PathVariable("num") String num){
        return service.findByAccountNumber(num);
    }

    @GetMapping("/savingAccountBalance/{savingAccount}")
    public String getAccountBalance(@PathVariable("savingAccount") String savingAccount){
        double balance=0;
//        RestTemplate restTemplate=new RestTemplate();
//        String urlDp = passPrdUrl +"/savingSavingAccountMovement/find/" + savingAccount;
//        ResponseEntity<SavingAccountMovement[]> savingSavingAccountMovements = restTemplate.getForEntity(urlDp,SavingAccountMovement[].class);
//        for(SavingAccountMovement am: savingSavingAccountMovements.getBody()){
//            if (am.getMovementType().equals("D")){
//                balance += am.getAmount();
//            }else if (am.getMovementType().equals("R")){
//                balance -= am.getAmount();
//            }
//        }
        return String.valueOf(balance);
    }

    @PostMapping
    public Mono<SavingAccountMovement> create(@RequestBody SavingAccountMovement savingSavingAccountMovement){
        return service.create(savingSavingAccountMovement);
    }

    @PostMapping("/update")
    public Mono<SavingAccountMovement> update(@RequestBody SavingAccountMovement savingSavingAccountMovement){
        return service.create(savingSavingAccountMovement);
    }

    @DeleteMapping
    public Mono<SavingAccountMovement> delete(@RequestBody SavingAccountMovement savingSavingAccountMovement){
        return service.delete(savingSavingAccountMovement);
    }

    @DeleteMapping("/byId/{id}")
    public Mono<SavingAccountMovement> deleteById(@RequestBody String id){
        return service.deleteById(id);
    }

}