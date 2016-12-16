package org.millardps.InventoryManagement;



import java.awt.Color;

import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;



public class BI{
	private static ArrayList<BI> BIStuff = new ArrayList<BI>();
	private int sMin, sMax;
	private boolean isButton;
	private int width, height, sX, sY;
	private Random rand = new Random();
	private BufferedImage image;
	private Image i;
	public int count;
	public long prevTime;
	private String name;
	private BufferedImage prevImage;
	private int rsx, rsy;
	private int clicks;
	private boolean DoubleClick;
	private int color;
	private boolean typeable;
	private boolean change;
	private boolean highlight;
	private ArrayList<Point> prevArr = new ArrayList<Point>();
	private BufferedImage largeImage;
	private ArrayList<BI> attachedBI = new ArrayList<BI>();
	private boolean picture;
	private ArrayList<Character> TextBox = new ArrayList<Character>();
	private ArrayList<String> Equation = new ArrayList<String>();
	

	public BI(int width, int height, int sX, int sY, boolean isB, boolean doubleClick){
		change = false;
		BIStuff.add(this);
		typeable = false;
		isButton = isB;
		DoubleClick = doubleClick;
		clicks = 0;
		count = 0;
		this.width = width;
		this.height = height;
		this.sX = sX;
		this.sY = sY;
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		prevImage = image;
		picture = false;
	}
	public BI(File f, int width, int height, int sX, int sY, boolean isB,boolean doubleClick){
		
try {
		image = ImageIO.read(f);
	} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
picture = true;
BIStuff.add(this);
	//	Test(f, width, height);
	//	image = (BufferedImage) i;
	isButton = isB;
	clicks = 0;
	DoubleClick = doubleClick;
	typeable = false;
		this.width = width;
		this.height = height;
		this.sX = sX;
		this.sY = sY;
		try {
			prevImage = ImageIO.read(f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		
	}
	public ArrayList<Character> getTextArr(){
		return TextBox;
	}
	public void addToTB(Character e){
		TextBox.add(e);
	}
	public void setTypeable(boolean k){
		typeable = k;
	}
	public boolean getTypeable(){
		return typeable;
	}
	public void type(char k){
		TextBox.add(k);
	}
	public void deleteChar(){
		TextBox.remove(TextBox.size()-1);
	}
	public void removeFromTB(int i){
		TextBox.remove(i);
	}
	public void attach(BI b){
		attachedBI.add(b);
	}
	public ArrayList<BI> getAttachedBI(){
		return attachedBI;
	}
	public void move(int tdx, int tdy){
		this.sX += tdx;
		this.sY += tdy;
		for(int i = 0; i < attachedBI.size(); i++){
			attachedBI.get(i).move(tdx, tdy);
		}
		
	}
	private void fillMyCircle(Point k, int width, int color){
		int g;
		if(width > 40){
			 g = 720;
			
		}
		else{
			 g = 360;
		}
		int x = (int) k.getX();
		int y = (int) k.getY();
		int size = width;
		for(int i = size; i > 0; i--){
			for(int angle = 0; angle < g; angle++){
				int x1 = (int) (Math.cos((double) angle/(double) 2) * i) + x;
				int y1 = (int) (Math.sin((double) angle/(double) 2) * i) + y;
				//System.out.println(x1 + ", " + y1 +", " + i);
				try{
				image.setRGB(x1, y1, color);
				}
				catch(Exception e){
					
				}
			}
			
		}
		
	}
	public void drawLine(Point start, Point end, int width, int color){
		double slope = (double)(end.getY() - start.getY())/(double)(end.getX() - start.getX());
		int b = (int) start.getY();
		for(int x = 0; x < end.getX() - start.getX(); x++){
			//image.setRGB(x, (int) ((slope * x) + b), color);
			Point k = new Point(x, (int) ((slope * x) + b));
			fillMyCircle(k,width, color);
		}
	}
public void drawLine(int sx, int sy, int ex, int ey, int width, int color){
	double slope = (double)(ey - sy)/(double)(ex - sx);
	int b = (int) sy;
	for(int x = 0; x < ex - sx; x++){
		//image.setRGB(x, (int) ((slope * x) + b), color);
		Point k = new Point(x, (int) ((slope * x) + b));
		fillMyCircle(k,width, color);
	}
	}
	public void addPicture(BufferedImage x){
		image = x;
		this.setWidth(x.getWidth());
		this.setHeight(x.getHeight());
		
		
		
	}
	public void setSize(int widh, int heigt){
		
		boolean b;
		if(widh != width && height != heigt)
		{
			b = true;
			change = true;
			if(largeImage == null){
				largeImage = new BufferedImage(widh, heigt, BufferedImage.TYPE_INT_RGB);
				
				for(int i = 0; i < largeImage.getWidth(); i++){
					for(int c = 0; c < largeImage.getHeight(); c++){
						if(i < image.getWidth() && c < image.getHeight()){
						largeImage.setRGB(i, c, image.getRGB(i, c));
						}
						else{
							largeImage.setRGB(i, c, Color.WHITE.getRGB());
						}
					}
				}
			}
		}
		else {
			b = false;
			change = false;
		}
		width = widh;
		height = heigt;
		image = largeImage;
		if(b){
			change = true;
		System.out.println("Testing");
		}
		//fillCanvas(color);
				
	}
	
	public void createLargeImage(int wid, int heigh){
		largeImage = new BufferedImage(wid, heigh, BufferedImage.TYPE_INT_RGB);
		System.out.println(wid + ", " + heigh);
		for(int i = 0; i < largeImage.getWidth(); i++){
			for(int c = 0; c < largeImage.getHeight(); c++){
				if(i < image.getWidth() && c < image.getHeight()){
				largeImage.setRGB(i, c, image.getRGB(i, c));
				}
				else{
					largeImage.setRGB(i, c, Color.WHITE.getRGB());
				}
			}
		}
	}
public boolean getB(){
	return change;
}
	public void Test(File f, int width, int height){
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		BufferedImage t = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		try {
			 t =  ImageIO.read(f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int r = 0; r < t.getHeight(); r++){
			for(int c = 0; c < t.getWidth(); c++){
				image.setRGB(c, r, t.getRGB(c, r));
			}
		}
	}
	public ArrayList<BI>getStaticBIArr(){
		return BIStuff;
	}
	public void setStaticBIArr(int index, BI b){
		BIStuff.set(index, b);
	}
	public void setDoubleClick(boolean doubleClick){
		DoubleClick = DoubleClick;
	}
	public boolean isDoubleClick(){
		return DoubleClick;
	}
	public void clicked(){
		clicks++;
	}
	public int getClicks(){
		return clicks;
	}
	   public boolean isSmallerThan(BI b){
		   if(this.getBuffImage().getHeight() * this.getBuffImage().getWidth() < b.getBuffImage().getHeight() * b.getBuffImage().getWidth() )
			   return true;
		   else{
			   return false;
		   }
	   }
	   public void setClicks(int i){
		   clicks = i;
	   }
	   public boolean isButton(){
		   return isButton;
	   }
	   public void setRSX(int x){
		   rsx = x;
	   }
	   public void setRSY(int x){
		   rsy = x;
	   }
	   public int getRSX(){
		   return rsx;
	   }
	   public int getRSY(){
		   return rsy;
	   }
	   public void setColor(int rgb){
		   color = rgb;
	   }
	   public int getColor(){
		   return color;
	   }
	public void addName(String name){
		this.name = name;
	}
	public String getName(){
		return name;
	}
	public int getSX(){
		return sX;
	}
	public int getSY(){
		return sY;
	}
	public void setSX(int x){
		sX = x;
	}
	public void setSY(int x){
		sY = x;
	}
	public void addSX(int x){
		sX += x;
	}
	public void addSY(int x){
		sY += x;
	}
	public BufferedImage getBuffImage(){
		return image;
	}
	public void setBuffImage(BufferedImage i){
		image = i;
	}
	
	public void setSliderRange(int min, int max){
		sMin = min;
		sMax = max;
	}
	public int getSliderRangeMin(){
		return sMin;
	}
	public int getSliderRangeYMax(){
		return sMax;
	}
	public void setWidth(int x){
		width = x;
	}
	public void setHeight(int x){
		height = x;
	}
	public boolean sameImage(){
		Outerloop:
		for(int r = 0; r < image.getHeight(); r++){
			for(int c = 0; c < image.getWidth(); c++){
			//	 System.out.println(image.getRGB(c, r) +", " + prevImage.getRGB(c, r));
				if(image.getRGB(c, r) != prevImage.getRGB(c, r)){
					return false;
					
				}
			}
		}
	return true;
	}
	public void setPrevImage(BufferedImage i){
		prevImage = i;
	}
	public void setRGB(int c, int r, int rgb){
		image.setRGB(c, r, rgb);
	}
	public void ImagePressed(){
	
		for(int r = 0; r < image.getHeight(); r++){
			for(int c = 0; c < image.getWidth(); c++){
				 
				int rq = image.getRGB(c,r);
				System.out.println(getName());
			//	image.setRGB(c, r, Color.BLUE.getRGB());
				//System.out.println(Color.BLUE.getRGB());
				
			System.out.println(image.getRGB(c, r));
			image.setRGB(c,r,Color.WHITE.getRGB());
			}
		}
		
	}
	public void fillCanvas(int rgb){
		   for(int r = 0; r < image.getHeight(); r++){
			   for(int c = 0; c < image.getWidth(); c++){
				   image.setRGB(c, r, rgb);
				  // System.out.println(c +"," + r);
			   }
			 
		   }
		  
	   }
	public void randomCanvas(){
		int rg = new Color(rand.nextInt(255),rand.nextInt(255),rand.nextInt(255)).getRGB();
		for(int r = 0; r < image.getHeight(); r++){
			   for(int c = 0; c < image.getWidth(); c++){
				  // int red = rand.nextInt();
				   //int green = rand.nextInt();
				   //int blue = rand.nextInt();
				   //int rgb = (int) Math.sqrt((red*red + blue*blue + green*green));
				  
				   image.setRGB(c, r, rg);
				  // System.out.println(c +"," + r);
			   }
			 
		   }
	}
	public void ImageReleased(){
		//System.out.println();
		for(int r = 0; r < image.getHeight(); r++){
			for(int c = 0; c < image.getWidth(); c++){
				//System.out.println(prevImage.getRGB(c, r)+ ", " + image.getRGB(c, r));
				if(prevImage.getRGB(c, r)!= image.getRGB(c, r)){
					//System.out.println("PutThorug");
					image.setRGB(c, r, prevImage.getRGB(c, r));
				}
				}
			}
	}
	public void addDestroyDrawing(ArrayList<Point> pk, int color){
		for(int i = 0; i < pk.size(); i++){
			setRGB((int)pk.get(i).getX(), (int)pk.get(i).getY(), color);
		}
		if(prevArr.isEmpty()){
			prevArr = pk;
		}
		else if(!prevArr.containsAll(pk)){
			ArrayList<Point> ml = pointToRemove(prevArr);
			System.out.println("Screw it");
	destroyDrawing(ml);
	prevArr = pk;
		}
	}
	public void addDrawing(ArrayList<Point> pk, int color){
		for(int i = 0; i < pk.size(); i++){
			setRGB((int)pk.get(i).getX(), (int)pk.get(i).getY(), color);
		}
		prevArr = pk;
	}

	public void destroyDrawing(ArrayList<Point> pk){
		for(int i = 0; i < pk.size(); i++){
			setRGB((int)pk.get(i).getX(), (int)pk.get(i).getY(), Color.WHITE.getRGB());
		}
	}
	public ArrayList<Point> pointToRemove(ArrayList<Point> k){
		ArrayList<Point> arr = new ArrayList<Point>();
		for(int i = 0; i < k.size(); i++){
			Point m = surroundingPoints(k.get(i));
			if(!k.contains(m) && m != null){
				arr.add(k.get(i));
			}
		}
		return arr;
	}
	public Point surroundingPoints(Point k){
		int g;
//		if > 40){
//			 g = 720;
//			
//		}
		//else{
			 g = 360;
		//}
		int x = (int) k.getX();
		int y = (int) k.getY();
		//int size = circleSize;
		for(int angle = 0; angle < g; angle++){
			int x1 = (int) Math.round((Math.cos((double) angle/(double) 2))) + x;
			int y1 = (int) Math.round((Math.sin((double) angle/(double) 2))) + y;
			//System.out.println(x1 + ", " + y1 +", " + i);
		if(image.getRGB(x1, y1) != color){
			return new Point(x1, y1);
		}
		
		}
		return null;
	//	repaint();
	}	
//	public int centerTextX(String text){
//		int pL = text.length() * 10;
//		int padding = (siz - pL)/2;
//		return padding;
//	}
//	public int centerTextY(){
//		int pL=  10;
//		int padding = ( height- pL)/2;
//		return padding;
//	}
	
	public void highlightBorder(int color){
	if(!highlight){
		for(int i = 0; i < image.getHeight(); i++){
			image.setRGB(0, i, color);
			//image.setRGB(1, i, color);
			//image.setRGB(2, i, color);
			image.setRGB(image.getWidth() - 1, i, color);
			//image.setRGB(image.getWidth() - 2, i, color);
			//image.setRGB(image.getWidth() - 3, i, color);
		}
		for(int u = 0; u < image.getWidth(); u++){
			image.setRGB(u, 0, color);
			image.setRGB(u , image.getHeight() - 1, color);
		}
		//fillCanvas(color);
		System.out.println("High");
		//System.exit(0);
		highlight = true;
	}
	}
	public void setHighlight(boolean b){
		highlight = b;
	}
	public boolean getHighlight(){
		return highlight;
	}
	public ArrayList<String> getEquationArray(){
		return Equation;
	}
}
