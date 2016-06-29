package com.elearn.tests;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ResourceUsageDataProcessor {

	private static String APP = "xpe";
	private static String TEST_FOLDER = "03-Jun-2016";
	private static String TEST_NUMBER = "1";
	private static String PATH = "D:/Xyleme/performance/products/xpe/cloud/";
//	private static String PATH = "D:/Xyleme/performance/products/xpe/cloud/";
	private static String PATHNAME = PATH + TEST_FOLDER + "/TEST_" + TEST_NUMBER + "/logs/" + "monitor.txt";
	private static String PARSED_FILE = PATH + TEST_FOLDER + "/TEST_" + TEST_NUMBER + "/logs/" + "resource_usage_parsed.txt";
	private static String LOAD_PATTERN = "top\\s+-\\s+(\\d\\d:\\d\\d:\\d\\d).*?load average:\\s+(\\d+.\\d+),\\s+(\\d+.\\d+),\\s+(\\d+.\\d+)";
	private static String TASKS_PATTERN = "Tasks:\\s+(\\d+)\\s+total,\\s+(\\d+)\\s+running,\\s+(\\d+)\\s+sleeping,\\s+(\\d+)\\s+stopped,\\s+(\\d+)\\s+zombie";
	private static String CPU_PATTERN = "Cpu\\(s\\):\\s*(\\d+.\\d+)%us,\\s*(\\d+.\\d+)%sy,\\s*(\\d+.\\d+)%ni,\\s*(\\d+.\\d+)%id,\\s*(\\d+.\\d+)%wa,\\s*(\\d+.\\d+)%hi,\\s*(\\d+.\\d+)%si,\\s*(\\d+.\\d+)%st";
	private static String MEMORY_PATTERN = "Mem:\\s+(\\d+)k\\s+total,\\s+(\\d+)k\\s+used,\\s+(\\d+)k\\s+free,\\s+(\\d+)k\\s+buffers";
	private static String SWAP_PATTERN = "Swap:\\s+(\\d+)k\\s+total,\\s+(\\d+)k\\s+used,\\s+(\\d+)k\\s+free,\\s+(\\d+)k\\s+cached";

	public static void main(String[] args) {
		BufferedReader br;
		FileWriter writer;
		Pattern loadPattern = Pattern.compile(LOAD_PATTERN);
		Pattern taskPattern = Pattern.compile(TASKS_PATTERN);
		Pattern cpuPattern = Pattern.compile(CPU_PATTERN);
		Pattern memoryPattern = Pattern.compile(MEMORY_PATTERN);
		Pattern swapPattern = Pattern.compile(SWAP_PATTERN);
		Matcher mLoad;
		Matcher mTask;
		Matcher mCpu;
		Matcher mMem;
		Matcher mSwap;
		try {
			br = new BufferedReader(new FileReader(PATHNAME));
			File loadOutputFile = new File(PARSED_FILE);
			if (loadOutputFile.exists()) {
				loadOutputFile.delete();
			}
			writer = new FileWriter(loadOutputFile);
			String line;
			while ((line = br.readLine()) != null) {
				mLoad = loadPattern.matcher(line);
				mTask = taskPattern.matcher(line);
				mCpu = cpuPattern.matcher(line);
				mMem = memoryPattern.matcher(line);
				mSwap = swapPattern.matcher(line);
				if (mLoad.matches()) {
					writer.write(mLoad.group(1).replace('.', ',') + "|" + mLoad.group(2).replace('.', ',') + "|"
							+ mLoad.group(3).replace('.', ',') + "|" + mLoad.group(4).replace('.', ',') + "|");
				}
				if (mTask.matches()) {
					writer.write(mTask.group(1).replace('.', ',') + "|" + mTask.group(2).replace('.', ',') + "|"
							+ mTask.group(3).replace('.', ',') + "|" + mTask.group(4).replace('.', ',') + "|" + mTask.group(5).replace('.', ',') + "|");
				}
				if (mCpu.matches()) {
					writer.write(mCpu.group(1).replace('.', ',') + "|" + mCpu.group(2).replace('.', ',') + "|"
							+ mCpu.group(3).replace('.', ',') + "|" + mCpu.group(4).replace('.', ',') + "|"
							+ mCpu.group(5).replace('.', ',') + "|" + mCpu.group(6).replace('.', ',') + "|"
							+ mCpu.group(7).replace('.', ',') + "|" + mCpu.group(8).replace('.', ',') + "|");
				}
				if (mMem.matches()) {
					writer.write(mMem.group(1).replace('.', ',') + "|" + mMem.group(2).replace('.', ',') + "|"
							+ mMem.group(3).replace('.', ',') + "|" + mMem.group(4).replace('.', ',') + "|");
				}
				if (mSwap.matches()) {
					writer.write(mSwap.group(1).replace('.', ',') + "|" + mSwap.group(2).replace('.', ',') + "|"
							+ mSwap.group(3).replace('.', ',') + "|" + mSwap.group(4).replace('.', ',') + "\n");
				}
			}
			br.close();
			writer.close();
		} catch (FileNotFoundException e) {
			throw new RuntimeException("File " + PATHNAME + " was not found.\n"
					+ e.getStackTrace().toString());
		} catch (IOException e) {
			throw new RuntimeException("Could not read " + PATHNAME
					+ " file.\n" + e.getStackTrace().toString());
		}
		System.out.println("Done");
	}

}
