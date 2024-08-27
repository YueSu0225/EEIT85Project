package tw.RC.tutor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Currency;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import tw.RC.apis.Line;
import tw.RC.apis.MyClock;
import tw.RC.apis.MyPanelV2;

public class MySign extends JFrame {//1.
	private MyPanelV2 myPanel;//6.
	private JButton clear, undo, redo, color, saveObj, loadObj, saveJEPG, saveNow, loadJEPG, bgColor;
	private JSlider widthSlider;
	private File NowFile;
	private MyClock myclock;
	
	
	public MySign() {//2.
		super("Sign App");//4.
		
		myPanel = new MyPanelV2();//7.
		setLayout(new BorderLayout());//8.
		add(myPanel, BorderLayout.CENTER);//9.
		
		clear = new JButton("Clear");
		undo = new JButton("Undo");
		redo = new JButton("Redo");
		color = new JButton("Color");
		saveObj = new JButton("Save Object");
		loadObj = new JButton("Load Object");
		saveJEPG = new JButton("Save JEPG");
		saveNow = new JButton("Save Now");
		loadJEPG = new JButton("Load JEPG");
		bgColor = new JButton("Background Color");
		myclock = new MyClock();
		widthSlider = new JSlider(JSlider.HORIZONTAL, 1, 20, 6);

		JPanel top = new JPanel(new FlowLayout());
		top.add(clear);top.add(undo);top.add(redo);
		top.add(color);top.add(saveObj);top.add(loadObj);
		top.add(saveJEPG);top.add(saveNow);top.add(loadJEPG);top.add(bgColor);top.add(myclock);top.add(widthSlider);
		add(top, BorderLayout.NORTH);
		
		setSize(800, 600);//5.
		setVisible(true);//5.
		setDefaultCloseOperation(EXIT_ON_CLOSE);//5.
	
	
		initEvent();
	}
	
	private void initEvent() {
		clear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				myPanel.clear();
			}
		});
		undo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				myPanel.undo();
			}
		});
		redo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				myPanel.redo();
			}
		});
		color.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				changeColor();
			}
		});
		saveObj.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				saveObject();
			}
		});
		loadObj.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loadObject();
			}
		});
		saveJEPG.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//				try {
					myPanel.saveJEPG();
					System.out.println("OK");
//				} catch (Exception e1) {
//					System.out.println(e1);
//				}
			}
		});
		saveNow.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				saveNow();
			}
		});
		
		loadJEPG.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				myPanel.loadJEPG();
			}
		});
		
		bgColor.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				changebgColor();				
			}
		});
		
		
		 widthSlider.addChangeListener(new ChangeListener() {
	            @Override
	            public void stateChanged(ChangeEvent e) {
	                changeWidth();
	            }
	        });

		
	}
	
	private void changeColor() {
		Color newColor = JColorChooser.showDialog(null, "Change Color", myPanel.getColor());
		if(newColor != null) {
			myPanel.setColor(newColor);
		}
	}
	
	private void changebgColor() {
		Color newbgColor = JColorChooser.showDialog(null, "Change bgColor", myPanel.getColor());
		if(newbgColor != null) {
			myPanel.setBackground(newbgColor);
		}
	}
	
	private void saveObject() {
		JFileChooser jfc = new JFileChooser();
		if (jfc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
			File file = jfc.getSelectedFile();
			System.out.println(file.getAbsolutePath());
			try {
				myPanel.saveLines(file);
				JOptionPane.showMessageDialog(null, "Save Success!");
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Save Failure");				
				System.out.println(e);
			}
		}
	}
	
	private void loadObject() {
		JFileChooser jfc = new JFileChooser();
		if (jfc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			File file = jfc.getSelectedFile();
			System.out.println(file.getAbsolutePath());
			try {
				myPanel.loadLines(file);
				NowFile = file;
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Load Failure");				
				System.out.println(e);
			}
		}
	}
	
    private void saveNow() {
        if (NowFile != null) {
            try {
                myPanel.saveLines(NowFile); // 保存到当前文件
                JOptionPane.showMessageDialog(null, "Save Success!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Save Failure");
                System.out.println(e);
            }
        } else {
            JOptionPane.showMessageDialog(null, "No file loaded for saving.");
        }
    }
    
    
            
	
	
    private void changeWidth() {
        float newWidth = widthSlider.getValue(); 
        if (newWidth != 6) {
            myPanel.setWidth(newWidth);
        }
    }
    

	
	
	


	public static void main(String[] args) {
		new MySign();//3.	
	}


}
