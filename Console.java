package org.millardps.InventoryManagement;
import java.io.*;
import java.util.*;
public class Console{
    Scanner scanner= new Scanner(System.in);
	Screen screen;
    private File p= new File("pmem.txt");
    private File m= new File("mem.txt");
    public String[] letters= new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "~", "`", "!", "@", "#", "$", "%", "^", "&", "*", "_", "-", "=", "+", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", " "};
    public String[] shifted= new String[]{"m", "l", "8", "q", "A", "P", "R", "W", "C", "h", "n", "k", "1", "7", "z", "B", "o", "9", "2", "g", "f", "6", "p", "O", "0", "a", "e", "3", "j", "Q", "V", "-", "4", "=", "b", "d", "J", "N", " ", "X", "", "M", "I", "U", "J", "i", "5", "y", "c", "w", "*", "T", "$", "%", "S", "v", "^", "L", "+", "&", "Y", "u", "#", "D", "_", "", "r", "Z", "H", "!", "s", "@", "F", "t", "x", "G", "E"};
    private String command;
	private double gross;
    private ArrayList<String>tmp= new ArrayList<String>();
    private ArrayList<String>names= new ArrayList<String>();
    private ArrayList<String>prices= new ArrayList<String>();
    private ArrayList<String>categories= new ArrayList<String>();
    private ArrayList<String>descriptions= new ArrayList<String>();
    private ArrayList<String>quantities= new ArrayList<String>();
	private ArrayList<String>filter= new ArrayList<String>();
	private int amount;
	private boolean gone;
	Console(){
		String answer = "";
		popArray();
		while(true){
			try{
				System.out.println("Would you like to launch the GUI?");
				answer = scanner.nextLine();
			}
			catch(Exception e){
			}
			if(answer.toLowerCase().equals("y") || answer.toLowerCase().equals("yes")){
				screen = new Screen();
				screen.passConsoleToGUI(this);
				//screen.getGUI().getConsole().help();
				//System.out.println(a);
				break;
			}
			else if(answer.toLowerCase().equals("n") || answer.toLowerCase().equals("no")){
				clear();
				break;
			}
			else{
				System.out.println("Please type 'yes' or 'no'");
			}
		}
	gross=0;
    }
	public void nextCommand(){
		//writeln("update");
		updateArrays();
        write(">");
        command = scanner.nextLine();
        try{
            if(command.toLowerCase().equals("help")){
                help();
            }
			else if(command.equals("") || command==null){
				
			}
			else if (command.toLowerCase().substring(0, 3).equals("add")){
                if(command.length() > 3){
                    add(command);
                }
				else{
                    writeln("Appends items to the inventory.");
                    writeln("");
                    writeln("USAGE:");
                    writeln("add [/N] [/C] [/P] [/D]");
                    writeln("");
                    writeln("  /N           Item name.");
                    writeln("  /C           Item category.");
                    writeln("  /P           Item price.");
                    writeln("  /D           Item description.");
                    writeln("  /Q           Item quantity.");
                    if(screen==null){
						nextCommand();
					}
                }
            } 
			else if(command.toLowerCase().equals("sell")){
                sell(select());
				if(screen==null){ 
					nextCommand();
				}
            } 
			else if(command.toLowerCase().equals("clear")){//claims command does not exist
                clear();
            } 
			else if(command.toLowerCase().equals("exit")){//claims command does not exist
                writeArray();
            } 
			else if(command.toLowerCase().substring(0, 3).equals("cat")){
				if(command.length()==3){
					writeln("");
				}
                else{
					writeln(command.substring(4, command.length()));
                }
				if(screen==null){ nextCommand();}
            } 
			else if(command.toLowerCase().equals("save")){
				writeArray();
				tmp.clear();
				popArray();
				updateArrays();
				writeln("Saved successfully.");
				if(screen==null){ 
					nextCommand();
				}
			}
			else if(command.toLowerCase().equals("passwd")){
                newPass();
                if(screen==null){ 
					nextCommand();
				}
            } 
			else if(command.toLowerCase().equals("about")){
                about();
            }
			else if(command.toLowerCase().equals("gross")){
				gross();
				if(screen==null){ 
					nextCommand();
				}
			}
			else if(command.toLowerCase().substring(0,7).equals("display")){
				if(command.toLowerCase().contains("/i") && command.toLowerCase().contains("/a")){
					writeln("Input invalid.");
					if(screen==null){ 
						nextCommand();
					}
				}
				else if(command.toLowerCase().contains("/a")){
					display();
				}
				else if(command.toLowerCase().contains("/i")){
					displayDetail(select());
				}
				else{
					writeln("Displays items stored in the inventory");
                    writeln("");
                    writeln("USAGE:");
                    writeln("display [/I] || display [/A]");
                    writeln("");
                    writeln("  /I           Displays a specific item.");
                    writeln("  /A           Displays all items.");
                    if(screen==null){ 
						nextCommand();
					}
				}
			}
			else if(command.toLowerCase().substring(0,6).equals("delete")){
				delete(command);
			}
			else if(command.toLowerCase().substring(0,6).equals("search")){
                if(command.length()>6){
					search(command);
				}
				else{
					writeln("Searches the inventory based upon given criteria.");
                    writeln("");
                    writeln("USAGE:");
                    writeln("search [/N] [/C] [/P] [/D]");
                    writeln("");
                    writeln("  /N           Item name.");
                    writeln("  /C           Item category.");
                    writeln("  /P           Item price.");
                    writeln("  /D           Item description.");
                    writeln("  /Q           Item quantity.");
                    if(screen==null){ 
						nextCommand();
					}
				}
            } 
			else if(command.toLowerCase().contains("edit")){
				if(!command.contains("/n") && !command.contains("/q") && !command.contains("/p") && !command.contains("/d") && command.contains("/c")){
					writeln("Edit an item stored in the inventory. Set the values you would like to change, and upon execution you can select your item by ID. Any values you wish to remain the same may be omitted.");
                    writeln("");
                    writeln("USAGE:");
                    writeln("edit [/N] [/C] [/P] [/D] [/Q]");
                    writeln("");
                    writeln("  /N           Item name.");
                    writeln("  /C           Item category.");
                    writeln("  /P           Item price.");
                    writeln("  /D           Item description.");
                    writeln("  /Q           Item quantity.");
                    if(screen==null){ 
						nextCommand();
					}
				}
				else{
					edit(select(),command);
				}
			}
			else{
                writeln("'" + command + "' is not recognized as an internal command.");
                if(screen==null){ 
					nextCommand();
				}
            }
        }
        catch (StringIndexOutOfBoundsException e){
			e.printStackTrace();
        	writeln("'" + command + "' is not recognized as an internal command.");
            if(screen==null){ 
				nextCommand();
			}
        }
    }
	public void displayDetail(int y){
		if(y !=-1){
			String name;
			String quantity;
			String category;
			String price;
			String description;
			String item;
			item=tmp.get(y);
			int x=0;
			while(! item.substring(0,x).contains("-")){
				x++;
			}
			name =item.substring(0,x-1).trim();
			int x1=x;
			while(! item.substring(x,x1).contains("-")){
				x1++;
			}
			quantity=item.substring(x,x1-1).trim();
			int x2=x1;
			while(! item.substring(x1,x2).contains("-")){
				x2++;
			}
			category= item.substring(x1,x2-1).trim();
			int x3=x2;
			while(! item.substring(x2,x3).contains("-")){
				x3++;
			}
			price= item.substring(x2,x3-1).trim();
			description=item.substring(x3,item.length()).trim();
			writeln("ID:        "+String.valueOf(y));  		
			writeln("Name:      "+name);  		
			writeln("Qty:       "+quantity);  		
			writeln("Category:  "+category);  		
			writeln("Price:     "+price);  		
			writeln("Desc:      "+description); 
		}
		else{
			//writeln("The inventory is empty...");
		}
			if(screen==null){ 
				nextCommand();
			}
	}
	public int select(){
		try{
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		}
		catch(InterruptedException e){
			writeln("InterruptedException");
		}
		catch(IOException e){
			writeln("IOException");
		}
		if(tmp.size()>0){
			writeln("ID        Name      Qty       Category  Price     Description");
			for(int i=0;i<tmp.size();i++){
				write(i);
				for(int x=0;x<10-String.valueOf(i).length();x++){
					write(" ");
				}
				displayOne(i);
			}
			writeln("");
			writeln("Please enter an ID to continue.");
			Scanner scanner=new Scanner(System.in);
			int theInt=-1;
			while(true){
				write("SELECT>");
				try{
					theInt=scanner.nextInt();
				}
				catch(InputMismatchException e){
					scanner.next();
				}
				if(theInt>tmp.size()){
					writeln("An item of that ID does not exist.");
				}
				else if(theInt<0){
					writeln("Input invalid.");
				}
				else{
					break;
				}
			}
			return(theInt);
		}
		else{
			writeln("The inventory is empty...");
			return(-1);
		}
	}
	public void delete(String command){
		if(command.toLowerCase().contains("/a") && command.toLowerCase().contains("/i")){
			writeln("Input invalid.");
		}
		else if(command.toLowerCase().contains("/a")){
			m.delete();
			tmp.clear();
			writeln("Inventory Cleared!");
		}
		else if(command.toLowerCase().contains("/i")){
			try{
				if(select()!=-1){
					tmp.remove(select());
					writeln("Item cleared successfully.");
				}
				else{
					//writeln("The inventory is empty...");
				}
			}
			catch(IndexOutOfBoundsException e){
				writeln("An item of that ID does not exist.");
				tmp.remove(select());
			}
			
		}
		else{
			writeln("Removes item(s) from the inventory.");
            writeln("");
            writeln("USAGE:");
            writeln("delete [/a] || delete [/i]");
            writeln("");
            writeln("  /A           Clear the inventory.");
            writeln("  /I           Select an item to be removed.");
		}
			
	}
    public void help(){
        writeln("For more specific information regarding a command, please type the command name.");
        writeln("ADD            Appends items to the inventory.");
		writeln("DELETE         Removes item(s) from the inventory.");
        writeln("DISPLAY        Displays item(s) from the inventory.");
		writeln("EDIT           Modify an item in the inventory.");
		writeln("SEARCH         Searches the inventory.");
        writeln("SELL           Removes items from inventory, modifies drawer amount.");
		writeln("GROSS          Displays the gross income accumulated during the session.");
        writeln("CAT            Prints the input to the console");
        writeln("PASSWD         Reset your password.");
        writeln("ABOUT          About this product.");
        writeln("CLEAR          Clears the console.");
        writeln("EXIT           Closes the current session");
        if(screen==null){ 
			nextCommand();
		}
    }
	public void gross(){
		writeln('$'+String.valueOf(gross));
	}
	public void add(String command){
        try{
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
				addString+="    -";
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
				quantity=String.valueOf(Integer.valueOf(quantity.substring(0,i).trim()));
				addString+=quantity+"-";
			}
			else{
				addString+="    -";
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
				addString+="    -";
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
				price=String.valueOf(Integer.valueOf(price.substring(0,i).trim()));
				addString+=price+"-";
			}
			else{
				addString+="    -";
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
				addString+="    ";
			}
			tmp.add(addString);
			writeln("Item added to inventory!");
			if(screen==null){ 
				nextCommand();
			}
		}
		catch(NumberFormatException e){
			writeln("Please make sure you provide only numbers for the price and quantity attributes.");
			if(screen==null){
				nextCommand();
			}
		}
	}
	public void display(){
		if(tmp.size()==0){
			writeln("The inventory is empty...");
		}
		else{
			writeln("ID        Name      Qty       Category  Price     Description");
			for(int i=0;i<tmp.size();i++){
				String n="    ";
				String q="    ";
				String c="    ";
				String p="    ";
				String d="    ";
				int x=0;
				while(! tmp.get(i).substring(0,x).contains("-")){
					x++;
				}
				n=(tmp.get(i).substring(0,x-1).trim());
				int x1=x;
				while(! tmp.get(i).substring(x,x1).contains("-")){
					x1++;
				}
				q=(tmp.get(i).substring(x,x1-1).trim());
				int x2=x1;
				while(! tmp.get(i).substring(x1,x2).contains("-")){
					x2++;
				}
				c=(tmp.get(i).substring(x1,x2-1).trim());
				int x3=x2;
				while(! tmp.get(i).substring(x2,x3).contains("-")){
					x3++;
				}
				p=(tmp.get(i).substring(x2,x3-1).trim());
				d=(tmp.get(i).substring(x3).trim());
				//writeln(tmp.get(1));
				for(int m=0;m<tmp.size();m++){
					if(n.equals(names.get(m)) && q.equals(quantities.get(m)) && c.equals(categories.get(m)) && p.equals(prices.get(m)) && d.equals(descriptions.get(m))){
						write(m);
					}
				}
			
				//write(tmp.indexOf(String.valueOf(n+"-"+q+"-"+c+"-"+p+"-"+d)));
				for(int a=0;a<10-String.valueOf(a).length();a++){
					write(" ");
				}
				if(n.length()<10){
					write(n);
					for(int u=0;u<10-n.length();u++){
						write(" ");
					}
				}
				else{
					write(n.substring(0,9)+" ");
				}
				if(q.length()<10){
					write(q);
					for(int u=0;u<10-q.length();u++){
						write(" ");
					}
				}
				else{
					write(q.substring(0,9)+" ");
				}
				if(c.length()<10){
					write(c);
					for(int u=0;u<10-c.length();u++){
						write(" ");
					}
				}
				else{
					write(c.substring(0,9)+" ");
				}
				if(p.length()<10){
					write(p);
					for(int u=0;u<10-p.length();u++){
						write(" ");
					}
				}
				else{
					write(p.substring(0,9)+" ");
				}
				if(d.length()<10){
					writeln(d);
				}
				else{
					writeln(d.substring(0,10));
				}
			}
		}
		if(screen==null){ 
			nextCommand();
		}
	}
	public void displayOne(int i){
		if( i!=-1){
			String n;
			String q;
			String c;
			String p;
			String d;
			int x=0;
			while(! tmp.get(i).substring(0,x).contains("-")){
				x++;
			}
			n=(tmp.get(i).substring(0,x-1).trim());
			int x1=x;
			while(! tmp.get(i).substring(x,x1).contains("-")){
				x1++;
			}
			q=(tmp.get(i).substring(x,x1-1).trim());
			int x2=x1;
			while(! tmp.get(i).substring(x1,x2).contains("-")){
				x2++;
			}
			c=(tmp.get(i).substring(x1,x2-1).trim());
			int x3=x2;
			while(! tmp.get(i).substring(x2,x3).contains("-")){
				x3++;
			}
			p=(tmp.get(i).substring(x2,x3-1).trim());
			d=(tmp.get(i).substring(x3).trim());
			if(n.length()<10){
				write(n);
				for(int u=0;u<10-n.length();u++){
					write(" ");
				}
			}
			else{
				write(n.substring(0,9)+" ");
			}
			if(q.length()<10){
				write(q);
				for(int u=0;u<10-q.length();u++){
					write(" ");
				}
			}
			else{
				write(q.substring(0,9)+" ");
			}
			if(c.length()<10){
				write(c);
				for(int u=0;u<10-c.length();u++){
					write(" ");
				}
			}
			else{
				write(c.substring(0,9)+" ");
			}
			if(p.length()<10){
				write(p);
				for(int u=0;u<10-p.length();u++){
					write(" ");
				}
			}
			else{
				write(p.substring(0,9)+" ");
			}
			if(d.length()<10){
				write(d);
				for(int u=0;u<10-d.length();u++){
					write(" ");
				}
				writeln("");
			}
			else{
				writeln(d.substring(0,9)+" ");
			}
		}
		else{
			writeln("The inventory is empty...");
		}
	}
    public void search(String command){
		String name="    ";
		String price="    ";
		String category="    ";
		String description="    ";
		String quantity="    ";
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
			if(! names.get(i).contains(name) && !name.equals("    ")){
				filter.set(i, "!!!!remove!!!!- - - -");
			}
			if(! quantities.get(i).equals(quantity) && !quantity.equals("    ")){
				filter.set(i, "!!!!remove!!!!- - - -");
			}
			if(! categories.get(i).contains(category) && !category.equals("    ")){
				filter.set(i, "!!!!remove!!!!- - - -");
			}
			if(! prices.get(i).equals(price) && !price.equals("    ")){
				filter.set(i, "!!!!remove!!!!- - - -");
			}
			if(! descriptions.get(i).contains(description) && !description.equals("    ")){
				filter.set(i, "!!!!remove!!!!- - - -");
			}
		}
		/**for(int i=0;i< filter.size();i++){
			if(filter.get(i).equals("!!!!remove!!!!- - - -")){
				filter.remove(i);
			}
		}
		for(int i=0;i<filter.size();i++){
			if(filter.get(i).equals("!!!!remove!!!!- - - -")){
				filter.remove(i);
			}
		}**/
		writeln("ID    Name      Qty       Category  Price     Description");
		for(int i=0;i<filter.size();i++){
			String n;
			String q;
			String c;
			String p;
			String d;
			int x=0;
			while(! filter.get(i).substring(0,x).contains("-")){
				x++;
			}
			n=(filter.get(i).substring(0,x-1).trim());
			int x1=x;
			while(! filter.get(i).substring(x,x1).contains("-")){
				x1++;
			}
			q=(filter.get(i).substring(x,x1-1).trim());
			int x2=x1;
			while(! filter.get(i).substring(x1,x2).contains("-")){
				x2++;
			}
			c=(filter.get(i).substring(x1,x2-1).trim());
			int x3=x2;
			while(! filter.get(i).substring(x2,x3).contains("-")){
				x3++;
			}
			p=(filter.get(i).substring(x2,x3-1).trim());
			d=(filter.get(i).substring(x3).trim());
			name=names.get(i);
			quantity=quantities.get(i);
			category=categories.get(i);
			price=prices.get(i);
			description=descriptions.get(i);
			//String id=String.valueOf(tmp.indexOf(name+"-"+quantity+"-"+category+"-"+price+"-"+description));
			if(!filter.get(i).equals("!!!!remove!!!!- - - -")){
				write(i);
				for(int t=0;t<6-String.valueOf(i).length();t++){
					write(" ");
				}
				if(n.length()<10){
					write(n);
					for(int u=0;u<10-n.length();u++){
						write(" ");
					}
				}
				else{
					write(n.substring(0,9)+" ");
				}
				if(q.length()<10){
					write(q);
					for(int u=0;u<10-q.length();u++){
						write(" ");
					}
				}
				else{
					write(q.substring(0,9)+" ");
				}
				if(c.length()<10){
					write(c);
					for(int u=0;u<10-c.length();u++){
						write(" ");
					}
				}
				else{
					write(c.substring(0,9)+" ");
				}
				if(p.length()<10){
					write(p);
					for(int u=0;u<10-p.length();u++){
						write(" ");
					}
				}
				else{
					write(p.substring(0,9)+" ");
				}
				if(d.length()<10){
					writeln(d);
				}
				else{
					writeln(d.substring(0,10));
				}
			}
		}
		if(screen==null){ 
			nextCommand();
		}
    }
    public void about(){
        try{
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        }
        catch(InterruptedException e){
            writeln("InterruptedException");
        }
        catch(IOException e){
            writeln("IOException");
        }
        writeln("Oxi Inventory Management is a product developed by MidWestOxidation Enterprise (c) 2016.");
        writeln("The intended purpose was to create a simple, secure, and powerful interface that can be used to manage small businesses.");
        writeln("For more information, please visit http://www.midwestoxidation.com/oxi");
        writeln("");
        if(screen==null){ 
			nextCommand();
		}
    }
	public void edit(int y, String command){
		String name;
		String quantity;
		String category;
		String price;
		String description;
		String item;
		item=tmp.get(y);
		int x=0;
		while(! item.substring(0,x).contains("-")){
			x++;
		}
		name =item.substring(0,x-1).trim();
		int x1=x;
		while(! item.substring(x,x1).contains("-")){
			x1++;
		}
		quantity=item.substring(x,x1-1).trim();
		int x2=x1;
		while(! item.substring(x1,x2).contains("-")){
			x2++;
		}
		category= item.substring(x1,x2-1).trim();
		int x3=x2;
		while(! item.substring(x2,x3).contains("-")){
			x3++;
		}
		price= item.substring(x2,x3-1).trim();
		description=item.substring(x3,item.length()).trim();
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
            name=name.substring(0,i).trim()+"-";
        }
		else{
            name=names.get(y)+"-";
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
            quantity=quantity.substring(0,i).trim()+"-";
        }
		else{
            quantity=quantities.get(y)+"-";
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
            category=category.substring(0,i).trim()+"-";
        }
		else{
            category=categories.get(y)+"-";
        }
        if(command.contains("/p")){
            int i=0;
            while(command.substring(i).contains("/p")){
                i++;
            }
            price=command.substring(i+1);
            i=price.length();
            while(price.substring(0,i).contains("/")){
                i--;
            }
            price=price.substring(0,i).trim()+"-";
        }
		else{
            price=prices.get(y)+"-";
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
		else{
            description=descriptions.get(y);
        }
		tmp.set(y,name+quantity+category+price+description);
		if(screen==null){
			nextCommand();
		}
	}
    public void sell(int y){
        String name;
		String quantity;
		String category;
		String price;
		String description;
		String item;
		item=tmp.get(y);
		int x=0;
		while(! item.substring(0,x).contains("-")){
			x++;
		}
		name =item.substring(0,x-1).trim();
		int x1=x;
		while(! item.substring(x,x1).contains("-")){
			x1++;
		}
		quantity=item.substring(x,x1-1).trim();
		int x2=x1;
		while(! item.substring(x1,x2).contains("-")){
			x2++;
		}
		category= item.substring(x1,x2-1).trim();
		int x3=x2;
		while(! item.substring(x2,x3).contains("-")){
			x3++;
		}
		price= item.substring(x2,x3-1).trim();
		description=item.substring(x3,item.length()).trim();
		writeln("How much quantity of this item would you like to sell?");
		write("SELL>");
		try{
			amount=scanner.nextInt();
			if(Integer.valueOf(quantity)-amount>0){
				tmp.set(y, name+"-"+String.valueOf(Integer.valueOf(quantity)-amount)+"-"+category+"-"+price+"-"+description);
				writeln("Item sold");
			}
			else if(Integer.valueOf(quantity)-amount==0){
				writeln("Item sold");
				gone=true;
			}
			else if(Integer.valueOf(quantity)-amount<0 || Integer.valueOf(quantity)==0 || Integer.valueOf(quantity)<0){
				writeln("You tried to sell more than you have.");
			}
			else{
				writeln("Invalid input");
			}
		}
		catch(InputMismatchException e){
			writeln("Only numbers are accepted...");
		}
		gross+=(Integer.valueOf(price)*amount);
		if(gone){
			writeln("You have items in the inventory with no stock remaining. Please restock, or remove these items.");
		}
		nextCommand();
    }
    public void clear(){
        try{
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        }
        catch (InterruptedException e){
            writeln("InterruptedException");
        }
        catch(IOException e){
            writeln("IOException");
        }
        writeln("Oxi Inventory Management [Version Beta 0.8.2]");
        writeln("(c) 2016 MidWestOxidation Enterprise. All rights reserved.");
        writeln("");
        if(screen==null){ 
			nextCommand();
		}
    }
	public void newPass(){
        writeln("This feature has not yet been implemented");
        if(screen==null){ 
			nextCommand();
		}
    }
	public void popArray(){
        try{
            Scanner scanner = new Scanner(m);
            while(scanner.hasNext()){
                tmp.add(decode(scanner.next()));
            }
        }
        catch(FileNotFoundException e){
            //writeln("FileNotFoundException");
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
			descriptions.add(tmp.get(i).substring(x3,tmp.get(i).length()).trim());
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
            writeln("FileNotFoundException");
        }
        catch(UnsupportedEncodingException e){
            writeln("UnsupportedEncodingException");
        }
    }
	public String encode(String msg){
		String output="";
		for(int x=0;x<msg.length();x++){
			output+=shifted[index(letters,msg.substring(x,x+1))];
		}
		return output;
	}
	public String decode(String msg){
		String output="";
		for(int x=0;x<msg.length();x++){
			output+=letters[index(shifted,msg.substring(x,x+1))];
		}
		return output;
	}
	public int index(String array[], String value){
		int index = -1;
		for (int i=0;i<array.length;i++) {
			if (array[i].equals(value)) {
				index = i;
				break;
			}
		}
		return index;
	}
	public void write(Object s){
		if(screen==null){
			System.out.print(s);;
		}
		else{
			String cast = "";
			String name = s.getClass().getName();
			if(name.contains("Integer")){
			cast =	String.valueOf(s);
			}
			else if(name.contains("Character")){
				cast = String.valueOf(s);
			}
			else if(name.contains("String")){
				cast = (String) s;
			}
			else if(name.contains("Double")){
				cast = String.valueOf(s);
			}
			//else if()
		screen.passToGUI(cast, false);
		}
	}
	public void writeln(String s){
		if(screen==null){
			System.out.println(s);
		}
		else{
			String cast = "";
			String name = s.getClass().getName();
			if(name.contains("Integer")){
				cast =	String.valueOf(s);
			}
			else if(name.contains("Character")){
				cast = String.valueOf(s);
			}
			else if(name.contains("String")){
				cast = (String) s;
			}
			else if(name.contains("Double")){
				cast = String.valueOf(s);
			}
			//else if()
		screen.passToGUI(cast, false);
		}
				
	}
	public void setScreen(Screen s){
		screen=s;
	}
}
/**
----Feature ideas----
--->obscure password fields
--->passwd
------Known Bugs-----
--->Program closes on incorrect password
**/