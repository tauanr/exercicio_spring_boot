package br.com.kyros.exercicio_spring_boot.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = FieldMustBeUniqueInDbAnnotationValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.TYPE_USE })
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldMustBeUniqueInDbAnnotation {

	String message() default "this value already exists and must be unique in database";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	String uniqueField() default "";
}
