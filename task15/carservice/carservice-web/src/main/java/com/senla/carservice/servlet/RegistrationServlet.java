package com.senla.carservice.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.senla.carservice.api.services.IUserService;
import com.senla.carservice.controller.services.UserService;
import com.senla.carservice.model.beans.User;
import com.senla.carservice.util.utils.JsonConverter;

public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		JSONObject jsonObject = JsonConverter.convertFromJson(req.getReader());
		String login = (String) jsonObject.get("login");
		String password = (String) jsonObject.get("password");
		IUserService userService = new UserService();
		userService.addUser(new User(login, password));
	}

}
