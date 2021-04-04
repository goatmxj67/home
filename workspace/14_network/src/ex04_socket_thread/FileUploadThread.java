package ex04_socket_thread;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.Socket;

public class FileUploadThread extends Thread {

	// field
	private Socket client;
	
	// method
	public Socket getClient() {
		return client;
	}
	public void setClient(Socket client) {
		this.client = client;
	}
	@Override
	public void run() {
		
		// client�� ���� ������ ������ ���͸�
		File dir = new File("C:\\MyServer");
		if (!dir.exists()) {
			dir.mkdirs();
		}
		
		DataInputStream dis = null;
		File file = null;
		BufferedOutputStream bos = null;
		
		try {
			
			// client�� ���ϸ� + ���ϳ����� ������ �ֽ��ϴ�.
			// client�� DataOutputStream���� �����͸� ������ �ֱ� ������,
			// DataInputStream�� ����ؼ� ���ϸ� + ���ϳ����� �н��ϴ�.
			dis = new DataInputStream(client.getInputStream());
			String filename = dis.readUTF();  // client�� ���ϸ��� writeUTF()�� ���±� ������
			
			// client�� ���� ���ϳ����� C:\MyServer ���͸��� �����մϴ�.
			// ������ �����ϱ� ���ؼ� FileOutputStream�� �ʿ��ϰ�,
			// ������ ���̱� ���ؼ� BufferedOutputStream�� ����մϴ�.
			file = new File(dir, filename);
			bos = new BufferedOutputStream(new FileOutputStream(file));
			byte[] b = new byte[1024];
			int length = 0;
			while ( (length = dis.read(b)) != -1 ) {
				bos.write(b, 0, length);
			}
			System.out.println(file.getAbsolutePath() + " ������ ������ ����Ǿ����ϴ�.");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (bos != null) { bos.close(); }
				if (dis != null) { dis.close(); }
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
}