package menu_member;

import java.time.LocalDate;

import controller.MallController;
import dao.BoardDAO;
import mall.MenuCommand;
import util.Util;

public class MemberBoard implements MenuCommand{
	private MallController cont;
	private Util util;
	private BoardDAO bDao;
	
	@Override
	public void init() {
		cont = MallController.getInstance();
		util = Util.getInstance();
		bDao = BoardDAO.getInstance();
		System.out.println("=====[게시판]=====");
		System.out.println("[1] 게시글 보기\n[2] 게시글 작성\n[3] 내 게시글 관리\n[4] 뒤로가기\n[0] 프로그램 종료");
	}

	@Override
	public boolean update() {
		int sel = util.getValue("메뉴 선택: ", 0, 5);
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
			System.out.println("=====[게시글 작성]=====");
			String title = util.getString("제목: ");
			String body = util.getString("본문: ");
			LocalDate nowDate = LocalDate.now();
			String date = nowDate.toString();
			bDao.insertBoard(title, cont.getLoginId(), date, body, 0);
			System.out.printf("[%s] %s\n%s님 게시글 작성 완료.\n", title, body, cont.getLoginId());
		}else if(sel == 3) {
			System.out.println("=====[내 게시글 관리]=====");
			int myPostSize = bDao.printUserPostList(cont.getLoginId());
			if(myPostSize == 0) return false;
			int input = util.getValue("[1] 게시글 삭제\n[2] 뒤로 가기", 1, 3);
			if(input == 1) {
				int delNum = util.getValue("삭제할 게시글 번호: ", 1, myPostSize+1);
				int delIdx = bDao.getpostIdx(cont.getLoginId(), delNum);
				bDao.deletePost(delIdx);
			}else if (input == 2){
				return false;
			}
		}else if(sel == 4) {
			cont.setNext("MemberMain");
		}else if(sel == 0) {
			System.out.println("[프로그램 종료]");
			cont.setNext(null);
		}
		return false;
	}
	

}
