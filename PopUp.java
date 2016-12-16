package org.millardps.InventoryManagement;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;



public class PopUp {
	private MyDispatcher m = new MyDispatcher();
	private KeyboardFocusManager manager;
	private BI background;
	
	public PopUp(int width, int height, int sx, int sy){
		
		background = new BI(width, height, sx, sy, false, false);
		background.fillCanvas(Color.GRAY.getRGB());
	
	}
	public BI getBackground(){
		return background;
	}
	public void setQuestion(String s){
		background.addName(s);
	}
	public void drawPopup(Graphics2D g2){
		g2.drawImage(background.getBuffImage(), background.getSX(), background.getSY(), null);
	}
	
	public void FocusKeys(){
		 manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
	        manager.addKeyEventDispatcher(m);
	}
	public void UnfocusKeys(){
		manager.removeKeyEventDispatcher(m);
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
