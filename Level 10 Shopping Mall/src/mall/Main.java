package mall;

import controller.MallController;

public class Main {

	public static void main(String[] args) {
		MallController con = MallController.getInstance();
		con.init();
	}
}