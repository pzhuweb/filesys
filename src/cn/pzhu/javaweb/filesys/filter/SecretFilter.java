package cn.pzhu.javaweb.filesys.filter;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import cn.pzhu.javaweb.filesys.utils.Conver2MD5;

/**
 * Servlet Filter implementation class SecretFilter
 */
@WebFilter(urlPatterns={"/login.jsp"})
public class SecretFilter implements Filter {

    /**
     * Default constructor. 
     */
    public SecretFilter() {
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
		System.out.print("生成时间戳：");
		HttpServletRequest request2 = (HttpServletRequest) request;
		String secret=Conver2MD5.getMD5(new Date().getTime()+"");	
		System.out.println(secret);
		request2.getSession(true).setAttribute("secret", secret);
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
