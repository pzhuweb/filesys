package cn.pzhu.javaweb.filesys.servlet.user;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.pzhu.javaweb.filesys.utils.ImageUtil;

/**
 * Servlet implementation class ImageServlet
 */
@WebServlet("/ImageServlet")
public class ImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//禁止缓存
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-control",	"no-cache");
		response.setDateHeader("expires", 0);
		//设置响应内容的类型是一张图
		response.setContentType("image/jpeg");
		//生成图片
		BufferedImage bImage =ImageUtil.createImage(80, 25, 4);
		String right_code = ImageUtil.getScode();
		//将正确的验证码保存到session中
		request.getSession(true).setAttribute("right_code", right_code);
		//将图片输出到视图层
		System.out.println("绘制验证码:"+right_code);
		ImageIO.write(bImage, "JPEG", response.getOutputStream());		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
