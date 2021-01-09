package Testers;

import java.util.*;
import java.util.Base64;


public class randomTesting {
			
	public static void main(String[] args) {
		int a = 0x90000000 + 0x90000000 + 0x90000000;
		int p = 0xAB12CE34;
		int u = (p | 0xFF000000) & 0xFFFFFF00;
		System.out.println(String.format("%x", a));
		System.out.println(a);
		
	}

}
