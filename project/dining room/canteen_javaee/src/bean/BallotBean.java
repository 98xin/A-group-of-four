package bean;

public class BallotBean {
	
	private int ballotId;
	private int ballotNum;
	private String foodName;
	private int foodId;
	private String ballotTime;
	
	public BallotBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BallotBean(int ballotId, int ballotNum, String foodName, int foodId, String ballotTime) {
		super();
		this.ballotId = ballotId;
		this.ballotNum = ballotNum;
		this.foodName = foodName;
		this.foodId = foodId;
		this.ballotTime = ballotTime;
	}

	public int getBallotId() {
		return ballotId;
	}

	public void setBallotId(int ballotId) {
		this.ballotId = ballotId;
	}

	public int getBallotNum() {
		return ballotNum;
	}

	public void setBallotNum(int ballotNum) {
		this.ballotNum = ballotNum;
	}

	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	public int getFoodId() {
		return foodId;
	}

	public void setFoodId(int foodId) {
		this.foodId = foodId;
	}

	public String getBallotTime() {
		return ballotTime;
	}

	public void setBallotTime(String ballotTime) {
		this.ballotTime = ballotTime;
	}
	
}
