package domain;

public class E_Seat {

	private int R_no;
	private int R_status;
	private int c_no;
	private String s_grade;

	public E_Seat(int r_no, int r_status, int c_no, String s_grade) {
		R_no = r_no;
		R_status = r_status;
		this.c_no = c_no;
		this.s_grade = s_grade;
	}

	public int getR_no() {
		return R_no;
	}

	public void setR_no(int r_no) {
		R_no = r_no;
	}

	public int getR_status() {
		return R_status;
	}

	public void setR_status(int r_status) {
		R_status = r_status;
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
