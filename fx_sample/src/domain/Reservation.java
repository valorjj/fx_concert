package domain;

public class Reservation {

	private int r_no;
	private int s_unique_no;
	private String s_grade;
	private int c_no;
	private int c_unique_no;
	private int m_no;
	private String c_title;

	public String getC_title() {
		return c_title;
	}

	public String getC_artist() {
		return c_artist;
	}

	private String c_artist;

	public Reservation(int s_unique_no, String s_grade, String c_title, String c_artist) {
		this.s_unique_no = s_unique_no;
		this.s_grade = s_grade;
		this.c_title = c_title;
		this.c_artist = c_artist;
	}

	public Reservation(int s_unique_no, String s_grade, int c_no) {
		this.s_unique_no = s_unique_no;
		this.s_grade = s_grade;
		this.c_no = c_no;
	}

	public Reservation(int s_unique_no, String s_grade, int c_no, int c_unique_no) {
		this.s_unique_no = s_unique_no;
		this.s_grade = s_grade;
		this.c_no = c_no;
		this.c_unique_no = c_unique_no;
	}

	public Reservation(int s_unique_no) {
		this.s_unique_no = s_unique_no;
	}

	public Reservation(int s_unique_no, String s_grade, int c_no, int c_unique_no, int m_no) {
		this.s_unique_no = s_unique_no;
		this.s_grade = s_grade;
		this.c_no = c_no;
		this.c_unique_no = c_unique_no;
		this.m_no = m_no;
	}

	public Reservation(int r_no, int s_unique_no, String s_grade, int c_no, int c_unique_no, int m_no) {
		this.r_no = r_no;
		this.s_unique_no = s_unique_no;
		this.s_grade = s_grade;
		this.c_no = c_no;
		this.c_unique_no = c_unique_no;
		this.m_no = m_no;
	}

	public int getR_no() {
		return r_no;
	}

	public void setR_no(int r_no) {
		this.r_no = r_no;
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

	public int getC_no() {
		return c_no;
	}

	public void setC_no(int c_no) {
		this.c_no = c_no;
	}

	public int getC_unique_no() {
		return c_unique_no;
	}

	public void setC_unique_no(int c_unique_no) {
		this.c_unique_no = c_unique_no;
	}

	public int getM_no() {
		return m_no;
	}

	public void setM_no(int m_no) {
		this.m_no = m_no;
	}

}
