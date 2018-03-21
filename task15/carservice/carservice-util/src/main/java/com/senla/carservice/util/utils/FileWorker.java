package com.senla.carservice.util.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileWorker {
	public static List<String> readFile(String filePath) throws FileNotFoundException, IOException {
		List<String> strings = new ArrayList<String>();
		String str = null;
		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			while ((str = reader.readLine()) != null) {
				strings.add(str);
			}
		}
		return strings;
	}

	public static boolean writeFile(String filePath, List<String> strings) throws IOException {
		File file = new File(filePath);
		if (!file.exists()) {
			file.createNewFile();
		}
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, false))) {
			for (String str : strings) {
				writer.write(str+"\n");
			}
			return true;
		}
	}
}
