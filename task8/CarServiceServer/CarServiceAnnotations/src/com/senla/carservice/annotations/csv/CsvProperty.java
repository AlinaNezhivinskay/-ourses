package com.senla.carservice.annotations.csv;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.senla.carservice.annotations.csv.propertytype.PropertyType;

@Target(value = ElementType.FIELD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface CsvProperty {
	PropertyType propertyType();

	int columnNumber() default 0;

	String keyField() default "";
}
