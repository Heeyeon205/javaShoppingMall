package dao;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import dto.Item;

public class ItemDAO {
	private static ItemDAO instance;
	private ArrayList<Item> iList = new ArrayList<>();
	private HashMap<Integer, ArrayList<Item>> iMap = new HashMap<>();
	private HashMap<Integer, String> categoryMap = new HashMap<>();

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

	public int printCategorylist() {
		System.out.println("=====[카테고리 목록]=====");
		if (iList.size() == 0) {
			System.out.println("[!] 상품이 없습니다.");
			return 0;
		} else {
			ArrayList<String> categoryName = iList.stream().map(category -> category.getCategoryName()).distinct()
					.collect(Collectors.toCollection(ArrayList::new));
			for (int i = 0; i < categoryName.size(); i++) {
				categoryMap.put(i + 1, categoryName.get(i));
			}
			for (Map.Entry<Integer, String> c : categoryMap.entrySet()) {
				System.out.printf("[%d] : %s\n", c.getKey(), c.getValue());
			}
			System.out.println("[0] : 뒤로가기");
			return categoryMap.size();
		}
	}

	public void printCategory(int key) {
		int no = 1;
		for (int i = 0; i < iList.size(); i++) {
			if (iList.get(i).getCategoryName().equals(categoryMap.get(key))) {
				System.out.printf("[%d] %s %d원\n", no++, iList.get(i).getItemName(), iList.get(i).getPrice());
			}
		}
	}

	public boolean isValidItemName(int key, String itemName) {
		for (int i = 0; i < iList.size(); i++) {
			if (iList.get(i).getCategoryName().equals(categoryMap.get(key))) {
				if (iList.get(i).getItemName().equals(itemName)) {
					return true;
				}
			}
		}
		System.out.println("[!] 해당 카테고리에 없는 상품입니다.");
		return false;
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
				System.out.println("[!] 카테고리 이름을 상품 이름으로 사용할 수 없습니다.");
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

	public String getData() {
		StringBuilder data = new StringBuilder();
		for (Item i : iList) {
			data.append(i.getItemNum()).append("/");
			data.append(i.getCategoryName()).append("/");
			data.append(i.getItemName()).append("/");
			data.append(i.getPrice()).append("\n");
		}
		return data.toString();
	}

	public int getItemNumToItemName(String itemName) {
		for (int i = 0; i < iList.size(); i++) {
			if (iList.get(i).getItemName().equals(itemName)) {
				return iList.get(i).getItemNum();
			}
		}
		return -1;
	}

	public void printMyCartList(ArrayList<Integer> userItemNoList) {
		if (userItemNoList.size() == 0) {
			System.out.println("[!] 아직 구매한 상품이 없습니다.");
		} else {
			int no = 1;
			int cnt = 0;
			for (int i = 0; i < iList.size(); i++) {
				cnt = 0;
				for (int j = 0; j < userItemNoList.size(); j++) {
					if (iList.get(i).getItemNum() == userItemNoList.get(j)) {
						cnt++;
					}
				}
				if (cnt > 0) {
					System.out.printf("[%d] %s %d원 %d개 (총 %d원)\n---------------------\n", no++,
							iList.get(i).getItemName(), iList.get(i).getPrice(), cnt, iList.get(i).getPrice() * cnt);
				}
			}
		}
	}
}
