package domain;

public class Board {

	// 1.필드
	private int b_no;
	private String b_title;
	private String b_writer;
	private String b_contents;
	private String b_date;
	private int b_view;
	
	// 2.생성자
	public Board() {} // 깡통 생성자

	// 모든 필드를 받는 생성자
	public Board(int b_no, String b_title, String b_writer, String b_contents, String b_date, int b_view) {
		this.b_no = b_no;
		this.b_title = b_title;
		this.b_writer = b_writer;
		this.b_contents = b_contents;
		this.b_date = b_date;
		this.b_view = b_view;
		
	}
	// 게시물등록시 생성자


	
	// 메소드
	public int getB_no() {return b_no;}
	public void setB_no(int b_no) {this.b_no = b_no;}
	public String getB_title() {return b_title;}
	public void setB_title(String b_title) {this.b_title = b_title;}
	public String getB_writer() {return b_writer;}
	public void setB_writer(String b_writer) {this.b_writer = b_writer;}
	public String getB_contents() {return b_contents;}
	public void setB_contents(String b_contents) {this.b_contents = b_contents;}
	public String getB_date() {return b_date;}
	public void setB_date(String b_date) {this.b_date = b_date;}
	public int getB_view() {return b_view;}
	public void setB_view(int b_view) {this.b_view = b_view;}
	
	
	
	
}