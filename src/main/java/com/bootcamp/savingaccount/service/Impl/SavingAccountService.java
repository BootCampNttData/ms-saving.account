package com.bootcamp.savingaccount.service.Impl;

import com.bootcamp.savingaccount.model.SavingAccount;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SavingAccountService {
    Flux<SavingAccount> findAll();
    Mono<SavingAccount> create(SavingAccount savingAccount);
    Flux<SavingAccount> findByAccountNumber(String num);
//    Mono<SavingAccount> findByClientIdAndSavingAccountType(String num,String type);
    Flux<SavingAccount> findByClientId(String id);
    Mono<SavingAccount> update(SavingAccount savingAccount);
    Mono<SavingAccount> deleteById(String id);
    Mono delete(SavingAccount savingAccount);
}
