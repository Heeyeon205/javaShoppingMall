package menu_member;

import controller.MallController;
import dao.MemberDAO;
import mall.MenuCommand;
import util.Util;

public class MemberQuit implements MenuCommand{
	private MallController cont;
	private MemberDAO mDAO;
		
	@Override
	public void init() {
		cont = MallController.getInstance();
		mDAO = MemberDAO.getInstance();
		System.out.println("=====[회원 삭제]=====");
		mDAO.getMemberList();
	}

	@Override
	public boolean update() {
		String id = Util.getString("삭제할 id 입력: ");
		if(id.equals("admin")) {
			System.out.println("관리자 계정은 삭제할 수 없습니다.");
			return false;
		}
		if(!mDAO.hasId(id)) return false;
		mDAO.deleteMember(id);
		System.out.println(id + " 회원 삭제 완료");
		cont.setNext("AdminMember");
		return false;
	}

}
