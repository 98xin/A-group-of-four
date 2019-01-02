package bean;

public class DetailBean {
	private int detailId;
	private int studentId;
	private int foodId;
	private String foodName;
	private int orderId;
	private int foodNum;
	
	public int getDetailId() {
		return detailId;
	}
	public void setDetailId(int detailId) {
		this.detailId = detailId;
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
	public String getFoodName() {
		return foodName;
	}
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getFoodNum() {
		return foodNum;
	}
	public void setFoodNum(int foodNum) {
		this.foodNum = foodNum;
	}
	public DetailBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DetailBean(int detailId, int studentId, int foodId, String foodName, int orderId, int foodNum) {
		super();
		this.detailId = detailId;
		this.studentId = studentId;
		this.foodId = foodId;
		this.foodName = foodName;
		this.orderId = orderId;
		this.foodNum = foodNum;
	}
	
}
