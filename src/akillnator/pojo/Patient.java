package akillnator.pojo;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name="Patient")
public class Patient implements Serializable{

	private static final long serialVersionUID = -8841012208499645685L;
	
	@Id
	@GeneratedValue(generator="Patient")
	@TableGenerator(name="Patient",table="sqlite_sequence",
			pkColumnName="name", valueColumnName="seq",pkColumnValue="Patient")
	private  Integer id;
	private String name;
	private Date birthDate;
	private String gender;
	private float weight;
	
	
	public Patient() {
		super();
	}
	
	public Patient(int id, String name, Date birthDate, String gender, float weight){
		this.id=id;
		this.name=name;
		this.birthDate=birthDate;
		this.gender=gender;
		this.weight=weight;
	}
	
	public Patient(String name, Date birthDate, String gender, float weight){
	
		this.name=name;
		this.birthDate=birthDate;
		this.gender=gender;
		this.weight=weight;
	}
	
	public Patient(int id, String name, Date birthDate){
		this.id=id;
		this.name=name;
		this.birthDate=birthDate;
		
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public float getWeight() {
		return weight;
	}


	public void setWeight(float weight) {
		this.weight = weight;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Patient other = (Patient) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Patient [id=" + id + ", name=" + name + ", birthDate=" + birthDate + ", gender=" + gender + ", weight=" + weight
				+ "]";
	}
	
	
}
