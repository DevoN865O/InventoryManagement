package org.millardps.InventoryManagement;
import java.util.*;
public class Runner{
	File memory= new File("mem.txt");
	Scanner scanner=new Scanner(System.in);
    public static void main(String args[]){
		if (! memory.exists()){
			System.out.println("Welcome to the inventory handler! Please set a password. This can be changed at any time with the 'passwd' command.");
			setPass();
		}
	}
	public static void setPass(){
		System.out.println("Please input existing password. If you do not currently have a password, please press ENTER");
		String existPass=scanner.nextLine();
		System.out.println("Please set your new password!");
		String newPass=scanner.nextLine();
		System.out.println("Please verify your new password!");
		String verifyPass=scanner.nextLine();
		if(newPass.equals(verifyPass)){
			System.out.println("Password Set!");
		}
		else{
			System.out.println("I'm sorry, but your passwords do not match... Please try again.");
			setPass();
		}
	}
}
