//EN EL UPDATE poner el condicional de si no meten nada

package akillnator.graphicInterface;
import akillnator.jbdc.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.xml.sax.helpers.ParserFactory;

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
				//pa verlo
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
					System.out.println(" Which one would you like to delete? \n ID :");
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
					System.out.println(" Which one would you like to update? \n ID :");
					Integer id = Integer.parseInt(reader.readLine());
					Patient oldPat=new Patient();
					for (Patient patient : patientListByName) {
						if(patient.getId()==id){
							oldPat=patient;
							break;
						}
					}
					
					System.out.println("Put the new information , ( If you don't want to update some information press ENTER");
					System.out.println("Name: ");
					name=reader.readLine();
					System.out.println("Give me the birthdate: YYYY-MM-DD");
					String birthdate=reader.readLine();
					System.out.println("Weigth:");
					String weigth = reader.readLine();
					System.out.println("Gender: masc/fem");
					String gender=reader.readLine();
					
					if(birthdate.compareTo("")==0){
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
						LocalDate birthDate= LocalDate.parse(birthdate, formatter);
						oldPat.setBirthDate(Date.valueOf(birthDate));
					}
					if(name.compareTo("")==0){
						oldPat.setName(name);
					}

					if(weigth.compareTo("")==0){
						oldPat.setWeight(Float.parseFloat(weigth));
					}
					if(gender.compareTo("")==0){
						oldPat.setGender(gender);
					}
					System.out.println(a.updatePatient(oldPat));
					
				}

			 }
			 catch(Exception e) {
					e.printStackTrace();
				}

		}
	 	a.disconnect();
	 	}

}
