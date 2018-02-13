package com.senla.carservice.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.log4j.Logger;

import com.senla.carservice.server.thread.ServerThread;

public class Server {
	private static Logger log = Logger.getLogger(Server.class.getName());

	public static void main(String[] args) {
		try (ServerSocket serv = new ServerSocket(8071)) {
			while (true) {
				Socket socket = serv.accept();
				ServerThread server = new ServerThread(socket);
				server.start();
			}
		} catch (IOException e) {
			log.error("IOException", e);
		}

	}

}
