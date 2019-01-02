package bean;

public class AssessBean {
	private int assessId;
	private String assessContent;
	private int studentId;
	private int foodId;
	
	public AssessBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public AssessBean(int assessId, String assessContent, int studentId, int foodId) {
		super();
		this.assessId = assessId;
		this.assessContent = assessContent;
		this.studentId = studentId;
		this.foodId = foodId;
	}

	public int getAssessId() {
		return assessId;
	}
	public void setAssessId(int assessId) {
		this.assessId = assessId;
	}
	public String getAssessContent() {
		return assessContent;
	}
	public void setAssessContent(String assessContent) {
		this.assessContent = assessContent;
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public int getFoodId() {
		return foodId;
	}
	public void setFoodId(int foodId) {
		this.foodId = foodId;
	}
	
	
}
