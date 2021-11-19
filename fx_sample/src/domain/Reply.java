package domain;



public class Reply {

			
	private int		r_no; 
	private String	r_contents; 
	private String	r_writer; 
	private String	r_date; 
	private int		b_no;
			
	
	public Reply() {}

	

	public Reply(int r_no, String r_contents, String r_writer, String r_date, int b_no) {
		super();
		this.r_no = r_no;
		this.r_contents = r_contents;
		this.r_writer = r_writer;
		this.r_date = r_date;
		this.b_no = b_no;
	}




	public int getR_no() {
		return r_no;
	}

	public void setR_no(int r_no) {
		this.r_no = r_no;
	}

	public String getR_contents() {
		return r_contents;
	}

	public void setR_contents(String r_contents) {
		this.r_contents = r_contents;
	}

	public String getR_writer() {
		return r_writer;
	}

	public void setR_writer(String r_writer) {
		this.r_writer = r_writer;
	}

	public String getR_date() {
		return r_date;
	}

	public void setR_date(String r_date) {
		this.r_date = r_date;
	}

	public int getB_no() {
		return b_no;
	}

	public void setB_no(int b_no) {
		this.b_no = b_no;
	}
	
	
}
