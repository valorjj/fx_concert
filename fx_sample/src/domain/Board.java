package domain;

public class Board {

	// 1.�ʵ�
	private int b_no;
	private String b_title;
	private String b_writer;
	private String b_contents;
	private String b_date;
	private int m_no;
	private int b_view;
	
	// 2.������
	public Board() {} // ���� ������

	// ��� �ʵ带 �޴� ������
	public Board(int b_no, String b_title, String b_writer, String b_contents, String b_date, int m_no, int b_view) {
		this.b_no = b_no;
		this.b_title = b_title;
		this.b_writer = b_writer;
		this.b_contents = b_contents;
		this.b_date = b_date;
		this.m_no = m_no;
		this.b_view = b_view;
	}
	// �Խù���Ͻ� ������


	
	// �޼ҵ�
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
	public int getM_no() {return m_no;}
	public void setM_no(int m_no) {this.m_no = m_no;}
	public int getB_view() {return b_view;}
	public void setB_view(int b_view) {this.b_view = b_view;}
	
	
	
	
}
