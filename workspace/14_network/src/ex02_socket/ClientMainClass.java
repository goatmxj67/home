package ex02_socket;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ClientMainClass {

	public static void main(String[] args) {
		
		// Socket : Ŭ���̾�Ʈ
		
		Socket client = null;
		
		try {
			
			// Ŭ���̾�Ʈ ����
			client = new Socket();
			
			System.out.println("Ŭ���̾�Ʈ�� ������ ������ �õ��մϴ�.");
			
			// Ŭ���̾�Ʈ�� ������ ������ �õ�
			client.connect(new InetSocketAddress("localhost", 4966));
			// socket.connect(new InetSocketAddress(InetAddress.getByName("localhost"), 4966));
			
			// ���� ����
			System.out.println("Ŭ���̾�Ʈ�� ������ ���ӵǾ����ϴ�.");
			
			// ���� �޽��� �ޱ�
			InputStream is = client.getInputStream();
			byte[] b = new byte[50];  // "Welcome to My Server!"�� ������ ������ ũ��
			int length = is.read(b);  // ���� ������ b�� ����, ������ ���� ����Ʈ ���� length�� ����
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