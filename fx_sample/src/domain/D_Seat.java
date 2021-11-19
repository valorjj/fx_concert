package domain;

public class D_Seat {

	private int D_no;
	private int D_status;
	private int c_no;
	private String s_grade;

	public D_Seat(int d_no, int d_status, int c_no, String s_grade) {
		D_no = d_no;
		D_status = d_status;
		this.c_no = c_no;
		this.s_grade = s_grade;
	}

	public int getD_no() {
		return D_no;
	}

	public void setD_no(int d_no) {
		D_no = d_no;
	}

	public int getD_status() {
		return D_status;
	}

	public void setD_status(int d_status) {
		D_status = d_status;
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
