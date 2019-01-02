package bean;

public class FoodBean {

	private int foodId;
	private int foodPrice;
	private String foodName;
	private String foodImageUrl;
	private int foodTypeId;
	private int foodAttendantId;
	
	
	public FoodBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FoodBean(int foodId, int foodPrice, String foodName, String foodImageUrl, int foodTypeId,
			int foodAttendentId) {
		super();
		this.foodId = foodId;
		this.foodPrice = foodPrice;
		this.foodName = foodName;
		this.foodImageUrl = foodImageUrl;
		this.foodTypeId = foodTypeId;
		this.foodAttendantId = foodAttendentId;
	}
	public int getFoodId() {
		return foodId;
	}
	public void setFoodId(int foodId) {
		this.foodId = foodId;
	}
	public int getFoodPrice() {
		return foodPrice;
	}
	public void setFoodPrice(int foodPrice) {
		this.foodPrice = foodPrice;
	}
	public String getFoodName() {
		return foodName;
	}
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	public String getFoodImageUrl() {
		return foodImageUrl;
	}
	public void setFoodImageUrl(String foodImageUrl) {
		this.foodImageUrl = foodImageUrl;
	}
	public int getFoodTypeId() {
		return foodTypeId;
	}
	public void setFoodTypeId(int foodTypeId) {
		this.foodTypeId = foodTypeId;
	}
	public int getFoodAttendantId() {
		return foodAttendantId;
	}
	public void setFoodAttendantId(int foodAttendentId) {
		this.foodAttendantId = foodAttendentId;
	}
}
