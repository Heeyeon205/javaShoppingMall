package menu_admin;

import controller.MallController;
import dao.MemberDAO;
import mall.MenuCommand;
import util.Util;

public class AdminMember implements MenuCommand{
	private MallController cont;
	private MemberDAO mDAO;

	@Override
	public void init() {
		cont = MallController.getInstance();
		mDAO = MemberDAO.getInstance();
		System.out.println("=====[회원 관리]=====");
		System.out.println("[1] 회원 목록\n[2] 회원 삭제\n[3] 뒤로가기\n[0] 프로그램 종료");
	}

	@Override
	public boolean update() {
		int sel = Util.getValue("메뉴 입력: ", 0, 4);
		if(sel == 1) {
			mDAO.getMemberList();
		}else if(sel == 2) {
			cont.setNext("MemberQuit");
		}else if(sel == 3) {
			cont.setNext("AdminMain");
		}else if(sel == 0) {
			System.out.println("프로그램 종료");
			cont.setNext(null);
		} 
		return false;
	}
}
