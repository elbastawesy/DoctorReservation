package com.bastawesy.core.model.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.reflect.Reflection;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import java.io.Serializable;
import java.util.Set;

public class BaseEntity implements Serializable {

    protected final Logger logger = LoggerFactory.getLogger(BaseEntity.class);

    public void validateEntity(){
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<BaseEntity>> constraintViolations = validator.validate(this);
        if (constraintViolations != null && !constraintViolations.isEmpty()) {
            StringBuilder errorBuilder = new StringBuilder();
            for (ConstraintViolation<BaseEntity> violation : constraintViolations) {
                String violationMessage = violation.getMessage() + System.lineSeparator();
                logger.error(violationMessage);
                errorBuilder.append(violationMessage);
            }
            throw new ConstraintViolationException(constraintViolations);
        }
    }

}
