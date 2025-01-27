package menu_member;

import controller.MallController;
import dao.MemberDAO;
import mall.MenuCommand;
import util.Util;

public class MemberInfo implements MenuCommand{
	private MallController cont;
	private MemberDAO mDao;

	@Override
	public void init() {
		cont = MallController.getInstance();
		mDao = MemberDAO.getInstance();
		System.out.println("=====[내 정보]=====");
		mDao.printUserInfo(cont.getLoginId());
		System.out.println("[1] 비밀번호 변경\n[2] 뒤로가기\n[0] 프로그램 종료");
	}

	@Override
	public boolean update() {
		int sel = Util.getValue("메뉴 선택: ", 0, 3);
		if(sel == 1) {
			System.out.println("=====[비밀번호 변경]=====");
			String newPw = Util.getString("변경할 비밀번호: ");
			int idx = mDao.isValidPw(cont.getLoginId(), newPw);
			if(idx == -1) return false;
			mDao.changePw(idx, newPw);
			System.out.printf("%s님 비밀번호 변경 완료.\n", cont.getLoginId());
		}else if (sel == 2) {
			cont.setNext("MemberMain");
		}else if(sel == 0) {
			System.out.println("프로그램 종료");
			cont.setNext(null);
		}
		return false;
	}
	

}
