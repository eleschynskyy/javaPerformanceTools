package com.elearn.tests;

import java.math.BigInteger;
import java.security.SecureRandom;

public class AuxiliaryStuff {

	public static void main(String[] args) {
		SecureRandom random = new SecureRandom();
		System.out.println();
		new BigInteger(130, random).toString(32);
	}

}
