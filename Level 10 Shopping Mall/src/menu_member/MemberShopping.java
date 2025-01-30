package menu_member;

import controller.MallController;
import dao.CartDAO;
import dao.ItemDAO;
import dto.Item;
import mall.MenuCommand;
import util.Util;

public class MemberShopping implements MenuCommand{
	private MallController cont;
	private Util util;
	private ItemDAO iDao;
	private CartDAO cDao;

	@Override
	public void init() {
		cont = MallController.getInstance();
		util = Util.getInstance();
		iDao = ItemDAO.getInstance();
		cDao = CartDAO.getInstance();
		System.out.println("=====[상품 구매]=====");
	}

	@Override
	public boolean update() {
		int categorySize = iDao.printCategorylist();
		int sel = util.getValue("카테고리 선택: ", 0, categorySize+1);
		if(sel == 0) {
			cont.setNext("MemberMain");
			return false;
		}
		iDao.printCategory(sel);
		String itemName = util.getString("아이템 이름: ");
		if(!iDao.isValidItemName(sel, itemName)) return false;
		int cnt = util.getValue("구매 수량(1~100): ", 1, 101);
		int itemNum = iDao.getItemNumToItemName(itemName);
		cDao.insertCart(cont.getLoginId(), itemNum, cnt);
		System.out.printf("%s님 %s %d개 구매완료.\n", cont.getLoginId(), itemName, cnt);
		return false;
	}


}
