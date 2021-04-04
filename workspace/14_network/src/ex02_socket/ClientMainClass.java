package ex02_socket;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ClientMainClass {

	public static void main(String[] args) {
		
		// Socket : 클라이언트
		
		Socket client = null;
		
		try {
			
			// 클라이언트 생성
			client = new Socket();
			
			System.out.println("클라이언트가 서버에 접속을 시도합니다.");
			
			// 클라이언트가 서버에 접속을 시도
			client.connect(new InetSocketAddress("localhost", 4966));
			// socket.connect(new InetSocketAddress(InetAddress.getByName("localhost"), 4966));
			
			// 접속 성공
			System.out.println("클라이언트가 서버에 접속되었습니다.");
			
			// 웰컴 메시지 받기
			InputStream is = client.getInputStream();
			byte[] b = new byte[50];  // "Welcome to My Server!"를 저장할 정도의 크기
			int length = is.read(b);  // 읽은 내용은 b에 저장, 실제로 읽은 바이트 수는 length에 저장
			String message = new String(b, 0, length, "UTF-8");
			System.out.println(message);
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if ( !client.isClosed() ) { client.close(); }
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}