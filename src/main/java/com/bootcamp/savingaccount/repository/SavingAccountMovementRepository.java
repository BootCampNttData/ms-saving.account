package com.bootcamp.savingaccount.repository;

import com.bootcamp.savingaccount.model.SavingAccountMovement;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface SavingAccountMovementRepository extends ReactiveCrudRepository<SavingAccountMovement, String> {
    Flux<SavingAccountMovement> findByAccountNumber(Integer accountNumber);
    Flux<SavingAccountMovement> findByAmount(String num);

}