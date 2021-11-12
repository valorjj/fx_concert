package domain;

public class Concert {

	// 1. �ʵ�
	private int c_no;			// �ܼ�Ʈ ��ȣ
	private String c_title;		// �ܼ�Ʈ �̸�
	private String c_artist;	// �ܼ�Ʈ ����
	private String c_info;		// �ܼ�Ʈ ����
	private String c_date;		// �ܼ�Ʈ ��¥
	private String c_time;		// �ܼ�Ʈ �ð�
	private int c_R_no;		// �ܼ�Ʈ R�� ����
	private int c_S_no;		// �ܼ�Ʈ S�� ����
	private int c_D_no;		// �ܼ�Ʈ D�� ����
	private int c_E_no;		// �ܼ�Ʈ E�� ����
	private int c_R_price; 	// �ܼ�Ʈ R�� ����
	private int c_S_price;	// �ܼ�Ʈ S�� ����
	private int c_D_price;	// �ܼ�Ʈ D�� ����
	private int c_E_price;	// �ܼ�Ʈ E�� ����
	
	// 2. ������
	public Concert() {} // ���� ������
	
	// ��� �ʵ带 �޴� ������
	public Concert(int c_no, String c_title, String c_artist, String c_info, String c_date, String c_time, int c_R_no,
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
	// �ܼ�Ʈ ��Ͻ� ������ [ �ܼ�Ʈ ��ȣ���� ]
	public Concert(String c_title, String c_artist, String c_info, String c_date, String c_time, int c_R_no, int c_S_no,
			int c_D_no, int c_E_no, int c_R_price, int c_S_price, int c_D_price, int c_E_price) {
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


	// 3. �޼ҵ�
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

}
