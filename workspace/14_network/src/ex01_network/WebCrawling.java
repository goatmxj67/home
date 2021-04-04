package ex01_network;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WebCrawling {

	public static void main(String[] args) {
		
		URL url = null;
		HttpURLConnection con = null;
		BufferedReader br = null;
		
		try {
			
			url = new URL("https://www.daum.net/");
			con = (HttpURLConnection) url.openConnection();
			br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			
			String line = null;
			while ( (line = br.readLine()) != null ) {
				
				// line : <a href="https://search.daum.net/search?w=tot&amp;q=%EC%9C%A4%EC%84%B8%EC%95%84+%EC%B6%9C%EC%97%B0%ED%99%95%EC%A0%95&amp;DA=NPI&amp;rtmaxcoll=NNS" class="link_favorsch @6">������ �⿬Ȯ��</a>
				// �̿� ���� "link_favorsch"�� ���Ե� line�� �ʿ��մϴ�.
				
				if (line.contains("link_favorsch @")) {
					
					// System.out.println(line);
					
					// String[] temps = line.split(">");
					// System.out.println(temps[1]);
					
					// line.split(">")[1] : ������ �⿬Ȯ��</a
					// line.split(">")[1].split("<") : { "������ �⿬Ȯ��", "/a" }
					// line.split(">")[1].split("<")[0] : "������ �⿬Ȯ��"
					
					System.out.println(line.split(">")[1].split("<")[0]);
					
				}
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null) { br.close(); }
				if (con != null) { con.disconnect(); }
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}