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

import bean.CartBean;
import bean.FoodBean;
import dao.CartDao;
import dao.FoodDao;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
				request.setCharacterEncoding("utf-8");
				response.setContentType("text/html;charset=utf-8");
				JSONArray array = new JSONArray();
				
				FoodDao foodDao = new FoodDao();
				List<FoodBean> food = foodDao.selectFood();
				CartDao cartDao = new CartDao();
				List<CartBean> cartList = cartDao.select();
				for(int i = 0;i<cartList.size();++i) {
					String name;
					int price;
					String imageUrl;
					JSONObject json = new JSONObject();
					for(int j = 0;j<food.size();++j) {
						if(cartList.get(i).getFoodId() == food.get(j).getFoodId()) {
							name = food.get(j).getFoodName();
							price = (cartList.get(i).getCounts())*(food.get(j).getFoodPrice());
							imageUrl = food.get(j).getFoodImageUrl();
							json.put("name", name);
							json.put("price", price);
							json.put("image", imageUrl);
						}
					}
					json.put("counts", cartList.get(i).getCounts());
					array.put(json);
				}
				response.getWriter().println(array.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
