package cn.pzhu.javaweb.filesys.utils;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.UnsupportedEncodingException;
import java.util.Random;

public class ImageUtil {
	private static String scode="";

	public static String getScode() {
		return scode;
	}

	public static void setScode(String scode) {
		ImageUtil.scode = scode;
	}
	
	public static BufferedImage createImage(int width,int height,int n){
		BufferedImage bImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics graphics = bImage.getGraphics();
		graphics.setColor(Color.white);
		Font mFont = new Font("Arial", Font.BOLD, 22);
		graphics.setFont(mFont);	
		graphics.fillRect(0, 0, width, height);
		graphics.setColor(Color.black);
		char[] ele = "0123456789qwertyuioplkjhgfdsazxcvbnm".toCharArray();
		String codes = "";
		Random random = new Random();
		for (int i = 0; i < n; i++) {
			int index = random.nextInt(ele.length);
			codes=codes+ele[index];
		}
		scode = codes;
		graphics.drawString(scode, 10, 20);				
		return bImage;
	}
	public static BufferedImage createMulImage(int width,int height,int n){
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();    					//获取Graphics类的对象
		Graphics2D g2d = (Graphics2D) g;
		Random random = new Random();						//实例化一个Random对象
		Font mFont = new Font("黑体", Font.BOLD, 17);			//通过Font构造字体
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
		int itmp = 0;
		for (int i = 0; i < n; i++) {
			if((random.nextInt(2)+1)==1){
				itmp = random.nextInt(10) + 48; // 生成0~9的数字
				ctmp = String.valueOf((char) itmp);
			}else{
				itmp = random.nextInt(26) + 65; // 生成A~Z的字母
				ctmp = String.valueOf((char) itmp);
			}
			sRand += ctmp;
			Color color = new Color(20 + random.nextInt(110), 20 + random
					.nextInt(110), 20 + random.nextInt(110));
			g.setColor(color);		//设置文字颜色
			/** **随机缩放文字并将文字旋转指定角度* */
			// 将文字旋转指定角度
			Graphics2D g2d_word = (Graphics2D) g;
			AffineTransform trans = new AffineTransform();
			trans.rotate(random.nextInt(45) * 3.14 / 180, 15 * i + 8, 7);
			// 缩放文字
			float scaleSize = random.nextFloat() +0.8f;
			if (scaleSize > 1f)	scaleSize = 1f;
			trans.scale(scaleSize, scaleSize);
			g2d_word.setTransform(trans);
			/** ********************* */
			g.drawString(ctmp, 15 * i + 18, 14);

		}
		
		scode=sRand;
		return image;
	}
	// 获取随机颜色
		public static Color getRandColor(int s, int e) {
			Random random = new Random();
			if (s > 255) s = 255;
			if (e > 255) e = 255;
			int r = s + random.nextInt(e - s);		//随机生成RGB颜色中的r值
			int g = s + random.nextInt(e - s);		//随机生成RGB颜色中的g值
			int b = s + random.nextInt(e - s);		//随机生成RGB颜色中的b值
			return new Color(r, g, b);
		}

		public static BufferedImage createCNImage(int width, int height, int digit) {
			       							//指定验证码的高度
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
			scode = "";
			// 输出随机的验证文字
			String ctmp = "";
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
	            bytes[1] = (byte) tempHigh;
	            try {
					ctmp = new String(bytes,"GBK");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	//根据字节数组生成汉字
				scode += ctmp;
				Color color = new Color(20 + random.nextInt(110), 20 + random
						.nextInt(110), 20 + random.nextInt(110));
				g.setColor(color);
				/*****随机缩放文字并将文字旋转指定角度* */
				// 将文字旋转指定角度
				Graphics2D g2d_word = (Graphics2D) g;
				AffineTransform trans = new AffineTransform();
				trans.rotate(random.nextInt(45) * 3.14 / 180, 15 * i + 8, 7);
				// 缩放文字
				float scaleSize = random.nextFloat() +0.8f;
				if (scaleSize > 1f)	scaleSize = 1f;
				trans.scale(scaleSize, scaleSize);
				g2d_word.setTransform(trans);
				/** ********************* */
				g.drawString(ctmp, width/6 * i+23, height/3);
			}
			return image;
		}

}
