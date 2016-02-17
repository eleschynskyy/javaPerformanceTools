package com.elearn.tests;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;

import au.com.bytecode.opencsv.CSVReader;

public class ProcessJtl {

	private static int PARTS = 10;
	private static String FILENAME = "bcpFinal-1448364628-complete";
	private static String PATH = "D:/Xyleme/performance/products/bcp/tests/24-Nov-2015/TEST_1/";
	private static String GRAPH = "compositeResults";

	private static String PATH_CSV = PATH + "csv/";
	private static String PATH_GRAPH = PATH + "graph/";
	private static String GRAPH_FILE = PATH_GRAPH + GRAPH + ".jmx";
	private static String FILE_EXT = "jtl";
	private static String HEADLINE = "timeStamp,elapsed,label,responseCode,responseMessage,threadName,dataType,success,bytes,grpThreads,allThreads,Latency,Hostname\n";
	private static int COLUMNS = 13;
	private static String CONVERTED = "_PART_";
	private static String PATHNAME = PATH_CSV + FILENAME + "." + FILE_EXT;

	public static void main(String[] args) throws IOException, ParseException {
		File file = new File(PATHNAME);
		File fileConverted;
		FileWriter writer;
		String pathnameConverted;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
//			CSVReader reader = new CSVReader(new FileReader(file));
			String[] dataParts;
			String line;
			int totalRows = 0;
			while ((line = reader.readLine()) != null) {
				totalRows++;
			}
			reader.close();
			int [] idx = doDivision(totalRows);
			System.out.println(totalRows);
//			reader = new CSVReader(new FileReader(file));
			reader = new BufferedReader(new FileReader(file));
			int row = 1;
			int skipped = 0;
			for (int p = 1; p <= PARTS; p++) {
				FileUtils.copyFile(new File(GRAPH_FILE), new File(PATH_GRAPH + GRAPH + "_" + p + ".jmx"));
				pathnameConverted = PATH_CSV + FILENAME + CONVERTED + p + "." + FILE_EXT;
				fileConverted = new File(pathnameConverted);
				if (fileConverted.exists()) {
					fileConverted.delete();
				}
				writer = new FileWriter(fileConverted);
				writer.write(HEADLINE);
				int upperBound = idx[p - 1];
				if (p == PARTS) {
					upperBound = totalRows;
				}
				for (int r = row; r <= upperBound; r++) {
					line = reader.readLine();
					dataParts = line.split(",");
//					dataParts = reader.readNext();
					if (dataParts.length == COLUMNS) {

						System.out.println("processing row #" + r + " out of " + totalRows + ": " + getProgress(r, totalRows) + "% done");
						for (int i = 0; i < dataParts.length - 1; i++) {
							writer.write(dataParts[i] + ",");
						}
						writer.write(dataParts[dataParts.length - 1] + "\n");
					} else {
						System.out.println("skipping row #" + r);
						skipped++;
					}
				}
				writer.close();
				row = idx[p - 1] + 1;
			}
			reader.close();
			System.out.println(">>> SUMMARY >>>");
			if (skipped > 0) {
				System.out.println("Skipped rows: " + skipped);
			}
			System.out.println("Total rows processed: " + totalRows);
		} catch (FileNotFoundException e) {
			throw new RuntimeException("File " + PATHNAME + " was not found.\n"
					+ e.getStackTrace().toString());
		} catch (IOException e) {
			throw new RuntimeException("Could not read " + PATHNAME
					+ " file.\n" + e.getStackTrace().toString());
		}
	}

	private static int[] doDivision(int n) {
		int[] arr = new int[n - 1];
		int ind = n/PARTS;
		arr[0] = ind;
		for (int j = 2; j < PARTS; j++) {
			arr[j - 1] = j * ind;
		}
		return arr;
	}

	private static String getProgress(int x, int y) {
		DecimalFormat df = new DecimalFormat("###.##");
		return df.format((float)x / (float)y * 100);
	}

	public void tmp() throws IOException, ParseException {
		String filename = "bravais3-perf-simple-work-results";
		String path = "D:/Xyleme/performance/cloud/stepping_one_instance/csv/";
		String pathname = path + filename + ".csv";
		File file = new File(pathname);
		String pathnameConverted = path + filename + "_converted.csv";
		File fileConverted = new File(pathnameConverted);
		if (fileConverted.exists()) {
			fileConverted.delete();
		}
		FileWriter writer = new FileWriter(fileConverted);
		try {
			CSVReader reader = new CSVReader(new FileReader(file));
			String[] keys = reader.readNext();
			if (keys != null) {
				writer.write("timeStamp,elapsed,label,responseCode,responseMessage,threadName,dataType,success,bytes,grpThreads,allThreads,Latency,Hostname\n");
				String[] dataParts;
				int r = 1;
				while ((dataParts = reader.readNext()) != null) {
					System.out.println("processing row #" + r);
					String timestamp = dataParts[0];
					Date d = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
							.parse(timestamp);
					String unixTimestamp = "" + d.getTime();
					writer.write(unixTimestamp + ",");
					for (int i = 1; i < keys.length - 1; i++) {
						writer.write(dataParts[i] + ",");
					}
					writer.write(dataParts[keys.length - 1] + "\n");
					r++;
				}
			}
			reader.close();
			writer.close();
		} catch (FileNotFoundException e) {
			throw new RuntimeException("File " + pathname + " was not found.\n"
					+ e.getStackTrace().toString());
		} catch (IOException e) {
			throw new RuntimeException("Could not read " + pathname
					+ " file.\n" + e.getStackTrace().toString());
		}
	}

}
