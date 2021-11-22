package domain;

public class Concert {

	@Override
	public String toString() {
		return c_title;
	}

	public class Concert_date {

		@Override
		public String toString() {
			return c_date;
		}
	}

	public class Concert_time {
		@Override
		public String toString() {
			return c_time + "";
		}
	}

	// 1. 필드
	private int c_no; // 콘서트 번호
	private String c_title; // 콘서트 이름
	private String c_artist; // 콘서트 가수
	private String c_info; // 콘서트 정보
	private String c_date; // 콘서트 날짜
	private String c_time; // 콘서트 시간
	private int c_R_no; // 콘서트 R석 개수
	private int c_S_no; // 콘서트 S석 개수
	private int c_D_no; // 콘서트 D석 개수
	private int c_E_no; // 콘서트 E석 개수
	private int c_R_price; // 콘서트 R석 가격
	private int c_S_price; // 콘서트 S석 가격
	private int c_D_price; // 콘서트 D석 가격
	private int c_E_price; // 콘서트 E석 가격
	private int c_unique_no; // 콘서트 고유번호

	// 2. 생성자
	public Concert() {
	} // 깡통 생성자

	// 모든 필드를 받는 생성자
	public Concert(int c_no, String c_title, String c_artist, String c_info, String c_date, String c_time, int c_R_no,
			int c_S_no, int c_D_no, int c_E_no, int c_R_price, int c_S_price, int c_D_price, int c_E_price,
			int c_unique_no) {
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
		this.c_unique_no = c_unique_no;
	}

	// 콘서트 등록시 생성자 [ 콘서트 번호제외 ]
	public Concert(String c_title, String c_artist, String c_info, String c_date, String c_time, int c_R_no, int c_S_no,
			int c_D_no, int c_E_no, int c_R_price, int c_S_price, int c_D_price, int c_E_price, int c_unique_no) {
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
		this.c_unique_no = c_unique_no;
	}

	// 콘서트 정보 불러올때 생성자
	public Concert(String c_title) {
		this.c_title = c_title;
		
		
	}
	// 콘서트 전체 좌석 생성자
	public Concert(int c_R_no, int c_S_no, int c_D_no , int c_E_no) {
		this.c_R_no = c_R_no;
		this.c_S_no = c_S_no;
		this.c_D_no = c_D_no;
		this.c_E_no = c_E_no;
	}

	public Concert(int c_no, int c_R_no, int c_S_no, int c_D_no, int c_E_no, int c_unique_no) {
		super();
		this.c_no = c_no;
		this.c_R_no = c_R_no;
		this.c_S_no = c_S_no;
		this.c_D_no = c_D_no;
		this.c_E_no = c_E_no;
		this.c_unique_no = c_unique_no;
	}

	public Concert(String c_title, String c_artist, String c_date, String c_time) {
		super();
		this.c_title = c_title;
		this.c_artist = c_artist;
		this.c_date = c_date;
		this.c_time = c_time;
	}

	// 3. 메소드
	public int getC_no() {return c_no;}
	public void setC_no(int c_no) {this.c_no = c_no;}
	public String getC_title() {return c_title;}
	public void setC_title(String c_title) {this.c_title = c_title;}
	public String getC_artist() {return c_artist;}
	public void setC_artist(String c_artist) {this.c_artist = c_artist;}
	public String getC_info() {return c_info;}
	public void setC_info(String c_info) {this.c_info = c_info;}
	public String getC_date() {return c_date;}
	public void setC_date(String c_date) {this.c_date = c_date;}
	public String getC_time() {return c_time;}
	public void setC_time(String c_time) {this.c_time = c_time;}
	public int getC_R_no() {return c_R_no;}
	public void setC_R_no(int c_R_no) {this.c_R_no = c_R_no;}
	public int getC_S_no() {return c_S_no;}
	public void setC_S_no(int c_S_no) {this.c_S_no = c_S_no;}
	public int getC_D_no() {return c_D_no;}
	public void setC_D_no(int c_D_no) {this.c_D_no = c_D_no;}
	public int getC_E_no() {return c_E_no;}
	public void setC_E_no(int c_E_no) {this.c_E_no = c_E_no;}
	public int getC_R_price() {return c_R_price;}
	public void setC_R_price(int c_R_price) {this.c_R_price = c_R_price;}
	public int getC_S_price() {return c_S_price;}
	public void setC_S_price(int c_S_price) {this.c_S_price = c_S_price;}
	public int getC_D_price() {return c_D_price;}
	public void setC_D_price(int c_D_price) {this.c_D_price = c_D_price;}
	public int getC_E_price() {return c_E_price;}
	public void setC_E_price(int c_E_price) {this.c_E_price = c_E_price;}
	public int getC_unique_no() {return c_unique_no;}
	public void setC_unique_no(int c_unique_no) {this.c_unique_no = c_unique_no;}

}
