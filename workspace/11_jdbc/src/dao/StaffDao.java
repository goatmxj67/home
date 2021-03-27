package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dto.StaffDto;

// DAO : Database Access Object
// DB�� CRUD�� ó���ϴ� �޼ҵ带 �����ϰ� �ֽ��ϴ�.

public class StaffDao {

	// field (�⺻ null ����)
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private int result;
	private String sql;
	
	// constructor (singleton pattern)
	// 1. �ϳ��� StaffDao�� ������ �� �ֽ��ϴ�.
	// 2. ����
	//    1) �ܺο����� new StaffDao()�� �Ұ����ϵ��� �����մϴ�.
	//    2) ���ο��� new StaffDao()�� �����ϰ� ������ 1���� �ν��Ͻ��� ��ȯ�ϴ� �޼ҵ带 ����ϴ�.
	// 3. getInstance() �޼ҵ� ȣ�� ���
	//    1) ��ü(�ν��Ͻ�)�� ���� ȣ��
	//       : private StaffDao() {} ������ �ܺο����� ��ü ������ �Ұ����մϴ�. ���� �Ұ����մϴ�.
	//    2) Ŭ������ ���� ȣ��
	//       : Ŭ���� �޼ҵ�� �ٲ�� �մϴ�. static ó���� �ؾ� �մϴ�.
	//         static �޼ҵ�� static �ʵ常�� ����� �� �ֽ��ϴ�.
	private StaffDao() {}
	private static StaffDao staffDao = new StaffDao();
	public static StaffDao getInstance() {
		return staffDao;
	}
	
	// method
	
	/***** 1. ���� *****/
	public Connection getConnection() throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
		String user = "system";
		String password = "1111";
		return DriverManager.getConnection(url, user, password);
	}
	
	/***** 2. ���� ���� *****/
	public void close(Connection con, PreparedStatement ps, ResultSet rs) {
		try {
			if (rs != null) { rs.close(); }
			if (ps != null) { ps.close(); }
			if (con != null) { con.close(); }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/***** 3. ���� �ֱٿ� �߰��� staff�� no �˾Ƴ��� *****/
	public int selectMaxNo() {
		int no = 0;
		try {
			con = getConnection();
			sql = "SELECT MAX(no) FROM staff";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
				no = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, rs);
		}
		return no;
	}
	
	/***** 4. staff �߰��ϱ� *****/
	// public int insertStaff(int no, String name, String department, Date hireDate)
	public int insertStaff(StaffDto staffDto) {
		try {
			con = getConnection();  // ������ �޼ҵ帶�� ���� �ݴ� ���� ���� �����ϴ�.
			sql = "INSERT INTO staff (no, name, department, hireDate) VALUES (?, ?, ?, SYSDATE)";
			ps = con.prepareStatement(sql);
			ps.setInt(1, staffDto.getNo());
			ps.setString(2, staffDto.getName());
			ps.setString(3, staffDto.getDepartment());
			result = ps.executeUpdate();  // executeUpdate() : INSERT, UPDATE, DELETE
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, null);
		}
		return result;  // ���ԵǸ� result�� 1, �����ϸ� result�� 0
	}
	
	/***** 5. staff �����ϱ� *****/
	public int updateStaff(StaffDto staffDto) {
		try {
			con = getConnection();
			sql = "UPDATE staff SET name = ?, department = ? WHERE no = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, staffDto.getName());
			ps.setString(2, staffDto.getDepartment());
			ps.setInt(3, staffDto.getNo());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, null);
		}
		return result;
	}
	
	/***** 6. staff �����ϱ� *****/
	// public int deleteStaff(int no) {
	public int deleteStaff(StaffDto staffDto) {
		try {
			con = getConnection();
			sql = "DELETE FROM staff WHERE no = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, staffDto.getNo());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, null);
		}
		return result;
	}
	
	/***** 7. staff ��ȸ�ϱ� *****/
	public StaffDto selectOneByNo(int no) {
		StaffDto staffDto = null;
		try {
			con = getConnection();
			sql = "SELECT no, name, department, hireDate FROM staff WHERE no = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, no);
			rs = ps.executeQuery();  // executeQuery() : SELECT
			if (rs.next()) {
				staffDto = new StaffDto();
				staffDto.setNo( rs.getInt(1) );
				staffDto.setName( rs.getString(2) );
				staffDto.setDepartment( rs.getString(3) );
				staffDto.setHireDate( rs.getDate(4) );
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, rs);
		}
		return staffDto;
	}
	
	/***** 8. staff ��ü��ȸ�ϱ� *****/
	public List<StaffDto> selectList() {
		List<StaffDto> staffList = new ArrayList<StaffDto>();
		try {
			con = getConnection();
			sql = "SELECT no, name, department, hireDate FROM staff";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				StaffDto staffDto = new StaffDto();
				staffDto.setNo( rs.getInt(1) );  // rs.getInt("no")
				staffDto.setName( rs.getString(2) );  // rs.getString("name")
				staffDto.setDepartment( rs.getString(3) );  // rs.getString("department")
				staffDto.setHireDate( rs.getDate(4) );  // rs.getDate("hireDate")
				staffList.add(staffDto);  // ����Ʈ�� staffDto �߰�
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, rs);
		}
		return staffList;
	}
	
}