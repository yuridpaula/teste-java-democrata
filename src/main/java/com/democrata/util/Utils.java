package com.democrata.util;

import java.util.Random;

public class Utils {
	public static int getRandom(int minimo, int maximo) {
		Random random = new Random();
		return random.nextInt((maximo - minimo) + 1) + minimo;
	}
}
