package com.senla.carservice.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import com.senla.carservice.token.TokenManager;
import com.senla.carservice.token.TokenRepository;

public class TokenAuthenticationFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String token = request.getParameter("token");
		HttpServletResponse resp = (HttpServletResponse) response;
		try {
			if (token != null && TokenRepository.getInstance().isTokenExist(token)
					&& TokenManager.validateToken(token)) {
				chain.doFilter(request, response);
			} else {
				resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			}
		} catch (Exception e) {
			resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}

	}

	@Override
	public void destroy() {

	}
}
