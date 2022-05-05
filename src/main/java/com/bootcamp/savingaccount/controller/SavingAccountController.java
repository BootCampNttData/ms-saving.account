package com.bootcamp.savingaccount.controller;

import com.bootcamp.savingaccount.exception.SavingAccountValidationException;
import org.springframework.util.ObjectUtils;
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
import java.util.Optional;
import java.util.function.Predicate;

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
    @GetMapping("/findAcountByClientId/{clientId}")
    public Mono<String> findAcountByClientId(@PathVariable("clientId") String clientId) {
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
     * @param savingAccount
     * @return
     */
    @PostMapping
    public Mono<SavingAccount> create(@RequestBody SavingAccount savingAccount){
        return Mono.just(savingAccount)
                .then(check(savingAccount, sav -> Optional.of(sav).isEmpty(), "Saving Account has not data"))
                .then(check(savingAccount, sav -> ObjectUtils.isEmpty(sav.getClientId()), "Client Id is required"))
                .then(check(savingAccount, sav -> ObjectUtils.isEmpty(sav.getAccountNumber()), "Account Number required"))
                .then(savingAccountService.findByClientId(savingAccount.getClientId())
                        .<SavingAccount>handle((record, sink) -> sink.error(new SavingAccountValidationException("The Client already have a Saving account")))
                        .switchIfEmpty(savingAccountService.create(savingAccount)))
                ;

    }

    @PostMapping("/update")
    public Mono<SavingAccount> update(@RequestBody SavingAccount savingAccount){
        return savingAccountService.create(savingAccount);
    }

    @DeleteMapping
    public Mono<SavingAccount> delete(@RequestBody SavingAccount savingAccount){
        return savingAccountService.delete(savingAccount);
    }

    @DeleteMapping("/byId/{id}")
    public Mono<SavingAccount> deleteById(@RequestBody String id){
        return savingAccountService.deleteById(id);
    }

    /** *****************************************/
    @GetMapping("/findByClientId/{id}")
    public Mono<SavingAccount> findByClientId(@PathVariable("id") String id){
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
    public Mono<SavingAccountMovement> create(@RequestBody SavingAccountMovement savingAccountMovement){
        return savingAccountMovementService.create(savingAccountMovement);
    }

    @DeleteMapping("/movement")
    public Mono<SavingAccountMovement> delete(@RequestBody SavingAccountMovement savingAccountMovement){
        return savingAccountMovementService.delete(savingAccountMovement);
    }

    @DeleteMapping("/movement/byId/{id}")
    public Mono<SavingAccountMovement> deleteMovementById(@RequestBody String id){
        return savingAccountMovementService.deleteById(id);
    }

    private <T> Mono<Void> check(T account, Predicate<T> predicate, String messageForException) {
        return Mono.create(sink -> {
            if (predicate.test(account)) {
                sink.error(new Exception(messageForException));
                return;
            } else {
                sink.success();
            }
        });
    }

}