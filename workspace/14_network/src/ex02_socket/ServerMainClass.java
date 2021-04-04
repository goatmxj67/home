package ex02_socket;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMainClass {

	public static void main(String[] args) {
		
		// ServerSocket : ����
		
		ServerSocket server = null;
		
		try {
			
			// ���� ����
			server = new ServerSocket();
			
			// ������ Ŭ���̾�Ʈ ����
			server.bind(new InetSocketAddress("localhost", 4966));
			
			// ������ �׻� ���� �ִ�. (���ѷ���)
			while (true) {
				
				System.out.println("=====������ �������Դϴ�.=====");
				
				// Ŭ���̾�Ʈ�� ���� ���
				Socket client = server.accept();
				
				// Ŭ���̾�Ʈ�� �ּ�
				InetSocketAddress isa = (InetSocketAddress) client.getRemoteSocketAddress();
				System.out.println("[" + isa.getHostName() + "] Ŭ���̾�Ʈ�� ���ӵǾ����ϴ�.");
				
				// Ŭ���̾�Ʈ���� ���� �޽��� ������
				String message = "Welcome to My Server! �ȳ�!";
				OutputStream os = client.getOutputStream();
				os.write(message.getBytes("UTF-8"));
				os.flush();  // (Ȥ��) ��Ʈ���� ���� �ִ� �����͸� ������ �о��
				
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