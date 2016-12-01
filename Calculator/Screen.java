package org.mpsomaha.ImageStuff;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JSlider;

public class Screen extends JPanel {
	private JFrame frame;
	//ArrayList<Point> a = new ArrayList<Point>();
private JSlider slider;
	public Screen(){
		this.setVisible(true);
		this.setBackground(Color.BLUE);
		frame = new JFrame("Dynamic Pixel Change");
		frame.setSize(800, 600);
		frame.setResizable(true);
		frame.getBounds().getWidth();
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		DirectDrawDemo demo = new DirectDrawDemo(800, 600);
//		   slider = new JSlider(1, 50);
//		   demo.add(slider);
		 
		demo.setSH(gd.getDisplayMode().getHeight());
		demo.setSW(gd.getDisplayMode().getWidth());
		frame.add(demo);
		//frame.add(this);
		frame.pack();
		
		
		
		demo.run();
		//frame.add(this);
		//run();
		
	}
	
	
	

//	@Override
//	public void run() {
//		// TODO Auto-generated method stub
//		
//	}
	}

