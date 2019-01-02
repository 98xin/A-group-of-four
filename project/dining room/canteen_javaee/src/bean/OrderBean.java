package bean;

public class OrderBean {
	private int orderId;
	private int orderTp;//总价
	private String orderStatement;
	private String orderTime;
	private int studentId;
	
	
	public OrderBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OrderBean(int orderId, int orderTp, String orderStatement, String orderTime, int studentId) {
		super();
		this.orderId = orderId;
		this.orderTp = orderTp;
		this.orderStatement = orderStatement;
		this.orderTime = orderTime;
		this.studentId = studentId;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getOrderTp() {
		return orderTp;
	}
	public void setOrderTp(int orderTp) {
		this.orderTp = orderTp;
	}
	public String getOrderStatement() {
		return orderStatement;
	}
	public void setOrderStatement(String orderStatement) {
		this.orderStatement = orderStatement;
	}
	public String getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	
	
}
