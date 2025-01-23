package controller;

import java.util.HashMap;
import java.util.Map;

import dao.FileDAO;
import mall.MenuCommand;
import menu_admin.AdminBoard;
import menu_admin.AdminItem;
import menu_admin.AdminMain;
import menu_admin.AdminMember;
import menu_mall.MallJoin;
import menu_mall.MallLogin;
import menu_mall.MallMain;
import menu_member.MemberBoard;
import menu_member.MemberCart;
import menu_member.MemberInfo;
import menu_member.MemberMain;
import menu_member.MemberQuit;
import menu_member.MemberShopping;

public class MallController {
	private String loginId;
	private String next;
	private MenuCommand menuCom;
	public Map<String, MenuCommand> mapCont;
	static private MallController instance = new MallController();
	
	private MallController() {}

	static public MallController getInstance() {
		if(instance == null) {
			instance = new MallController();
		}
		return instance;
	}
	public String getNext() {
		return next;
	}
	public void setNext(String next) {
		this.next = next;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public void init() {
		FileDAO.getInstance().loadAllFiles();
		mapCont = new HashMap<>();
		mapCont.put("MallMain", new MallMain());
		mapCont.put("MallJoin", new MallJoin());
		mapCont.put("MallLogin", new MallLogin());
		mapCont.put("AdminBoard", new AdminBoard());
		mapCont.put("AdminItem", new AdminItem());
		mapCont.put("AdminMain", new AdminMain());
		mapCont.put("AdminMember", new AdminMember());
		mapCont.put("MemberBoard", new MemberBoard());
		mapCont.put("MemberCart", new MemberCart());
		mapCont.put("MemberInfo", new MemberInfo());
		mapCont.put("MemberMain", new MemberMain());
		mapCont.put("MemberShopping", new MemberShopping());
		mapCont.put("MemberQuit", new MemberQuit());

		menuCom = mapCont.get("MallMain");
		menuCom.init();
		update();
	}

	public void update() {
		while (true) {
			if (!menuCom.update()) {
				if (next != null) {
					menuCom = mapCont.get(next);
					menuCom.init();
				} else {
					return;
				}
			}
		}
	}


}