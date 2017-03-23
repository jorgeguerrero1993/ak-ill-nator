package akillnator.jbdc;


import java.util.*;
import java.sql.*;

import akillnator.pojo.Patient;

public class Dbmanager {
	
	
	private Connection c;
	
	public Dbmanager(Connection c){
	
		Connect();
	}

	public void Connect(){
		try {
			Class.forName("org.sqlite.JDBC");
			c=DriverManager.getConnection("jdbc:sqlite:./db/akillnator.db");
			c.createStatement().execute("PRAGMA foreign_keys=ON");
			System.out.println("Database connection opened.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void Disconnect(){
		try {
			c.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void InsertPatient(Patient Abe ){
		
		Statement stmt;
		try {
			stmt = c.createStatement();
		
		String sql = "INSERT INTO Patient (name, address) "
				+ "VALUES ('" + Abe.getName() + "', '" + Abe.getName()	+ "');";
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
			while(rs.next() == true){
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				String gender = rs.getString("gender");
				float weight  = rs.getFloat("weight");
				returnedList.add(new Patient(id,name,age,gender,weight));
				stmt.close();
			}
		} 
		
		catch (SQLException e){
			e.printStackTrace();
		}
		return returnedList;
	}
	
	public void createTable(){
		try{
			Statement stmt = c.createStatement();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
	
}
