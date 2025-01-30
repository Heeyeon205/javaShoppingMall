package menu_admin;

import controller.MallController;
import dao.BoardDAO;
import mall.MenuCommand;
import util.Util;

public class AdminBoard implements MenuCommand {
	private MallController cont;
	private Util util;
	private BoardDAO bDao;
	
	@Override
	public void init() {
		cont = MallController.getInstance();
		util = Util.getInstance();
		bDao = BoardDAO.getInstance();
		System.out.println("=====[게시판 관리]=====");
		System.out.println("[1] 게시글 목록\n[2] 게시글 삭제\n[3] 뒤로가기\n[0] 프로그램 종료");
	}

	@Override
	public boolean update() {
		int sel = util.getValue("메뉴 입력: ", 0, 4);
		if(sel == 1) {
			while(true) {
			bDao.printBoard();
			System.out.println("---------------");
			System.out.println("[1] 이전\n[2] 이후\n[3] 게시글 보기\n[4] 뒤로가기\n[0] 프로그램 종료");
			int input = util.getValue("메뉴 입력: ", 0, 5);
			if(input == 1) {
				bDao.getPreviousPage();
			}else if(input == 2) {
				bDao.getNextPage();
			}else if(input == 3) {
				int postSel = util.getValue("게시글 번호 입력: ", 1, bDao.getBListSize()+1);
				bDao.printPost(postSel-1);
			}else if(input == 4) {
				break;
			}else if(input == 0) {
				System.out.println("프로그램 종료");
				cont.setNext(null);
			}
			}
		}else if(sel == 2) {
			bDao.printAllPost();
			int delSel = util.getValue("삭제할 번호: ", 1, bDao.getBListSize()+1);
			bDao.deletePost(delSel-1);
		}else if(sel == 3) {
			cont.setNext("AdminMain");
		}else if(sel == 0) {
			System.out.println("프로그램 종료");
			cont.setNext(null);
		}
		return false;
	}

}
