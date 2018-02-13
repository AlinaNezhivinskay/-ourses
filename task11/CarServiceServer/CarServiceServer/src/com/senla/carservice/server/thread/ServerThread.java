package com.senla.carservice.server.thread;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.Socket;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.senla.carservice.server.handler.Handler;
import com.senla.carservice.util.carservicecommands.Commands;

public class ServerThread extends Thread {
	private static Logger log = Logger.getLogger(ServerThread.class.getName());
	private ObjectOutputStream os;
	private ObjectInputStream is;

	public ServerThread(Socket s) throws IOException {
		os = new ObjectOutputStream(s.getOutputStream());
		is = new ObjectInputStream(s.getInputStream());
	}

	@SuppressWarnings("unchecked")
	@Override
	public void run() {
		Object request;
		try {
			while ((request = is.readObject()) != null) {
				os.writeObject(Handler.handleRequest((Map<Commands, List<Object>>) request));
				os.flush();
			}
		} catch (IOException e) {
			log.error("IOException", e);
		} catch (ClassNotFoundException e) {
			log.error("ClassNotFoundException", e);
		} catch (NoSuchMethodException e) {
			log.error("NoSuchMethodException", e);
		} catch (IllegalAccessException e) {
			log.error("IllegalAccessException", e);
		} catch (InvocationTargetException e) {
			log.error("InvocationTargetException", e);
		} finally {
			disconnect();
		}
	}

	public void disconnect() {
		try {
			os.close();
			is.close();
		} catch (IOException e) {
			log.error("IOException", e);
		} finally {
			this.interrupt();
		}
	}
}
