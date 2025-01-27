package menu_admin;

import controller.MallController;
import dao.FileDAO;
import mall.MenuCommand;
import util.Util;

public class AdminMain implements MenuCommand {
	private MallController cont;

	@Override
	public void init() {
		cont = MallController.getInstance();
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
			int response = Util.getValue("로그아웃 하시겠습니까?\n[1] 예 [2] 아니오 ", 1, 3);
			if(response == 1) {
				System.out.println("안녕히가십시오 관리자님.");
				cont.setLoginId(null);
				cont.setNext("MallMain");
			}
		} else if (sel == 5) {
			int response = Util.getValue("현재까지 수정한 데이터를 저장 하시겠습니까?\n[1] 예 [2] 아니오 ", 1, 3);
			if(response == 1) {
				FileDAO.getInstance().saveAllFiles();
			}
		} else if (sel == 0) {
			System.out.println("프로그램 종료");
			cont.setNext(null);
		}
		return false;
	}

}
