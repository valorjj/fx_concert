package domain;

public class S_Seat {

	private int S_no;
	private int S_status;
	private int c_no;
	private String s_grade;

	public S_Seat(int s_no, int s_status, int c_no, String s_grade) {
		S_no = s_no;
		S_status = s_status;
		this.c_no = c_no;
		this.s_grade = s_grade;
	}

	public int getS_no() {
		return S_no;
	}

	public void setS_no(int s_no) {
		S_no = s_no;
	}

	public int getS_status() {
		return S_status;
	}

	public void setS_status(int s_status) {
		S_status = s_status;
	}

	public int getC_no() {
		return c_no;
	}

	public void setC_no(int c_no) {
		this.c_no = c_no;
	}

	public String getS_grade() {
		return s_grade;
	}

	public void setS_grade(String s_grade) {
		this.s_grade = s_grade;
	}

}
