package main;

import java.util.List;
import java.util.Scanner;

import dao.BookDao;
import dto.BookDto;

public class LibraryProgram {

	// field
	private BookDao dao = BookDao.getInstance();
	private Scanner sc = new Scanner(System.in);

	// method
	public void menu() {
		System.out.println("=====�����������α׷�=====");
		System.out.println("1. å ���");
		System.out.println("2. å ���� ����");
		System.out.println("3. å ����");
		System.out.println("4. å ��ȸ");
		System.out.println("5. å ��ü ��ȸ");
		System.out.println("6. ���α׷� ����");
		System.out.println("==========================");
	}

	public void execute() {
		while (true) {
			menu();
			System.out.print("����(1~6) >>> ");
			switch (sc.nextInt()) {
			case 1:
				insertBook();
				break;
			case 2:
				updateBook();
				break;
			case 3:
				deleteBook();
				break;
			case 4:
				selectOne();
				break;
			case 5:
				selectList();
				break;
			case 6:
				System.out.println("���α׷��� �����մϴ�.");
				return;
			default:
				System.out.println("�ٽ� �����ϼ���.");
			}
		}
	}

	public void insertBook() {

		BookDto BookDto = new BookDto();

		System.out.print("���ο� å ���� >>> ");
		String bookTitle = sc.next();
		BookDto.setBookTitle(bookTitle);

		System.out.print("å ���� �̸� >>> ");
		String bookWriter = sc.next();
		BookDto.setBookWriter(bookWriter);

		int bookNo = dao.selectMaxNo();
		BookDto.setBookNo(bookNo + 1);

		int result = dao.insertBook(BookDto);
		System.out.println(result + "���� å�� �߰��Ǿ����ϴ�.");

	}

	public void updateBook() {

		System.out.print("������ å ��ȣ �Է� >>> ");
		int bookNo = sc.nextInt();

		BookDto BookDto = dao.selectOneByNo(bookNo);
		System.out.print("������ ������ �����ϼ���(1:å ����, 2:å ����) >>> ");
		int choice = sc.nextInt();
		if (choice == 1) {
			System.out.print("���ο� å ���� �Է� >>> ");
			String bookTitle = sc.next();
			BookDto.setBookTitle(bookTitle);
		} else if (choice == 2) {
			System.out.print("���ο� å ���� �Է� >>> ");
			String bookWriter = sc.next();
			BookDto.setBookWriter(bookWriter);
		} else {
			System.out.println("�߸��� �����Դϴ�. ������ ��ҵ˴ϴ�.");
			return;
		}

		int result = dao.updateBook(BookDto);
		System.out.println(result + "���� å ������ �����Ǿ����ϴ�.");

	}

	public void deleteBook() {

		System.out.print("������ å ��ȣ �Է� >>> ");
		int bookNo = sc.nextInt();

		BookDto BookDto = new BookDto();
		BookDto.setBookNo(bookNo);

		int result = dao.deleteBook(BookDto);

		System.out.println(result + "���� å ������ �����Ǿ����ϴ�.");

	}

	public void selectOne() {

		System.out.print("��ȸ�� å ��ȣ �Է� >>> ");
		int bookNo = sc.nextInt();

		BookDto BookDto = dao.selectOneByNo(bookNo);

		if (BookDto == null) {
			System.out.println("å ��ȣ " + bookNo + "�� ���� å�� �����ϴ�.");
		} else {
			System.out.println(BookDto);
		}

	}

	public void selectList() {

		List<BookDto> bookList = dao.selectList();

		System.out.println("�� å ��: " + bookList.size() + "��");
		for (BookDto BookDto : bookList) {
			System.out.println(BookDto);
		}

	}

}
