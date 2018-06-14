package cn.pzhu.javaweb.filesys.servlet.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.pzhu.javaweb.filesys.dao.UserDAO;
import cn.pzhu.javaweb.filesys.dao.UserDAOImp;
import cn.pzhu.javaweb.filesys.pojo.User;
import cn.pzhu.javaweb.filesys.utils.Conver2MD5;
import cn.pzhu.javaweb.filesys.utils.ImageUtil;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String usercode = request.getParameter("usercode");
		System.out.println("可能被抓包截获的信息："+password);
		String right_code = ImageUtil.getScode();
		if (right_code!=null && usercode!=null &&right_code.equals(usercode.toLowerCase())) {
			UserDAO dao = new UserDAOImp();
			User user = dao.select(username);
			String db_pwd=Conver2MD5.getMD5(user.getPassword()+request.getSession().getAttribute("secret"));
			System.out.println("经过时间戳加密的数据库密码:"+db_pwd);
			request.getSession().setAttribute("secret","");
			if (user!=null && password.equals(db_pwd)) {
				request.getSession(true).setAttribute("user", user);
				//判断是否需要保存，如果是，则写入cookie，如果不是则清除cookie
				
				String isSave = request.getParameter("isSave");
				if (isSave!=null) {
					saveInfo(username, password, request, response);
				} else {
					deleteInfo(request, response);
				}				
				out.println("<script>alert('login success!');window.location.href='index.jsp'</script>");
			} else {
				out.println("<script>alert('login error!');window.location.href='login.jsp'</script>");
			}
		}else {
			out.println("<script>alert('checkcode is "+right_code+"!');window.location.href='login.jsp'</script>");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	
	protected void saveInfo(String username,String password,HttpServletRequest request, HttpServletResponse response){
		try {
			System.out.println("开始写入cookie");
			username = URLEncoder.encode(username,"UTF-8");
			Cookie cookie_username = new Cookie("username", username);
			cookie_username.setMaxAge(7*24*60*60);
			response.addCookie(cookie_username);
			
			password = URLEncoder.encode(password,"UTF-8");
			Cookie cookie_password = new Cookie("password", password);
			cookie_password.setMaxAge(7*24*60*60);
			response.addCookie(cookie_password);
			
			Cookie cookie_isSave = new Cookie("isSave", "yes");
			cookie_username.setMaxAge(7*24*60*60);
			response.addCookie(cookie_isSave);	
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}		
	}
	
	protected void deleteInfo(HttpServletRequest request, HttpServletResponse response){
		try {
			System.out.println("开始清除cookie");
			Cookie cookie_username = new Cookie("username", "");
			cookie_username.setMaxAge(0);
			response.addCookie(cookie_username);
			
			Cookie cookie_password = new Cookie("password", "");
			cookie_password.setMaxAge(0);
			response.addCookie(cookie_password);
			
			Cookie cookie_isSave = new Cookie("isSave", "no");
			cookie_isSave.setMaxAge(7*24*60*60);
			response.addCookie(cookie_isSave);	
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

}
