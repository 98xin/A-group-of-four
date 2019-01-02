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
 * Servlet implementation class SelectCook
 */
@WebServlet("/SelectCook")
public class SelectCook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectCook() {
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
				FoodDao cook = new FoodDao();
				List<FoodBean> list = cook.selectFoodByFoodType(2);
				JSONArray array = new JSONArray();
				for(int i = 0;i<list.size();++i) {
					JSONObject json = new JSONObject();
					json.put("foodName", list.get(i).getFoodName());
					json.put("foodPrice", list.get(i).getFoodPrice());
					json.put("foodImageUrl", list.get(i).getFoodImageUrl());
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
