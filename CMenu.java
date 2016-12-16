package org.millardps.InventoryManagement;

import java.util.ArrayList;

public class CMenu {
	private static ArrayList<CMenu> Menu = new ArrayList<CMenu>();
	private static int CBarHeight;
	private BI cMenu;
	private String text;
	private ArrayList<CMenu> cMenuOptions = new ArrayList<CMenu>();
	private int siz;
	private CMenu WrapCMenu;
	private int height;
	private boolean inCMenu;
	private boolean isOn;
public CMenu(int size, String word){
	if(Menu.isEmpty()){
	cMenu = new BI(size, 20, 0, 0, true, false);
	
	Menu.add(this);
	text = word;
	//drawText();
	}
	else{
	BI b = Menu.get(Menu.size() - 1).getCMenu();
	cMenu = new BI(size, 20, b.getSX()+size, 0, true, false);
	Menu.add(this);
	text = word;
	}
	siz = size;
	height = (int) cMenu.getBuffImage().getHeight();
	inCMenu = false;
	isOn =false;
}
public boolean isOn(){
	return isOn;
}
public void setIsOn(boolean k){
	isOn = k;
}
public void setCBarHeight(int x){
	CBarHeight = x;
}
public String getText(){
	return text;
}

public void setText(String txt){
	text = txt;
}
public BI getCMenu(){
	return cMenu;
}
public void setInCMenu(boolean k){
	inCMenu = k;
}
public boolean getInCMenu(){
	return inCMenu;
}
public void setWrappingCMenu(CMenu m){
	WrapCMenu = m;
}
public CMenu getWrappingCMenu(){
	return WrapCMenu;
}
public boolean hasWrapper(){
	if(WrapCMenu != null){
		return true;
	}
	else{
		return false;
	}
}
public void add(CMenu k, boolean inCbar){
	k.setInCMenu(true);
	k.setWrappingCMenu(this);
	if(inCbar){
	int height = CBarHeight;
	for(int i = 0; i< cMenuOptions.size(); i++){
		height+=cMenuOptions.get(i).getCMenu().getBuffImage().getHeight();
	}
	k.getCMenu().setSY(height);
	k.getCMenu().setSX(this.getCMenu().getSX());
	}
	else{
		k.getCMenu().setSX(this.getCMenu().getSX() + this.getCMenu().getBuffImage().getWidth());
		k.getCMenu().setSY(this.getCMenu().getSY() + this.getCMenu().getBuffImage().getHeight() * cMenuOptions.size() + 2);
	}
	cMenuOptions.add(k);
	
	
	
	
}
public void drawText(){
	if(text != null){
		for(int i = 0; i < Menu.size(); i++){
			if(Menu.get(i) == this){
				System.out.println("Drawn " + i );
				break;
			}
		}
		
		
		// uses xy coordinates relative to the BufferedImage
	cMenu.getBuffImage().getGraphics().drawString(text, 0, 0 + 10);
	
	}
	
	else{
		for(int i = 0; i < Menu.size(); i++){
			if(Menu.get(i) == this){
				if(Menu.get(i).getText() == null){
				System.out.println("NULL " + i );
				break;
				}
			}
	}
	}
}
public int centerTextX(){
	int pL = text.length() * 10;
	int padding = (siz - pL)/2;
	return padding;
}
public int centerTextY(){
	int pL=  10;
	int padding = ( height- pL)/2;
	return padding;
}
public void remove(CMenu k){
	for(int i = 0; i< Menu.size(); i++){
		if(Menu.get(i) == k){
			Menu.remove(i);
		}
	}
}
public static ArrayList<CMenu> getMenus(){
	return Menu;
}
public ArrayList<CMenu> getCMenuOptions(){
	return cMenuOptions;
}
}
