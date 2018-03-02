package com.senla.carservice.ui;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import org.apache.log4j.Logger;

import com.senla.carservice.ui.handler.ClientHandler;
import com.senla.carservice.ui.menu.controller.MenuController;

public class Test {
	private static Logger log = Logger.getLogger(Test.class.getName());

	public static void main(String[] args) {
		try {
			Socket socket = new Socket(InetAddress.getLocalHost(), 8072);
			ClientHandler.initClentHandler(socket);
			MenuController mc = new MenuController();
			mc.run();
			socket.close();
		} catch (UnknownHostException e) {
			log.error("UnknownHostException", e);
		} catch (IOException e) {
			log.error("IOException", e);
		}

	}

}
