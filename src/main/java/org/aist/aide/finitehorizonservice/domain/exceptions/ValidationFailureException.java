package org.aist.aide.finitehorizonservice.domain.exceptions;

public class ValidationFailureException extends Exception {
    public ValidationFailureException(String str) {
        super(str);
    }
}
