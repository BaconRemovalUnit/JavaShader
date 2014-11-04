import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;


/*
 *filename: Shader.java
 *Author:SuizhuShengqi
 *Date 2014Äê10ÔÂ29ÈÕ
 *Class:CSC172
 *Lab Session:MW	18:15-19:30
 */


	public class Shader  extends JPanel{
		boolean drawn = false;
		boolean flag = true;
		int nanotime = (int) System.nanoTime();
		long a = System.currentTimeMillis();
		static boolean out = false;
		static int x =1600;//1000
		static int y = 900;//600
		static int u = 1;
		static int t = 0;
		static int DELAY = 500;		
		static String[] buttons = { "Yes", "No" };

		SimpleDateFormat df = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
		String date = (df.format(new Date()));
		String filename = "E:/Shader/";
		String name = filename.concat(date).concat(".jpg");
		
		File file = new File(name);
		
		public static void main (String[] args)
		{

			JFrame frame = new JFrame("Shader");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.getContentPane().add(new Shader(x,y));
			frame.setSize(x*u,y*u);
			frame.setLocationRelativeTo(null); 
			//frame.setResizable(false);
			frame.setVisible(true);
		}
		
		
		public Shader(int x, int y) {
			Timer timer;
			timer = new Timer(DELAY, new BoardListener());
			timer.start();
		}

		public void paint(Graphics g)
		{
			BufferedImage bi = new BufferedImage(x, y, BufferedImage.TYPE_INT_RGB);
			Graphics2D g2;
			g2 = (Graphics2D)bi.getGraphics();
			nanotime = (int) System.nanoTime();
			Color[][] pixel = new Color[x][y];
			Line2D line;
			g2.setBackground(Color.WHITE); 

			g2.clearRect(0, 0, x, y);
			int[][]  R = new int[x][y];
			int[][]  G = new int[x][y];
			int[][]  B = new int[x][y];
			int[][]  A = new int[x][y];
			
			for(int i = 0;i < x;i++)
			{
				for(int j = 0; j < y; j++)
				{
				R[i][j] = RD(i,j);
				G[i][j] = GR(i,j);
				B[i][j] = BL(i,j);
				A[i][j] = 100;//(int) Math.sin(i*j)%256;
				pixel[i][j] = new Color(R[i][j],G[i][j],B[i][j]);
				g.setColor(pixel[i][j]);
				g.drawLine(i, j,i, j);
				g2.setPaint(pixel[i][j]);
				line = new Line2D.Double(i,j,i,j);
				g2.draw(line);
				}
			}
			
			if(flag)
			 try {
				ImageIO.write(bi, "jpg", file);
				drawn = true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}
			//toFile();
}

		/**
		 * Color Red
		 * @param j 
		 * @param i 
		 * @return
		 */
		int RD(int i, int j) {


			int Xcord = (x/2 - i);
			int Ycord = y/2 - j;
			int B = +(int) (100*Math.sin(Math.sqrt(i+1000)));
			B += (int) (100*Math.sin(Math.sqrt(j+1000)));
			
			B = Math.abs(B);
			return B%255;
			
		}
		
		/**;
		 * Color Green
		 * @param j 
		 * @param i 
		 * @return
		 */
		int GR(int i, int j) {

			int Xcord = (x/2 - i);
			int Ycord = y/2 - j;
			int B = +(int) (100*Math.sin(Math.sqrt(i+1000)+Math.PI*4/3));
			B -= (int) (100*(Math.sin(Math.sqrt(j+1000)-Math.PI*2/3)));
			B = Math.abs(B);
			return B%255;
			}
		
		/**
		 * Color Blue
		 * @param j 
		 * @param i 
		 * @return
		 */
		int BL(int i, int j) {
			int Xcord = (x/2 - i);
			int Ycord = y/2 - j;
			int B = +(int) (100*Math.sin(Math.sqrt(i+1000)+Math.PI*2/3));
			B -= (int) (100*Math.sin(Math.sqrt(j+1000)-Math.PI*2/3));
			
			B = Math.abs(B);
			return B%255;
		}
		

		private class BoardListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e) 
			{	
				t++;
				repaint();
			}
		}
		
		private void toFile()
		{
			BufferedImage bi = new BufferedImage(x, y, BufferedImage.TYPE_INT_RGB);
			Graphics2D g2;
			g2 = (Graphics2D)bi.getGraphics();
			
			nanotime = (int) System.nanoTime();
			Color[][] pixel = new Color[x][y];
			Line2D line;
			int[][]  R = new int[x][y];
			int[][]  G = new int[x][y];
			int[][]  B = new int[x][y];
			int[][]  A = new int[x][y];
			
			g2.setBackground(Color.WHITE); 

			g2.clearRect(0, 0, x, y);
			
			for(int i = 0;i < x;i++)
			{
				for(int j = 0; j < y; j++)
				{
				R[i][j] = RD(i,j);
				G[i][j] = GR(i,j);
				B[i][j] = BL(i,j);
				A[i][j] = 100;//(int) Math.sin(i*j)%256;
				pixel[i][j] = new Color(R[i][j],G[i][j],B[i][j]);
				g2.setPaint(pixel[i][j]);
				line = new Line2D.Double(i,j,i,j);
				g2.draw(line);
				}
			}
			
			 try {
				ImageIO.write(bi, "jpg", file);
				drawn = true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			
			}
		}
	}

