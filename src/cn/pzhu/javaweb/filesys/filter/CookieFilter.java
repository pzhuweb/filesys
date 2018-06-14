package cn.pzhu.javaweb.filesys.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.pzhu.javaweb.filesys.dao.UserDAO;
import cn.pzhu.javaweb.filesys.dao.UserDAOImp;
import cn.pzhu.javaweb.filesys.pojo.User;

/**
 * Servlet Filter implementation class CookieFilter
 */
@WebFilter("/login.jsp")
public class CookieFilter implements Filter {

    /**
     * Default constructor. 
     */
    public CookieFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("开始扫描Cookie!");
		
		HttpServletRequest request2 = (HttpServletRequest) request;
		HttpServletResponse response2 = (HttpServletResponse)response;
		response2.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response2.getWriter();
		Cookie[] cookies = request2.getCookies();
		String username="";
		String password = "";
		String isSave = "no";
		if (cookies!=null) {
			for (int i = 0; i < cookies.length; i++) {
				if ("username".equals(cookies[i].getName())) {
					username = URLDecoder.decode(cookies[i].getValue(), "UTF-8");
				} else if ("password".equals(cookies[i].getName())) {
					password = URLDecoder.decode(cookies[i].getValue(), "UTF-8");
				} else if("isSave".equals(cookies[i].getName())){
					isSave = cookies[i].getValue();
				}
			}
		}
		if ("yes".equals(isSave)) {
			UserDAO dao = new UserDAOImp();
			User user = dao.select(username);
			if (user!=null && password.equals(user.getPassword())) {
				request2.getSession(true).setAttribute("user", user);
				//判断是否需要保存，如果是，则写入cookie，如果不是则清除cookie			
				out.println("<script>alert('login success!');window.location.href='index.jsp'</script>");
			} else {
				out.println("<script>alert('login error!');window.location.href='login.jsp'</script>");
			}			
		}else{
			request2.setAttribute("isSave", isSave);
			chain.doFilter(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
