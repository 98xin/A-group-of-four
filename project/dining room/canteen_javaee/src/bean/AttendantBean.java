package bean;

public class AttendantBean {

	private int attendantId;
	private String attendantName;
	private String attendantPassword;
	
	
	public int getAttendantId() {
		return attendantId;
	}
	public void setAttendantId(int attendantId) {
		this.attendantId = attendantId;
	}
	
	public String getAttendantPassword() {
		return attendantPassword;
	}
	public void setAttendantPassword(String attendantPassword) {
		this.attendantPassword = attendantPassword;
	}
	public String getAttendantName() {
		return attendantName;
	}
	public void setAttendantName(String attendantName) {
		this.attendantName = attendantName;
	}
	
	public AttendantBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AttendantBean(int attendantId, String attendantName, String attendantPassword) {
		super();
		this.attendantId = attendantId;
		this.attendantName = attendantName;
		this.attendantPassword = attendantPassword;
	}
	
}
