package menu_admin;

import controller.MallController;
import dao.CartDAO;
import dao.ItemDAO;
import mall.MenuCommand;
import util.Util;

public class AdminItem implements MenuCommand{
	private MallController cont;
	private ItemDAO iDao;
	private CartDAO cDao;
	private Util util;

	@Override
	public void init() {
		cont = MallController.getInstance();
		util = Util.getInstance();
		iDao = ItemDAO.getInstance();
		cDao = CartDAO.getInstance();
		System.out.println("=====[상품 관리]=====");
		System.out.println("[1] 상품 추가\n[2] 상품 삭제\n[3] 판매 목록\n[4] 뒤로가기\n[0] 프로그램 종료");
	}

	@Override
	public boolean update() {
		int sel = util.getValue("메뉴 입력: ", 0, 5);
		if(sel == 1) {
			iDao.printItemList();
			System.out.println("=====[상품 추가]=====");
			String item = util.getString("상품: ");
			if(iDao.isItemNameDup(item)) return false;
			String category = util.getString("품목: ");
			if(!iDao.isValidCategoryName(category)) return false;
			int price = util.getValue("가격: ", 100, Integer.MAX_VALUE);
			iDao.insertItem(category, item, price);
			System.out.println("[" + item + "] 상품 추가 완료!");
		}else if(sel == 2) {
			System.out.println("=====[상품 삭제]=====");
			iDao.printItemList();
			int delNo = util.getValue("아이템 번호 입력: ", 1, iDao.getItemListSize()+1);
			if(iDao.isdeleteItem(delNo));
		}else if(sel == 3) {
			cDao.printCartList(iDao);
		}else if(sel == 4) {
			cont.setNext("AdminMain");
		}else if(sel == 0) {
			System.out.println("프로그램 종료");
			cont.setNext(null);
		}
		return false;
	}
}
