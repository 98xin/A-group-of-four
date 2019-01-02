package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import bean.FoodBean;
import dao.FoodDao;

/**
 * Servlet implementation class SelectFoodServlet
 */
@WebServlet("/SelectFoodServlet")
public class SelectFoodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectFoodServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		JSONArray foodArray1 = new JSONArray();
		JSONArray foodArray2 = new JSONArray();
		JSONArray foodArray3 = new JSONArray();
		JSONArray foodArray4 = new JSONArray();
		JSONArray foodArray5 = new JSONArray();
		JSONArray foodArray6 = new JSONArray();
		JSONArray foodArray7 = new JSONArray();
		JSONArray foodArray8 = new JSONArray();
		
		JSONObject flag1 = new JSONObject();
		flag1.put("flag", 1);
		foodArray1.put(flag1);
		
		JSONObject flag2 = new JSONObject();
		flag2.put("flag", 2);
		foodArray2.put(flag2);
		
		JSONObject flag3 = new JSONObject();
		flag3.put("flag", 3);
		foodArray3.put(flag3);
		
		JSONObject flag4 = new JSONObject();
		flag4.put("flag", 4);
		foodArray4.put(flag4);
		
		JSONObject flag5 = new JSONObject();
		flag5.put("flag", 5);
		foodArray5.put(flag5);
		
		JSONObject flag6 = new JSONObject();
		flag6.put("flag", 6);
		foodArray6.put(flag6);
		
		JSONObject flag7 = new JSONObject();
		flag7.put("flag", 7);
		foodArray7.put(flag7);
		
		JSONObject flag8 = new JSONObject();
		flag8.put("flag", 8);
		foodArray8.put(flag8);
		
		
		FoodDao foodDao = new FoodDao();
		int[] foodType= {1,2,3,4,5,6,7,8};
		
		List<FoodBean> foodList1=foodDao.selectFoodByFoodType(1);
		List<FoodBean> foodList2=foodDao.selectFoodByFoodType(foodType[1]);
		List<FoodBean> foodList3=foodDao.selectFoodByFoodType(foodType[2]);
		List<FoodBean> foodList4=foodDao.selectFoodByFoodType(foodType[3]);
		List<FoodBean> foodList5=foodDao.selectFoodByFoodType(foodType[4]);
		List<FoodBean> foodList6=foodDao.selectFoodByFoodType(foodType[5]);
		List<FoodBean> foodList7=foodDao.selectFoodByFoodType(foodType[6]);
		List<FoodBean> foodList8=foodDao.selectFoodByFoodType(foodType[7]);
		
		for(int a=0;a<foodList1.size();++a) {
			String foodName;
			int foodPrice;
			String foodImageUrl;
			JSONObject json=new JSONObject();
			foodName=foodList1.get(a).getFoodName();
			foodPrice=foodList1.get(a).getFoodPrice();
			foodImageUrl=foodList1.get(a).getFoodImageUrl();
			json.put("foodName", foodName);
			json.put("foodPrice", foodPrice);
			json.put("foodImageUrl", foodImageUrl);
			foodArray1.put(json);
		}
		
		for(int b=0;b<foodList2.size();++b) {
			String foodName;
			int foodPrice;
			String foodImageUrl;
			JSONObject json=new JSONObject();
			foodName=foodList2.get(b).getFoodName();
			foodPrice=foodList2.get(b).getFoodPrice();
			foodImageUrl=foodList2.get(b).getFoodImageUrl();
			json.put("foodName", foodName);
			json.put("foodPrice", foodPrice);
			json.put("foodImageUrl", foodImageUrl);
			foodArray2.put(json);
		}
		
		for(int c=0;c<foodList3.size();++c) {
			String foodName;
			int foodPrice;
			String foodImageUrl;
			JSONObject json=new JSONObject();
			foodName=foodList3.get(c).getFoodName();
			foodPrice=foodList3.get(c).getFoodPrice();
			foodImageUrl=foodList3.get(c).getFoodImageUrl();
			json.put("foodName", foodName);
			json.put("foodPrice", foodPrice);
			json.put("foodImageUrl", foodImageUrl);
			foodArray3.put(json);
		}
		
		for(int d=0;d<foodList4.size();++d) {
			String foodName;
			int foodPrice;
			String foodImageUrl;
			JSONObject json=new JSONObject();
			foodName=foodList4.get(d).getFoodName();
			foodPrice=foodList4.get(d).getFoodPrice();
			foodImageUrl=foodList4.get(d).getFoodImageUrl();
			json.put("foodName", foodName);
			json.put("foodPrice", foodPrice);
			json.put("foodImageUrl", foodImageUrl);
			foodArray4.put(json);
		}
		
		for(int e=0;e<foodList5.size();++e) {
			String foodName;
			int foodPrice;
			String foodImageUrl;
			JSONObject json=new JSONObject();
			foodName=foodList5.get(e).getFoodName();
			foodPrice=foodList5.get(e).getFoodPrice();
			foodImageUrl=foodList5.get(e).getFoodImageUrl();
			json.put("foodName", foodName);
			json.put("foodPrice", foodPrice);
			json.put("foodImageUrl", foodImageUrl);
			foodArray5.put(json);
		}
	
		for(int f=0;f<foodList6.size();++f) {
			String foodName;
			int foodPrice;
			String foodImageUrl;
			JSONObject json=new JSONObject();
			foodName=foodList6.get(f).getFoodName();
			foodPrice=foodList6.get(f).getFoodPrice();
			foodImageUrl=foodList6.get(f).getFoodImageUrl();
			json.put("foodName", foodName);
			json.put("foodPrice", foodPrice);
			json.put("foodImageUrl", foodImageUrl);
			foodArray6.put(json);
		}
		
		for(int g=0;g<foodList7.size();++g) {
			String foodName;
			int foodPrice;
			String foodImageUrl;
			JSONObject json=new JSONObject();
			foodName=foodList7.get(g).getFoodName();
			foodPrice=foodList7.get(g).getFoodPrice();
			foodImageUrl=foodList7.get(g).getFoodImageUrl();
			json.put("foodName", foodName);
			json.put("foodPrice", foodPrice);
			json.put("foodImageUrl", foodImageUrl);
			foodArray7.put(json);
		}
		
		for(int h=0;h<foodList8.size();++h) {
			String foodName;
			int foodPrice;
			String foodImageUrl;
			JSONObject json=new JSONObject();
			foodName=foodList8.get(h).getFoodName();
			foodPrice=foodList8.get(h).getFoodPrice();
			foodImageUrl=foodList8.get(h).getFoodImageUrl();
			json.put("foodName", foodName);
			json.put("foodPrice", foodPrice);
			json.put("foodImageUrl", foodImageUrl);
			foodArray8.put(json);
		}
		response.getWriter().print(foodArray1.toString()+"\n"
				+foodArray2.toString()+"\n"
				+foodArray3.toString()+"\n"
				+foodArray4.toString()+"\n"
				+foodArray5.toString()+"\n"
				+foodArray6.toString()+"\n"
				+foodArray7.toString()+"\n"
				+foodArray8.toString());
//		response.getWriter().println(foodList1.toString());
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
