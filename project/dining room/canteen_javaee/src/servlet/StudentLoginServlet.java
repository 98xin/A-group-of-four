package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import bean.StudentBean;
import dao.StudentDao;

/**
 * Servlet implementation class StudentLoginServlet
 */
@WebServlet("/StudentLoginServlet")
public class StudentLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentLoginServlet() {
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
				
				String userName = request.getParameter("userName");
				String password = request.getParameter("passWord");
				boolean b = true;
				System.out.println(userName);
				StudentDao student = new StudentDao();
				
				List<StudentBean> studentList = student.select();
				
				if(userName == null || password == null) {
					JSONObject json = new JSONObject();
					json.put("message", "用户名或密码不能为空！");
					response.getWriter().println(json.toString());
				}else {
					for(int i = 0;i<studentList.size();++i) {
						if(userName.equals(studentList.get(i).getStudentName()) &&
								password.equals(studentList.get(i).getStudentPassword())) {
							JSONObject json = new JSONObject();
							json.put("message", "true");
							b = true;
							response.getWriter().println(json.toString());
						}else if(userName.equals(studentList.get(i).getStudentName()) &&
								!password.equals(studentList.get(i).getStudentPassword())){
							JSONObject json = new JSONObject();
							json.put("message", "密码错误");
							response.getWriter().println(json.toString());
						}else if(!userName.equals(studentList.get(i).getStudentName()) &&
								password.equals(studentList.get(i).getStudentPassword())) {
							JSONObject json = new JSONObject();
							json.put("message", "用户名错误");
							response.getWriter().println(json.toString());
						}else {
							b = false;
						}
					}
					
					if(b == false) {
						JSONObject json = new JSONObject();
						json.put("message", "false");
						response.getWriter().println(json.toString());
					}
				}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
