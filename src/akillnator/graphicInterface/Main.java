package akillnator.graphicInterface;
import akillnator.jbdc.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import akillnator.jbdc.Dbmanager;
import akillnator.pojo.Patient;

public class Main {
	
	public static void main(String[] args) {
		Dbmanager a= new Dbmanager();
		Integer answer=50;
	 	while(answer!=0){
			System.out.println(" MENU :"
		 			+ "\n0.Exit \n 1. Add a patient \n 2.Create tables \n 3.Show patients \n 4.Delete");
			 try{
				BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
				answer = Integer.parseInt(reader.readLine());
				if(answer==1){
					
					Patient abe = new Patient(); 
			    	
			    	System.out.println("Please, input the Patient info:");
					System.out.print("Name: ");
					String name = reader.readLine();
					System.out.print("Age: ");
					Integer age = Integer.parseInt(reader.readLine());
					abe.setAge(age);
					abe.setName(name);
					a.insertPatient(abe);
				}
				if(answer==2){
					a.createTable();
				}
				if(answer==3){

					List <Patient> listpat = a.getAllPatients();
					for (Patient patient : listpat) {
						System.out.println(patient);
					}
				
				}
				if(answer==4){
					System.out.println("Write the name of the patient that you\n want to delete :");
					String name = reader.readLine();	
					int id= a.searchIdByName(name);
					//Poner que si id = -1
				}

			 }
			 catch(Exception e) {
					e.printStackTrace();
				}

		}
	 	a.disconnect();
	 	}

}
