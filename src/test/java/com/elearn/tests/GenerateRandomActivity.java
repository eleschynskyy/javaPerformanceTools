package com.elearn.tests;

import java.util.Random;

public class GenerateRandomActivity {
	
	private static int TIME_IN_SECONDS = 3600;
	private static int PERIOD_IN_SECONDS = 60;
	private static int NUMBER_OF_USERS = 100;

	public static void main(String[] args) {
		int times = TIME_IN_SECONDS / PERIOD_IN_SECONDS;
		Random r = new Random();
		for (int i = 1; i <= times; i++) {
			System.out.println(i + " " + r.nextInt(NUMBER_OF_USERS + 1));
		}
	}

}
