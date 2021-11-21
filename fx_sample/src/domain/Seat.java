
package domain;

public class Seat {

	// 1. 필드
	private int s_no; // 좌석번호
	private int s_status; // 좌석예약상태
	private int c_no;
	private int s_unique_no;// 좌석 가격
	private String s_grade;

	public Seat(int s_no, int s_status, int c_no, int s_unique_no, String s_grade) {
		this.s_no = s_no;
		this.s_status = s_status;
		this.c_no = c_no;
		this.s_unique_no = s_unique_no;
		this.s_grade = s_grade;
	}

	public Seat(int s_status, int c_no, int s_unique_no, String s_grade) {
		this.s_status = s_status;
		this.c_no = c_no;
		this.s_unique_no = s_unique_no;
		this.s_grade = s_grade;
	}

	public int getS_no() {
		return s_no;
	}

	public void setS_no(int s_no) {
		this.s_no = s_no;
	}

	public int getS_status() {
		return s_status;
	}

	public void setS_status(int s_status) {
		this.s_status = s_status;
	}

	public int getC_no() {
		return c_no;
	}

	public void setC_no(int c_no) {
		this.c_no = c_no;
	}

	public int getS_unique_no() {
		return s_unique_no;
	}

	public void setS_unique_no(int s_unique_no) {
		this.s_unique_no = s_unique_no;
	}

	public String getS_grade() {
		return s_grade;
	}

	public void setS_grade(String s_grade) {
		this.s_grade = s_grade;
	}

}
