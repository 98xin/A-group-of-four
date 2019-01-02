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

import bean.BallotBean;
import bean.FoodBean;
import dao.BallotDao;
import dao.FoodDao;

/**
 * Servlet implementation class UpdateBallotServlet
 */
@WebServlet("/UpdateBallotServlet")
public class UpdateBallotServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateBallotServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
			
		String foodName = request.getParameter("foodName");
		System.out.println(foodName);

		JSONArray ballotArray = new JSONArray();
		
		BallotDao ballotDao = new BallotDao();
		FoodDao foodDao = new FoodDao();
		
		List<BallotBean> ballotList = ballotDao.selectBallot();
		List<FoodBean> foodList = foodDao.selectFood();
		
		int foodId = 0;
		for(int x=0;x<foodList.size();++x) {
			System.out.println(foodList.get(x).getFoodName().equals(foodName));
			if(foodName.equals(foodList.get(x).getFoodName())) {
				foodId=foodList.get(x).getFoodId();
				break;
			}
			
		}
		
		int r = ballotDao.updateBallot(foodId);
		
		for(int i=0;i<ballotList.size();++i) {
			int ballotNum;
			String imageUrl;
			
			JSONObject json = new JSONObject();
			
			ballotNum = ballotList.get(i).getBallotNum();
			json.put("ballotNum", ballotNum);
			json.put("foodName", foodName);
			
			for(int j=0;j<foodList.size();++j) {
				if(ballotList.get(i).getFoodId()==foodList.get(j).getFoodId()) {
					imageUrl = foodList.get(j).getFoodImageUrl();
					json.put("imageUrl", imageUrl);
				}
			}
			
			ballotArray.put(json);
		}
		
		response.getWriter().println(ballotArray.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
