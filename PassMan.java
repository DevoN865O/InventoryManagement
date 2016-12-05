package org.millardps.InventoryManagement;
import java.util.*;
import java.io.*;
public class PassMan{
	private File p= new File("pmem.txt");
	private File m= new File("mem.txt");
	private String newPass;
	private String verPass;
	private String oldPass;
	private boolean cn;
	private Scanner scanner=new Scanner(System.in);
	private Scanner scanner2=new Scanner(System.in);
	String letters[]={"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","1","2","3","4","5","6","7","8","9","0","~","`","!","@","#","$","%","^","&","*","_","-","=","+","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"," "};
	String shifted[]={"m","l","8","q","A","P","R","W","C","h","n","k","1","7","z","B","o","9","2","g","f","6","p","O","0","a","e","3","j","Q","V","-","4","=","b","d","J","N"," ","X","","M","I","U","J","i","5","y","c","w","*","T","$","%","S","v","^","L","+","&","Y","u","#","D","_","","r","Z","H","!","s","@","F","t","x","G","E"};
	private boolean resettting;
	PassMan(){
		if (! p.exists()){
			if(m.exists()){
				System.out.println("Tampering detected...");
				System.out.println("Clearing Inventory...");
				m.delete();
				System.out.println("Inventory Cleared.");
			}
			System.out.println("Welcome to the inventory handler! Please set a password. This can be changed at any time with the 'passwd' command.");
			try{
				PrintWriter pWrite= new PrintWriter("pmem.txt","UTF-8");
				while (true){
					System.out.println("Please create your password!");
					newPass=scanner.nextLine();
					System.out.println("Please verify your password!");
					verPass=scanner2.nextLine();
					for(int i=0;i<newPass.length();i++){
						if (Arrays.stream(letters).anyMatch(newPass.substring(i)::equals)){
							cn=true;
						}
						else{
							cn=false;
						}
					}
					if (newPass.equals(verPass) && cn){
						pWrite.println(encode(newPass));
						pWrite.close();
						//Console console=new Console();
						break;
					}
				}
			}
			catch (IOException e){
				System.out.println("IO Exception.");
			}
		}
		else if (p.exists()){
			try{
				Scanner scanner3=new Scanner(new File("pmem.txt"));
				System.out.println("Welcome back to the inventory handler! Please enter your password!");
				oldPass=scanner.nextLine();
				String readPass=decode(scanner3.nextLine());
				if (oldPass.equals(readPass)){
					//Console console=new Console();
				}
				else{
					System.out.println("Password Incorrect.");
				}
			}
			catch(FileNotFoundException e){
				System.out.println("Password does not exist.");
			}
			
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
	/**public String decode(String msg2){
		String output = "";
		for(int x = 0; x < msg2.length(); x++){
			char ch = (char)(msg2.charAt(x) - 7);
			if (ch > 'z')
				output += (char)(msg2.charAt(x) - (19));
			else
				output += ch;
		}
		return output;
	}**/
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
	
