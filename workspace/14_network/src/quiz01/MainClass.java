package quiz01;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainClass {

	public static void main(String[] args) {
		
		// https://m.naver.com/ 의 소스코드를
		// C:\mnaver.html 파일로 저장하시오.
		
		URL url = null;
		HttpURLConnection con = null;
		BufferedReader br = null;
		
		File file = null;
		BufferedWriter bw = null;
		
		try {
			
			// 네이버 모바일 페이지 소스코드 읽기
			url = new URL("https://m.naver.com/");
			con = (HttpURLConnection) url.openConnection();
			br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			
			// C:\mnaver.html 만들기
			file = new File("C:\\mnaver.html");
			bw = new BufferedWriter(new FileWriter(file));
			
			String line = null;
			while ( (line = br.readLine()) != null ) {
				bw.write(line + "\n");
			}
			System.out.println(file.getAbsolutePath() + " 파일이 생성되었습니다.");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (bw != null) { bw.close(); }
				if (br != null) { br.close(); }
				if (con != null) { con.disconnect(); }
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
