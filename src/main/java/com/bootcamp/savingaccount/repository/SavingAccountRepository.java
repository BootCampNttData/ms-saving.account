package com.bootcamp.savingaccount.repository;

import com.bootcamp.savingaccount.model.SavingAccount;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface SavingAccountRepository extends ReactiveCrudRepository<SavingAccount, String> {
    Flux<SavingAccount> findByAccountNumber(String num);
//    Mono<SavingAccount> findByClientIdAndSavingAccountType(String num,String type);
    Flux<SavingAccount> findByClientId(String id);

}
