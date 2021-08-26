package com.zup.aviacao.config.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ValidaSeTemIdValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidaSeTemIdValue {

    String message() default "Mensagem de erro";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default{};
    String fieldName() default "id";
    Class<?> domaiClass();
}
