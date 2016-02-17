package com.elearn.tests;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;

public class InstanceComparator {
	
	private static String PATHNAME = "D:/Xyleme/performance/products/xpe/cloud/23-Oct-2014/TEST_1/csv/cloudXPE-1414164226-complete.jtl";

	public static void main(String[] args) throws IOException, ParseException {
		List<Integer> responseTimeLst = new ArrayList<Integer>();
		int rows = 0;
		File file = new File(PATHNAME);
		int minTime = 0;
		int maxTime = minTime;
		try {
			CSVReader reader = new CSVReader(new FileReader(file));
			String[] keys = reader.readNext();
			if (keys != null) {
				String[] dataParts;
				for (int i = 1; i <= 4; i++) {
					dataParts = reader.readNext();
				}
				while ((dataParts = reader.readNext()) != null) {
					rows++;
					responseTimeLst.add(Integer.valueOf(dataParts[1]));
				}
			}
			reader.close();
		} catch (FileNotFoundException e) {
			throw new RuntimeException("File " + PATHNAME + " was not found.\n"
					+ e.getStackTrace().toString());
		} catch (IOException e) {
			throw new RuntimeException("Could not read " + PATHNAME
					+ " file.\n" + e.getStackTrace().toString());
		}
		Iterator<Integer> it = responseTimeLst.iterator();
		if (it.hasNext()){
			minTime = it.next();
			maxTime = minTime;
		}
		while(it.hasNext()){
			int current = it.next();
			if(current < minTime) {
				minTime = current; 
			}
			if(current > maxTime) {
				maxTime = current;
			}
		}
		float sum = 0;
		DecimalFormat df = new DecimalFormat("###.##");
		System.out.println("Rows: " + rows);
		System.out.println("Min: " + df.format((double) minTime) + "ms");
		System.out.println("Max: " + df.format((double) maxTime) + "ms");
		responseTimeLst.remove((Integer)minTime);
		responseTimeLst.remove((Integer)maxTime);
		it = responseTimeLst.iterator();
		
//		while(it.hasNext()){
//			System.out.println("RR: " + it.next());
//		}
//		it = responseTimeLst.iterator();
		
		while(it.hasNext()){
			int responseTime = it.next();
			sum = sum + responseTime;
		}
		float avg = sum / rows;
		System.out.println("Avg: " + df.format((double) avg) + "ms");
		it = responseTimeLst.iterator();
		double sumDiff = 0;
		while(it.hasNext()){
			int responseTime = it.next();
			float diff = (responseTime - avg) * (responseTime - avg);
			sumDiff = sumDiff + diff;
		}
		double sigma = Math.sqrt(sumDiff / rows);
//		System.out.println("AMD: " + df.format((double) sigma));
		double deviation = Math.sqrt(sigma * sigma * rows / (rows - 1));
		System.out.println("SD: " + df.format((double) deviation) + "ms");
	}

}

