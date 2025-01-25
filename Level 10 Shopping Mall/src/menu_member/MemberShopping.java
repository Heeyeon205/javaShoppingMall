package menu_member;

import controller.MallController;
import dao.CartDAO;
import dao.ItemDAO;
import dto.Item;
import mall.MenuCommand;

public class MemberShopping implements MenuCommand{
	private MallController cont;
	private ItemDAO iDao;

	@Override
	public void init() {
		cont = MallController.getInstance();
		iDao = ItemDAO.getInstance();
		System.out.println("=====[상품 구매]=====");
		for(int i = 0; i < iDao.getItemListSize(); i++) {
			System.out.printf("[%d] [%s]\n", i+1,  iDao.getCategoryName(i));
		}
		
	}

	@Override
	public boolean update() {
		
		return false;
	}

}
