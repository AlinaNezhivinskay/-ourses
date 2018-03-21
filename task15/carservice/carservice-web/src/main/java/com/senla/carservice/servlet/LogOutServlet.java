package com.senla.carservice.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.senla.carservice.token.TokenRepository;

public class LogOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String token = req.getParameter("token");
		TokenRepository.getInstance().removeToken(token);
		resp.setStatus(HttpServletResponse.SC_OK);
	}

}
