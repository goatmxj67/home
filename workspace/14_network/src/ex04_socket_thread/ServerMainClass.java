package ex04_socket_thread;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMainClass {

	public static void main(String[] args) {
		
		// 클라이언트들이 파일을 전송합니다.
		// 파일 전송 작업은 스레드로 작성합니다.
		
		ServerSocket server = null;
		Socket client = null;
		
		try {
			
			// 서버 생성
			server = new ServerSocket();
			server.bind(new InetSocketAddress("localhost", 4966));
			
			while (true) {
				
				// 클라이언트들의 접속 처리
				client = server.accept();
				
				// 클라이언트들의 파일 전송
				FileUploadThread upload = new FileUploadThread();  // 스레드 생성
				upload.setClient(client);  // client를 통한 스트림 생성이 필요하므로 전달해 줍니다.
				upload.start();  // 스레드 실행
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (!server.isClosed()) { server.close(); }
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

}