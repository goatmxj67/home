package ex03_socket;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerMainClass {

	public static void main(String[] args) {
		
		ServerSocket server = null;
		Socket client = null;
		BufferedInputStream bis = null;
		
		Scanner sc = null;
		BufferedOutputStream bos = null;
		
		try {
			
			// ���� ����
			server = new ServerSocket();
			server.bind(new InetSocketAddress("localhost", 4966));
			
			while (true) {
				
				// Ŭ���̾�Ʈ ����ó��
				System.out.println("=====������ �����ϰ� �ֽ��ϴ�.=====");
				client = server.accept();
				InetSocketAddress isa = (InetSocketAddress)client.getRemoteSocketAddress();
				System.out.println("���� Ŭ���̾�Ʈ: [" + isa.getHostName() + "]");
				
				// Ŭ���̾�Ʈ�� ���� �޽��� �ޱ�
				bis = new BufferedInputStream(client.getInputStream());
				byte[] b = new byte[1024];
				int length = bis.read(b);  // �޽���: b, �޽������ڼ�: length
				String receiveMsg = new String(b, 0, length, "UTF-8");
				System.out.println(receiveMsg);
				
				// Ŭ���̾�Ʈ���� �亯 �޽��� ������
				sc = new Scanner(System.in);
				bos = new BufferedOutputStream(client.getOutputStream());
				System.out.print("Ŭ���̾�Ʈ���� �亯�� �����ּ��� >>> ");
				String sendMsg = sc.nextLine();
				bos.write(sendMsg.getBytes("UTF-8"));
				bos.flush();
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (bos != null) { bos.close(); }
				if (bis != null) { bis.close(); }
				if (!server.isClosed()) {	server.close(); }
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}