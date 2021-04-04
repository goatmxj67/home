package ex04_socket_thread;

import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;


public class ClientMainClass {

	public static void main(String[] args) {
		
		Socket client = null;
		Scanner sc = null;
		File dir = null;
		File file = null;
		DataOutputStream dos = null;
		BufferedInputStream bis = null;
		
		try {
			
			// ������ ����
			client = new Socket();
			client.connect(new InetSocketAddress("localhost", 4966));
			
			// C:\MyClient ���͸� ������ ���� �� �ϳ��� �̸��� �Է� �޾Ƽ� �ش� ������ ����
			sc = new Scanner(System.in);
			System.out.print("������ ���ϸ� �Է� >>> ");
			String filename = sc.nextLine();
			
			dir = new File("C:\\MyClient");
			file = new File(dir, filename);
			
			// ������ ���ϸ� ������
			dos = new DataOutputStream(client.getOutputStream());
			dos.writeUTF(filename);
			
			// ���ϳ����� �о ������ ���ϳ��� ������
			// ���ϳ����� �б� ���ؼ� FileInputStream�� �ʿ��մϴ�.
			// ������ ���̱� ���ؼ� BufferedInputStream�� ����մϴ�.
			bis = new BufferedInputStream(new FileInputStream(file));
			byte[] b = new byte[1024];
			int length = 0;
			while ( (length = bis.read(b)) != -1 ) {
				dos.write(b, 0, length);
			}
			
			System.out.println(file.getAbsolutePath() + " ������ ������ �����߽��ϴ�.");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (dos != null) { dos.close(); }
				if (bis != null) { bis.close(); }
				if (!client.isClosed()) { client.close(); }
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}