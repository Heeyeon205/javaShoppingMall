package dao;

import java.util.ArrayList;

import dto.Member;

public class MemberDAO {
	private static MemberDAO instance;
	private ArrayList<Member> mList = new ArrayList<>();
	
	private MemberDAO() {}

	public static MemberDAO getInstance() {
		if(instance == null) {
			instance = new MemberDAO();
		}
		return instance;
	}

	public Object getMemberById(String id) {
		for(int i = 0; i < mList.size(); i++) {
			if(mList.get(i).getId().equals(id)) {
				return null;
			}
		}
		return "";
	}

	public boolean insertMember(String id, String pw, String name) {
		int number = 1001;
		mList.add(new Member(++number, id, pw, name));
		return false;
	}


}
