package ex02_socket;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMainClass {

	public static void main(String[] args) {
		
		// ServerSocket : 서버
		
		ServerSocket server = null;
		
		try {
			
			// 서버 생성
			server = new ServerSocket();
			
			// 접속할 클라이언트 정보
			server.bind(new InetSocketAddress("localhost", 4966));
			
			// 서버는 항상 켜져 있다. (무한루프)
			while (true) {
				
				System.out.println("=====서버가 실행중입니다.=====");
				
				// 클라이언트의 접속 허용
				Socket client = server.accept();
				
				// 클라이언트의 주소
				InetSocketAddress isa = (InetSocketAddress) client.getRemoteSocketAddress();
				System.out.println("[" + isa.getHostName() + "] 클라이언트가 접속되었습니다.");
				
				// 클라이언트에게 웰컴 메시지 보내기
				String message = "Welcome to My Server! 안녕!";
				OutputStream os = client.getOutputStream();
				os.write(message.getBytes("UTF-8"));
				os.flush();  // (혹시) 스트림에 남아 있는 데이터를 강제로 밀어내기
				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if ( !server.isClosed() ) {
					server.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}