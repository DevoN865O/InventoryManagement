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
        System.out.println("Oxi Inventory Management [Version Beta 0.8.2]");
        System.out.println("(c) 2016 MidWestOxidation Enterprise. All rights reserved.");
        System.out.println();
        nextCommand();
    }
	public void nextCommand(){
		//System.out.println("update");
		updateArrays();
        System.out.print(">");
        command = scanner.nextLine();
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
				if(command.length()==3){
					System.out.println();
				}
                else{
					System.out.println(command.substring(4, command.length()));
                }
				nextCommand();
            } 
			else if(command.toLowerCase().equals("save")){
				writeArray();
				tmp.clear();
				popArray();
				updateArrays();
				System.out.println("Saved successfully.");
				nextCommand();
			}
			else if(command.toLowerCase().equals("passwd")){
                newPass();
                nextCommand();
            } 
			else if(command.toLowerCase().equals("about")){
                about();
            }
			else if(command.toLowerCase().equals("gross")){
				gross();
				nextCommand();
			}
			else if(command.toLowerCase().substring(0,7).equals("display")){
				if(command.toLowerCase().contains("/i") && command.toLowerCase().contains("/a")){
					System.out.println("Input invalid.");
					nextCommand();
				}
				else if(command.toLowerCase().contains("/a")){
					display();
				}
				else if(command.toLowerCase().contains("/i")){
					displayDetail(select());
				}
				else{
					System.out.println("Displays items stored in the inventory");
                    System.out.println();
                    System.out.println("USAGE:");
                    System.out.println("display [/I] || display [/A]");
                    System.out.println();
                    System.out.println("  /I           Displays a specific item.");
                    System.out.println("  /A           Displays all items.");
                    nextCommand();
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
	public void displayDetail(int y){
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
		System.out.println("ID:        "+String.valueOf(y));  		
		System.out.println("Name:      "+name);  		
		System.out.println("Qty:       "+quantity);  		
		System.out.println("Category:  "+category);  		
		System.out.println("Price:     "+price);  		
		System.out.println("Desc:      "+description); 
		nextCommand();
	}
	public void save(){
		
	}
	public int select(){
		try{
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		}
		catch(InterruptedException e){
			System.out.println("InterruptedException");
		}
		catch(IOException e){
			System.out.println("IOException");
		}
		System.out.println("ID        Name      Qty       Category  Price     Description");
		for(int i=0;i<tmp.size();i++){
			System.out.print(i);
			for(int x=0;x<10-String.valueOf(i).length();x++){
				System.out.print(" ");
			}
			displayOne(i);
		}
		System.out.println();
		System.out.println("Please enter an ID to continue.");
		Scanner scanner=new Scanner(System.in);
		int theInt=-1;
		while(true){
			System.out.print("SELECT>");
			try{
				theInt=scanner.nextInt();
				
			}
			catch(InputMismatchException e){
				scanner.next();
			}
			if(theInt>tmp.size()){
				System.out.println("An item of that ID does not exist.");
			}
			else if(theInt<0){
				System.out.println("Input invalid.");
			}
			else{
				break;
			}
		}
		return(theInt);
	}
	public void delete(String command){
		if(command.toLowerCase().contains("/a") && command.toLowerCase().contains("/i")){
			System.out.println("Input invalid.");
		}
		else if(command.toLowerCase().contains("/a")){
			m.delete();
			tmp.clear();
			System.out.println("Inventory Cleared!");
		}
		else if(command.toLowerCase().contains("/i")){
			try{
				tmp.remove(select());
				System.out.println("Item cleared successfully.");
			}
			catch(IndexOutOfBoundsException e){
				System.out.println("An item of that ID does not exist.");
				tmp.remove(select());
			}
			
		}
		else{
			System.out.println("Removes item(s) from the inventory.");
            System.out.println();
            System.out.println("USAGE:");
            System.out.println("delete [/a] || delete [/i]");
            System.out.println();
            System.out.println("  /A           Clear the inventory.");
            System.out.println("  /I           Select an item to be removed.");
			}
			nextCommand();	
	}
    public void help(){
        System.out.println("For more specific information regarding a command, please type the command name.");
        System.out.println("ADD            Appends items to the inventory.");
		System.out.println("DELETE         Removes item(s) from the inventory.");
        System.out.println("DISPLAY        Displays item(s) from the inventory.");
		System.out.println("SEARCH         Searches the inventory.");
        System.out.println("SELL           Removes items from inventory, modifies drawer amount.");
		System.out.println("GROSS          Displays the gross income accumulated during the session.");
        System.out.println("CAT            Prints the input to the console");
        System.out.println("PASSWD         Reset your password.");
        System.out.println("ABOUT          About this product.");
        System.out.println("CLEAR          Clears the console.");
        System.out.println("EXIT           Closes the current session");
        nextCommand();
    }
	public void gross(){
		System.out.println("This feature has not been implemented.");
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
            addString+=quantity.substring(0,i).trim()+"-";
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
            addString+=price.substring(0,i).trim()+"-";
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
        System.out.println("Item added to inventory!");
        nextCommand();
    }
	public void display(){
		System.out.println("ID        Name      Qty       Category  Price     Description");
		for(int i=0;i<tmp.size();i++){
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
			System.out.print(tmp.indexOf(String.valueOf(n+"-"+q+"-"+c+"-"+p+"-"+d)));
			for(int a=0;a<10-String.valueOf(a).length();a++){
				System.out.print(" ");
			}
			if(n.length()<10){
				System.out.print(n);
				for(int u=0;u<10-n.length();u++){
					System.out.print(" ");
				}
			}
			else{
				System.out.print(n.substring(0,9)+" ");
			}
			if(q.length()<10){
				System.out.print(q);
				for(int u=0;u<10-q.length();u++){
					System.out.print(" ");
				}
			}
			else{
				System.out.print(q.substring(0,9)+" ");
			}
			if(c.length()<10){
				System.out.print(c);
				for(int u=0;u<10-c.length();u++){
					System.out.print(" ");
				}
			}
			else{
				System.out.print(c.substring(0,9)+" ");
			}
			if(p.length()<10){
				System.out.print(p);
				for(int u=0;u<10-p.length();u++){
					System.out.print(" ");
				}
			}
			else{
				System.out.print(p.substring(0,9)+" ");
			}
			if(d.length()<10){
				System.out.println(d);
			}
			else{
				System.out.println(d.substring(0,10));
			}
		}
		nextCommand();
	}
	public void displayOne(int i){
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
			System.out.print(n);
			for(int u=0;u<10-n.length();u++){
				System.out.print(" ");
			}
		}
		else{
			System.out.print(n.substring(0,9)+" ");
		}
		if(q.length()<10){
			System.out.print(q);
			for(int u=0;u<10-q.length();u++){
				System.out.print(" ");
			}
		}
		else{
			System.out.print(q.substring(0,9)+" ");
		}
		if(c.length()<10){
			System.out.print(c);
			for(int u=0;u<10-c.length();u++){
				System.out.print(" ");
			}
		}
		else{
			System.out.print(c.substring(0,9)+" ");
		}
		if(p.length()<10){
			System.out.print(p);
			for(int u=0;u<10-p.length();u++){
				System.out.print(" ");
			}
		}
		else{
			System.out.print(p.substring(0,9)+" ");
		}
		if(d.length()<10){
			System.out.print(d);
			for(int u=0;u<10-d.length();u++){
				System.out.print(" ");
			}
			System.out.println();
		}
		else{
			System.out.println(d.substring(0,9)+" ");
		}
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
			if((! name.equals("default0x0") && (! names.get(i).contains(name)))){
			filter.set(i, "!!!!remove!!!!");
			}
			if((! quantity.equals("default0x0") && (! quantities.get(i).equals(quantity)))){
				filter.set(i, "!!!!remove!!!!");
			}
			if((! category.equals("default0x0") && (! categories.get(i).contains(category)))){
				filter.set(i, "!!!!remove!!!!");
			}
			if((! price.equals("default0x0") && (! prices.get(i).equals(price)))){
				filter.set(i, "!!!!remove!!!!");
			}
			if((! description.equals("default0x0") && (! descriptions.get(i).contains(description)))){
				filter.set(i, "!!!!remove!!!!");
			}
		}
		for(int i = 0; i < filter.size(); i++){
			if(filter.get(i).equals("!!!!remove!!!!")){
				filter.remove(i);
			}
		}
		for(int i = 0; i < filter.size(); i++){
			if(filter.get(i).equals("!!!!remove!!!!")){
				filter.remove(i);
			}
		}
		System.out.println("Name      Qty       Category  Price     Description");
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
			if(n.length()<10){
				System.out.print(n);
				for(int u=0;u<10-n.length();u++){
					System.out.print(" ");
				}
			}
			else{
				System.out.print(n.substring(0,9)+" ");
			}
			if(q.length()<10){
				System.out.print(q);
				for(int u=0;u<10-q.length();u++){
					System.out.print(" ");
				}
			}
			else{
				System.out.print(q.substring(0,9)+" ");
			}
			if(c.length()<10){
				System.out.print(c);
				for(int u=0;u<10-c.length();u++){
					System.out.print(" ");
				}
			}
			else{
				System.out.print(c.substring(0,9)+" ");
			}
			if(p.length()<10){
				System.out.print(p);
				for(int u=0;u<10-p.length();u++){
					System.out.print(" ");
				}
			}
			else{
				System.out.print(p.substring(0,9)+" ");
			}
			if(d.length()<10){
				System.out.println(d);
			}
			else{
				System.out.println(d.substring(0,10));
			}
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
        System.out.println("Oxi Inventory Management [Version Beta 0.8.2]");
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
            //System.out.println("FileNotFoundException");
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
}
/**
----Feature ideas----
Implement edit feature
this is a thing too now//Save without quitting
obscure password fields
passwd
select is a thing now//Select 
search does not display id's


-----Known Bugs----
fixed //FileNotFoundException on first launch due to mem.txt not existing (mutable- does not affect execution)
Program closes on incorrect password
Some users experience valid passwords not letting them in
Searching by anything but name sometimes does not work
**/


