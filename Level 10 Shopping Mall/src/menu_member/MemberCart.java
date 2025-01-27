package menu_member;

import controller.MallController;
import dao.CartDAO;
import dao.ItemDAO;
import mall.MenuCommand;
import util.Util;

public class MemberCart implements MenuCommand{
	private MallController cont;
	private CartDAO cDao;
	private ItemDAO iDao;

	@Override
	public void init() {
		cont = MallController.getInstance();
		cDao = CartDAO.getInstance();
		iDao = ItemDAO.getInstance();
		System.out.println("=====[구매 내역]=====");
	}

	@Override
	public boolean update() {
		iDao.printMyCartList(cDao.getUserItemNoList(cont.getLoginId()));
		System.out.println("[1] 상품 구매\n[2] 뒤로가기\n[0] 프로그램 종료");
		int sel = Util.getValue("메뉴 선택: ", 0, 3);
		switch(sel) {
		case 1 : cont.setNext("MemberShopping"); break;
		case 2 : cont.setNext("MemberMain"); break;
		case 0 : System.out.println("[프로그램 종료]"); cont.setNext(null); break;
		}
		return false;
	}
	
}
