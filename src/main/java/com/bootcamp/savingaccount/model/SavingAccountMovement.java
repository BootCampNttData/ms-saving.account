package com.bootcamp.savingaccount.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document

public class SavingAccountMovement {
    @Id
    private String  id;
    private Integer accountNumber;
    private String  movementType;
    private String  movementDate;
    private Double  amount;
}
