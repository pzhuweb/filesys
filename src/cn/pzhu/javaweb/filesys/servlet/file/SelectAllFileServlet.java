package cn.pzhu.javaweb.filesys.servlet.file;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.pzhu.javaweb.filesys.dao.FileMsgDAO;
import cn.pzhu.javaweb.filesys.dao.FileMsgDAOImp;
import cn.pzhu.javaweb.filesys.pojo.FileMsg;
import cn.pzhu.javaweb.filesys.utils.DataUtil;

/**
 * Servlet implementation class SelectAllFileServlet
 */
@WebServlet("/SelectAllFileServlet")
public class SelectAllFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectAllFileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("flag", "true");
		FileMsgDAO dao = new FileMsgDAOImp();
		ArrayList<FileMsg> list = dao.selectAll();
		
		String spage = request.getParameter("page");
		int page = 1;
		try {
			page = Integer.valueOf(spage);
			if(page<1){
				throw new Exception();
			}
		} catch (Exception e) {
			page =1;
		}
		
		ArrayList<FileMsg> split_list = new DataUtil<FileMsg>().slipeList(list, DataUtil.PAGE_SIZE, page);
		StringBuffer bar = new DataUtil<FileMsg>().createBar(list, 5, page, this.getClass().getSimpleName(),"");
		
		request.getSession(true).setAttribute("list", split_list);
		request.getSession(true).setAttribute("bar", bar);
		request.getRequestDispatcher("show.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
