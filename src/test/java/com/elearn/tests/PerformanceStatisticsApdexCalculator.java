package com.elearn.tests;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import au.com.bytecode.opencsv.CSVReader;

public class PerformanceStatisticsApdexCalculator {

	private static String TEST_FOLDER = "19-Jan-2016";
	private static String TEST_NUMBER = "1";
//	private static String PATH = "D:/Xyleme/performance/products/msis/testing/";
	private static String PATH = "D:/Xyleme/performance/products/sps/testing/";
//	private static String PATH = "D:/Xyleme/performance/products/xpe/cloud/";
//	private static String PATH = "D:/Xyleme/performance/products/lcms/testing/";
	private static String FOLDER = PATH + TEST_FOLDER + "/TEST_" + TEST_NUMBER + "/csv/";
	private static String TTIME_FILE = PATH + TEST_FOLDER + "/TEST_" + TEST_NUMBER + "/csv/_apdex_t_time.txt";
	private static String TTIME_FILE_EXT = PATH + TEST_FOLDER + "/TEST_" + TEST_NUMBER + "/csv/_apdex_t_time_ext.txt";
	private static String ERRSTAT_FILE = PATH + TEST_FOLDER + "/TEST_" + TEST_NUMBER + "/csv/_errorStatistic.txt";
	private static String OUTPUT_PATH = PATH + TEST_FOLDER + "/TEST_" + TEST_NUMBER + "/csv/";
	private static String SATISFIED = "satisfied";
	private static String TOLERANTED = "toleranted";
	private static String FRUSTRATED = "frustrated";

	public static void main(String[] args) {
		DecimalFormat df = new DecimalFormat("###.#");
		Map<String, Integer> errors = new HashMap<String, Integer>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(ERRSTAT_FILE));
			String line;
			String[] dataParts;
			line = br.readLine();
			while((line = br.readLine()) != null){
				dataParts = line.split("\\|");
				errors.put(dataParts[0], new Integer(dataParts[1]));
			}
			br.close();
    	} catch (FileNotFoundException e) {
    		throw new RuntimeException("File " + ERRSTAT_FILE + " was not found.\n" + e.getStackTrace().toString());
    	} catch (IOException e) {
    		throw new RuntimeException("Could not read " + ERRSTAT_FILE + " file.\n" + e.getStackTrace().toString());
    	}

		//update pool with requests missing in baseline
		try {
			Set<String> allApdexFilesList = new HashSet<String>();
			File folder = new File(FOLDER + "apdex");
			File[] listOfFiles = folder.listFiles();
			for(File file: listOfFiles){
				allApdexFilesList.add(file.getName());
			}
			File file = new File(TTIME_FILE);
			BufferedReader reader = new BufferedReader(new FileReader(file));
			File outputFile = new File(OUTPUT_PATH + "_apdex_t_time_ext.txt");
			if (outputFile.exists()) {
				outputFile.delete();
			}
			FileWriter writer = new FileWriter(outputFile);
			String line = reader.readLine();
			writer.write(line + "\n");
			String[] keys = line.split(",");
			if (keys != null) {
				String[] dataParts;
				while ((line = reader.readLine()) != null) {
					dataParts = line.split(",");
					String key = dataParts[0];
					if(allApdexFilesList.contains(key)){
						allApdexFilesList.remove(key);
					}
					writer.write(line + "\n");
				}
				Iterator<String> allApdexFilesListIterator = allApdexFilesList.iterator();
				while(allApdexFilesListIterator.hasNext()){
					writer.write(allApdexFilesListIterator.next() + "," + "-1,-1\n");
				}
			}
			writer.close();
			reader.close();
		} catch (IOException e) {
    		throw new RuntimeException("Could not read " + TTIME_FILE + " file.\n" + e.getStackTrace().toString());
    	}

		try {
			Map<String, String> row = new HashMap<String, String>();
			File file = new File(TTIME_FILE_EXT);
			CSVReader reader = new CSVReader(new FileReader(file));
			String[] keys = reader.readNext();
			if (keys != null) {
				File outputFile = new File(OUTPUT_PATH + "_apdex.txt");
				if (outputFile.exists()) {
					outputFile.delete();
				}
				FileWriter apdexWriter = new FileWriter(outputFile);
				String[] dataParts;
				apdexWriter.write("request|apd_baseline|apd_ttime|apd_satisfied_n|apd_toleranted_n|apd_frustrated_n|apd_total_n|apd_satisfiedP|apd_tolerantedP|apd_frustratedP|apd_apdex\n");
				while ((dataParts = reader.readNext()) != null) {
					for (int i = 0; i < keys.length; i++) {
						row.put(keys[i], dataParts[i]);
					}
					Map<String, String> result = calculateApdex(row.get("file"), row.get("time"), errors.get(row.get("file").substring(1, row.get("file").length() - 4)));
					String baseline = row.get("baseline");
					String time = row.get("time");
					String apdex = result.get("apdex");
					if(baseline.equals("-1")){
						baseline = "N/A";
						time = "N/A";
					} else {
						apdex = String.format("%.2f", new Float(apdex));
						time = String.format("%.2f", new Float(time));
					}
					apdexWriter.write(row.get("file").substring(1, row.get("file").length() - 4) + "|" +
					baseline + "|" +
					time + "|" +
					result.get(SATISFIED) + "|" +
					result.get(TOLERANTED) + "|" +
					result.get(FRUSTRATED) + "|" +
					result.get("total") + "|" +
					result.get("satisfiedP").replaceAll("\\.", ",") + "|" +
					result.get("tolerantedP").replaceAll("\\.", ",") + "|" +
					result.get("frustratedP").replaceAll("\\.", ",") + "|" +
					apdex + "\n");
				}
				apdexWriter.close();
			}
			reader.close();
		} catch (FileNotFoundException e) {
			throw new RuntimeException("File " + TTIME_FILE + " was not found.\n"
					+ e.getStackTrace().toString());
		} catch (IOException e) {
			throw new RuntimeException("Could not read " + TTIME_FILE
					+ " file.\n" + e.getStackTrace().toString());
		}
		System.out.println("Done");
	}

	private static Map<String, String> calculateApdex(String fileName, String time, Integer error) {
//		System.out.println(fileName + error);
		Map<String, String> result = new HashMap<String, String>();
		if(!time.equals("-1")){
			File file = new File(FOLDER + "apdex/" + fileName);
			try{
				BufferedReader br = new BufferedReader(new FileReader(file));
				String line;
				int satisfied = 0;
				int toleranted = 0;
				int frustrated = 0;
				while ((line = br.readLine()) != null) {
					switch (getResponseType(line, new Float(time))){
					case 1: satisfied++;
						break;
					case 2: toleranted++;
						break;
					case 3: frustrated++;
						break;
					}
				}
				br.close();
				frustrated = frustrated + error;
				int total = satisfied + toleranted + frustrated;
				result.put(SATISFIED, new Integer(satisfied).toString());
				result.put(TOLERANTED, new Integer(toleranted).toString());
				result.put(FRUSTRATED, new Integer(frustrated).toString());
				result.put("satisfiedP", new Float((float)satisfied / total * 100).toString());
				result.put("tolerantedP", new Float((float)toleranted / total * 100).toString());
				result.put("frustratedP", new Float((float)frustrated / total * 100).toString());
				result.put("total", new Integer(total).toString());
				result.put("apdex", new Float((satisfied + (float) toleranted / 2) / total).toString());
			}
			catch (FileNotFoundException e) {
				throw new RuntimeException("File " + fileName + " was not found.\n"
						+ e.getStackTrace().toString());
			} catch (IOException e) {
				throw new RuntimeException("Could not read " + fileName
						+ " file.\n" + e.getStackTrace().toString());
			}

		} else {
			result.put(SATISFIED, "N/A");
			result.put(TOLERANTED, "N/A");
			result.put(FRUSTRATED, "N/A");
			result.put("satisfiedP", "N/A");
			result.put("tolerantedP", "N/A");
			result.put("frustratedP", "N/A");
			result.put("total", "N/A");
			result.put("apdex", "N/A");
		}
		return result;
	}

	private static int getResponseType(String line, float time){
		int rt = Integer.parseInt(line);
		if(rt <= time){
			return 1;
		}
		if((rt > time) && (rt <= 4 * time)){
			return 2;
		} else {
			return 3;
		}
	}

}
