package akillnator.jbdc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

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
	
	public ArrayList <Patient> GetAllPatients(){
	
	}
	
	public void CreateTable(){
		
	}
	
}
