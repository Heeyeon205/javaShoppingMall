package dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import dto.Cart;
import dto.Item;

public class CartDAO {
	private static CartDAO instance;
	private ArrayList<Cart> cList = new ArrayList<>();

	private CartDAO() {
	}

	public static CartDAO getInstance() {
		if (instance == null) {
			instance = new CartDAO();
		}
		return instance;
	}
	
	public ArrayList<Cart> getcList() {
		return cList;
	}

	public void insertCart(String id, int itemNum, int itemCnt) {
		cList.add(new Cart(id, itemNum, itemCnt));
	}

	public void printCartList(ItemDAO iDao) {
		System.out.println("=====[판매 목록]=====");
		if (cList.size() == 0) {
			System.out.println("[!] 판매 내역이 없습니다.");
		} else {
			Collections.sort(cList, Comparator.comparing(Cart::getItemCnt).reversed());
			for (int i = 0; i < cList.size(); i++) {
				ArrayList<Item> iList = iDao.getItemMap(cList.get(i).getItemNum());
				if (iList != null) {
					System.out.printf("[%d] [%s] [%s] [%d원] [%d개]\n", cList.get(i).getItemNum(),
							iList.get(i).getCategoryName(), iList.get(i).getItemName(), 
							iList.get(i).getPrice(), cList.get(i).getItemCnt());
				}

			}
		}
	}

	public String getData() {
			StringBuilder data = new StringBuilder();
			for(Cart c : cList) {
				data.append(c.getCartNum()).append("/");
				data.append(c.getId()).append("/");
				data.append(c.getItemNum()).append("/");
				data.append(c.getItemCnt()).append("\n");
			}
			return data.toString();
	}

	public ArrayList<Integer> getUserItemNoList(String loginId) {
		ArrayList<Integer> userNoList = new ArrayList<>();
		for(int i = 0; i < cList.size(); i++) {
			if(cList.get(i).getId().equals(loginId)) {
				userNoList.add(cList.get(i).getItemNum());
			}
		}
		return userNoList;
	}

}
