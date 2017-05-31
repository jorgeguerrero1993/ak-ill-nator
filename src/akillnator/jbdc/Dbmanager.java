package akillnator.jbdc;

import java.sql.SQLException;
import java.util.List;

import akillnator.pojo.Patient;

public interface Dbmanager {
	public List <Patient> getAllPatients();
	public void insertPatient(Patient Abe);
	public void createTable();
	public void deletePatient(int id)throws SQLException;
	public String updatePatient ( Patient newPat);
	
}
