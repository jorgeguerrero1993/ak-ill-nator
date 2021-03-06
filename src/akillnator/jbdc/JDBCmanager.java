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
					+ "VALUES (?,?,?,?)"; //Faltan atributos Y A�ADIR ATRIBTOS NOT NULL
			
			PreparedStatement stmt=c.prepareStatement(sql);
			stmt.setString(1, Abe.getName());
			stmt.setDate(2, Abe.getBirthDate());;
			stmt.setString(3, Abe.getGender());
			stmt.setFloat(4, Abe.getWeight());
			
			stmt.executeUpdate(); 
			stmt.close();
		
		}
		 catch (SQLException e) {
				e.printStackTrace();
			}
	}
		
	
	
	public List <Patient> getAllPatients(){
		Statement stmt;
		JDBCmanager d= new JDBCmanager();
		List <Patient> returnedList = new ArrayList<>(); 
		try {
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Patient");
			while(rs.next()){
				
    			int id = rs.getInt("id");
    			System.out.println(" id = "+id);
				String name = rs.getString("name");
				Date birthDate=rs.getDate("birthDate");
				String gender = rs.getString("gender");
				float weight  = rs.getFloat("weight");
				Patient p=new Patient(id,name,birthDate,gender,weight);
				returnedList.add(p);				
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

	public Symptons getSymptonFromPatient( int idp) throws SQLException{
	
		String sql ="SELECT * FROM Patient WHERE id = ?";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setInt(1, idp);
		ResultSet rs = prep.executeQuery();
			
			Patient b = new Patient();
			int id = rs.getInt("id");
			String name = rs.getString("name");
			Date birthDate=rs.getDate("birthDate");
			String gender = rs.getString("gender");
			float weight  = rs.getFloat("weight");
			Patient p=new Patient(id,name,birthDate,gender,weight);
			List <Symptons> sint=p.getSymptons();
			Symptons symp = sint.get(1);
			prep.close();
			
			 return symp;
				
			
	}
	
	public Symptons getSymptom(int a){

	
		Symptons b = new Symptons();
	
		
		try {
		
		String sql ="SELECT * FROM Symptons WHERE id LIKE ?";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setInt(1, a);
		ResultSet rs = prep.executeQuery();
			
			while(rs.next()){
    			int id = rs.getInt("id");
    			b.setId(id);
				String name = rs.getString("name");
				b.setName(name);
				String type = rs.getString("type");
				b.setType(type);
				
			}
			prep.close();
			return b;
			
		} 
			
		catch (SQLException e){
			e.printStackTrace();
		}
		
		return b;
		
	}
	
	

	 public void insertIllness(int id, String name, String type, String aproxDuration, int treatmentId) throws SQLException{
     	try{
    		String sql = "INSERT INTO Illness (id, name, type, aprox_duration, treatment_id) VALUES (?,?,?,?,?)";
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
			String sql = "INSERT INTO Symptons (id,name,type) VALUES (?,?,?)";
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
			String sql = "INSERT INTO Treatment (id,name,type) VALUES (?,?,?)";
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
		
	 public void linkSymptonsIllness(int sympId,int illId){
		 try {
			String sql = "INSERT INTO SymptonsIllness (sympId,illnessId) VALUES (?,?)";
			PreparedStatement prepStmt=c.prepareStatement(sql);
			prepStmt.setInt(1,sympId);
			prepStmt.setInt(2,illId);
			prepStmt.executeUpdate();
			prepStmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	 }
			
			
	public void createTable(){
		try{
			Statement stmt = c.createStatement();
			//Patient table
			stmt.executeUpdate("CREATE TABLE Patient (" + 
			"id INTEGER PRIMARY KEY AUTOINCREMENT,"+
			"name TEXT,"+
			"birthDate DATE,"+
			"gender TEXT,"+
			"weight FLOAT)");
			//Symptons table  CORREGIR FALTA DE ORTOGRAF�A!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			stmt.executeUpdate("CREATE TABLE Symptons("+
			"id INTEGER PRIMARY KEY AUTOINCREMENT,"+
			"name TEXT,"+
			"type TEXT)");
			insertSymptons(1,"Hot head","external");
			insertSymptons(2,"Sneeze, mucus","external");
			insertSymptons(3,"Pain in the belly button","internal");
			insertSymptons(4,"Stomach ache","internal");
			insertSymptons(5,"none","none");
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
			"sympId INTEGER NULL REFERENCES Symptons(id),"+
			"illnessId INTEGER NULL REFERENCES Illness(id))"+
			"PRIMARY KEY(sympId,illnessId))");		
			linkSymptonsIllness(1,1);
			linkSymptonsIllness(2,2);
			linkSymptonsIllness(3,3);
			linkSymptonsIllness(4,4);
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
	
	public void linkSymtonsPatient(int idSymton, int idPatient){
		try {
			String sql = "INSERT INTO PatientSymptons (sympId, patID) VALUES (?,?)";
			PreparedStatement stm = c.prepareStatement(sql);
			stm.setInt(1,idSymton);
			stm.setInt(2,idPatient);
			stm.executeUpdate();
			stm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public String diagnosis(int idPatient){
		try {
			String sql = "SELECT i.name FROM Illness AS i JOIN SymptonsIllness AS si ON i.id=si.illnessId JOIN PatientSymptons AS ps ON si.sympId=ps.sympId WHERE patId=?";
			PreparedStatement stm = c.prepareStatement(sql);
			stm.setInt(idPatient,1);
			ResultSet rs = stm.executeQuery();
			String returned="";
			while(rs.next()){
				returned=rs.getString("name");
			}
			stm.close();
			return returned;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "Error"; 
	}

}