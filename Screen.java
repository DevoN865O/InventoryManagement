package org.millardps.InventoryManagement;

import java.awt.Dimension;

import javax.swing.JFrame;

public class Screen  {
private JFrame frame;
private GUI g;
public Screen(){
	frame = new JFrame("Testing?");
	frame.setSize(800, 800);
	frame.setVisible(true);
	frame.setResizable(false);
	frame.setMinimumSize(new Dimension(800, 800));
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	g = new GUI();
	frame.add(g);
	frame.pack();
	g.run();
}
public void passToGUI(String s, boolean newLine){
 g.addToTextArray(s);
 g.addToNewLineArray(newLine);
}
public void passConsoleToGUI(Console c){
	g.setConsole(c);
}
public GUI getGUI(){
	return g;
}
}


