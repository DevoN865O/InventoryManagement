package org.millardps.InventoryManagement;
import java.io.*;
import java.util.*;
public class Console{
    Scanner scanner= new Scanner(System.in);
    private File p= new File("pmem.txt");
    private File m= new File("mem.txt");
    public String[] letters= new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "~", "`", "!", "@", "#", "$", "%", "^", "&", "*", "_", "-", "=", "+", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", " "};
    public String[] shifted= new String[]{"m", "l", "8", "q", "A", "P", "R", "W", "C", "h", "n", "k", "1", "7", "z", "B", "o", "9", "2", "g", "f", "6", "p", "O", "0", "a", "e", "3", "j", "Q", "V", "-", "4", "=", "b", "d", "J", "N", " ", "X", "", "M", "I", "U", "J", "i", "5", "y", "c", "w", "*", "T", "$", "%", "S", "v", "^", "L", "+", "&", "Y", "u", "#", "D", "_", "", "r", "Z", "H", "!", "s", "@", "F", "t", "x", "G", "E"};
    private String command;
    private ArrayList<String>tmp= new ArrayList<String>();
    private ArrayList<String>names= new ArrayList<String>();
    private ArrayList<String>prices= new ArrayList<String>();
    private ArrayList<String>categories= new ArrayList<String>();
    private ArrayList<String>descriptions= new ArrayList<String>();
    private ArrayList<String>quantities= new ArrayList<String>();
	private ArrayList<String>filter= new ArrayList<String>();
	Console(){
        popArray();
        System.out.println("Oxi Inventory Management [Version Alpha 0.1]");
        System.out.println("(c) 2016 MidWestOxidation Enterprise. All rights reserved.");
        System.out.println();
        nextCommand();
    }
	public void nextCommand(){
		//System.out.println("update");
		updateArrays();
        System.out.print(">");
        command = scanner.nextLine();
        System.out.println(command);
        try{
            if(command.toLowerCase().equals("help")){
                help();
            } 
			else if (command.toLowerCase().substring(0, 3).equals("add")){
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
                    System.out.println("  /Q           Item quantity.");
                    nextCommand();
                }
            } 
			else if(command.toLowerCase().equals("sell")){
                sell();
            } 
			else if(command.toLowerCase().equals("clear")){//claims command does not exist
                clear();
            } 
			else if(command.toLowerCase().equals("exit")){//claims command does not exist
                writeArray();
            } 
			else if(command.toLowerCase().substring(0, 3).equals("cat")){
                System.out.println(command.substring(4, command.length()));
                nextCommand();
            } 
			else if(command.toLowerCase().equals("passwd")){
                newPass();
                nextCommand();
            } 
			else if(command.toLowerCase().equals("about")){
                about();
            }
			else if(command.toLowerCase().equals("display")){
				for(int i=0;i<tmp.size();i++){
					System.out.println(tmp.get(i));
				}
				nextCommand();
			}
			else if(command.toLowerCase().equals("delete")){
				m.delete();
				tmp.clear();
				System.out.println("Inventory Cleared!");
				nextCommand();
			}
			else if(command.toLowerCase().substring(0,6).equals("search")){
                if(command.length()>6){
					search(command);
				}
				else{
					System.out.println("Searches the inventory based upon given criteria.");
                    System.out.println();
                    System.out.println("USAGE:");
                    System.out.println("search [/N] [/C] [/P] [/D]");
                    System.out.println();
                    System.out.println("  /N           Item name.");
                    System.out.println("  /C           Item category.");
                    System.out.println("  /P           Item price.");
                    System.out.println("  /D           Item description.");
                    System.out.println("  /Q           Item quantity.");
                    nextCommand();
				}
            } 
		
			else{
				//System.out.println("Else: " + command);
                System.out.println("'" + command + "' is not recognized as an internal command.");
                nextCommand();
            }
        }
        catch (StringIndexOutOfBoundsException e){
        	//System.out.println("Else: " + command);
            System.out.println("'" + command + "' is not recognized as an internal command.");
            nextCommand();
        }
    }
    public void help(){
        System.out.println("For more specific information regarding a command, please type the command name.");
        System.out.println("ADD            Appends items to the inventory.");
		System.out.println("DELETE         Clears all items from the inventory.");
        System.out.println("DISPLAY        Displays entire inventory.");
		System.out.println("SEARCH         Searches the inventory.");
        System.out.println("SELL           Removes items from inventory, modifies drawer amount.");
        System.out.println("CAT            Prints the input to the console");
        System.out.println("PASSWD         Reset your password.");
        System.out.println("ABOUT          About this product.");
        System.out.println("CLEAR          Clears the console.");
        System.out.println("EXIT           Closes the current session");
        nextCommand();
    }
    public void add(String command){
        int i;
        String addString="";
		String name;
		String category;
		String price;
		String description;
		String quantity;
        if(command.contains("/n")){
            i=0;
            while(command.substring(i).contains("/n")){
                i++;
            }
            name=command.substring(i+1);
            i=name.length();
            while(name.substring(0,i).contains("/")){
                i--;
            }
            addString+=name.substring(0,i).trim()+"-";
        }
		else{
            addString+="null-";
        }
        if(command.contains("/q")){
            i=0;
            while(command.substring(i).contains("/q")){
                i++;
            }
            quantity=command.substring(i+1);
            i=quantity.length();
            while(quantity.substring(0,i).contains("/")){
                i--;
            }
            addString+=quantity.substring(0,i).trim()+"-";
        }
		else{
            addString+="null-";
        }
        if(command.contains("/c")){
            i=0;
            while(command.substring(i).contains("/c")){
                i++;
            }
            category=command.substring(i+1);
            i=category.length();
            while(category.substring(0,i).contains("/")){
                i--;
            }
            addString+=category.substring(0,i).trim()+"-";
        }
		else{
            addString+="null-";
        }
        if(command.contains("/p")){
            i=0;
            while(command.substring(i).contains("/p")){
                i++;
            }
            price=command.substring(i+1);
            i=price.length();
            while(price.substring(0,i).contains("/")){
                i--;
            }
            addString+=price.substring(0,i).trim()+"-";
        }
		else{
            addString+="null-";
        }
        if(command.contains("/d")){
            i=0;
            while(command.substring(i).contains("/d")){
                i++;
            }
            description=command.substring(i+1);
            i=description.length();
            while(description.substring(0,i).contains("/")){
                i--;
            }
            addString+=description.substring(0,i).trim();
        }
		else{
            addString+="null";
        }
        tmp.add(addString);
        System.out.println("Item added to inventory: "+addString+".");
        nextCommand();
    }
    public void search(String command){
		String name="default0x0";
		String price="default0x0";
		String category="default0x0";
		String description="default0x0";
		String quantity="default0x0";
        if(command.contains("/n")){
            int i=0;
            while(command.substring(i).contains("/n")){
                i++;
            }
            name=command.substring(i+1);
            i=name.length();
            while(name.substring(0,i).contains("/")){
                i--;
            }
            name=name.substring(0,i).trim();
        }
		if(command.contains("/q")){
            int i=0;
            while(command.substring(i).contains("/q")){
                i++;
            }
            quantity=command.substring(i+1);
            i=quantity.length();
            while(quantity.substring(0,i).contains("/")){
                i--;
            }
            quantity=quantity.substring(0,i).trim();
        }
		if(command.contains("/c")){
            int i=0;
            while(command.substring(i).contains("/c")){
                i++;
            }
            category=command.substring(i+1);
            i=category.length();
            while(category.substring(0,i).contains("/")){
                i--;
            }
            category=category.substring(0,i).trim();
        }
		if(command.contains("/p")){
            int i=0;
            while(command.substring(i).contains("/p")){
                i++;
            }
            name=command.substring(i+1);
            i=price.length();
            while(name.substring(0,i).contains("/")){
                i--;
            }
            price=price.substring(0,i).trim();
        }
		if(command.contains("/d")){
            int i=0;
            while(command.substring(i).contains("/d")){
                i++;
            }
            description=command.substring(i+1);
            i=description.length();
            while(description.substring(0,i).contains("/")){
                i--;
            }
            description=description.substring(0,i).trim();
        }
		filter.clear();
		for(int i=0;i<tmp.size();i++){
			filter.add(tmp.get(i));
		}
		for(int i=0;i<filter.size();i++){
			//System.out.println("Name: " + name);
			//System.out.println(categories.contains(name));
			//System.out.println(categories);
			if((! name.equals("default0x0") && (! names.get(i).equals(name)))){
			filter.set(i, "!!!!remove!!!!");
			}
			if((! quantity.equals("default0x0") && (! quantities.get(i).equals(quantity)))){
				filter.set(i, "!!!!remove!!!!");
			}
			if((! category.equals("default0x0") && (! categories.get(i).equals(category)))){
				filter.set(i, "!!!!remove!!!!");
			}
			if((! price.equals("default0x0") && (! prices.get(i).equals(price)))){
				filter.set(i, "!!!!remove!!!!");
			}
			if((! description.equals("default0x0") && (! descriptions.get(i).equals(description)))){
				filter.set(i, "!!!!remove!!!!");
			}
//			if(filter.get(i).equals("!!!!remove!!!!")){
//				filter.remove(i);
//			}
		}
		for(int i = 0; i < filter.size(); i++){
			if(filter.get(i).equals("!!!!remove!!!!")){
				filter.remove(i);
			}
		}
		System.out.println(filter.size());
		for(int i=0;i<filter.size();i++){
			System.out.println(filter.get(i));
		}
		nextCommand();
    }
    public void about(){
        try{
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        }
        catch(InterruptedException e){
            System.out.println("InterruptedException");
        }
        catch(IOException e){
            System.out.println("IOException");
        }
        System.out.println("Oxi Inventory Management is a product developed by MidWestOxidation Enterprise (c) 2016.");
        System.out.println("The intended purpose was to create a simple, secure, and powerful interface that can be used to manage small businesses.");
        System.out.println("For more information, please visit http://www.midwestoxidation.com/oxi");
        System.out.println();
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
        catch (InterruptedException e){
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
	public void popArray(){
        try{
            Scanner scanner = new Scanner(m);
            while(scanner.hasNext()){
                tmp.add(decode(scanner.next()));
            }
        }
        catch(FileNotFoundException e){
            System.out.println("FileNotFoundException");
        }
    }
	public void updateArrays(){
		names.clear();
		quantities.clear();
		prices.clear();
		descriptions.clear();
		categories.clear();
		for(int i=0;i<tmp.size();i++){
			int x=0;
			while(! tmp.get(i).substring(0,x).contains("-")){
				x++;
			}
			names.add(tmp.get(i).substring(0,x-1).trim());
			int x1=x;
			while(! tmp.get(i).substring(x,x1).contains("-")){
				x1++;
			}
			quantities.add(tmp.get(i).substring(x,x1-1).trim());
			int x2=x1;
			while(! tmp.get(i).substring(x1,x2).contains("-")){
				x2++;
			}
			categories.add(tmp.get(i).substring(x1,x2-1).trim());
			int x3=x2;
			while(! tmp.get(i).substring(x2,x3).contains("-")){
				x3++;
			}
			prices.add(tmp.get(i).substring(x2,x3-1).trim());
			descriptions.add(tmp.get(i).substring(x3,tmp.get(i).length()-1).trim());
		}
	}
	public void writeArray(){
        m.delete();
        try{
            PrintWriter printWriter = new PrintWriter("mem.txt", "UTF-8");
            for (int i = 0; i < tmp.size(); ++i){
                printWriter.println(encode(tmp.get(i)));
            }
            printWriter.close();
        }
        catch(FileNotFoundException e){
            System.out.println("FileNotFoundException");
        }
        catch(UnsupportedEncodingException e){
            System.out.println("UnsupportedEncodingException");
        }
    }
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
	public int indexOf(String[] arrstring, String string) {
        int n = -1;
        for (int i = 0; i < arrstring.length; ++i) {
            if (!arrstring[i].equals(string)) continue;
            n = i;
            break;
        }
        return n;
    }
}