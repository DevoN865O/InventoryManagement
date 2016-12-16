package org.millardps.InventoryManagement;

import java.util.ArrayList;

public class CMenuBar {

	private BI MenuBar;
	private int width;
	private int height;
	private ArrayList<CMenu> cMenu = new ArrayList<CMenu>();
	public CMenuBar(int size, int h){
		MenuBar = new BI(size, h, 0, 0, false, false);
		width = size;
		height = h;
	}
	public BI getMenuBar(){
		return MenuBar;
	}
	public void setMenuBarWidth(int size){
		MenuBar.setWidth(size);
	}
	public void add(CMenu b){
		if(cMenu.isEmpty()){
			b.setCBarHeight(height);
		}
		cMenu.add(b);
	
	}
	public ArrayList<CMenu> getMenuArray(){
		return cMenu;
	}
}
