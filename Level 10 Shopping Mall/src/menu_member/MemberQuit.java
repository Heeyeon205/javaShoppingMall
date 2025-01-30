package menu_member;

import controller.MallController;
import dao.MemberDAO;
import mall.MenuCommand;
import util.Util;

public class MemberQuit implements MenuCommand{
	private MallController cont;
	private Util util;
	private MemberDAO mDAO;
		
	@Override
	public void init() {
		cont = MallController.getInstance();
		util = Util.getInstance();
		mDAO = MemberDAO.getInstance();
	}

	@Override
	public boolean update() {
		if(cont.getLoginId().equals("admin")) {
			System.out.println("=====[회원 삭제]=====");
			mDAO.getMemberList();
			String id = util.getString("삭제할 id 입력: ");
			if(id.equals("admin")) {
				System.out.println("관리자 계정은 삭제할 수 없습니다.");
				return false;
			}
			if(!mDAO.hasId(id)) return false;
			mDAO.deleteMember(id);
			System.out.println(id + " 회원 삭제 완료");
			cont.setNext("AdminMember");
			return false;
		}else {
			System.out.println("=====[회원 탈퇴]=====");
			System.out.printf("%s님 회원 탈퇴 시 구매 내역이 사라집니다.\n정말 탈퇴하시겠습니까?\n", cont.getLoginId());
			int sel = util.getValue("[1] 예\n[2] 아니오\n[0] 프로그램 종료", 0, 3);
			if(sel == 1) {
				mDAO.deleteMember(cont.getLoginId());
				System.out.println("이용해 주셔서 감사합니다.\n회원 탈퇴 완료.");
				cont.setNext("MallMain");
			}else if(sel == 2) {
				cont.setNext("MemberMain");
			}else if(sel == 0) {
				System.out.println("[프로그램 종료]");
				cont.setNext(null);
			}
		}
		return false;
	}

}
