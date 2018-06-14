package cn.pzhu.javaweb.filesys.servlet.file;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.pzhu.javaweb.filesys.dao.FileMsgDAO;
import cn.pzhu.javaweb.filesys.dao.FileMsgDAOImp;

/**
 * Servlet implementation class EditFileServlet
 */

public class EditFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditFileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		int fid = Integer.valueOf(request.getParameter("fid"));
		String filename = request.getParameter("filename");
		String note = request.getParameter("note");
		
		FileMsgDAO dao = new FileMsgDAOImp();
		if (dao.update(fid, filename, note)) {
			out.println("<script>alert('文件修改成功!');window.location.href='show.jsp'</script>");
		} else {
			out.println("<script>alert('文件修改失败!');window.location.href='show.jsp'</script>");
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
