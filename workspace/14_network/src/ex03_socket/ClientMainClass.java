package ex03_socket;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class ClientMainClass {

	public static void main(String[] args) {
		
		Socket client = null;
		Scanner sc = null;
		BufferedOutputStream bos = null;
		BufferedInputStream bis = null;
		
		try {
			
			// ������ �����ϱ�
			client = new Socket();
			client.connect(new InetSocketAddress("localhost", 4966));
			System.out.println("������ ���ӵǾ����ϴ�.");
			
			// ������ �޽��� ������
			sc = new Scanner(System.in);
			System.out.print("������ �λ縻�� �ǳ׺����� >>> ");
			String message = sc.nextLine();
			bos = new BufferedOutputStream(client.getOutputStream());
			bos.write(message.getBytes("UTF-8"));
			bos.flush();
			
			// ������ ���� �亯 �޽��� �ޱ�
			bis = new BufferedInputStream(client.getInputStream());
			byte[] b = new byte[1024];
			int length = bis.read(b);
			String receiveMsg = new String(b, 0, length, "UTF-8");
			System.out.println(receiveMsg);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (bis != null) { bis.close(); }
				if (bos != null) { bos.close(); }
				if (!client.isClosed()) { client.close(); }
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}