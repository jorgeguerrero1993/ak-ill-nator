package akillnator.jpa;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import akillnator.jbdc.Dbmanager;
import akillnator.pojo.Patient;

public class JPAmanager implements Dbmanager {
	
	EntityManager em;
	
	JPAmanager(){
		connect();
	}

	public void connect(){
		try {
		  em = Persistence.createEntityManagerFactory("akillnator-provider").createEntityManager();
		  em.getTransaction().begin();
		  em.createNativeQuery("PRAGMA foreign_keys= ON").executeUpdate();
		  em.getTransaction().commit();
		  
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void disconnect(){
		em.close();
	}
	
	
	
	@Override
	public List<Patient> getAllPatients() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createTable() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletePatient(int id) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String updatePatient(Patient newPat) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Patient> searchByName(String nameToSearch) {
		// TODO Auto-generated method stub
		return null;
	}

}
