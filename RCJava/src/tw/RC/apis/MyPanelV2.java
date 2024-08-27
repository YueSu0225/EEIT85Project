package tw.RC.apis;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MyPanelV2 extends JPanel {
	private LinkedList<Line> lines, recycle;
	private Color nowColor, nowbgColor;
	private float nowWidth;
	private BufferedImage image;

	
	public MyPanelV2() { //建構式裡面包含的是初始化
//		setBackground(Color.YELLOW);
		
		
		lines = new LinkedList<>();
		recycle = new LinkedList<>();
		nowbgColor = Color.YELLOW;
		nowWidth = 6;
		nowColor = Color.BLACK;
		MyListener myListener = new MyListener();
		
		addMouseListener(myListener);
		addMouseMotionListener(myListener);	
	}
	
	private class MyListener extends MouseAdapter {
		@Override
		public void mousePressed(MouseEvent e) {
			recycle.clear();
			
			Line line = new Line(nowColor, nowWidth);				
			Point point = new Point(e.getX(), e.getY());
			line.addPoint(point);
	
			lines.add(line);
			
		}
		@Override
		public void mouseDragged(MouseEvent e) {
			Point point = new Point(e.getX(), e.getY());
			lines.getLast().addPoint(point);
			
			repaint();//16.
		}
	}	
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D)g;
		
        if (image != null) {
            g.drawImage(image, 0, 0, this); // 绘制加载的图像
        }
	
		for (Line line : lines) {
			
			g2d.setColor(line.getColor());
			g2d.setStroke(new BasicStroke(line.getWidth()));
			
			for (int i = 1; i< line.size(); i++) {
				Point p0 = line.getPoint(i-1);
				Point p1 = line.getPoint(i);
				g2d.drawLine(p0.getX(), p0.getY(), p1.getX(), p1.getY());
			}
		}
		
	}
	
	public void clear() {
		lines.clear();
		repaint();
	}
	
	public void undo() {
		if (lines.size() > 0) {
			recycle.add(lines.removeLast());
			repaint();
		}

	}
	
	public void redo() {
		if (recycle.size() > 0) {
			lines.add(recycle.removeLast());
			repaint();
		}
	}
	
	public Color getColor() {
		return nowColor;
	}
	
	public void setColor(Color color) {
		nowColor = color;
	}

	public void setWidth(float width) {
		nowWidth = width;
	}
	
	public Color getbgColor() {
		return nowbgColor;
	}
//	
//	public void setbgColor(Color color) {
//		nowbgColor = color;
//	}
	
	public void saveLines(File saveFile) throws Exception {//saveLine(File saveFile 參數要型別+自訂名稱)
		ObjectOutputStream oout = new ObjectOutputStream(
				new FileOutputStream(saveFile));//new FileOutputStream(saveFile 帶入參數))
		oout.writeObject(lines);
		oout.flush();
		oout.close();
	}
	
	public void loadLines(File loadFile) throws Exception{
		try (ObjectInputStream oin = new ObjectInputStream(new FileInputStream(loadFile))){//自動關閉語法
			Object obj = oin.readObject();
			lines = (LinkedList<Line>)obj;
			repaint();
		}
	
	}
	
//	public void saveJEPG() throws Exception {
//		BufferedImage img = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
//		Graphics g = img.getGraphics();
//		paint(g);//将当前面板的内容绘制到 BufferedImage 中
//		
//		ImageIO.write(img, "jpg", new File("dir1/RC.jpg"));
//
//	}
	public void saveJEPG() {
        JFileChooser jfc = new JFileChooser();
        // 设定文件过滤器，仅允许保存 JPEG 文件
        jfc.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("JPEG Image", "jpg", "jpeg"));
        
        int userSelection = jfc.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = jfc.getSelectedFile();
            
            // 确保文件名具有 .jpg 扩展名
            if (!fileToSave.getName().endsWith(".jpg")) {
                fileToSave = new File(fileToSave.getAbsolutePath() + ".jpg");
            }
            
            try {
                BufferedImage img = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
                Graphics g = img.getGraphics();
                paint(g); // 将当前面板的内容绘制到 BufferedImage 中

                ImageIO.write(img, "jpg", fileToSave); // 保存图像到指定文件
                JOptionPane.showMessageDialog(this, "Image saved successfully!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Failed to save image: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
    public void loadJEPG() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("JPEG Image", "jpg", "jpeg"));
        
        int userSelection = fileChooser.showOpenDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToLoad = fileChooser.getSelectedFile();
            
            try {
                image = ImageIO.read(fileToLoad); // 读取图像
//                lines.clear(); // 清除现有绘图线条
//                recycle.clear();
                repaint(); // 重新绘制面板
                JOptionPane.showMessageDialog(this, "Image loaded successfully!");
                System.out.println("OK");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Failed to load image: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }


    
	
	
	
}
