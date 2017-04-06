package akillnator.graphicInterface;
import akillnator.jbdc.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.*;

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
					System.out.println("Give me the birthdate: YYYY-MM-DD");
					String birthdate=reader.readLine();
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
					LocalDate birthDate= LocalDate.parse(birthdate, formatter);
					abe.setBirthDate(Date.valueOf(birthDate));
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
					List <Patient> patientListByName = a.searchByName(name);
					for (Patient patient : patientListByName) {
						System.out.println(patient+" \n");
					}
					System.out.println(" Which one would you like to delete? \n ID :____");
					Integer id = Integer.parseInt(reader.readLine());
					a.deletePatient(id);
					
				}
				if(answer==5){
					System.out.println("Write the name of the patient that you\n want to update :");
					String name = reader.readLine();
					List <Patient> patientListByName = a.searchByName(name);
					for (Patient patient : patientListByName) {
						System.out.println(patient+" \n");
					}
					System.out.println(" Which one would you like to update? \n ID :____");
					Integer id = Integer.parseInt(reader.readLine());
					Patient oldPat=new Patient();
					for (Patient patient : patientListByName) {
						if(patient.getId()==id){
							oldPat=patient;
							break;
						}
					}
					
					Patient updateInfo = new Patient();
					System.out.println("Put the new information , ( If you don't want to update some information press ENTER");
					System.out.println("Name: ");
					name=reader.readLine();
					System.out.println("Give me the birthdate: YYYY-MM-DD");
					String birthdate=reader.readLine();
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
					LocalDate birthDate= LocalDate.parse(birthdate, formatter);
					
					if (birthDate != ""){
						//Meter cosas del nuevo en el viejo, si no no se hace nada. Luego se lo pasas al db y hace el swl para actualizar
					}
					// else age = ageString
					updateInfo.setBirthDate(Date.valueOf(birthDate));
					a.updatePatient(updateInfo);
					
				}

			 }
			 catch(Exception e) {
					e.printStackTrace();
				}

		}
	 	a.disconnect();
	 	}

}
