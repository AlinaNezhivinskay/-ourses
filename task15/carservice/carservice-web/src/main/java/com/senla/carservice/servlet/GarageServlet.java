package com.senla.carservice.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.senla.carservice.api.facade.ICarService;
import com.senla.carservice.model.beans.Garage;
import com.senla.carservice.util.utils.JsonConverter;
import com.senla.carservice.view.facade.CarService;

public class GarageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final ICarService carService = CarService.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		long id = Long.parseLong(req.getParameter("id"));
		Garage garage = carService.getGarageById(id);

		ServletOutputStream out = resp.getOutputStream();
		String output = JsonConverter.convertToJson(garage);

		out.print(output);
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		JSONObject jsonObject = JsonConverter.convertFromJson(req.getReader());
		long id = (long) jsonObject.get("id");
		Garage garage = carService.getGarageById(id);
		carService.removeGarage(garage);
		resp.setStatus(HttpServletResponse.SC_OK);
	}

}
