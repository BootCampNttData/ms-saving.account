package com.bootcamp.savingaccount.exception;

public class SavingAccountValidationException extends Exception {

  private static final long serialVersionUID = 1L;

  public SavingAccountValidationException(String message, Throwable cause) {
    super(message, cause);
  }

  public SavingAccountValidationException(String message) {
    super(message);
  }

}
