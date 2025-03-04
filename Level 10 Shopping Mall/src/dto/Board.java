package dto;

public class Board {
	private static int num;
	private int boradNum;
	private String title;
	private String id;
	private String date;
	private String contents;
	private int hits;
	
	public Board(String title, String id, String date, String contents, int hits) {
		this.boradNum = ++num;
		this.title = title;
		this.id = id;
		this.date = date;
		this.contents = contents;
		this.hits = hits;
	}

	@Override
	public String toString() {
		return "[" + boradNum + "] [" + title + "] [" + id + "] [" + date + "] [" + contents + "] [" + hits + "]\n";
	}

	public static int getNum() {
		return num;
	}
	public static void setNum(int num) {
		Board.num = num;
	}
	public int getBoradNum() {
		return boradNum;
	}
	public void setBoradNum(int boradNum) {
		this.boradNum = boradNum;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public int getHits() {
		return hits;
	}
	public void setHits(int hits) {
		this.hits = hits;
	}
}