package dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileDAO {
	private static FileDAO instance;
	private final static String CUR_PATH = System.getProperty("user.dir") + "/src/files/";

	private FileDAO() {}
	static public FileDAO getInstance() {
		if (instance == null) {
			instance = new FileDAO();
		}
		return instance;
	}
	
	enum FileName {
		BOARD("board.txt"), MEMBER("member.txt"), ITEM("item.txt"), CART("cart.txt");
		private String name;
		
		FileName(String name) {
			this.name = name;
		}
		
		public String getName() {
			return name;
		}
	}

	private void createFile(String name) {
		Path path = Paths.get(CUR_PATH + name);
		try {
			Files.createFile(path);
		} catch (IOException e) {
			System.out.println("파일이 이미 있음");
		}
	}

	private void init() {
		for(FileName fileName : FileName.values()) {
			createFile(fileName.getName());
		}
	}
	
	public void loadAllFiles() {
		for (FileName fileName : FileName.values()) {
            loadFile(fileName);
        }
        System.out.println("[✅] 모든 데이터 로드 완료");
	}
	
	private void loadFile(FileName fileName) {
		try (FileReader fw = new FileReader(CUR_PATH + fileName.getName()); 
				BufferedReader br = new BufferedReader(fw);) {
			String line;
			while ((line = br.readLine()) != null) {
				 insertData(fileName, line);
			}
		} catch (FileNotFoundException e) {
			System.out.println("[!] 파일을 찾을 수 없습니다.\n경로: " + CUR_PATH + fileName.getName());
		} catch (IOException e) {
			System.out.println("오류 발생");
		}
	}
	
	private void insertData(FileName fileName, String line) {
		String[] info = line.split("/");
		switch(fileName) {
		case BOARD : BoardDAO.getInstance().insertBoard(info[1], info[2], info[3], info[4], Integer.parseInt(info[5])); break;
		case CART : CartDAO.getInstance().insertCart(info[1], Integer.parseInt(info[2]), Integer.parseInt(info[3])); break;
		case ITEM : ItemDAO.getInstance().insertItem(info[1], info[2], Integer.parseInt(info[3])); break;
		case MEMBER : MemberDAO.getInstance().insertMember(info[1], info[2], info[3]); break;
		default : System.out.println("[!] 알 수 없는 파일 형식입니다.\n" + fileName.getName());
		}
	}
	
	public void saveFile() {
		
	}
}
