package akillnator.jbdc;


import java.util.*;
import java.sql.*;

import akillnator.pojo.Analysis;
import akillnator.pojo.Drugs;
import akillnator.pojo.Illness;
import akillnator.pojo.Patient;
import akillnator.pojo.PreviousEvents;
import akillnator.pojo.Symptons;
import akillnator.pojo.Treatment;

public class Dbmanager {
	
	
	private Connection c;
	
	public Dbmanager(Connection c){
	
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
			String sql = "INSERT INTO Patient (name, address) "
					+ "VALUES ('" + Abe.getName() + "', '" + Abe.getName()	+ "');";
			
			Statement stmt = c.createStatement();
		
		
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
	
	public void deletePatient(Patient Abe)throws SQLException{
		String sql = "DELETE "+Abe+" FROM patient ";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.executeUpdate();
	}
	public void deleteAnalysis(Analysis analysis)throws SQLException{
		String sql = "DELETE "+analysis+" FROM analysis ";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.executeUpdate();
	}
	public void deleteDrugs(Drugs drugs)throws SQLException{
		String sql = "DELETE "+drugs+" FROM drugs ";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.executeUpdate();
	}
	public void deleteIllness(Illness ill)throws SQLException{
		String sql = "DELETE "+ill+" FROM patient ";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.executeUpdate();
	}
	public void deletePreviousEvents(PreviousEvents prvev)throws SQLException{
		String sql = "DELETE "+prvev+" FROM patient ";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.executeUpdate();
	}
	public void deleteSymptons(Symptons symp)throws SQLException{
		String sql = "DELETE "+symp+" FROM patient ";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.executeUpdate();
	}
	public void deleteTreatment(Treatment treatment)throws SQLException{
		String sql = "DELETE "+treatment+" FROM patient ";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.executeUpdate();
	}
}
