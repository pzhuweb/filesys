package cn.pzhu.javaweb.filesys.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import cn.pzhu.javaweb.filesys.dao.FileMsgDAO;
import cn.pzhu.javaweb.filesys.dao.FileMsgDAOImp;
import cn.pzhu.javaweb.filesys.pojo.FileMsg;
import cn.pzhu.javaweb.filesys.pojo.User;

/**
 * Servlet Filter implementation class UpdateFilter
 */
@WebFilter(
		servletNames = { 
				"DeleteFileServlet", 
				"EditFileServlet"
		})
public class UpdateFilter implements Filter {

    /**
     * Default constructor. 
     */
    public UpdateFilter() {
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
		System.out.println("检测拥有者的过滤器已经运行！");
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		User user= (User) request2.getSession(true).getAttribute("user");
		int fid = Integer.valueOf(request2.getParameter("fid"));
		
		FileMsgDAO dao = new FileMsgDAOImp();
		FileMsg fileMsg = dao.select(fid);
		if (user!=null && user.getUsername().equals(fileMsg.getUsername())) {
			chain.doFilter(request, response);
		} else {
			out.println("<script>alert('你并非文件的上传者，不能修改或者删除!');window.location.href='show.jsp'</script>");
		}
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
