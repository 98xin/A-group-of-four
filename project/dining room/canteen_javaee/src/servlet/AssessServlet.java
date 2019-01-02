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

import bean.AssessBean;
import bean.FoodBean;
import bean.StudentBean;
import dao.AssessDao;
import dao.FoodDao;
import dao.StudentDao;


/**
 * Servlet implementation class AssessServlet
 */
@WebServlet("/AssessServlet")
public class AssessServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AssessServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		//查询assess评论表中的数据
		AssessDao assess = new AssessDao();
		List<AssessBean> assessList = new ArrayList<AssessBean>();
		assessList = assess.selectAssess();
		
		//获取food表中菜名
		FoodDao food = new FoodDao();
		List<FoodBean> foodList = new ArrayList<FoodBean>();
		foodList = food.selectFood();
		
		//获取学生学号
		StudentDao student = new StudentDao();
		List<StudentBean> studentList = new ArrayList<StudentBean>();
		studentList = student.select();
		
		JSONArray array = new JSONArray();
		
		for(int i=0; i<assessList.size(); ++i) {
			String assessContent;
			String foodName;
			String studentName;
			
			JSONObject json = new JSONObject();
			
			assessContent = assessList.get(i).getAssessContent();
			json.put("assessContent", assessContent);
			
			for(int j=0; j<studentList.size(); ++j) {
				if(assessList.get(i).getStudentId() == studentList.get(j).getStudentId()) {
					studentName = studentList.get(j).getStudentName();
					json.put("studentName", studentName);
				}
			}
			for(int k=0; k<foodList.size(); ++k) {
				if(assessList.get(i).getFoodId() == foodList.get(k).getFoodId()) {
					foodName = foodList.get(k).getFoodName();
					json.put("foodName", foodName);
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
