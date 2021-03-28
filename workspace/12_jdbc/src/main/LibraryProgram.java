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
		System.out.println("=====도서관리프로그램=====");
		System.out.println("1. 책 등록");
		System.out.println("2. 책 정보 수정");
		System.out.println("3. 책 삭제");
		System.out.println("4. 책 조회");
		System.out.println("5. 책 전체 조회");
		System.out.println("6. 프로그램 종료");
		System.out.println("==========================");
	}

	public void execute() {
		while (true) {
			menu();
			System.out.print("선택(1~6) >>> ");
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
				System.out.println("프로그램을 종료합니다.");
				return;
			default:
				System.out.println("다시 선택하세요.");
			}
		}
	}

	public void insertBook() {

		BookDto BookDto = new BookDto();

		System.out.print("새로운 책 제목 >>> ");
		String bookTitle = sc.next();
		BookDto.setBookTitle(bookTitle);

		System.out.print("책 저자 이름 >>> ");
		String bookWriter = sc.next();
		BookDto.setBookWriter(bookWriter);

		int bookNo = dao.selectMaxNo();
		BookDto.setBookNo(bookNo + 1);

		int result = dao.insertBook(BookDto);
		System.out.println(result + "권의 책이 추가되었습니다.");

	}

	public void updateBook() {

		System.out.print("수정할 책 번호 입력 >>> ");
		int bookNo = sc.nextInt();

		BookDto BookDto = dao.selectOneByNo(bookNo);
		System.out.print("수정할 내용을 선택하세요(1:책 제목, 2:책 저자) >>> ");
		int choice = sc.nextInt();
		if (choice == 1) {
			System.out.print("새로운 책 제목 입력 >>> ");
			String bookTitle = sc.next();
			BookDto.setBookTitle(bookTitle);
		} else if (choice == 2) {
			System.out.print("새로운 책 저자 입력 >>> ");
			String bookWriter = sc.next();
			BookDto.setBookWriter(bookWriter);
		} else {
			System.out.println("잘못된 선택입니다. 수정이 취소됩니다.");
			return;
		}

		int result = dao.updateBook(BookDto);
		System.out.println(result + "권의 책 정보가 수정되었습니다.");

	}

	public void deleteBook() {

		System.out.print("삭제할 책 번호 입력 >>> ");
		int bookNo = sc.nextInt();

		BookDto BookDto = new BookDto();
		BookDto.setBookNo(bookNo);

		int result = dao.deleteBook(BookDto);

		System.out.println(result + "권의 책 정보가 삭제되었습니다.");

	}

	public void selectOne() {

		System.out.print("조회할 책 번호 입력 >>> ");
		int bookNo = sc.nextInt();

		BookDto BookDto = dao.selectOneByNo(bookNo);

		if (BookDto == null) {
			System.out.println("책 번호 " + bookNo + "를 가진 책이 없습니다.");
		} else {
			System.out.println(BookDto);
		}

	}

	public void selectList() {

		List<BookDto> bookList = dao.selectList();

		System.out.println("총 책 수: " + bookList.size() + "권");
		for (BookDto BookDto : bookList) {
			System.out.println(BookDto);
		}

	}

}
