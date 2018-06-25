package cn.pzhu.javaweb.filesys.servlet.user;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CheckCode2
 */
@WebServlet("/CheckCode2")
public class CheckCode2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckCode2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "No-cache");
		response.setDateHeader("Expires", 0);
		// 指定生成的响应是图片
		response.setContentType("image/jpeg");
		int width = 166;       								//指定验证码的宽度
		int height = 45;        							//指定验证码的高度
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();    					//获取Graphics类的对象
		Graphics2D g2d = (Graphics2D) g;
		Random random = new Random();						//实例化一个Random对象
		Font mFont = new Font("黑体", Font.PLAIN, 24);			//通过Font构造字体
		g.setColor(getRandColor(200, 250)); 					//设置颜色
		g.fillRect(0, 0, width, height);    						//绘制验证码背景
		g.setFont(mFont);						//设置字体
		g.setColor(getRandColor(180, 200));		//设置颜色
		// 画随机的线条
		for (int i = 0; i < 100; i++) {
			int x = random.nextInt(width - 1);	//生成起始点x轴的坐标
			int y = random.nextInt(height - 1);	//生成起始点y轴的坐标
			int x1 = random.nextInt(6) + 1;	//生成结束点x轴的坐标
			int y1 = random.nextInt(12) + 1;	//生成结束点y轴的坐标
			BasicStroke bs = new BasicStroke(2f, BasicStroke.CAP_BUTT,
					BasicStroke.JOIN_BEVEL);
			Line2D line = new Line2D.Double(x, y, x + x1, y + y1);
			g2d.setStroke(bs);
			g2d.draw(line);     //绘制直线
		}
		String sRand = "";
		// 输出随机的验证文字
		String ctmp = "";
		int digit=4;		//随机获取验证码的位数
		for (int i = 0; i < digit; i++) {
			// 生成汉字
            String[] rBase = { "0", "1", "2", "3", "4", "5", "6", "7", "8",
                    "9", "a", "b", "c", "d", "e", "f" };
            // 生成第1位的区码
            int r1 = random.nextInt(3) + 11;	//生成11到14之间的随机数
            String str_r1 = rBase[r1];
            // 生成第2位的区码
            int r2;
            if (r1 == 13) {
                r2 = random.nextInt(7);			//生成0到7之间的随机数
            } else {
                r2 = random.nextInt(16);		//生成0到16之间的随机数
            }
            String str_r2 = rBase[r2];
            // 生成第1位的位码
            int r3 = random.nextInt(6) + 10;	//生成10到16之间的随机数
            String str_r3 = rBase[r3];
            // 生成第2位的位码
            int r4;
            if (r3 == 10) {
                r4 = random.nextInt(15) + 1;	//生成1到16之间的随机数
            } else if (r3 == 15) {
                r4 = random.nextInt(15);		//生成0到15之间的随机数
            } else {
                r4 = random.nextInt(16);		//生成0到16之间的随机数
            }
            String str_r4 = rBase[r4];
            // 将生成机内码转换为汉字
            byte[] bytes = new byte[2];
            //将生成的区码保存到字节数组的第1个元素中
            String str_r12 = str_r1 + str_r2;
            int tempLow = Integer.parseInt(str_r12, 16);
            bytes[0] = (byte) tempLow;
            //将生成的位码保存到字节数组的第2个元素中
            String str_r34 = str_r3 + str_r4;
            int tempHigh =Integer.parseInt(str_r34, 16);
            byte[] b = "啊".getBytes("GB2312");
            System.out.println(b.toString());
            bytes[1] = (byte) tempHigh;
            ctmp = new String(bytes,"GBK");	//根据字节数组生成汉字
			sRand += ctmp;
			Color color = new Color(20 + random.nextInt(110), 20 + random
					.nextInt(110), 20 + random.nextInt(110));
			g.setColor(color);
			/*****随机缩放文字并将文字旋转指定角度* */
			// 将文字旋转指定角度
			Graphics2D g2d_word = (Graphics2D) g;
			AffineTransform trans = new AffineTransform();
			trans.rotate(random.nextInt(45) * 3.14 / 180, 15 * i + 8, 7);
			// 缩放文字
			float scaleSize = random.nextFloat() +0.5f;
			if (scaleSize > 1f)	scaleSize = 1f;
			trans.scale(scaleSize, scaleSize);
			g2d_word.setTransform(trans);
			/** ********************* */
			g.drawString(ctmp, width/6 * i+23, height/3);

		}
		// 将生成的验证码保存到Session中
		HttpSession session = request.getSession(true);
		session.setAttribute("randCheckCode", sRand);
		g.dispose();
		ImageIO.write(image, "JPEG", response.getOutputStream());
	}
	// 获取随机颜色
	public Color getRandColor(int s, int e) {
		Random random = new Random();
		if (s > 255) s = 255;
		if (e > 255) e = 255;
		int r = s + random.nextInt(e - s);		//随机生成RGB颜色中的r值
		int g = s + random.nextInt(e - s);		//随机生成RGB颜色中的g值
		int b = s + random.nextInt(e - s);		//随机生成RGB颜色中的b值
		return new Color(r, g, b);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
