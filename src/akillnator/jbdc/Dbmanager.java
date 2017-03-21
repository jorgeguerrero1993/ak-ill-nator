package akillnator.jbdc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import akillnator.pojo.Patient;

public class Dbmanager {
	
	
	private Connection c;
	
	public Dbmanager(Connection c){
		
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
	
	public void InsertPatient(Patient c ){
		
	}
}
