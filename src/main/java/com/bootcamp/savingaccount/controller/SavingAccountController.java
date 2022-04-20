package com.bootcamp.savingaccount.controller;


import com.bootcamp.savingaccount.model.SavingAccount;
import com.bootcamp.savingaccount.service.Impl.SavingAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/savingSavingAccount")
@RequiredArgsConstructor
public class SavingAccountController {
//    @Value("${passiveproducts.server.url}")
//    private String passPrdUrl;

    public final SavingAccountService service;
    @GetMapping
    public Flux<SavingAccount> getAll(){
        return service.findAll();
    }

    @GetMapping("/accountNumber/{num}")
    public Flux<SavingAccount> findByAccountNumber(@PathVariable("num") String num){
        return service.findByAccountNumber(num);
    }

    /**
     * Crea una nueva cuenta Ahorro o Corriente dependiendo del pararameto ingresado en SavingAccountType [C|A]
     * Tambien valida si el cliente es una Empresa [E] o persona natural [P]
     * En caso que no se cumplan las condiciones retornara un objeto vacio y no se almacenara en la BD.
     * @param savingSavingAccount
     * @return
     */
    @PostMapping
    public Mono<SavingAccount> create(@RequestBody SavingAccount savingSavingAccount){

        RestTemplate restTemplate=new RestTemplate();
        Mono<SavingAccount> mono=Mono.just(new SavingAccount());

                                                /** Si es cuenta Personal */
            boolean haveSavingAcc=false;
            boolean haveCurrentAcc=false;

//            String url = passPrdUrl +"/savingSavingAccount/findByClientId/" + savingSavingAccount.getClientId();
//            ResponseEntity<SavingAccount[]> savingSavingAccounts = restTemplate.getForEntity(url,SavingAccount[].class);

        return service.create(savingSavingAccount);
    }

    @PostMapping("/update")
    public Mono<SavingAccount> update(@RequestBody SavingAccount savingSavingAccount){
        return service.create(savingSavingAccount);
    }

    @DeleteMapping
    public Mono<SavingAccount> delete(@RequestBody SavingAccount savingSavingAccount){
        return service.delete(savingSavingAccount);
    }

    @DeleteMapping("/byId/{id}")
    public Mono<SavingAccount> deleteById(@RequestBody String id){
        return service.deleteById(id);
    }

    /** *****************************************/
    @GetMapping("/findByClientId/{id}")
    public Flux<SavingAccount> findByClientId(@PathVariable("id") String id){
        return service.findByClientId(id);
    }



}