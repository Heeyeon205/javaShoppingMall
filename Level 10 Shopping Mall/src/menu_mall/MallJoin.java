package menu_mall;

import controller.MallController;
import dao.MemberDAO;
import mall.MenuCommand;
import util.Util;

public class MallJoin implements MenuCommand {
	private MallController cont;

	@Override
	public void init() {
		cont = MallController.getInstance();
	}

	@Override
	public boolean update() {
		System.out.println("=====[ 회원가입 ]=====");
		MemberDAO dao = MemberDAO.getInstance();
		while(true){
		String id = Util.getString("아이디: ");
		if(dao.isDupId(id)) continue;
		String pw = Util.getString("비밀번호: ");
		String name = Util.getString("이름: ");
		dao.addMember(id , pw , name);
		cont.setNext("MallMain");
		return false;
		}
	}

}