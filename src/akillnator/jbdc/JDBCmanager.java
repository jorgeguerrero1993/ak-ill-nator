package akillnator.jbdc;


import java.io.File;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import akillnator.pojo.*;

public class JDBCmanager implements Dbmanager{
	
	
	
	
	//PREPARESTATEMENT DE TREATMENT PARA RELLENARLO
	
	
	
	
	private Connection c;
	
	public JDBCmanager(){
		connect();
	}

	public void connect(){
		try {
			Class.forName("org.sqlite.JDBC");
			c=DriverManager.getConnection("jdbc:sqlite:./db/akillnator.db");
			c.createStatement().execute("PRAGMA foreign_keys=ON");
			System.out.println("Database connection opened.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void disconnect(){
		try {
			c.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
  
   
	public void insertPatient(Patient Abe ){
		try {
			String sql = "INSERT INTO Patient (name,birthDate,gender,weight) "
					+ "VALUES (?,?,?,?)"; //Faltan atributos Y AÑADIR ATRIBTOS NOT NULL
			
			PreparedStatement stmt=c.prepareStatement(sql);
			stmt.setString(1, Abe.getName());
			stmt.setDate(2, Abe.getBirthDate());;
			stmt.setString(3, Abe.getGender());
			stmt.setFloat(4, Abe.getWeight());
			
			stmt.executeUpdate(); 
			stmt.close();
		
		}
		 catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	

		
		/**
		 * Simple transformation method. You can use it in your project.
		 * @param sourcePath - Absolute path to source xml file.
		 * @param xsltPath - Absolute path to xslt file.
		 * @param resultDir - Directory where you want to put resulting files.
		 */
	
	

	public List <Patient> getAllPatients(){
		Statement stmt;
		List <Patient> returnedList = new ArrayList<>(); 
		try {
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Patient");
			while(rs.next()){
    			int id = rs.getInt("id");
				String name = rs.getString("name");
				Date birthDate=rs.getDate("birthDate");
				String gender = rs.getString("gender");
				float weight  = rs.getFloat("weight");
				returnedList.add(new Patient(id,name,birthDate,gender,weight));
			}
			stmt.close();
		} 
		
		
		catch (SQLException e){
			e.printStackTrace();
		}
		return returnedList;
	}
	
	
	public List <Symptons> getAllSymptoms(){
		Statement stmt;
		List <Symptons> returnedList = new ArrayList<>(); 
		try {
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Symptons");
			while(rs.next()){
    			int id = rs.getInt("id");
				String name = rs.getString("name");
				String type = rs.getString("type");
				returnedList.add(new Symptons(id,name,type));
			}
			stmt.close();
		} 
		
		
		catch (SQLException e){
			e.printStackTrace();
		}
		return returnedList;
	}
	
	

	 public void insertIllness(int id, String name, String type, String aproxDuration, int treatmentId) throws SQLException{
     	try{
    		String sql = "INSERT Illness id=?, name=?, type=?, aprox_duration=?, treatment_id=?";
            PreparedStatement stmt1=c.prepareStatement(sql);
        	stmt1.setInt(1,id);
        	stmt1.setString(2,name);
        	stmt1.setString(3,type );
        	stmt1.setString(4,aproxDuration );
        	stmt1.setInt(5,treatmentId);
    	
        	stmt1.executeUpdate(); 
        	stmt1.close();
     		
     	} catch (SQLException e){
     		e.printStackTrace();
     	}
	 }
	 
	 public void insertSymptons(int id, String name, String type){
		
		 try {
			String sql = "INSERT Symptons id=?, name=?, type=?";
			PreparedStatement stmt1=c.prepareStatement(sql);
			stmt1.setInt(1,id);
			stmt1.setString(2,name);
			stmt1.setString(3,type);
			stmt1.executeUpdate();
			stmt1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	 }
	 
	 public void insertTreatment(int id, String name, String type){
		 try {
			String sql = "INSERT Treatment id=?, name=?, type=?";
			PreparedStatement stmt1=c.prepareStatement(sql);
			stmt1.setInt(1,id);
			stmt1.setString(2,name);
			stmt1.setString(3,type);
			stmt1.executeUpdate();
			stmt1.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 
		 
	 }
			
			
			
	public void createTable(){
		try{
			Statement stmt = c.createStatement();
			//Patient table
			stmt.executeUpdate("CREATE TABLE Patient(" + 
			"id INTEGER PRIMARY KEY AUTOINCREMENT,"+
			"name TEXT,"+
			"birthDate DATE,"+
			"gender TEXT,"+
			"weight FLOAT)");
			//Symptons table  CORREGIR FALTA DE ORTOGRAFÍA!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			stmt.executeUpdate("CREATE TABLE Symptons("+
			"id INTEGER PRIMARY KEY AUTOINCREMENT,"+
			"name TEXT,"+
			"type TEXT)");
			insertSymptons(1,"hot temperature","external");
			insertSymptons(2,"sneeze mucus","external");
			insertSymptons(3,"pain in the button","internal");
			insertSymptons(4,"stamach ache","internal");
			//Previous events table
			stmt.executeUpdate("CREATE TABLE PreviousEvents("+
			"id INTEGER PRIMARY KEY AUTOINCREMENT,"+
			"environment TEXT,"+
			"accident TEXT)");
			//Drugs table
			stmt.executeUpdate("CREATE TABLE Drugs("+
			"id INTEGER PRIMARY KEY AUTOINCREMENT,"+
			"name TEXT,"+
			"warnings TEXT)");
			
						
			
			//Illness table
			stmt.executeUpdate("CREATE TABLE Illness("+
			"id INTEGER PRIMARY KEY AUTOINCREMENT,"+
			"name INTEGER,"+
			"type TEXT,"+
			"aprox_duration TEXT, "+
			"treatment_id INTEGER REFERENCES Treatment (id))");
			insertIllness(1,"Flu","Viric","3 to 5 days",1);
			insertIllness(2,"High fever","Genetic","Cronic ilness",2);
			insertIllness(3,"Apendicitis","Bacterial infection","2-3 days in hospital + few weeks.",3);
			insertIllness(4, "Stomach flu", "Viral or bacterial", "24 hours", 4);
			
           
        
			
//////////////USAR LAS FUNCIONES INSERTAR PARA RELLENAR 
//////////////LAS TABLAS SINTOMAS Y TRATAMIENTO
			
			//Treatment table
			stmt.executeUpdate("CREATE TABLE Treatment("+
			"id INTEGER PRIMARY KEY AUTOINCREMENT,"+
			"name TEXT,"+
			"type TEXT)");
			//Analysis table
			stmt.executeUpdate("CREATE TABLE Analysis("+
			"id INTEGER PRIMARY KEY AUTOINCREMENT,"+
			"name TEXT,"+
			"illness_id INTEGER REFERENCES Illness(id))");
			//Patient-Previous events table
			stmt.executeUpdate("CREATE TABLE PatientPrevEvents("+
			"patId INTEGER REFERENCES Patient(id),"+
			"prevId INTEGER REFERENCES PreviousEvents(id),"+
			"PRIMARY KEY(patId,prevId))");
			//Patient-symptons table
			stmt.executeUpdate("CREATE TABLE PatientSymptons("+
			"patId INTEGER REFERENCES Patient(id),"+
			"sympId INTEGER REFERENCES Symptons(id))");
			//Patient-drugs table
			stmt.executeUpdate("CREATE TABLE PatientDrugs("+
			"patId INTEGER REFERENCES Patient(id),"+
			"drugsId INTEGER REFERENCES Drugs(id))");
			//Previous events - symptons table
			stmt.executeUpdate("CREATE TABLE PrevEventsSymptons("+
			"prevId INTEGER REFERENCES PreviousEvents(id),"+
			"sympId INTEGER REFERENCES Symptons(id),"+
			"PRIMARY KEY(prevId,sympId))");
			//Symptons - Illness table reall
			stmt.executeUpdate("CREATE TABLE SymptonsIllness("+
			"sympId INTEGER REFERENCES Symptons(id),"+
			"illnessId INTEGER REFERENCES Illness(id),"+
			"PRIMARY KEY(sympId,illnessId))");
			//Drugs - Treatment table
			stmt.executeUpdate("CREATE TABLE DrugsTreatment("+
			"drugsId INTEGER REFERENCES Drugs(id),"+
			"treatId INTEGER REFERENCES Treatment(id),"+
			"quantity TEXT,"+
			"frequency TEXT,"+
			"PRIMARY KEY (drugsId,treatId))");
			//Patient - Illness table
			stmt.executeUpdate("CREATE TABLE PatientIllness("+
			"patId INTEGER REFERENCES Patient(id),"+
			"illnessId INTEGER REFERENCES Illness(id),"+
			"PRIMARY KEY(patId,illnessId))");
			stmt.close();					
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
	}
	//Poner en un futuro mas tipos de delete
	public void deletePatient(int id)throws SQLException{
		String sql = "DELETE FROM patient WHERE id = ?";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setInt(1,id);
		prep.executeUpdate();
		prep.close();
		System.out.println("It has been succesfully deleted");
	}
	
	public String updatePatient ( Patient updatedPat){
		
		try {
			String sql = "UPDATE Patient SET name=?, birthDate=?, gender=?, weight=? WHERE id=?";
			
			PreparedStatement stmt=c.prepareStatement(sql);
			stmt.setString(1, updatedPat.getName());
			stmt.setDate(2, updatedPat.getBirthDate());
			stmt.setString(3, updatedPat.getGender());
			stmt.setFloat(4, updatedPat.getWeight());
			stmt.setInt(5, updatedPat.getId());
			
			stmt.executeUpdate(); 
			stmt.close();
			return "Done";
		
		}
		 catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return "Error";
	}
	
	public List<Patient> searchByName(String nameToSearch){
		Statement stmt;
		List <Patient> returnedList = new ArrayList<>(); 
		try {
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Patient WHERE name = '" +nameToSearch+"'");
			while(rs.next()){
     			int id = rs.getInt("id");
				String name = rs.getString("name");
				Date birthDate=rs.getDate("birthDate");
				String gender = rs.getString("gender");
				float weight  = rs.getFloat("weight");
				returnedList.add(new Patient(id,name,birthDate,gender,weight));
			}
			stmt.close();
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		return returnedList;
	}

}




