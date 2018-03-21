package com.senla.carservice.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.senla.carservice.api.facade.ICarService;
import com.senla.carservice.model.beans.Order;
import com.senla.carservice.util.utils.JsonConverter;
import com.senla.carservice.view.facade.CarService;

public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final ICarService carService = CarService.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		long id = Long.parseLong(req.getParameter("id"));
		Order order = carService.getOrderById(id);
		ServletOutputStream out = resp.getOutputStream();
		String output = JsonConverter.convertToJson(order);
		out.print(output);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		JSONObject jsonObject = JsonConverter.convertFromJson(req.getReader());
		long id = (long) jsonObject.get("id");
		Order order = carService.getOrderById(id);
		order.setExecutionDate((Date) jsonObject.get("executionDate"));
		order.setPlannedStartDate((Date) jsonObject.get("plannedStartDate"));
		order.setSubmissionDate((Date) jsonObject.get("submissionDate"));
		order.setPrice((double) jsonObject.get("submissionDate"));
		order.setGarage(carService.getGarageById((long) jsonObject.get("garage")));
		order.setMaster(carService.getMasterById((long) jsonObject.get("master")));
		carService.updateOrder(order);
		resp.setStatus(HttpServletResponse.SC_OK);
	}

}
