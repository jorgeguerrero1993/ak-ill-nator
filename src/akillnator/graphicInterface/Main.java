package akillnator.graphicInterface;
import akillnator.jbdc.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import akillnator.jbdc.Dbmanager;

public class Main {
	public static void main(String[] args) {
		Dbmanager a= new Dbmanager();
		Integer answer=50;
	 	while(answer!=0){
			System.out.println(" MENU :"
		 			+ "\n0.Exit \n 1. ADD A PATIENT \n 2.Create tables");
			 try{
				BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
				answer = Integer.parseInt(reader.readLine());
				if(answer==1){
					a.getInfo(a);
				}
				if(answer==2){
					a.createTable();
				}
				if(answer==3){
					a.disconnect();
				}
			 }
			 catch(Exception e) {
					e.printStackTrace();
				}

		}
	 	}

}
