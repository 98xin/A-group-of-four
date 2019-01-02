package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import bean.DetailBean;
import bean.FoodBean;
import bean.OrderBean;
import dao.DetailDao;
import dao.FoodDao;
import dao.OrderDao;

/**
 * Servlet implementation class DetailServlet
 */
@WebServlet("/DetailServlet")
public class DetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		//查询detail表中的食品名称，食品数量
		DetailDao detail= new DetailDao();
		List<DetailBean> detailList = new ArrayList<DetailBean>();
		detailList = detail.select();
		
		//获取order表中的订单状态，订单时间,总价
		OrderDao order = new OrderDao();
		List<OrderBean> orderList = new ArrayList<OrderBean>();
		orderList = order.select();
		
		//获取食品表中的食品价格，图片 
		FoodDao food = new FoodDao();
		List<FoodBean> foodList = new ArrayList<FoodBean>();
		foodList = food.selectFood();
		
		JSONArray array = new JSONArray();
		
		for(int i = 0; i<detailList.size(); ++i) {
			String foodName;
			int foodNum;
			String orderStatement;
			String orderTime;
			int orderTp;
			int foodPrice;
			String imageUrl;
			
			//获取食品名称和数量
			JSONObject json = new JSONObject();
			foodName = detailList.get(i).getFoodName();
			json.put("foodName", foodName);
			foodNum = detailList.get(i).getFoodNum();
			json.put("foodNum", foodNum);
			
			//获取订单状态，总价和时间
			for(int j = 0; j<orderList.size(); ++j) {
				if(detailList.get(i).getOrderId() == orderList.get(j).getOrderId()) {
					orderStatement = orderList.get(j).getOrderStatement();
					json.put("orderStatement", orderStatement);
					
					orderTp = orderList.get(j).getOrderTp();
					json.put("orderTp", orderTp);
					
					orderTime = orderList.get(j).getOrderTime();
					json.put("orderTime",orderTime);
					
				}
			}
			
			//获取食品单价和图片
			for(int k = 0; k<foodList.size(); ++k) {
				if(detailList.get(i).getFoodId() == foodList.get(k).getFoodId()) {
					foodPrice = foodList.get(k).getFoodPrice();
					json.put("foodPrice", foodPrice);
					
					imageUrl = foodList.get(k).getFoodImageUrl();
					json.put("imageUrl", imageUrl);
				}
			}
			array.put(json);
			
		}
		
		response.getWriter().append(array.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
