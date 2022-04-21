package com.bootcamp.savingaccount.service.Impl;

import com.bootcamp.savingaccount.model.SavingAccountMovement;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SavingAccountMovementService {
    Flux<SavingAccountMovement> findAll();
    Mono<SavingAccountMovement> create(SavingAccountMovement savingAccountMovement);
    Flux<SavingAccountMovement> findByAccountNumber(Integer num);
    Mono<SavingAccountMovement> update(SavingAccountMovement savingAccountMovement);
    Mono<SavingAccountMovement> deleteById(String id);
    Mono delete(SavingAccountMovement savingAccountMovement);
    Mono<Double> getBalanceByAccount(Integer num);
}
