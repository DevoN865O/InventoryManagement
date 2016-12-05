package org.millardps.InventoryManagement;
import java.util.*;
import java.io.*;
public class PassMan{
	private File p= new File("pmem.txt");
	private String newPass;
	private String verPass;
	private String oldPass;
	private Scanner scanner=new Scanner(System.in);
	private Scanner scanner2=new Scanner(System.in);

	private boolean resettting;
	PassMan(){
		if (! p.exists()){
			System.out.println("Welcome to the inventory handler! Please set a password. This can be changed at any time with the 'passwd' command.");
			try{
				PrintWriter pWrite= new PrintWriter("pmem.txt","UTF-8");
				while (true){
					System.out.println("Please create your password!");
					newPass=scanner.nextLine();
					System.out.println("Please verify your password!");
					verPass=scanner2.nextLine();
					if (newPass.equals(verPass)){
						pWrite.println(encode(newPass));
						pWrite.close();
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
				String readPass=scanner3.nextLine();
				if (oldPass.equals(readPass)){
					System.out.println("Wow ur good m8");
				}
				else{
					System.out.println("Dude such garb");
				}
			}
			catch(FileNotFoundException e){
				System.out.println("Password does not exist.");
			}
			
		}
	}
}