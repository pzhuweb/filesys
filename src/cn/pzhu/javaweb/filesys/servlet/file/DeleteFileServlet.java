package cn.pzhu.javaweb.filesys.servlet.file;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.pzhu.javaweb.filesys.dao.FileMsgDAO;
import cn.pzhu.javaweb.filesys.dao.FileMsgDAOImp;

/**
 * Servlet implementation class DeleteFileServlet
 */

public class DeleteFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteFileServlet() {
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
		String filepath = request.getParameter("filepath");
		
		FileMsgDAO dao = new FileMsgDAOImp();
		
		String dir = request.getServletContext().getRealPath("/") + filepath;
		File file = new File(dir);
		
		if (file.exists()&&dao.delete(fid)) {
			file.delete();
			out.println("<script>alert('文件删除成功!');window.location.href='show.jsp'</script>");
		} else {
			out.println("<script>alert('文件删除失败!');window.location.href='show.jsp'</script>");
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
