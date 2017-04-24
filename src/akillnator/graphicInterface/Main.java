//Aplicar interfaz

package akillnator.graphicInterface;
import akillnator.jbdc.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import akillnator.jbdc.JDBCmanager;
import akillnator.pojo.Patient;

public class Main {
	
	public static void main(String[] args) {
		JDBCmanager a= new JDBCmanager();
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
					abe.setName(name);
					
					System.out.println("Give me the birthdate: YYYY MM DD");
					String birthdate=reader.readLine();
			        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MM dd");
					LocalDate parseDate= LocalDate.parse(birthdate, formatter);
					abe.setBirthDate(Date.valueOf(parseDate));
					
					System.out.print("Gender: Masc/Fem ");
					String gender = reader.readLine();
					abe.setGender(gender);
					
					System.out.print("Weight (kg): ");
					float weight = Float.parseFloat(reader.readLine());
					abe.setWeight(weight);
				//pa ver
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
					
					Patient newPat = new Patient();
					System.out.println("Put the new information , ( If you don't want to update some information press ENTER");
					System.out.println("Name: ");
					name=reader.readLine();
					System.out.println("Give me the birthdate: YYYY-MM-DD");
					String birthdate=reader.readLine();
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
					LocalDate birthDate= LocalDate.parse(birthdate, formatter);
					
					if(newPat.getName()!=""){
						oldPat.setName(newPat.getName());
					}
					
					if (birthDate.equals("")==false){
						oldPat.setBirthDate(newPat.getBirthDate());
					}
					
					a.updatePatient(newPat);
					
				}

			 }
			 catch(Exception e) {
					e.printStackTrace();
				}

		}
	 	a.disconnect();
	 	}

}
