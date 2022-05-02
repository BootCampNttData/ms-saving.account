package com.bootcamp.savingaccount.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.bootcamp.savingaccount.model.SavingAccount;
import com.bootcamp.savingaccount.model.SavingAccountMovement;
import com.bootcamp.savingaccount.service.Impl.SavingAccountMovementService;
import com.bootcamp.savingaccount.service.Impl.SavingAccountService;
import lombok.RequiredArgsConstructor;
import reactor.core.CoreSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/savingaccount")
@RequiredArgsConstructor
public class SavingAccountController {

    public final SavingAccountService savingAccountService;
    public final SavingAccountMovementService savingAccountMovementService;

    @GetMapping
    public Flux<SavingAccount> getAll(){
        return savingAccountService.findAll();
    }

    @GetMapping("/accountNumber/{num}")
    public Flux<SavingAccount> findByAccountNumber(@PathVariable("num") String num){
        return savingAccountService.findByAccountNumber(num);
    }

    /**
     * Obtiene una lista de las cuentas de Ahorro que posea el Cliente segun su Documento
     * @param clientId Documento del Cliente
     * @return Lista con las cuentas pertenecientes al Documento
     */
    @GetMapping("/findAcountsByClientId/{clientId}")
    public Flux<String> findAcountsByClientId(@PathVariable("clientId") String clientId) {
        var accounts = savingAccountService.findByClientId(clientId);
        var lst = accounts.map(acc -> {
            return acc.getAccountNumber();
        });
        return lst;
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

        return savingAccountService.create(savingSavingAccount);
    }

    @PostMapping("/update")
    public Mono<SavingAccount> update(@RequestBody SavingAccount savingSavingAccount){
        return savingAccountService.create(savingSavingAccount);
    }

    @DeleteMapping
    public Mono<SavingAccount> delete(@RequestBody SavingAccount savingSavingAccount){
        return savingAccountService.delete(savingSavingAccount);
    }

    @DeleteMapping("/byId/{id}")
    public Mono<SavingAccount> deleteById(@RequestBody String id){
        return savingAccountService.deleteById(id);
    }

    /** *****************************************/
    @GetMapping("/findByClientId/{id}")
    public Flux<SavingAccount> findByClientId(@PathVariable("id") String id){
        return savingAccountService.findByClientId(id);
    }
    
    @GetMapping("/movement")
    public Flux<SavingAccountMovement> getAllMovements(){
        return savingAccountMovementService.findAll();
    }

    @GetMapping("/movement/{accountNumber}")
    public Flux<SavingAccountMovement> getByAccountNumber(@PathVariable("accountNumber") String num){
        return savingAccountMovementService.findByAccountNumber(num);
    }

    @GetMapping("/accountBalance/{accountNumber}")
    public Mono<Double> getAccountBalance(@PathVariable("accountNumber") String accountNumber){
        return savingAccountMovementService.getBalanceByAccount(accountNumber);
    }

    @PostMapping("/movement")
    public Mono<SavingAccountMovement> create(@RequestBody SavingAccountMovement savingSavingAccountMovement){
        return savingAccountMovementService.create(savingSavingAccountMovement);
    }

    @DeleteMapping("/movement")
    public Mono<SavingAccountMovement> delete(@RequestBody SavingAccountMovement savingSavingAccountMovement){
        return savingAccountMovementService.delete(savingSavingAccountMovement);
    }

    @DeleteMapping("/movement/byId/{id}")
    public Mono<SavingAccountMovement> deleteMovementById(@RequestBody String id){
        return savingAccountMovementService.deleteById(id);
    }

}