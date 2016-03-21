package com.index.prajwal;

public class Encoding {

	/*public static void main(String[] args) {

		int n = 2; // To calculate gamma code
		int x = 3; // To calculate delta code
		String gamma = calculateGamma(n);
		String delta = calculateDelta(x);
		System.out.println("Gamma:" + gamma);
		System.out.println("Delta:" + delta);
	}*/

	static byte[] calculateGammaByte(int n){
		
		String s=calculateGamma(n);
		return s.getBytes();
	}
	
	static byte[] calculateDeltaByte(int n){
		String s=calculateDelta(n);
		return s.getBytes();
	}
	
	static String calculateGamma(int n) {
		if (n == 1) {
			System.out.println("0");
			System.exit(0);
		}
		String s1 = calculateOffset(n);
		String s2 = calculateUnary(s1.length()).toString();
		return s2 + s1;

	}

	
	static String calculateDelta(int n) {
		if (n == 1) {
			System.out.println("0");
			System.exit(0);
		}
		String bin = Integer.toBinaryString(n);
		String gamma = calculateGamma(bin.length());
		bin = bin.substring(1, bin.length());
		// System.out.println(bin);
		// System.out.println(gamma);
		return gamma + bin;
	}

	private static StringBuffer calculateUnary(int n) {
		StringBuffer s = new StringBuffer();
		for (int i = 0; i < n; i++) {
			s.append('1');
		}
		s.append('0');
		return s;
	}

	private static String calculateOffset(int n) {

		if (n == 1) {
			return "0";
		}
		String bin = Integer.toBinaryString(n);
		bin = bin.substring(1, bin.length());
		return bin;

	}

}
