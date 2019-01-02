package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import bean.AttendantBean;
import dao.AttendantDao;

/**
 * Servlet implementation class AttendantLoginServlet
 */
@WebServlet("/AttendantLoginServlet")
public class AttendantLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AttendantLoginServlet() {
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
				
				AttendantDao attendant = new AttendantDao();
				
				List<AttendantBean> attendantList = attendant.getAllAttendant();
				
				//System.out.println(userName == null || password == null);
				if(userName == null || password == null) {
					JSONObject object = new JSONObject();
					object.put("message", "用户名或密码不能为空！");
					response.getWriter().println(object.toString());
				}else {
					for(int i = 0;i<attendantList.size();++i) {
						if(userName.equals(attendantList.get(i).getAttendantName()) &&
								password.equals(attendantList.get(i).getAttendantPassword())) {
							JSONObject object = new JSONObject();
							object.put("message", "true");
							b = true;
							response.getWriter().println(object.toString());
						}else if(userName.equals(attendantList.get(i).getAttendantName()) &&
								!password.equals(attendantList.get(i).getAttendantPassword())){
							JSONObject object = new JSONObject();
							object.put("message", "密码错误");
							response.getWriter().println(object.toString());
						}else if(!userName.equals(attendantList.get(i).getAttendantName()) &&
								password.equals(attendantList.get(i).getAttendantPassword())) {
							JSONObject object = new JSONObject();
							object.put("message", "用户名错误");
							response.getWriter().println(object.toString());
						}else {
							b = false;
						}
					}
					
					if(b == false) {
						JSONObject object = new JSONObject();
						object.put("message", "false");
						response.getWriter().println(object.toString());
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
