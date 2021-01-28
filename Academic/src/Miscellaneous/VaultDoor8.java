package Miscellaneous;
// These pesky special agents keep reverse engineering our source code and then
// breaking into our secret vaults. THIS will teach those sneaky sneaks a
// lesson.
//
// -Minion #0891
import java.util.*;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;

class VaultDoor8 {
	
	public static void main(String args[]) {
		/*
		Scanner b = new Scanner(System.in);
		System.out.print("Enter vault password: ");
		String c = b.next();
		String f = c.substring(8,c.length()-1);
		VaultDoor8 a = new VaultDoor8();
		if (a.checkPassword(f)) {
			System.out.println("Access granted.");
		}
		else {
			System.out.println("Access denied!");
		}
		*/
		
		char c = 'c';
		c = switchBits(c,5,7);
		System.out.println(c);
	}
	
	public char[] scramble(String password) {
		/* Scramble a password by transposing pairs of bits. */
		char[] a = password.toCharArray();
		for (int b=0; b<a.length; b++) {
			char c = a[b];
			// abcdefgh
			c = switchBits(c,1,2);
			// abcdegfh
			c = switchBits(c,0,3); 
			// abcdhgfe
			/* c = switchBits(c,14,3); c = switchBits(c, 2, 0); */
			c = switchBits(c,5,6);
			// acbdhgfe
			c = switchBits(c,4,7);
			// dcbahgfe
			c = switchBits(c,0,1);
			// dcbahgef
			/* d = switchBits(d, 4, 5); e = switchBits(e, 5, 6); */
			c = switchBits(c,3,4);
			// dcbhagef
			c = switchBits(c,2,5);
			// dcghabef
			c = switchBits(c,6,7);
			// cdghabef
			a[b] = c;
		}
		return a;
	}
	
	/* Move the bit in position p1 to position p2, and move the bit that was in position p2 to position p1. Precondition: p1 < p2 */
	public static char switchBits(char c, int p1, int p2) {
		char mask1 = (char)(1 << p1);
		char mask2 = (char)(1 << p2);
		/* char mask3 = (char)(1<<p1<<p2); mask1++; mask1--; */
		char bit1 = (char)(c & mask1);
		char bit2 = (char)(c & mask2);
		/* System.out.println("bit1 " + Integer.toBinaryString(bit1)); System.out.println("bit2 " + Integer.toBinaryString(bit2)); */
		char rest = (char)(c & ~(mask1 | mask2));
		char shift = (char)(p2 - p1);
		char result = (char)((bit1<<shift) | (bit2>>shift) | rest);
		return result;
		/*
		cdghabef
		// s0m3_m0r3_b1t_sh1fTiNg_60bea5ea1
		11110100 01110011 s
		11000000 00110000 0
		10010111 01101101 m
		11110000 00110011 3
		01110111 01011111 _
		10010111 01101101 m
		11000000 00110000 0
		11100100 01110010 r
		11110000 00110011 3
		01110111 01011111 _
		10100100 01100010 b
		11010000 00110001 1
		11000101 01110100 t
		01110111 01011111 _
		11110100 01110011 s
		10000110 01101000 h
		11010000 00110001 1
		10100101 01100110 f
		01000101 01010100 T
		10010110 01101001 i
		00100111 01001110 N
		10110101 01100111 g
		01110111 01011111 _
		11100001 00110110 6
		11000000 00110000 0
		10100100 01100010 b
		10010101 01100101 e
		10010100 01100001 a
		11010001 00110101 5
		10010101 01100101 e
		10010100 01100001 a
		11010000 00110001 1
		*/
		
	}
	
	public boolean checkPassword(String password) {
		char[] scrambled = scramble(password);
		char[] expected = { 0xF4, 0xC0, 0x97, 0xF0, 
				0x77, 0x97, 0xC0, 0xE4, 0xF0, 0x77, 0xA4, 
				0xD0, 0xC5, 0x77, 0xF4, 0x86, 0xD0, 0xA5, 
				0x45, 0x96, 0x27, 0xB5, 0x77, 0xE1, 0xC0, 
				0xA4, 0x95, 0x94, 0xD1, 0x95, 0x94, 0xD0 };
		return Arrays.equals(scrambled, expected);
	}
}
