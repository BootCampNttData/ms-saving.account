package com.bootcamp.savingaccount.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document
public class SavingAccount {
    @Id
    public String  id;
    public String  clientId;
    public Integer savingAccountNumber;
    public Double  feeAmount;
    public Double  minimalAmount;
    public Integer movementLimit;
    public Date    creationDate;
}
