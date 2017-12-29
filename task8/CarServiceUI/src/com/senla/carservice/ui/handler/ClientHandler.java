package com.senla.carservice.ui.handler;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Map;

import com.senla.carservice.util.carservicecommands.Commands;

public class ClientHandler {
	static private ObjectOutputStream os;
	static private ObjectInputStream is;

	static public void initClentHandler(Socket s) throws IOException {
		os = new ObjectOutputStream(s.getOutputStream());
		is = new ObjectInputStream(s.getInputStream());
	}

	@SuppressWarnings("unchecked")
	static public Map<String,Object> handle(Map<Commands, List<Object>> request) {
		try {
			os.writeObject(request);
			os.flush();
			return (Map<String, Object>) is.readObject();
		} catch (IOException e1) {

		} catch (ClassNotFoundException e) {
		}
		return null;
	}

}
