package cn.pzhu.javaweb.filesys.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class RootFilter
 */
@WebFilter(
		urlPatterns = { 
				"/edit.jsp", 
				"/index.jsp", 
				"/show.jsp", 
				"/download.jsp"
		}, 
		servletNames = { 
				"DeleteFileServlet", 
				"EditFileServlet", 
				"UploadServlet", 
				"CheckUserNameServlet"
		})
public class RootFilter implements Filter {

    /**
     * Default constructor. 
     */
    public RootFilter() {
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
		HttpServletRequest request2 = (HttpServletRequest)request;
		HttpServletResponse response2 = (HttpServletResponse)response;
		System.out.println("检测登录的过滤器已经开始运行！");
		if (request2.getSession(true).getAttribute("user")==null) {
			response2.sendRedirect("login.jsp");
		} else {
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
