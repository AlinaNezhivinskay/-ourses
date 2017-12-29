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
			Method carServiceMethod = carServise.getClass().getMethod(command);
			List<Object> list=request.get(Commands.valueOf(command));
			Object returnedParam=null;
			if (list == null) {
				returnedParam=carServiceMethod.invoke(carServise);
			} else {
				returnedParam=carServiceMethod.invoke(carServise, list.toArray());
			}
			responce.put("value", returnedParam);
		}
		return responce;

	}
}
