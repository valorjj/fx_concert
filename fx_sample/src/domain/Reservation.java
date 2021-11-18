package domain;

public class Reservation {

	private int r_no;
	private int s_no;
	private int c_no;
	private int m_no;
	private int r_unique_c_no;

	public Reservation(int r_no, int s_no, int c_no, int m_no) {
		this.r_no = r_no;
		this.s_no = s_no;
		this.c_no = c_no;
		this.m_no = m_no;
	}

	public Reservation(int s_no, int c_no, int m_no) {
		this.s_no = s_no;
		this.c_no = c_no;
		this.m_no = m_no;
	}

	public int getR_no() {
		return r_no;
	}

	public void setR_no(int r_no) {
		this.r_no = r_no;
	}

	public int getS_no() {
		return s_no;
	}

	public void setS_no(int s_no) {
		this.s_no = s_no;
	}

	public int getC_no() {
		return c_no;
	}

	public void setC_no(int c_no) {
		this.c_no = c_no;
	}

	public int getM_no() {
		return m_no;
	}

	public void setM_no(int m_no) {
		this.m_no = m_no;
	}

	public int getR_unique_c_no() {
		return r_unique_c_no;
	}

	public void setR_unique_c_no(int r_unique_c_no) {
		this.r_unique_c_no = r_unique_c_no;
	}

}
