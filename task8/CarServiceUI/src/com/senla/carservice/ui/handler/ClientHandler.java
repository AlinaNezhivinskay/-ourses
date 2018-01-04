package com.senla.carservice.ui.handler;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.senla.carservice.ui.Test;
import com.senla.carservice.util.carservicecommands.Commands;

public class ClientHandler {
	private static Logger log = Logger.getLogger(Test.class.getName());
	static private ObjectOutputStream os;
	static private ObjectInputStream is;

	static public void initClentHandler(Socket s) throws IOException {
		os = new ObjectOutputStream(s.getOutputStream());
		is = new ObjectInputStream(s.getInputStream());
	}

	@SuppressWarnings("unchecked")
	static public Map<String, Object> handle(Map<Commands, List<Object>> request) {
		try {
			os.writeObject(request);
			os.flush();
			return (Map<String, Object>) is.readObject();
		} catch (IOException e) {
			log.error("IOException", e);
		} catch (ClassNotFoundException e) {
			log.error("ClassNotFoundException", e);
		}
		return null;
	}

}
