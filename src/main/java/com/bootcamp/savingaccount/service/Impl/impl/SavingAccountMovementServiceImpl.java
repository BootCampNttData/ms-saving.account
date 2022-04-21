package com.bootcamp.savingaccount.service.Impl.impl;

import com.bootcamp.savingaccount.model.SavingAccountMovement;
import com.bootcamp.savingaccount.repository.SavingAccountMovementRepository;
import com.bootcamp.savingaccount.service.Impl.SavingAccountMovementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor

public class SavingAccountMovementServiceImpl implements SavingAccountMovementService {
    public final SavingAccountMovementRepository repository;

    /**
     * Movimiento Retiro o deposito a cuenta corriente o Ahorro
     * @param savingAccountMovement
     * @return
     */
    @Override
    public Mono<SavingAccountMovement> create(SavingAccountMovement savingAccountMovement) {
        return repository.save(savingAccountMovement);
    }

    @Override
    public Mono<SavingAccountMovement> update(SavingAccountMovement savingAccountMovement) {
        return repository.save(savingAccountMovement);
    }

    @Override
    public Mono deleteById(String id) {
        return repository.deleteById(id);
    }

    @Override
    public Mono delete(SavingAccountMovement savingAccountMovement) {
        return repository.delete(savingAccountMovement);
    }

    @Override
    public Flux<SavingAccountMovement> findAll() {
        return repository.findAll();
    }

    @Override
    public Flux<SavingAccountMovement> findByAccountNumber(Integer accountNumber) {
        return repository.findByAccountNumber(accountNumber);
    }

    @Override
    public Mono<Double> getBalanceByAccount(Integer num) {
        var movements = this.findByAccountNumber(num);
        var balance = movements
                .map(mov -> {
                    return ("D".equals(mov.getMovementType()) ? 1 : -1 ) * mov.getAmount();
                }).reduce(0d, (a, b) -> a + b);
        return balance;
    }
}