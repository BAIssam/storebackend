package com.iba.storebackend.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;

@SuppressWarnings("deprecation")
@Email(message="Mauvaise adresse mail !")
@Pattern(regexp=".+@.+\\..+", message="Veuillez saisir une adresse mail valide !")
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
@Documented
public @interface ExtendedEmailValidator {
	String message() default "Wrong email";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}

interface Constants {
    static final String ATOM = "[a-z0-9!#$%&'*+/=?^_`{|}~-]";
    static final String DOMAIN = "(" + ATOM + "+(\\." + ATOM + "+)+";
    static final String IP_DOMAIN = "\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\]";

    static final String PATTERN =
            "^" + ATOM + "+(\\." + ATOM + "+)*@"
                    + DOMAIN
                    + "|"
                    + IP_DOMAIN
                    + ")$";
}