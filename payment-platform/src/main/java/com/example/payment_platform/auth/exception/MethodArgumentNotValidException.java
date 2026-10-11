package com.example.payment_platform.auth.exception;

import org.springframework.core.MethodParameter;
import org.springframework.validation.BindingResult;

public class MethodArgumentNotValidException extends Exception{
 private final MethodParameter parameter;
    private final BindingResult bindingResult; 

    public MethodArgumentNotValidException(MethodParameter parameter, BindingResult bindingResult) {
        super("Validation failed for argument [" + parameter.getParameterIndex() + "]...");
        this.parameter = parameter;
        this.bindingResult = bindingResult;
    }

    public final BindingResult getBindingResult() {
        return this.bindingResult;
    }
}
