package util;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Util {
	static Scanner sc = new Scanner(System.in);

	public static int getValue(String msg, int min, int max) {
		while (true) {
			System.out.println(msg);
			try {
				int num = sc.nextInt();
				sc.nextLine();
				if (num < min || num >= max) {
					System.out.println("입력 범위 오류입니다.");
					continue;
				}
				return num;
			} catch (InputMismatchException e) {
				System.out.println("숫자만 입력 가능합니다.");
				sc.nextLine();
				continue;
			}
		}
	}

	public static String getString(String msg) {
		System.out.println(msg);
		String str = sc.nextLine();
		return str;
	}

}
