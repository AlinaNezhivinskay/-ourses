package com.senla.carservice.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.senla.carservice.api.facade.ICarService;
import com.senla.carservice.model.beans.Master;
import com.senla.carservice.util.utils.JsonConverter;
import com.senla.carservice.view.facade.CarService;

public class MastersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ICarService carService = CarService.getInstance();
		resp.setContentType("application/json;charset=UTF-8");
		ServletOutputStream out = resp.getOutputStream();
		List<Master> masters = carService.getMasters();
		String output = JsonConverter.convertToJson(masters);
		out.print(output);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
