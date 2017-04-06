package akillnator.jbdc;


import java.sql.Connection;
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

public class Dbmanager {
	
	private Connection c;
	
	public Dbmanager(){
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
			String sql = "INSERT INTO Patient (name, age,gender,weight) "
					+ "VALUES (?,?,?,?)";
			
			PreparedStatement stmt=c.prepareStatement(sql);
			stmt.setString(1, Abe.getName());
			stmt.setInt(2, Abe.getAge());
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
				int age = rs.getInt("age");
//				String gender = rs.getString("gender");
//				float weight  = rs.getFloat("weight");
				returnedList.add(new Patient(id,name,age/*,gender,weight*/));
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
			"age INTEGER,"+
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
			"accident TEXT);");
			//Drugs table
			stmt.executeUpdate("CREATE TABLE Drugs("+
			"id INTEGER PRIMARY KEY AUTOINCREMENT,"+
			"name TEXT,"+
			"warnings TEXT)");
			//Illness table
			//Treatment table
			//Analysis table
			//Patient-Previous events table
			//Patient-symptons table
			//Patient-drugs table
			//Previous events - symptons table
			//Symptons - Illness table
			//Drugs - Treatment table
			//Patient - Ilness table
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
	
	public void updatePatient ( Patient newPat){
		//compare info to know if it is " " then remain old info

	
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
				int age = rs.getInt("age");
//				String gender = rs.getString("gender");
//				float weight  = rs.getFloat("weight");
				returnedList.add(new Patient(id,name,age/*,gender,weight*/));
			}
			stmt.close();
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		return returnedList;
	}

}




