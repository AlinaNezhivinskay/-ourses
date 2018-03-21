package com.senla.carservice.api.services;

import java.util.List;

import com.senla.carservice.model.beans.User;

public interface IUserService {
	List<User> getUsers();

	User getUserById(Long id);

	void addUser(User user);

	boolean removeUser(User user);

	boolean updateUser(User user, String login, String password);

	User getUserByLogin(String login);

}
