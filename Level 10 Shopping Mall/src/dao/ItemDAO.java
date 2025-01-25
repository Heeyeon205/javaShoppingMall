package dao;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import dto.Item;

public class ItemDAO {
	private static ItemDAO instance;
	private ArrayList<Item> iList = new ArrayList<>();

	private HashMap<Integer, ArrayList<Item>> iMap = new HashMap<>();

	private ItemDAO() {
	}

	public static ItemDAO getInstance() {
		if (instance == null) {
			instance = new ItemDAO();
		}
		return instance;
	}
	
	public ArrayList<Item> getiList() {
		return iList;
	}

	public int getItemListSize() {
		return iList.size();
	}

	public void insertItem(String categoryName, String itemName, int price) {
		iList.add(new Item(Item.getNum(), categoryName, itemName, price));
		iMap.put(Item.getNum(), iList);
	}

	public ArrayList<Item> getItemMap(int itemNum) {
		return iMap.get(itemNum);
	}

	public void printItemList() {
		System.out.println("=====[상품 목록]=====");
		if (iList.size() == 0) {
			System.out.println("[!] 상품이 없습니다.");
		} else {
			iList.stream().sorted(Comparator.comparing(Item::getCategoryName).thenComparing(Item::getItemName))
					.forEach(System.out::print);
		}
	}

	public boolean isItemNameDup(String itemName) {
		for (int i = 0; i < iList.size(); i++) {
			if (iList.get(i).getItemName().equals(itemName)) {
				System.out.println("[!] 이미 판매중인 상품입니다.");
				return true;
			}
			if (iList.get(i).getCategoryName().equals(itemName)) {
				System.out.println("[!] 상품 이름을 카테고리 이름으로 사용할 수 없습니다.");
				return true;
			}
		}
		return false;
	}

	public boolean isValidCategoryName(String categoryName) {
		for (int i = 0; i < iList.size(); i++) {
			if (iList.get(i).getItemName().equals(categoryName)) {
				System.out.println("[!] 상품 이름을 카테고리 이름으로 사용할 수 없습니다.");
				return false;
			}
		}
		return true;
	}

	public boolean isdeleteItem(int delNo) {
		for (int i = 0; i < iList.size(); i++) {
			if (iList.get(i).getItemNum() == delNo) {
				System.out.println(iList.get(i) + "삭제 완료.");
				iList.remove(i);
				return true;
			}
		}
		System.out.println("[!] 해당 번호는 없는 아이템 번호입니다.");
		return false;
	}

	public int getItemNum(int i) {
		return iList.get(i).getItemNum();
	}

	public String getCategoryName(int i) {
			return iList.get(i).getCategoryName();
	}
}
