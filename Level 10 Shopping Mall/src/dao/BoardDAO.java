package dao;

import java.util.ArrayList;

import dto.Board;

public class BoardDAO {
	private static BoardDAO instance;
	private ArrayList<Board> bList = new ArrayList<>();
	private final int SHOW_PAGE = 5;
	private int totalPost;
	private int curPage;
	private int totalPage;
	private int startPost;
	private int endPost;

	
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
	
	private void init() {
		curPage = curPage == 0 ? 1 : curPage;
		totalPost = bList.size();
		startPost = (curPage-1) * SHOW_PAGE; 
		endPost = startPost + SHOW_PAGE-1;
		if(endPost > bList.size()-1) {
			endPost = bList.size()-1;
		}
	}
	
	private void paging() {
		totalPage = totalPost % SHOW_PAGE == 0 ? totalPost / SHOW_PAGE : (totalPost / SHOW_PAGE)+1;
	}
	
	public void printBoard() {
		init();
		paging();
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
	
	public void getPreviousPage() {
		curPage--;
		if(curPage < 1) {
			System.out.println("[!] 첫 페이지입니다.");
			curPage = 1;
		}
	}

	public void getNextPage() {
		curPage++;
		if(curPage > totalPage) {
			System.out.println("[!] 마지막 페이지입니다.");
			curPage = totalPage;
		}
	}
	
	public int getBListSize() {
		return bList.size();
	}
	
	private boolean isValidPostIdx(int idx) {
		if(idx < startPost || idx > endPost) {
			System.out.println("[!] 현재 페이지에 없는 게시글입니다.");
			return false;
		}
		return true;
		
	}
	
	public void printPost(int idx) {
		if(!isValidPostIdx(idx)) return;
		bList.get(idx).setHits(bList.get(idx).getHits()+1);
		System.out.println(bList.get(idx));
	}
	
	public void printAllPost() {
		System.out.println("=====[게시글 전체 목록]=====");
		for(Board b : bList) {
			System.out.print(b);
		}
	}

	public void deletePost(int idx) {
		bList.remove(idx);
		System.out.println(idx+1 + "번 게시글 삭제 완료.");
	}
}
