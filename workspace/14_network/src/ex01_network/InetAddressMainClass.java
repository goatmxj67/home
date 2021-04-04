package ex01_network;

import java.net.InetAddress;
import java.util.Arrays;

public class InetAddressMainClass {

	public static void main(String[] args) {
		
		// 원격 호스트
		String host = "www.naver.com";
		
		// 인터넷 주소를 처리하는 InetAddress
		InetAddress ia = null;
		
		try {
			
			ia = InetAddress.getByName(host);
			System.out.println(ia);  // www.naver.com/125.209.222.141
			System.out.println(ia.getHostName());  // www.naver.com
			System.out.println(ia.getHostAddress());  // 125.209.222.141
			
			InetAddress[] ias = InetAddress.getAllByName(host);
			for (InetAddress i : ias) {
				System.out.println(i.getHostName() + "의 ip 주소: " + i.getHostAddress());
			}
			
			byte[] byteIp = ia.getAddress();
			System.out.println(Arrays.toString(byteIp));
			
			short[] ip = new short[byteIp.length];
			for (int i = 0; i < ip.length; i++) {
				ip[i] = (short)(byteIp[i] < 0 ? byteIp[i] + 256 : byteIp[i]);
			}
			System.out.println(Arrays.toString(ip));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}