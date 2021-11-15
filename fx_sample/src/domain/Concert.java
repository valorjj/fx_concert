package domain;

public class Concert {
	
	// c_no 를 꺼내서 활용하므로 필드에 c_no 를 생성한다.
	// 수업 때 사용한 Member 에는 m_no 를 필드에 포함시키지 않았다. 다른 테이블에 fk 로 들어가기는 했으나 m_no 자체를 꺼내 쓸 일이 없었다. 
	// c_no 는 1~6, 7~12, 13~18 등으로 구분할 것이므로 꺼내서 쓸 일이 있을 것이다. (현재 미구현)

	private int c_no;
	private String c_title;
	private String c_artist;
	private String c_info;
	private String c_date;
	private int c_time;

	private int c_R_no;
	private int c_S_no;
	private int c_D_no;
	private int c_E_no;

	private int c_R_price;
	private int c_S_price;
	private int c_D_price;
	private int c_E_price;
	
	
	
	
	public Concert(int c_no, String c_title, String c_artist, String c_info, String c_date, int c_time, int c_R_no,
			int c_S_no, int c_D_no, int c_E_no, int c_R_price, int c_S_price, int c_D_price, int c_E_price) {
		this.c_no = c_no;
		this.c_title = c_title;
		this.c_artist = c_artist;
		this.c_info = c_info;
		this.c_date = c_date;
		this.c_time = c_time;
		this.c_R_no = c_R_no;
		this.c_S_no = c_S_no;
		this.c_D_no = c_D_no;
		this.c_E_no = c_E_no;
		this.c_R_price = c_R_price;
		this.c_S_price = c_S_price;
		this.c_D_price = c_D_price;
		this.c_E_price = c_E_price;
	}
	public int getC_no() {
		return c_no;
	}
	public void setC_no(int c_no) {
		this.c_no = c_no;
	}
	public String getC_title() {
		return c_title;
	}
	public void setC_title(String c_title) {
		this.c_title = c_title;
	}
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
	public String getC_date() {
		return c_date;
	}
	public void setC_date(String c_date) {
		this.c_date = c_date;
	}
	public int getC_time() {
		return c_time;
	}
	public void setC_time(int c_time) {
		this.c_time = c_time;
	}
	public int getC_R_no() {
		return c_R_no;
	}
	public void setC_R_no(int c_R_no) {
		this.c_R_no = c_R_no;
	}
	public int getC_S_no() {
		return c_S_no;
	}
	public void setC_S_no(int c_S_no) {
		this.c_S_no = c_S_no;
	}
	public int getC_D_no() {
		return c_D_no;
	}
	public void setC_D_no(int c_D_no) {
		this.c_D_no = c_D_no;
	}
	public int getC_E_no() {
		return c_E_no;
	}
	public void setC_E_no(int c_E_no) {
		this.c_E_no = c_E_no;
	}
	public int getC_R_price() {
		return c_R_price;
	}
	public void setC_R_price(int c_R_price) {
		this.c_R_price = c_R_price;
	}
	public int getC_S_price() {
		return c_S_price;
	}
	public void setC_S_price(int c_S_price) {
		this.c_S_price = c_S_price;
	}
	public int getC_D_price() {
		return c_D_price;
	}
	public void setC_D_price(int c_D_price) {
		this.c_D_price = c_D_price;
	}
	public int getC_E_price() {
		return c_E_price;
	}
	public void setC_E_price(int c_E_price) {
		this.c_E_price = c_E_price;
	}
	
	

}
