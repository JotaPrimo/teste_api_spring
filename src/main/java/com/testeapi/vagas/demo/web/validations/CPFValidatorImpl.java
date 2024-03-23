package com.testeapi.vagas.demo.web.validations;

import com.testeapi.vagas.demo.utils.CPFUtil;
import com.testeapi.vagas.demo.web.validations.interfaces.ICPFValidator;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CPFValidatorImpl implements ConstraintValidator<ICPFValidator, String> {
    @Override
    public void initialize(ICPFValidator constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String cpf, ConstraintValidatorContext constraintValidatorContext) {
        return CPFUtil.isValid(cpf);
    }
}
