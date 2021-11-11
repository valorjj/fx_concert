package domain;

public class Concert {

	// 1. 필드
	private int c_no;
	private String c_title;
	private String c_artist;
	private String c_info;
	// 2. 생성자
	public Concert() {} // 깡통 생성자
	
	// 모든 필드를 받는 생성자
	public Concert(int c_no, String c_title, String c_artist, String c_info) {
		this.c_no = c_no;
		this.c_title = c_title;
		this.c_artist = c_artist;
		this.c_info = c_info;
	}

	
	// 3. 메소드

	public int getC_no() {return c_no;}
	public void setC_no(int c_no) {this.c_no = c_no;}
	public String getC_title() {return c_title;}
	public void setC_title(String c_title) {this.c_title = c_title;}
	public String getC_artist() {
		return c_artist;
	}

	public void setC_artist(String c_artist) {
		this.c_artist = c_artist;
	}

	public String getC_info() {
		return c_info;
	}

	public void setC_info(String c_info) {
		this.c_info = c_info;
	}
	
}
