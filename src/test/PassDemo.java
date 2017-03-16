package test;

public class PassDemo {
	public static int dd = 0;

	public static void main(String[] args) {
		String c = "error";
		s(c);
	}

	public static void s(String sid) {
		if (sid.equals("OK")) {
			System.out.println("请求成功");
		}
		else {
			dd++;
			if (dd == 3) {
				sid = "OK";
			}
			s(sid);
		}
	}
}
