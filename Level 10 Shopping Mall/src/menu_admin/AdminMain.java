package menu_admin;

import controller.MallController;
import dao.MemberDAO;
import mall.MenuCommand;
import util.Util;

public class AdminMain implements MenuCommand {
	private MallController cont;
	private MemberDAO mDAO;

	@Override
	public void init() {
		cont = MallController.getInstance();
		mDAO = MemberDAO.getInstance();
		System.out.println("=====[admin Menu]=====");
		System.out.println("[1] 회원 관리\n[2] 상품 관리\n[3] 게시판 관리\n[4] 로그아웃\n[5] 파일 저장\n[0] 프로그램 종료");
	}

	@Override
	public boolean update() {
		int sel = Util.getValue("메뉴 입력: ", 0, 6);
		if (sel == 1) {
			cont.setNext("AdminMember");
		} else if (sel == 2) {
			cont.setNext("AdminItem");
		} else if (sel == 3) {
			cont.setNext("AdminBoard");
		} else if (sel == 4) {

		} else if (sel == 5) {

		} else if (sel == 0) {
			System.out.println("프로그램 종료");
			cont.setNext(null);
		}
		return false;
	}

}
