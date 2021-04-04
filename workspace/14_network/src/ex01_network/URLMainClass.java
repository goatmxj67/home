package ex01_network;

import java.net.URL;

public class URLMainClass {

	public static void main(String[] args) {
		
		// URL : Uniform Resource Locator
		// ������ ������ �ִ� �ڿ��� ǥ����
		// ��������://ȣ��Ʈ:��Ʈ?�Ķ����
		
		URL url1 = null;
		URL url2 = null;
		
		try {
			
			url1 = new URL("https://www.naver.com?query=java&page=3");
			System.out.println(url1.getHost());
			System.out.println(url1.getDefaultPort());
			System.out.println(url1.getProtocol());
			System.out.println(url1.getQuery());
			
			url2 = new URL("https://www.naver.com/webtoon/webtoon.jsp");
			System.out.println(url2.getFile());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}