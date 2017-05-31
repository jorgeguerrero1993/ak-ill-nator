package akillnator.jpa;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import akillnator.jbdc.Dbmanager;

import akillnator.pojo.*;

import akillnator.pojo.Illness;
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
	
	
	//No estoy seguro de que se haga así
	@Override
	public String updatePatient(Patient newPat) {
		Query q=em.createNativeQuery("SELECT * FROM Patient WHERE id=?",Patient.class);
		q.setParameter(1, newPat.getId());
		Patient oldPatient = (Patient) q.getSingleResult();
		em.getTransaction().begin();
		oldPatient.setId(newPat.getId());
		oldPatient.setBirthDate(newPat.getBirthDate());
		oldPatient.setName(newPat.getName());
		oldPatient.setWeight(newPat.getWeight());
		em.getTransaction().commit();
		return "Correctly Updated";
	}
	
	@Override
	public List<Patient> getAllPatients() {
		// TODO Auto-generated method stub
		return null;
	}
	
	//Not needed, already done in JDBC
	@Override
	public void createTable() {

	}

	@Override
	public void deletePatient(int id) throws SQLException {
		Query q=em.createNativeQuery("SELECT * FROM Patient WHERE id=?",Patient.class);
		q.setParameter(1,id);
		Patient fired = (Patient) q.getSingleResult();
		em.getTransaction().begin();
		em.remove(fired);
		em.getTransaction().commit();
	}

	//
	@Override
	public void insertPatient(Patient patientCreated) {
		em.getTransaction().begin();
		em.persist(patientCreated);
		em.getTransaction().commit();
	}

    // JPA read illness method to obtain a list with all of them 
	public List<Illness> getAllIllnessJPA (){
	    Query q= em.createNativeQuery("SELECT * FROM Illness", Illness.class);
	    List <Illness> lista = q.getResultList();
		return lista;
		
	}
	
}















