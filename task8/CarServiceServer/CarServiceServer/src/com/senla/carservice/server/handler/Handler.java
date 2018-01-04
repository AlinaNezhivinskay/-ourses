package com.senla.carservice.server.handler;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.senla.carservice.util.carservicecommands.Commands;
import com.senla.carservice.view.facade.CarService;

public class Handler {
	static public Map<String, Object> handleRequest(Map<Commands, List<Object>> request)
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		CarService carServise = (CarService) CarService.getInstance();
		Map<String, Object> responce = new HashMap<>();
		if (request != null && !request.isEmpty()) {
			String command = request.keySet().toArray()[0].toString();
			List<Object> list = request.get(Commands.valueOf(command));
			Method carServiceMethod = null;
			Object returnedParam = null;
			if (list == null) {
				carServiceMethod = carServise.getClass().getMethod(command);
				returnedParam = carServiceMethod.invoke(carServise);
			} else {
				carServiceMethod = carServise.getClass().getMethod(command, getParamsClass(list));
				returnedParam = carServiceMethod.invoke(carServise, list.toArray());
			}
			responce.put("value", returnedParam);
		}
		return responce;

	}

	public static Class[] getParamsClass(List<Object> list) {
		List<Class> result = new ArrayList<>();
		for (Object obj : list) {
			if(obj.getClass().getSimpleName().equals("ArrayList")) {
				result.add(List.class);
			}else {
				result.add(obj.getClass());
			}
		}
		return result.toArray(new Class[list.size()]);
	}
}
