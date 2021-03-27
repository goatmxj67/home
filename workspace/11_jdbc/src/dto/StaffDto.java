package dto;

import java.sql.Date;

// DB Table�� ����� ��(Row)�� �����ϴ� Ŭ����
// VO : Value Object
// DTO : Data Transfer Object

// ���̺� Į��   Ŭ���� �ʵ�
// BOARD_NO      board_no �Ǵ� boardNo
// BNO           bNo

// ����Ʈ ������ �ʼ� : �����ڸ� �� ����ų�, ����Ʈ �����ڿ� �ʵ带 �̿��� �����ڸ� ��� ����ϴ�.
// getter/setter �ʼ�

public class StaffDto {

	// field
	private int no;
	private String name;
	private String department;
	private Date hireDate;

	// constructor
	public StaffDto() {}
	public StaffDto(int no, String name, String department, Date hireDate) {
		super();
		this.no = no;
		this.name = name;
		this.department = department;
		this.hireDate = hireDate;
	}
	
	// getter/setter
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public Date getHireDate() {
		return hireDate;
	}
	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}
	@Override
	public String toString() {
		return "StaffDto [no=" + no + ", name=" + name + ", department=" + department + ", hireDate=" + hireDate + "]";
	}
	
}