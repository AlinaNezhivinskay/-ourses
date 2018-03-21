package com.senla.carservice.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.senla.carservice.api.services.IAuditService;
import com.senla.carservice.api.services.IUserService;
import com.senla.carservice.controller.services.AuditService;
import com.senla.carservice.controller.services.UserService;
import com.senla.carservice.model.beans.Audit;
import com.senla.carservice.model.beans.User;
import com.senla.carservice.token.TokenManager;
import com.senla.carservice.token.TokenRepository;
import com.senla.carservice.util.utils.JsonConverter;

public class LogInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		JSONObject jsonObject = JsonConverter.convertFromJson(req.getReader());

		String login = (String) jsonObject.get("login");
		String password = (String) jsonObject.get("password");

		IUserService userService = new UserService();
		User user = userService.getUserByLogin(login);
		try {
			if (user != null) {
				String token;
				token = TokenManager.generateToken(login, password);
				TokenRepository.getInstance().putToken(token, user);

				Audit audit = new Audit(user, new Date(), "LogIn");
				IAuditService auditService = new AuditService();
				auditService.addAudit(audit);

				ServletOutputStream out = resp.getOutputStream();
				out.print(token);
			} else {
				resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			}
		} catch (Exception e) {
			resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}

}
