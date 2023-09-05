package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Controller {
	public static String FileReader(String path, String branch) throws FileNotFoundException {
		try {
			File myfile = new File(path);
			Scanner scanner = new Scanner(myfile);
			if (myfile.exists()) {
				while (scanner.hasNext()) {
					String line = scanner.nextLine();
					String[] attributes = line.split(",");
					if (attributes.length < 3 || attributes.length > 3) {
						return "Error With the File";
					} else {
						if (attributes[1].trim().equalsIgnoreCase(branch)) {
							Student student = new Student(Integer.parseInt(attributes[0]), attributes[1].trim(),
									Double.parseDouble(attributes[2]));
							TawjihiDS.add(student);
						}
					}
				}
			} else {
				return "Error: Reading File!";
			}
			return "File reading Successful!";
		} catch (Exception e) {
			return "Error : Reading File ";
		}

	}

	public static String FileReaderAll(String path) throws FileNotFoundException {
		try {
			File myfile = new File(path);
			Scanner scanner = new Scanner(myfile);
			if (myfile.exists()) {
				while (scanner.hasNext()) {
					String line = scanner.nextLine();
					String[] attributes = line.split(",");
					if (attributes.length < 3 || attributes.length > 3) {
						return "Error With the File";
					} else {
						Student student = new Student(Integer.parseInt(attributes[0]), attributes[1].trim(),
								Double.parseDouble(attributes[2]));
						TawjihiDS.add(student);
					}

				}
			} else {
				return "Error: Reading File!";
			}
			return "File reading Successful!";

		} catch (Exception e) {
			return "Error : Reading File ";
		}

	}
}
