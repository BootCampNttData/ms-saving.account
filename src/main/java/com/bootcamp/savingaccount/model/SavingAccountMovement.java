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
    private Integer savingAccountNumber;
    private String  movementType;
    private Date    movementDate;
    private Double  amount;
}
