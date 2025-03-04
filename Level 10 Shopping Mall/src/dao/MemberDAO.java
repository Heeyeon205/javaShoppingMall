package dao;

import java.util.ArrayList;

import dto.Item;
import dto.Member;

public class MemberDAO {
	private static MemberDAO instance;
	private ArrayList<Member> mList = new ArrayList<>();

	private MemberDAO() {
	}

	public static MemberDAO getInstance() {
		if (instance == null) {
			instance = new MemberDAO();
		}
		return instance;
	}

	public boolean isDupId(String id) {
		for (int i = 0; i < mList.size(); i++) {
			if (mList.get(i).getId().equals(id)) {
				System.out.println("이미 사용중인 아이디입니다.");
				return true;
			}
		}
		return false;
	}
	
	public void insertMember(String id, String pw, String name) {
		mList.add(new Member(Member.getNum(), id, pw, name));
	}

	public void addMember(String id, String pw, String name) {
		mList.add(new Member(Member.getNum(), id, pw, name));
		System.out.println(name + "님 회원가입 완료!");
	}

	public Object isValidMember(String id, String pw) {
		for (int i = 0; i < mList.size(); i++) {
			if (mList.get(i).getId().equals(id) && mList.get(i).getPw().equals(pw)) {
				if (id.equals("admin")) {
					System.out.println("관리자님 환영합니다.");
				} else {
					System.out.printf("[ID: %s] %s님 로그인 성공!\n", mList.get(i).getId(), mList.get(i).getMemberName());
				}
				return true;
			}
		}
		return null;
	}

	public void getMemberList() {
		System.out.println("=====[회원 목록]=====");
		int i = 1;
		for(Member m : mList) {
			System.out.printf("[%d] " , i++);
			System.out.print(m);
		}
	}

	public boolean hasId(String id) {
		for (int i = 0; i < mList.size(); i++) {
			if (mList.get(i).getId().equals(id)) {
				return true;
			}
		}
		System.out.println("아이디를 다시 확인해주세요.");
		return false;
	}

	public void deleteMember(String id) {
		for(int i = 0; i < mList.size(); i++) {
			if(mList.get(i).getId().equals(id)){
				mList.remove(i);
			}
		}
	}

	public String getData() {
		StringBuilder data = new StringBuilder();
		for(Member m : mList) {
			data.append(m.getMemberNum()).append("/");
			data.append(m.getId()).append("/");
			data.append(m.getPw()).append("/");
			data.append(m.getMemberName()).append("\n");
		}
		return data.toString();
	}

	public void printUserInfo(String loginId) {
		for(Member m : mList) {
			if(m.getId().equals(loginId)) {
				System.out.println(m);
			}
		}
	}

	public int isValidPw(String loginId, String newPw) {
		int idx = 0;
		for(int i = 0; i < mList.size(); i++) {
			if(mList.get(i).getId().equals(loginId)) {
				idx = i;
				if(mList.get(i).getPw().equals(newPw)) {
					System.out.println("[!] 현재와 동일한 비밀번호로 설정할 수 없습니다.");
					return -1;
				}
			}
		}
		return idx;
	}

	public void changePw(int idx, String newPw) {
		mList.get(idx).setPw(newPw);
	}

}
