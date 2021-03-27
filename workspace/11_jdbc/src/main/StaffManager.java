package main;

import java.util.List;
import java.util.Scanner;

import dao.StaffDao;
import dto.StaffDto;

public class StaffManager {

	// field
	private StaffDao dao = StaffDao.getInstance();
	private Scanner sc = new Scanner(System.in);
	
	// method
	public void menu() {
		System.out.println("=====����������α׷�=====");
		System.out.println("1. ��� ���");
		System.out.println("2. ���� ����");
		System.out.println("3. ��� ó��");
		System.out.println("4. ��� ��ȸ");
		System.out.println("5. ��ü ��ȸ");
		System.out.println("6. ���α׷� ����");
		System.out.println("==========================");
	}
	
	public void execute() {
		while (true) {
			menu();
			System.out.print("����(1~6) >>> ");
			switch (sc.nextInt()) {
			case 1 : insertStaff(); break;
			case 2 : updateStaff(); break;
			case 3 : deleteStaff(); break;
			case 4 : selectOne(); break;
			case 5 : selectList(); break;
			case 6 : System.out.println("���α׷��� �����մϴ�.");
			         return;
			default : System.out.println("�ٽ� �����ϼ���.");
			}
		}
	}
	
	public void insertStaff() {

		StaffDto staffDto = new StaffDto();

		System.out.print("�ű� ��� �̸� >>> ");
		String name = sc.next();
		staffDto.setName(name);

		System.out.print("�ű� �μ� �̸� >>> ");
		String department = sc.next();
		staffDto.setDepartment(department);

		int no = dao.selectMaxNo();
		staffDto.setNo(no + 1);
		
		int result = dao.insertStaff(staffDto);  // DB�� ����
		System.out.println(result + "���� ����� �߰��Ǿ����ϴ�.");
		
	}
	
	public void updateStaff() {
		
		System.out.print("������ ��� ��ȣ �Է� >>> ");
		int no = sc.nextInt();
		
		StaffDto staffDto = dao.selectOneByNo(no);
		System.out.print("������ ������ �����ϼ���(1:�̸�, 2:�μ�) >>> ");
		int choice = sc.nextInt();
		if (choice == 1) {
			System.out.print("���ο� ��� �̸� �Է� >>> ");
			String name = sc.next();
			staffDto.setName(name);
		} else if (choice == 2) {
			System.out.print("���ο� �μ� �̸� �Է� >>> ");
			String department = sc.next();
			staffDto.setDepartment(department);
		} else {
			System.out.println("�߸��� �����Դϴ�. ������ ��ҵ˴ϴ�.");
			return;
		}
		
		int result = dao.updateStaff(staffDto);
		System.out.println(result + "���� ȸ�������� �����Ǿ����ϴ�.");
		
	}
	
	public void deleteStaff() {
		
		System.out.print("������ ��� ��ȣ �Է� >>> ");
		int no = sc.nextInt();
		
		StaffDto staffDto = new StaffDto();
		staffDto.setNo(no);
		
		int result = dao.deleteStaff(staffDto);
		
		System.out.println(result + "���� ȸ�������� �����Ǿ����ϴ�.");
		
	}
	
	public void selectOne() {
		
		System.out.print("��ȸ�� ��� ��ȣ �Է� >>> ");
		int no = sc.nextInt();
		
		StaffDto staffDto = dao.selectOneByNo(no);
		
		if (staffDto == null) {
			System.out.println("�����ȣ " + no + "�� ���� ����� �����ϴ�.");
		} else {
			System.out.println(staffDto);
		}
		
	}
	
	public void selectList() {
		
		List<StaffDto> staffList = dao.selectList();
		
		System.out.println("�� ��� ��: " + staffList.size() + "��");
		for (StaffDto staffDto : staffList) {
			System.out.println(staffDto);
		}
		
	}
	
}
