package bean;

public class StudentBean {
	private int studentId;
	private String studentName;
	private String studentPassword;
	
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getStudentPassword() {
		return studentPassword;
	}
	public void setStudentPassword(String studentPassword) {
		this.studentPassword = studentPassword;
	}
	public StudentBean(int studentId, String studentName, String studentPassword) {
		super();
		this.studentId = studentId;
		this.studentName = studentName;
		this.studentPassword = studentPassword;
	}
	public StudentBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
