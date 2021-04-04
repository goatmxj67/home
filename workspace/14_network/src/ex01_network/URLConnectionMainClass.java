package ex01_network;

import java.net.URL;
import java.net.URLConnection;

public class URLConnectionMainClass {

	public static void main(String[] args) {
		
		URL url = null;
		URLConnection con = null;
		
		try {
			
			url = new URL("https://www.naver.com");
			con = url.openConnection();
			System.out.println(con.getContent());
			System.out.println(con.getURL());
			System.out.println(con.getContentType());

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}