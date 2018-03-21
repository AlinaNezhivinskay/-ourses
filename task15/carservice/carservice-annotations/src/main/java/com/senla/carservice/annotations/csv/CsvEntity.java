package com.senla.carservice.annotations.csv;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value = ElementType.TYPE)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface CsvEntity {
	String filename();

	String valuesSeparator() default ";";

	String entityId();
}
