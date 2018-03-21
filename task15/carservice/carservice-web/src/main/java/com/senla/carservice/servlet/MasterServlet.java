package com.senla.carservice.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.senla.carservice.api.facade.ICarService;
import com.senla.carservice.model.beans.Master;
import com.senla.carservice.util.utils.JsonConverter;
import com.senla.carservice.view.facade.CarService;

public class MasterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final ICarService carService = CarService.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		long id = Long.parseLong(req.getParameter("id"));
		Master master = carService.getMasterById(id);
		ServletOutputStream out = resp.getOutputStream();
		String output = JsonConverter.convertToJson(master);
		out.print(output);
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		JSONObject jsonObject = JsonConverter.convertFromJson(req.getReader());
		String name = (String) jsonObject.get("name");
		carService.addMaster(new Master(name));
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		JSONObject jsonObject = JsonConverter.convertFromJson(req.getReader());
		long id = (long) jsonObject.get("id");
		Master master = carService.getMasterById(id);
		carService.removeMaster(master);
	}

}
