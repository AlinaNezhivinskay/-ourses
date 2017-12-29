package com.senla.carservice.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.senla.carservice.server.thread.ServerThread;
import com.senla.carservice.view.facade.CarService;

public class Server {

	public static void main(String[] args) {
		CarService.getInstance().loadData();
		try (ServerSocket serv = new ServerSocket(8071)) {
			while (true) {
				Socket socket = serv.accept();
				ServerThread server = new ServerThread(socket);
				server.start();
			}
		} catch (IOException e) {

		}

	}

}
