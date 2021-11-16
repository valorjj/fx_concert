package domain;

public class Seat {

	// 1. 필드
	private int s_no;			// 좌석번호
	private int s_status;		// 좌석예약상태
	private int s_price;		// 좌석 가격
	
	private String status;

	
	// 2. 생성자
	public Seat() {} // 깡통 생성자

	// 모든 필드를 받는 생성자
	public Seat(int s_no, int s_status, int s_price) {
		this.s_no = s_no;
		this.s_status = s_status;
		this.s_price = s_price;
		
		if(s_status == 1) {status="예약된 좌석";}
		else{status="빈 좌석";}
	}


		

	
	// 3. 메소드
	public int getS_no() {return s_no;}
	public void setS_no(int s_no) {this.s_no = s_no;}
	public int getS_status() {return s_status;}
	public void setS_status(int s_status) {this.s_status = s_status;}
	public int getS_price() {return s_price;}
	public void setS_price(int s_price) {this.s_price = s_price;}

	
}