package cn.pzhu.javaweb.filesys.servlet.file;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspsmart.upload.Files;
import com.jspsmart.upload.SmartUpload;

import cn.pzhu.javaweb.filesys.dao.FileMsgDAO;
import cn.pzhu.javaweb.filesys.dao.FileMsgDAOImp;
import cn.pzhu.javaweb.filesys.pojo.User;

/**
 * Servlet implementation class UploadServlet
 */
@WebServlet("/UploadServlet")
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		//上传路径（虚拟路径）
		String path = "/upload/";
		//判断路径是否存在，如果不存在，则创建这个文件夹
		File file = new File(request.getServletContext().getRealPath("/")+path);
		if (!file.exists()) {
			file.mkdirs();
		}
		//使用第三方插件进行文件上传
		SmartUpload smart = new SmartUpload();
		smart.initialize(this.getServletConfig(), request, response);
		try {
			smart.upload();
			//获得session中的用户名
			User user = (User) request.getSession(true).getAttribute("user");
			//获得表单中的文件描述
			String note = smart.getRequest().getParameter("note");
			//获得表单中上传的文件
			Files files = smart.getFiles();
			for (int i = 0; i < files.getCount(); i++) {
				com.jspsmart.upload.File uploadfile = files.getFile(i);
				String filename = uploadfile.getFileName().replaceAll(" ", "");
				String filepath = path + System.currentTimeMillis()+filename;
				uploadfile.saveAs(filepath, com.jspsmart.upload.File.SAVEAS_VIRTUAL);
				
				FileMsgDAO dao = new FileMsgDAOImp();
				if (!dao.insert(user.getUsername(), filename, filepath, new Date(), note)) {
					throw new Exception();
				} 				
			}
			out.println("<script>alert('upload success!');window.location.href='show.jsp'</script>");
		} catch (Exception e) {
			e.printStackTrace();
			out.println("<script>alert('upload failed!');window.location.href='index.jsp'</script>");
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
