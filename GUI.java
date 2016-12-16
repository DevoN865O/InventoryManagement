package org.millardps.InventoryManagement;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JPanel;

public class GUI extends JPanel implements MouseListener, Runnable, KeyListener{
	boolean open = false;
	private ArrayList<Character> CharArray = new ArrayList<Character>();
	private ArrayList<String> textArray = new ArrayList<String>();
	private ArrayList<Boolean> newLineArray = new ArrayList<Boolean>();
	private ArrayList<BI> buttonArray = new ArrayList<BI>();
	private Font font1 = new Font(Font.SERIF, Font.PLAIN, 15);
	private Font font2 = new Font(Font.SERIF, Font.BOLD, 15);
	private MyDispatcher m = new MyDispatcher();
	private KeyboardFocusManager manager;
	private Console console;
	//private BI helpButton;
	private CMenuBar CMenuBar;
	private CMenu show;
	private CMenu helpMenu;
	private CMenu searchMenu;
	private CMenu displayMenu;
	private CMenu clearMenu;
	private int fix = 0;
	private int paddingTop = 40;
	private PopUp test;
			
			
	public GUI(){
		this.setSize(800, 800);
		this.setVisible(true);
		this.addMouseListener(this);
		this.setBackground(Color.WHITE);
		//helpButton = new BI(100, 50, 20, 20, true, false);
		//helpButton.fillCanvas(Color.BLUE.getRGB());
		//buttonArray.add(helpButton);
		  CMenuBar = new CMenuBar((int) this.getWidth(), 20);
	        CMenuBar.getMenuBar().fillCanvas(Color.YELLOW.getRGB());
	        helpMenu = new CMenu(80, "help");
	        helpMenu.getCMenu().fillCanvas(Color.YELLOW.getRGB());
	        searchMenu = new CMenu(80, "Search");
	        searchMenu.getCMenu().fillCanvas(Color.YELLOW.getRGB());
	        displayMenu = new CMenu(80, "Display");
	        displayMenu.getCMenu().fillCanvas(Color.YELLOW.getRGB());
	      clearMenu = new CMenu(80, "Clear");
	        clearMenu.getCMenu().fillCanvas(Color.YELLOW.getRGB());
	        
	        CMenuBar.add(helpMenu);
	        CMenuBar.add(searchMenu);
	        CMenuBar.add(displayMenu);
	        CMenuBar.add(clearMenu);
	        test = new PopUp(200, 200, 70, 70);
	        
	        
		FocusKeys();
		//test("Pie");
		
	}
	

		
//		public void test(Object s){
//			String k = (String) s;
//			System.out.println(k);
//		}
//	
	public void FocusKeys(){
		 manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
	        manager.addKeyEventDispatcher(m);
	}
	public void UnfocusKeys(){
		manager.removeKeyEventDispatcher(m);
	}
	public void setConsole(Console c){
		console = c;
	}
	public Console getConsole(){
		return console;
	}
	public void addToTextArray(String s){
		textArray.add(s);
	}
	public void addToNewLineArray(boolean e){
		newLineArray.add(e);
	}
	public boolean isWithin(BI b){
		//b.getBuffImage().
		for(int r = 0; r < b.getBuffImage().getHeight(); r++){
			for(int c = 0; r < b.getBuffImage().getWidth(); c++){
				try{
					int mx =(int) getMousePosition().getX();
					int my =(int) getMousePosition().getY();
					//System.out.println(mx + ", " + my);
					//System.out.println(b.getBuffImage().getWidth());
					if(mx < b.getBuffImage().getWidth() +b.getSX() && mx > b.getSX() && my > b.getSY() && my < b.getBuffImage().getHeight() + b.getSY() ){
						return true;
					}
					else{
							return false;
						}
					
				}
				catch(Exception e){
					e.printStackTrace();
				}
				
			}
			
		}
		return false;
		
	}
	public void performButton(BI b){
		  // b.clicked();
		   for(int i = 0; i < CMenuBar.getMenuArray().size(); i++){
			   if(b == CMenuBar.getMenuArray().get(i).getCMenu()){
				//   System.out.println("HEEERE");
				   if(CMenuBar.getMenuArray().get(i).getText().equals("help")){
					   textArray.clear();
					   console.help();
				   }
				   else if(CMenuBar.getMenuArray().get(i).getText().equals("Display")){
					   textArray.clear();
					   console.display();
				   }
				   else if(CMenuBar.getMenuArray().get(i).getText().equals("Clear")){
					   System.out.println("PIeISGOOD");
					   textArray.clear();
					  // console.display();
				   }
				   show = CMenuBar.getMenuArray().get(i);
				   System.out.println(show);
				   break;
			   }
		   }
		   if(show == null){
			 //  System.out.println("YULO");
		   }
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		Graphics2D g3r = (Graphics2D) g;
		
		
		g2.setFont(font1);
		g3r.setFont(font2);
		
		System.out.println(textArray.size() + " HI");
		for(int i = 0; i < textArray.size(); i++){
			g3r.drawString(textArray.get(i), 100, paddingTop + font1.getSize() + font1.getSize()*i);
			
		}
		for(int i = 0; i < buttonArray.size(); i++){
			BI a = buttonArray.get(i);
			g2.drawImage(a.getBuffImage(), a.getSX(), a.getSY(), null);
		}
		test.drawPopup(g2);
		
		//CMenu Stuff
		  // System.out.println(show);
		   g2.drawImage(CMenuBar.getMenuBar().getBuffImage(), CMenuBar.getMenuBar().getSX(), CMenuBar.getMenuBar().getSY(), null);
		   for(int i = 0; i < CMenuBar.getMenuArray().size(); i++){
			   CMenu t =  CMenuBar.getMenuArray().get(i);
			   g2.drawImage(t.getCMenu().getBuffImage(), t.getCMenu().getSX(), t.getCMenu().getSY(), null);
			   //System.out.println(CMenuBar.getMenuArray().size());
			   //t.drawText();
			   g2.drawString(t.getText(), t.getCMenu().getSX()+ t.centerTextX(), t.getCMenu().getSY() + 10 + t.centerTextY());
			   //System.out.println(show);
			  
		   }
		   if(show != null && !show.getInCMenu()){
			   if(!open){
			   open = true;
			   }
			 //  System.out.println("HIGHS");
			   ArrayList<CMenu> p = show.getCMenuOptions();
			   
			   int height = CMenuBar.getMenuBar().getBuffImage().getHeight();
			//   System.out.println(p.size());
			   for(int i = 0; i < p.size(); i++){
				   //p.get(i).getCMenu().fillCanvas(Color.YELLOW.getRGB());
				   g2.drawImage(p.get(i).getCMenu().getBuffImage(), p.get(i).getCMenu().getSX(), p.get(i).getCMenu().getSY(), null);
				   g2.drawString(p.get(i).getText(), p.get(i).getCMenu().getSX() + p.get(i).centerTextX(), p.get(i).getCMenu().getSY() + 10 + p.get(i).centerTextY());
				  
				   height+= p.get(i).getCMenu().getBuffImage().getHeight();
				 //  System.out.println(p.get(i).getCMenu().getSY());
			   }
		   }
		   
		   else if(show != null && show.getWrappingCMenu() != null && open){
			 //  System.out.println(show);
			  
			//  System.out.println("GS");
			   ArrayList<CMenu> p = show.getCMenuOptions();
			 CMenu ty = show.getWrappingCMenu();
			 while(ty != null){
				// System.out.println("Display");
			 System.out.println("Show : " +  show);
				 System.out.println("TY : " + ty);
				// System.out.println("Test : " + test);
				 //System.out.println("HI : " + popup);
				 
			// if(ty.getWrappingCMenu() != null){
				 
				
				// System.out.println("GOTPastBreak");
				//if(ty.getInCMenu()){
			   ArrayList<CMenu> p2 = ty.getCMenuOptions();
				//}
				
				//	      System.out.println("SIZE : " + p2.size());
					      
				   for(int i = 0; i < ty.getCMenuOptions().size(); i++){
					   //p.get(i).getCMenu().fillCanvas(Color.YELLOW.getRGB());
					   g2.drawImage(p2.get(i).getCMenu().getBuffImage(), p2.get(i).getCMenu().getSX(), p2.get(i).getCMenu().getSY(), null);
					   g2.drawString(p2.get(i).getText(), p2.get(i).getCMenu().getSX() + p2.get(i).centerTextX(), p2.get(i).getCMenu().getSY() + 10 + p2.get(i).centerTextY());
					  
					   
					 //  System.out.println(p.get(i).getCMenu().getSY());
				   }
				   ty = ty.getWrappingCMenu();
			 }
			// }
					      
			   
			   int height = CMenuBar.getMenuBar().getBuffImage().getHeight();
			  // System.out.println(p.size());
			   for(int i = 0; i < p.size(); i++){
				  // System.out.println(p.get(i));
				  // System.out.println(p.get(i).getCMenu().getSX() + ", " + p.get(i).getCMenu().getSY());
				   //p.get(i).getCMenu().fillCanvas(Color.YELLOW.getRGB());
				   g2.drawImage(p.get(i).getCMenu().getBuffImage(), p.get(i).getCMenu().getSX(), p.get(i).getCMenu().getSY(), null);
				   g2.drawString(p.get(i).getText(),  p.get(i).getCMenu().getSX() + p.get(i).centerTextX(), p.get(i).getCMenu().getSY() + 10 + p.get(i).centerTextY());
				  
				   height+= p.get(i).getCMenu().getBuffImage().getHeight();
				 //  System.out.println(p.get(i).getCMenu().getSY());
				   //Soemhow it thinks that show is the thing it should be displaying
			   }
		   }
		   else{
			   if(show != null)
			
			//   System.out.println(show.getWrappingCMenu());
			   open = false;
		   }
//		   if(show != null){
//			   System.out.println(show.getWrappingCMenu());
//		   }
//		   else{
//			   System.out.println("WTF");
//		   }



	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		for(int i = 0; i < buttonArray.size(); i++){
			if(isWithin(buttonArray.get(i))){
				System.out.println("HELPING");
				console.help();
				System.out.println(textArray.size());
				
			}
		}
		Outerloop:
			for(int i = 0; i < CMenuBar.getMenuArray().size(); i++){
			//	System.out.println("HEAS");
			System.out.println(isWithin(CMenuBar.getMenuArray().get(i).getCMenu()));
			if(isWithin(CMenuBar.getMenuArray().get(i).getCMenu())){
				//System.out.println("FIRST");
				show = CMenuBar.getMenuArray().get(i);
				
				performButton(CMenuBar.getMenuArray().get(i).getCMenu());
				fix = 0;
				break;
			}
			else if(!isWithin(CMenuBar.getMenuArray().get(i).getCMenu()) && !open){
			//	System.out.println("IHERE");
				show = null;
				fix = 0;
			}
			else if(!isWithin(CMenuBar.getMenuArray().get(i).getCMenu()) && open){
			//	System.out.println("IMHEREEBOIS");
				if(fix == 0){
				boolean tada = false;
				if(show.getCMenuOptions().size() > 0){
				for(int g = 0; g < show.getCMenuOptions().size(); g++){
				//	System.out.println("IIP: " + show);
					if(isWithin(show.getCMenuOptions().get(g).getCMenu()) && fix == 0){
				//		System.out.println("UUUU");
						show = show.getCMenuOptions().get(g);
						//System.out.println(show);
						//System.out.println(show.hasWrapper());
						tada = true;
						fix = 0;
						break Outerloop;
					}
					
				}
				}
				//issues with never reaching thsi?
				System.out.println("T: " + tada);
				if(!tada){
				//	System.out.println("TADA");
					show = null;
					open = false;
					fix = 0;
				}
				}
				else{
				//	System.out.println("WTFFF");
					show = null;
					open = false;
					fix = 0;
				}
			}
			else{
				if(fix == 0){
				//System.out.println("Nullified");
				show = null;
				}
			}
			//System.out.println("OP " + open);
			}

		//Object b = new Object();
		//b.getClass().getName()
		repaint();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	private class MyDispatcher implements KeyEventDispatcher {
        @Override
        public boolean dispatchKeyEvent(KeyEvent e) {
//            if (e.getID() == KeyEvent.KEY_PRESSED) {
//                System.out.println("tester");
//            } else if (e.getID() == KeyEvent.KEY_RELEASED) {
//                System.out.println("2test2");
//            } 
         if (e.getID() == KeyEvent.KEY_TYPED) {
                System.out.println("Key Typed: " + e.getKeyChar());
              
            }
            return false;
        }
    }
}
