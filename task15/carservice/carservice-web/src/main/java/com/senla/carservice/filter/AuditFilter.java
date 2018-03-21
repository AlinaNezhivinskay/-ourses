package com.senla.carservice.filter;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.senla.carservice.api.services.IAuditService;
import com.senla.carservice.controller.services.AuditService;
import com.senla.carservice.model.beans.Audit;
import com.senla.carservice.model.beans.User;
import com.senla.carservice.token.TokenRepository;

public class AuditFilter implements Filter {

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String token = request.getParameter("token");
		User user = TokenRepository.getInstance().getUserByToken(token);
		Audit audit = new Audit(user, new Date(), "Resource request");
		IAuditService auditService = new AuditService();
		auditService.addAudit(audit);
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {

	}

}
