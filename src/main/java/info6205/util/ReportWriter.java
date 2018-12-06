package info6205.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import info6205.ga.City;

public class ReportWriter {

	public static void generateRandom(String input, int size, int scale) throws IOException {
		File file = new File("input/" + input);

		if (file.exists()) {
			file.delete();
		}

		FileWriter writer = new FileWriter("input/" + input, true);

		try {
			for (int i = 0; i < size; i++) {

				int x = (int) (scale * Math.random());
				int y = (int) (scale * Math.random());

				writer.append(x + "," + y);
				writer.append(System.lineSeparator());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				writer.flush();
				writer.close();
			}
		}
	}

	public static void makeReport(Report report) throws IOException {
		File directory = new File("output");
		if(!directory.exists()) {
			directory.mkdir();
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yy HH-mm-ss");
		Date date = new Date();
		String fileName = "output/Output_" + dateFormat.format(date) + ".log";
		FileWriter writer = new FileWriter(fileName, true);

		try {
			writer.append(report.getResult());
			writer.append(System.lineSeparator());
			
			for (Record r : report.getReport()) {
				writer.append(r.getGeneration() + "," + r.getAvgFit() + "," + r.getBestFit());
				writer.append(System.lineSeparator());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				writer.flush();
				writer.close();
			}
		}
	}
}
