package quiz03;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerMainClass {

	public static void main(String[] args) {
		
		ServerSocket server = null;
		Socket client = null;
		
		BufferedReader br = null;
		BufferedWriter bw = null;  // PrintWriter out = null;
		Scanner sc = null;
		
		try {
			
			// ���� ����
			server = new ServerSocket();
			server.bind(new InetSocketAddress(InetAddress.getLocalHost().getHostAddress(), 4966));
			
			// Ŭ���̾�Ʈ ���� ó��
			client = server.accept();
			InetSocketAddress isa = (InetSocketAddress)client.getRemoteSocketAddress();
			System.out.println("Connected client : [" + isa.getHostString() + "]");
			
			// ����Ʈ ��� ��Ʈ���� ���� ��� ��Ʈ������ �ٲ㼭 �����մϴ�.
			br = new BufferedReader(new InputStreamReader(client.getInputStream(), "UTF-8"));
			bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream(), "UTF-8"));
			// out = new PrintWriter(new OutputStreamWriter(client.getOutputStream(), "UTF-8"));
			sc = new Scanner(System.in);

			while (true) {

				// receive from client
				String fromClient = null;
				fromClient = br.readLine();  // "\n"�� ���������� read�մϴ�.
				if (fromClient == null) {
					System.out.println("Disconnect By Client!");
					break;
				}
				System.out.println("From Client <<< " + fromClient);
				
				// send to client
				System.out.print("To Client >>> ");
				String toClient = sc.nextLine();
				bw.write(toClient + "\n");  // out.println(toClient);
				bw.flush();
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (bw != null) { bw.close(); }
				if (br != null) { br.close(); }
				if (!server.isClosed()) { server.close(); }
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}