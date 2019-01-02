package bean;

public class FoodTypeBean {

	private int foodTypeId;
	private String foodTypeName;
	
	public FoodTypeBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FoodTypeBean(int foodTypeId, String foodTypeName) {
		super();
		this.foodTypeId = foodTypeId;
		this.foodTypeName = foodTypeName;
	}

	public int getFoodTypeId() {
		return foodTypeId;
	}

	public void setFoodTypeId(int foodTypeId) {
		this.foodTypeId = foodTypeId;
	}

	public String getFoodTypeName() {
		return foodTypeName;
	}

	public void setFoodTypeName(String foodTypeName) {
		this.foodTypeName = foodTypeName;
	}
}
