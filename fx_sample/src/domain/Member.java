package domain;

public class Member {

	// 필드
	private int m_no;
	private String m_id;
	private String m_pw;
	private String m_name;
	private String m_email;
	private int m_age;
	private String m_sex;

	// 2. 생성자
	
		// 빈생성자
	public Member() {}

		// 회원번호를 제외 한 생성자[디비에서 가져올 사용되는것]
	public Member(String m_id, String m_pw, String m_name, String m_email, int m_age, String m_sex) {
		this.m_id = m_id;
		this.m_pw = m_pw;
		this.m_name = m_name;
		this.m_email = m_email;
		this.m_age = m_age;
		this.m_sex = m_sex;
	}
	//3. 메소드
	public int getM_no() {return m_no;}
	public void setM_no(int m_no) {this.m_no = m_no;}
	public String getM_id() {return m_id;}
	public void setM_id(String m_id) {this.m_id = m_id;}
	public String getM_pw() {return m_pw;}
	public void setM_pw(String m_pw) {this.m_pw = m_pw;}
	public String getM_name() {return m_name;}
	public void setM_name(String m_name) {this.m_name = m_name;}
	public String getM_email() {return m_email;}
	public void setM_email(String m_email) {this.m_email = m_email;}
	public int getM_age() {return m_age;}
	public void setM_age(int m_age) {this.m_age = m_age;}
	public String getM_sex() {return m_sex;}
	public void setM_sex(String m_sex) {this.m_sex = m_sex;}
	
} 