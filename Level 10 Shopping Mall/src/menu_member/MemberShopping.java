package menu_member;

import controller.MallController;
import mall.MenuCommand;

public class MemberShopping implements MenuCommand{
	private MallController cont;

	@Override
	public void init() {
		cont = MallController.getInstance();
		
		
	}

	@Override
	public boolean update() {
		
		return false;
	}

}
