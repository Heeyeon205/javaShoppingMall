package menu_mall;

import controller.MallController;
import dao.MemberDAO;
import mall.MenuCommand;
import util.Util;

public class MallJoin implements MenuCommand {
	private MallController cont;
	private Util util;

	@Override
	public void init() {
		cont = MallController.getInstance();
		util = Util.getInstance();
	}

	@Override
	public boolean update() {
		System.out.println("=====[ 회원가입 ]=====");
		MemberDAO dao = MemberDAO.getInstance();
		while(true){
		String id = util.getString("아이디: ");
		if(dao.isDupId(id)) continue;
		String pw = util.getString("비밀번호: ");
		String name = util.getString("이름: ");
		dao.addMember(id , pw , name);
		cont.setNext("MallMain");
		return false;
		}
	}

}