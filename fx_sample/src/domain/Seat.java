package domain;

public class Seat {

	// 1. �ʵ�
	private int s_no;			// �¼���ȣ
	private int s_status;		// �¼��������
	private int s_price;		// �¼� ����
	
	private String status;

	
	// 2. ������
	public Seat() {} // ���� ������

	// ��� �ʵ带 �޴� ������
	public Seat(int s_no, int s_status, int s_price) {
		this.s_no = s_no;
		this.s_status = s_status;
		this.s_price = s_price;
		
		if(s_status == 1) {status="����� �¼�";}
		else{status="�� �¼�";}
	}


		

	
	// 3. �޼ҵ�
	public int getS_no() {return s_no;}
	public void setS_no(int s_no) {this.s_no = s_no;}
	public int getS_status() {return s_status;}
	public void setS_status(int s_status) {this.s_status = s_status;}
	public int getS_price() {return s_price;}
	public void setS_price(int s_price) {this.s_price = s_price;}

	
}
