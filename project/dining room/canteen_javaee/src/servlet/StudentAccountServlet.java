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
 * Servlet implementation class StudentAccountServlet
 */
@WebServlet("/StudentAccountServlet")
public class StudentAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentAccountServlet() {
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
				String passWord = request.getParameter("passWord");
				boolean b = true;
				
				StudentDao student = new StudentDao();
				List<StudentBean> studentList = student.select();
				
				if("".equals(userName) || "".equals(passWord)) {
					JSONObject json = new JSONObject();
					json.put("message", "用户名或密码不能为空！");
					response.getWriter().println(json.toString());
				}else {
					for(int i = 0;i<studentList.size();++i) {
						if(userName.equals(studentList.get(i).getStudentName())) {
							JSONObject json = new JSONObject();
							json.put("message", "用户名已存在");
							response.getWriter().println(json.toString());
						}else {
							b = false;
						}
					}
					
					if(b == false) {
						StudentBean studentBean = new StudentBean();
						studentBean.setStudentName(userName);
						studentBean.setStudentPassword(passWord);
						student.insert(studentBean);
						JSONObject json = new JSONObject();
						json.put("message", "true");
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
