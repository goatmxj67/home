package quiz02;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

public class WebCrawling2 {

	public static void main(String[] args) {
		URL url = null;
		HttpURLConnection con = null;
		BufferedReader br = null;
		
		try {
			
			url = new URL("https://zum.com/#!/home");
			con = (HttpURLConnection) url.openConnection();
			br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			
			String line = null;
			Set<String> set = new HashSet<String>();
			while ( (line = br.readLine()) != null ) {
				if (line.contains("d_keyword")) {
					if ( !line.split(">")[1].split("<")[0].isEmpty() ) {
						set.add(line.split(">")[1].split("<")[0]);
					}
				}
			}
			for (String keyword : set) {
				System.out.println(keyword);
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