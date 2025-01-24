package dao;

import java.util.ArrayList;
import java.util.Comparator;

import dto.Cart;
import dto.Item;

public class CartDAO {
	private static CartDAO instance;
	private ArrayList<Cart> cList = new ArrayList<>();
	
	private CartDAO() {}
	
	public static CartDAO getInstance() {
		if(instance == null) {
			instance = new CartDAO();
		}
		return instance;
	}

	public void printCartList(ItemDAO iDao) {
		System.out.println("=====[판매 목록]=====");
		if(cList.size() == 0) {
			System.out.println("[!] 판매 내역이 없습니다.");
		}else {
			cList.stream().sorted((a,b) -> a.getItemCnt() - b.getItemCnt()).forEach(System.out::print);
		}
	}

	public void insertCart(String id, int itemNum, int itemCnt) {
		cList.add(new Cart(id, itemNum, itemCnt));
	}
}
