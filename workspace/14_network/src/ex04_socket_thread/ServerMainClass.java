package ex04_socket_thread;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMainClass {

	public static void main(String[] args) {
		
		// Ŭ���̾�Ʈ���� ������ �����մϴ�.
		// ���� ���� �۾��� ������� �ۼ��մϴ�.
		
		ServerSocket server = null;
		Socket client = null;
		
		try {
			
			// ���� ����
			server = new ServerSocket();
			server.bind(new InetSocketAddress("localhost", 4966));
			
			while (true) {
				
				// Ŭ���̾�Ʈ���� ���� ó��
				client = server.accept();
				
				// Ŭ���̾�Ʈ���� ���� ����
				FileUploadThread upload = new FileUploadThread();  // ������ ����
				upload.setClient(client);  // client�� ���� ��Ʈ�� ������ �ʿ��ϹǷ� ������ �ݴϴ�.
				upload.start();  // ������ ����
				
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