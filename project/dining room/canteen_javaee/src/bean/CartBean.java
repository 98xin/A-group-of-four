package bean;

public class CartBean {
	private int studentId;
	private int foodId;
	private int counts;
	
	public CartBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CartBean(int studentId, int foodId,int counts) {
		super();
		this.studentId = studentId;
		this.foodId = foodId;
		this.counts = counts;
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
	public int getCounts() {
		return counts;
	}
	public void setCounts(int counts) {
		this.counts = counts;
	}
	
}
