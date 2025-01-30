package util;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Util {
	private static Scanner sc = new Scanner(System.in);
	private static Util instance;
	

	private Util() {}
	public static Util getInstance() {
		if(instance == null) {
			instance = new Util();
		}
		return instance;
	}

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
		while (true) {
			System.out.println(msg);
			String str = sc.nextLine().trim();
			if (str.isEmpty()) {
				System.out.println("공백은 입력할 수 없습니다.");
				continue;
			}
			if(str.contains(" ")) {
				System.out.println("문자 사이 공백은 입력할 수 없습니다.");
				continue;
			}
			return str;
		}
	}
}
