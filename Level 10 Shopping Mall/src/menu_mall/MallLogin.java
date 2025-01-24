package menu_mall;

import controller.MallController;
import dao.MemberDAO;
import mall.MenuCommand;
import util.Util;

public class MallLogin implements MenuCommand {
	private MallController cont;

	@Override
	public void init() {
		System.out.println("=====[ 로그인 ]=====");
		cont = MallController.getInstance();
	}

	@Override
	public boolean update() {
		MemberDAO dao = MemberDAO.getInstance();
		String id = Util.getString("아이디: ");
		String pw = Util.getString("비밀번호: ");
		if (dao.isValidMember(id, pw) != null) {
			if (id.equals("admin")) {
				cont.setLoginId("admin");
				cont.setNext("AdminMain");
			} else {
				cont.setLoginId(id);
				cont.setNext("MemberMain");
			}
		} else {
			System.err.println("아이디 혹은 비밀번호를 확인해주세요");
			cont.setNext("MallMain");
		}
		return false;
	}

}