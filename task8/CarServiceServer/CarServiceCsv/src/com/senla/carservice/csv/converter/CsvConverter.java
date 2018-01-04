package com.senla.carservice.csv.converter;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.senla.carservice.annotations.csv.CsvEntity;
import com.senla.carservice.annotations.csv.CsvProperty;
import com.senla.carservice.annotations.csv.propertytype.PropertyType;
import com.senla.carservice.model.beans.abstractentity.Entity;
import com.senla.carservice.model.orderstate.OrderState;
import com.senla.carservice.util.utils.DateWorker;

public class CsvConverter {
	public static List<String> convertToStrings(List<? extends Entity> entities, String separator)
			throws IllegalAccessException, NoSuchFieldException {
		List<String> strEntities = new ArrayList<String>();
		for (Entity entity : entities) {
			StringBuilder str = new StringBuilder();
			Class<? extends Entity> entityClass = entity.getClass();
			Field[] fields = entityClass.getDeclaredFields();
			for (Field field : fields) {
				if (field.isAnnotationPresent(CsvProperty.class)) {
					CsvProperty annotation = field.getDeclaredAnnotation(CsvProperty.class);
					field.setAccessible(true);
					if (annotation.propertyType() == PropertyType.SimpleProperty) {
						if (field.getType().equals(Date.class)) {
							String date = DateWorker.format((Date) field.get(entity));
							str.append(date).append(separator);
						} else {
							str.append(field.get(entity)).append(separator);
						}
					} else {
						Object obj = field.get(entity);
						if (obj != null) {
							Class<?> cl = field.getType();
							Field keyField = cl.getDeclaredField(annotation.keyField());
							keyField.setAccessible(true);
							str.append(keyField.get(obj)).append(separator);
						}
					}
				}
			}
			strEntities.add(str.toString());
		}
		return strEntities;
	}

	public static List<? extends Entity> convertFromStrings(Class<? extends Entity> entityClass, List<String> strings)
			throws IllegalAccessException, NoSuchFieldException, InstantiationException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
		List<Entity> entities = new ArrayList<>();
		String separator = entityClass.getAnnotation(CsvEntity.class).valuesSeparator();
		for (String str : strings) {
			String[] components = str.split(separator);

			Constructor<? extends Entity> constructor = entityClass.getConstructor();
			Entity entity = constructor.newInstance();

			Field[] fields = entityClass.getDeclaredFields();
			for (Field field : fields) {
				if (field.isAnnotationPresent(CsvProperty.class)) {
					CsvProperty annotation = field.getDeclaredAnnotation(CsvProperty.class);
					int columnNumber = annotation.columnNumber();
					field.setAccessible(true);
					if (annotation.propertyType() == PropertyType.SimpleProperty) {
						Method parseMethod = getMethodByFieldType(field.getType().getSimpleName());
						if(parseMethod==null) {
							field.set(entity,components[columnNumber - 1]);
						}
						else {
							field.set(entity, parseMethod.invoke(null, components[columnNumber - 1]));
						}
					} else {
						Class<?> fieldClass = field.getType();
						Field keyField = fieldClass.getDeclaredField(annotation.keyField());
						Class<?> IdClass = keyField.getType();
						Method parseMethod = getMethodByFieldType(IdClass.getSimpleName());

						String repositoryStr = "com.senla.carservice.repository." + fieldClass.getSimpleName()
								+ "Repository";
						Class<?> repositoryClass = Class.forName(repositoryStr);
						Method getInstance = repositoryClass.getMethod("getInstance");
						String getByIdStr = "get" + fieldClass.getSimpleName();
						Method getByIdMethod = repositoryClass.getMethod(getByIdStr, new Class[] { Long.class });
						if(components.length<columnNumber) {
							field.set(entity, null);
						}
						else {
						field.set(entity, getByIdMethod.invoke(getInstance.invoke(null),
								parseMethod.invoke(null, components[columnNumber - 1])));
						}
					}
				}
			}
			entities.add(entity);
		}
		return entities;
	}

	private static Method getMethodByFieldType(String type) throws NoSuchMethodException, SecurityException {
		Class<?> fieldClass;
		String parseStr;
		switch (type) {
		case "long":
			fieldClass = Long.class;
			parseStr = "parse" + fieldClass.getSimpleName();
			return fieldClass.getMethod(parseStr, new Class[] { String.class });
		case "boolean":
			fieldClass = Boolean.class;
			parseStr = "parse" + fieldClass.getSimpleName();
			return fieldClass.getMethod(parseStr, new Class[] { String.class });
		case "double":
			fieldClass = Double.class;
			parseStr = "parse" + fieldClass.getSimpleName();
			return fieldClass.getMethod(parseStr, new Class[] { String.class });
		case "String":
			return null;
		case "Date":
			fieldClass = DateWorker.class;
			parseStr = "parse";
			return fieldClass.getMethod(parseStr, new Class[] { String.class });
		case "OrderState":
			fieldClass = OrderState.class;
			parseStr = "valueOf";
			return fieldClass.getMethod(parseStr, new Class[] { String.class });
		}
		return null;
	}
}
