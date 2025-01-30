package menu_mall;

import controller.MallController;
import mall.MenuCommand;
import util.Util;

public class MallMain implements MenuCommand {
	private MallController cont;
	private Util util;

	@Override
	public void init() {
		cont = MallController.getInstance();
		util = Util.getInstance();
		System.out.println("=====[ 쇼핑몰 ]=====");
		System.out.println("[1] 회원가입\n[2] 로그인\n[0] 종료");
		System.out.println("=====================");
	}

	@Override
	public boolean update() {
		int sel = util.getValue("메뉴 입력: ", 0, 3);
		if (sel == 0) {
			System.out.println("[ 프로그램 종료 ]");
			cont.setNext(null);
		} else if (sel == 1) {
			cont.setNext("MallJoin");

		} else if (sel == 2) {
			cont.setNext("MallLogin");
		}
		return false;
	}

}