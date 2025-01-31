package dto;

public class Cart {
	private static int num;
	private int cartNum;
	private String id;
	private int itemNum;
	private int itemCnt;

	public Cart(String id, int itemNum, int itemCnt) {
		this.cartNum = ++num;
		this.id = id;
		this.itemNum = itemNum;
		this.itemCnt = itemCnt;
	}

	@Override
	public String toString() {
		return "[" + cartNum + "] [" + id + "] [" + itemNum + "] [" + itemCnt + "개]\n";
	}

	public static int getNum() {
		return num;
	}

	public static void setNum(int num) {
		Cart.num = num;
	}

	public int getCartNum() {
		return cartNum;
	}

	public void setCartNum(int cartNum) {
		this.cartNum = cartNum;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getItemNum() {
		return itemNum;
	}

	public void setItemNum(int itemNum) {
		this.itemNum = itemNum;
	}

	public int getItemCnt() {
		return itemCnt;
	}

	public void setItemCnt(int itemCnt) {
		this.itemCnt = itemCnt;
	}
}