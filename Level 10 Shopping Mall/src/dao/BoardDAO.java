package dao;

import java.util.ArrayList;

import dto.Board;

public class BoardDAO {
	private static BoardDAO instance;
	private ArrayList<Board> bList = new ArrayList<>();
	
	private BoardDAO() {}
	
	public static BoardDAO getInstance() {
		if(instance == null) {
			instance = new BoardDAO();
		}
		return instance;
	}

	public void insertBoard(String title, String id, String date, String contents, int hits) {
		bList.add(new Board(title, id, date, contents, hits));
	}
	
//	final int totalPost = bList.size();
//	int curPage;
//	int totalPage;
//	int startPage;
//	int endPage;
//	final int showPage = 5;
	
//	private void paging() {
//		totalPage = totalPost % showPage == 0 ? totalPost / showPage : (totalPost / showPage)+1;
//	}
	
	public void printBoard() {
		final int totalPost = bList.size();
		int curPage;
		int totalPage;
		int startPost;
		int endPost;
		final int showPage = 5;
		
		totalPage = totalPost % showPage == 0 ? totalPost / showPage : (totalPost / showPage)+1;
		curPage = 1;
		startPost = (curPage-1) * showPage; 
		endPost = startPost + showPage-1;
		if(endPost > totalPage) {
			endPost = totalPage;
		}
		
		System.out.println(startPost);
		System.out.println(endPost);
		
		System.out.println("=====[게시글 목록]=====");
		System.out.printf("[총 게시글 %d개] [현재 페이지 %d/%d]\n", bList.size(), curPage,totalPage);
		if(bList.size() == 0) {
			System.out.println("[!] 게시글이 없습니다.");
		}else {
			for(int i = startPost; i <= endPost; i++) {
				System.out.print(bList.get(i));
			}
		}
	}
	
	
}
