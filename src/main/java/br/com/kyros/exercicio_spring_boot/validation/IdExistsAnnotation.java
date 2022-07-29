package br.com.kyros.exercicio_spring_boot.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = IdExistsAnnotationValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.TYPE_USE, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface IdExistsAnnotation {

	String message() default "Id does not exist";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	String entity() default "";
}
