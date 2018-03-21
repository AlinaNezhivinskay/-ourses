package com.senla.carservice.token;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.DatatypeConverter;

import com.senla.carservice.api.services.IUserService;
import com.senla.carservice.controller.services.UserService;
import com.senla.carservice.model.beans.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenManager {
	private static final String KEY = "alina0211";

	public static String generateToken(String login, String password) throws Exception {
		if (login == null || password == null)
			return null;
		IUserService userService = new UserService();
		User user = userService.getUserByLogin(login);
		Map<String, Object> tokenData = new HashMap<>();
		if (password.equals(user.getPassword())) {
			tokenData.put("clientType", "user");
			tokenData.put("userID", user.getId());
			tokenData.put("username", login);
			tokenData.put("token_create_date", new Date().getTime());
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.HOUR, 1);
			tokenData.put("token_expiration_date", calendar.getTime());

			JwtBuilder jwtBuilder = Jwts.builder();

			jwtBuilder.setExpiration(calendar.getTime());

			jwtBuilder.setClaims(tokenData);
			String token = jwtBuilder.signWith(SignatureAlgorithm.HS512, KEY).compact();

			return token;
		} else

		{
			throw new Exception("Authentication error");
		}
	}

	public static boolean validateToken(String token) throws Exception {
		Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(KEY)).parseClaimsJws(token)
				.getBody();
		Date expirationDate = new Date((Long) claims.get("token_expiration_date"));
		Date now = new Date();
		if (expirationDate.before(now)) {
			return false;
		}
		return true;
	}
}
