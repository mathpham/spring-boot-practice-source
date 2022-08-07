package com.example.demo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = CheckFileValidator.class)
public @interface CheckFile {
	public String message() default "File Error! File name is too long";
	public int max_length() default Integer.MAX_VALUE;
	public Class<?>[] groups() default {};
	public Class<? extends Payload>[] payload() default {};
}
