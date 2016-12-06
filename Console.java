package org.millardps.InventoryManagement;
import java.util.*;
import java.io.*;
public class Console{
	Scanner scanner=new Scanner(System.in);
	private File p= new File("pmem.txt");
	private File m= new File("mem.txt");
	public String letters[]={"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","1","2","3","4","5","6","7","8","9","0","~","`","!","@","#","$","%","^","&","*","_","-","=","+","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"," "};
	public String shifted[]={"m","l","8","q","A","P","R","W","C","h","n","k","1","7","z","B","o","9","2","g","f","6","p","O","0","a","e","3","j","Q","V","-","4","=","b","d","J","N"," ","X","","M","I","U","J","i","5","y","c","w","*","T","$","%","S","v","^","L","+","&","Y","u","#","D","_","","r","Z","H","!","s","@","F","t","x","G","E"};
	private String command;
	//PrintWriter pWrite= new PrintWriter("pmem.txt","UTF-8");
	Console(){
		System.out.println("Oxi Inventory Management [Version Alpha 0.1]");
		System.out.println("(c) 2016 MidWestOxidation Enterprise. All rights reserved.");
		System.out.println();
		nextCommand();
	}
	public void nextCommand(){
		System.out.print(">");
		command=scanner.nextLine();
		try{
			if(command.toLowerCase().equals("help")){
				help();
			}
			else if(command.toLowerCase().substring(0,3).equals("add")){
				if(command.length() > 3){
					add(command);
				}
				else{
					System.out.println("Appends items to the inventory.");
					System.out.println();
					System.out.println("USAGE:");
					System.out.println("add [/N] [/C] [/P] [/D]");
					System.out.println();
					System.out.println("  /N           Item name.");
					System.out.println("  /C           Item category.");
					System.out.println("  /P           Item price.");
					System.out.println("  /D           Item description.");
					nextCommand();
				}
			}
			else if(command.toLowerCase().equals("search")){
				search();
			}
			else if(command.toLowerCase().equals("sell")){
				sell();
			}
			else if(command.toLowerCase().equals("clear")){
				clear();
			}
			else if(command.toLowerCase().equals("exit")){	
			}
			else if(command.toLowerCase().substring(0,3).equals("cat")){
				System.out.println(command.substring(4,command.length()));
				nextCommand();
			}
			else if(command.toLowerCase().equals("passwd")){
				newPass();
				nextCommand();
			}
			else{
				System.out.println("'"+command+"' is not recognized as an internal command.");
				nextCommand();
			}
		}
		catch(StringIndexOutOfBoundsException e){
			System.out.println("'"+command+"' is not recognized as an internal command.");
			nextCommand();
		}
	}
	public void help(){
		System.out.println("For more specific information regarding a command, please type the command name.");
		System.out.println("ADD            Appends items to the inventory.");
		System.out.println("SEARCH         Searches the inventory for items.");
		System.out.println("SELL           Removes items from inventory, modifies drawer amount.");
		System.out.println("CAT            Prints the input to the console");
		System.out.println("PASSWD         Reset your password.");
		System.out.println("CLEAR          Clears the console.");
		System.out.println("EXIT           Closes the current session");
		nextCommand();
	}
	public void add(String command){
		String addString="";
		String name;
		String category;
		String price;
		String description;
		if(command.contains("/n")){
			int i=0;
			while(command.substring(i).contains("/n")){
				i++;
			}
			name=command.substring(i+1);
			i=name.length();
			while(name.contains("/")){
				i--;
			}
			addString+=name.substring(0,i).trim()+"-";
		}
		else{
			addString+="null-";
		}
		if(command.contains("/c")){
			int i=0;
			while(command.substring(i).contains("/c")){
				i++;
				System.out.println(1);
			}
			category=command.substring(i+1);
			i=category.length();
			while(category.contains("/")){
				i--;
				System.out.println(2);
			}
			addString+=category.substring(0,i).trim()+"-";
		}
		else{
			addString+="null-";
		}
		if(command.contains("/p")){
			int i=0;
			while(command.substring(i).contains("/p")){
				i++;
				System.out.println(3);
			}
			price=command.substring(i+1);
			i=price.length();
			while(price.contains("/")){
				i--;
				System.out.println(4);
			}
			addString+=price.substring(0,i).trim()+"-";
		}
		else{
			addString+="null-";
		}
		if(command.contains("/d")){
			int i=0;
			while(command.substring(i).contains("/d")){
				i++;
				System.out.println(5);
			}
			description=command.substring(i+1);
			i=description.length();
			while(description.contains("/")){
				i--;
				System.out.println(6);
			}
			addString+=description.substring(0,i).trim();
		}
		else{
			addString+="null";
		}
		System.out.println(addString);
		/**try{
			PrintWriter mWrite= new PrintWriter("mem.txt","UTF-8");
			mWrite.println(addString);
			mWrite.close();
		}
		catch(FileNotFoundException e){
			System.out.println("FileNotFoundException");
		}
		catch(UnsupportedEncodingException e){
			System.out.println("UnsupportedEncodingException");	
		}**/
		nextCommand();
	}
	public void search(){
		System.out.println("This feature has not yet been implemented");
		nextCommand();
	}
	public void sell(){
		System.out.println("This feature has not yet been implemented");
		nextCommand();
	}
	public void clear(){
		try{
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		}
		catch(InterruptedException e){
			System.out.println("InterruptedException");
		}
		catch(IOException e){
			System.out.println("IOException");
		}
		System.out.println("Oxi Inventory Management [Version Alpha 0.1]");
		System.out.println("(c) 2016 MidWestOxidation Enterprise. All rights reserved.");
		System.out.println();
		nextCommand();
	}
	public void newPass(){
		System.out.println("This feature has not yet been implemented");
		nextCommand();
	}
	//commandlist:
	//help
	//add
	//search
	//sell
	//clear
	//exit
	public String encode(String msg){
		String output="";
		for(int x=0;x<msg.length();x++){
			output+=shifted[indexOf(letters,msg.substring(x,x+1))];
		}
		return output;
	}
	public String decode(String msg){
		String output="";
		for(int x=0;x<msg.length();x++){
			output+=letters[indexOf(shifted,msg.substring(x,x+1))];
		}
		return output;
	}
	public int indexOf(String array[], String value){
		int index = -1;
		for (int i=0;i<array.length;i++) {
			if (array[i].equals(value)) {
				index = i;
				break;
			}
		}
		return index;
	}
	



}