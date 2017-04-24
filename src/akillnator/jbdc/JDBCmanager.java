package akillnator.jbdc;


import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import akillnator.pojo.Analysis;
import akillnator.pojo.Drugs;
import akillnator.pojo.Illness;
import akillnator.pojo.Patient;

public class JDBCmanager implements Dbmanager{
	
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
			String sql = "INSERT INTO Patient (name, birthDate,gender,weight) "
					+ "VALUES (?,?,?,?)"; //Faltan atributos Y AÑADIR ATRIBTOS NOT NULL
			
			PreparedStatement stmt=c.prepareStatement(sql);
			stmt.setString(1, Abe.getName());
			stmt.setDate(2, Abe.getBirthDate());;
			stmt.setString(3, Abe.getGender());
			stmt.setFloat(4, Abe.getWeight());
			
			stmt.executeUpdate(sql); 
			stmt.close();
		
		}
		 catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
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
				returnedList.add(new Patient(name,birthDate,gender,weight));
			}
			stmt.close();
		} 
		
		catch (SQLException e){
			e.printStackTrace();
		}
		return returnedList;
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
			//Symptons table
			stmt.executeUpdate("CREATE TABLE Symptons("+
			"id INTEGER PRIMARY KEY AUTOINCREMENT,"+
			"name TEXT,"+
			"type TEXT)");
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
			"text TEXT,"+
			"aprox_duration TEXT "+
			"FOREIGN KEY (treatment_id) REFERENCES Treatment (id))");
			//Treatment table
			stmt.executeUpdate("CREATE TABLE Treatment("+
			"id INTEGER PRIMARY KEY AUTOINCREMENT,"+
			"name TEXT,"+
			"type TEXT)");
			//Analysis table
			stmt.executeUpdate("CREATE TABLE Analysis("+
			"id INTEGER PRIMARY KEY AUTOINCREMENT,"+
			"name TEXT,"+
			"FOREIGN KEY (illness_id) REFERENCES Illness(id))");
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
			"sympId INTEGER REFERENCES Symptons(id)"+
			"PRIMARY KEY(prevId,sympId))");
			//Symptons - Illness table
			stmt.executeUpdate("CREATE TABLE SymptonsIllness("+
			"sympId INTEGER REFERENCES Symptons(id),"+
			"illnessId INTEGER REFERENCES Illness(id),"+
			"PRIMARY KEY(sympId,illnessId))");
			//Drugs - Treatment table
			stmt.executeUpdate("CREATE TABLE DrugsTreatment("+
			"drugsId INTEGER REFERENCES Drugs(id),"+
			"treatId INTEGER REFERENCES Treatment(id),"+
			"quantity TEXT,"+
			"Frequency TEXT,"+
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
	
	public String updatePatient ( Patient newPat){
		
		try {
			String sql = "UPDATE Patient SET name=? birthDate=? gender=? weight=?"
					+ "WHERE id=?";
			
			PreparedStatement stmt=c.prepareStatement(sql);
			stmt.setString(1, newPat.getName());
			stmt.setDate(2, newPat.getBirthDate());
			stmt.setString(3, newPat.getGender());
			stmt.setFloat(4, newPat.getWeight());
			stmt.setInt(5, newPat.getId());
			
			stmt.executeUpdate(sql); 
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
//				String gender = rs.getString("gender");
//				float weight  = rs.getFloat("weight");
				returnedList.add(new Patient(id,name,birthDate/*,gender,weight*/));
			}
			stmt.close();
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		return returnedList;
	}

}




