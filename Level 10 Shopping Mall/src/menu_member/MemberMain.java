package menu_member;

import controller.MallController;
import mall.MenuCommand;
import util.Util;

public class MemberMain implements MenuCommand{
	private MallController cont;

	@Override
	public void init() {
		cont = MallController.getInstance();
		System.out.printf("[%s님 로그인 상태]\n", cont.getLoginId());
		System.out.println("[1] 상품 구매\n[2] 상품 구매 내역\n[3] 게시판\n[4] 내 정보\n[5] 회원 탈퇴\n[6] 로그아웃\n[0] 프로그램 종료");
	}

	@Override
	public boolean update() {
		int sel = Util.getValue("메뉴 입력: ", 0, 7);
		if(sel == 1) {
			cont.setNext("MemberShopping");
		}else if(sel == 2) {
			cont.setNext("MemberShopping");
		}else if(sel == 3) {
			
		}else if(sel == 4) {
			
		}else if(sel == 5) {
			
		}else if(sel == 6) {
			
		}else if(sel == 0) {
			System.out.println("프로그램 종료");
		} 
		return false;
	}

}
