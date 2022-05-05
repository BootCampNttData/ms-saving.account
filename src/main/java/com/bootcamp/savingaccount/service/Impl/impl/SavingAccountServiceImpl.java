package com.bootcamp.savingaccount.service.Impl.impl;

import com.bootcamp.savingaccount.model.SavingAccount;
import com.bootcamp.savingaccount.repository.SavingAccountRepository;
import com.bootcamp.savingaccount.service.Impl.SavingAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor

public class SavingAccountServiceImpl implements SavingAccountService {
    public final SavingAccountRepository repository;

    @Override
    public Mono<SavingAccount> create(SavingAccount savingAccount) {
        return repository.save(savingAccount);

    }

    @Override
    public Mono<SavingAccount> update(SavingAccount savingAccount) {
        return repository.save(savingAccount);
    }

    @Override
    public Mono deleteById(String id) {
        return repository.deleteById(id);
    }

    @Override
    public Mono delete(SavingAccount savingAccount) {
        return repository.delete(savingAccount);
    }

    @Override
    public Flux<SavingAccount> findAll() {
        return repository.findAll();
    }

    @Override
    public Flux<SavingAccount> findByAccountNumber(String num) {
        return repository.findByAccountNumber(num);
    }

    @Override
    public Mono<SavingAccount> findByClientId(String id) {
        return repository.findByClientId(id);
    }

}