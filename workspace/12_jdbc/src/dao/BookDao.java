package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dto.BookDto;

public class BookDao {

	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private int result;
	private String sql;

	private BookDao() {
	}

	private static BookDao BookDao = new BookDao();

	public static BookDao getInstance() {
		return BookDao;
	}

	/***** 1. 접속 *****/
	public Connection getConnection() throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
		String user = "system";
		String password = "1111";
		return DriverManager.getConnection(url, user, password);
	}

	/***** 2. 접속 해제 *****/
	public void close(Connection con, PreparedStatement ps, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (con != null) {
				con.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/***** 3. 가장 최근에 추가된 Book의 bookNo 알아내기 *****/
	public int selectMaxNo() {
		int bookNo = 0;
		try {
			con = getConnection();
			sql = "SELECT MAX(bookNo) FROM Book";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
				bookNo = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, rs);
		}
		return bookNo;
	}

	/***** 4. Book 추가하기 *****/
	public int insertBook(BookDto BookDto) {
		try {
			con = getConnection();
			sql = "INSERT INTO Book (bookNo, bookTitle, bookWriter, pubDate) VALUES (?, ?, ?, SYSDATE)";
			ps = con.prepareStatement(sql);
			ps.setInt(1, BookDto.getBookNo());
			ps.setString(2, BookDto.getBookTitle());
			ps.setString(3, BookDto.getBookWriter());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, null);
		}
		return result;
	}

	/***** 5. Book 수정하기 *****/
	public int updateBook(BookDto BookDto) {
		try {
			con = getConnection();
			sql = "UPDATE Book SET bookTitle = ?, bookWriter = ? WHERE bookNo = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, BookDto.getBookTitle());
			ps.setString(2, BookDto.getBookWriter());
			ps.setInt(3, BookDto.getBookNo());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, null);
		}
		return result;
	}

	/***** 6. Book 삭제하기 *****/
	public int deleteBook(BookDto BookDto) {
		try {
			con = getConnection();
			sql = "DELETE FROM Book WHERE bookNo = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, BookDto.getBookNo());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, null);
		}
		return result;
	}

	/***** 7. Book 조회하기 *****/
	public BookDto selectOneByNo(int bookNo) {
		BookDto BookDto = null;
		try {
			con = getConnection();
			sql = "SELECT bookNo, bookTitle, bookWriter, pubDate FROM Book WHERE bookNo = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, bookNo);
			rs = ps.executeQuery();
			if (rs.next()) {
				BookDto = new BookDto();
				BookDto.setBookNo(rs.getInt(1));
				BookDto.setBookTitle(rs.getString(2));
				BookDto.setBookWriter(rs.getString(3));
				BookDto.setPubDate(rs.getDate(4));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, rs);
		}
		return BookDto;
	}

	/***** 8. Book 전체조회하기 *****/
	public List<BookDto> selectList() {
		List<BookDto> BookList = new ArrayList<BookDto>();
		try {
			con = getConnection();
			sql = "SELECT bookNo, bookTitle, bookWriter, pubDate FROM Book";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				BookDto BookDto = new BookDto();
				BookDto.setBookNo(rs.getInt(1));
				BookDto.setBookTitle(rs.getString(2));
				BookDto.setBookWriter(rs.getString(3));
				BookDto.setPubDate(rs.getDate(4));
				BookList.add(BookDto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, rs);
		}
		return BookList;
	}

}