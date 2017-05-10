package akillnator.pojo;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name="Patient")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="Patient")
public class Patient implements Serializable{

	private static final long serialVersionUID = -8841012208499645685L;
	
	@Id
	@GeneratedValue(generator="Patient")
	@TableGenerator(name="Patient",table="sqlite_sequence",
			pkColumnName="name", valueColumnName="seq",pkColumnValue="Patient")
	@XmlTransient
	private  Integer id;
	@XmlAttribute
	private String name;
	@XmlAttribute
	private Date birthDate;
	@XmlAttribute
	private String gender;
	@XmlAttribute
	private float weight;
	
	@ManyToMany
	@JoinTable(name="pat_prevevent",
			joinColumns={@JoinColumn(name="pat_id", referencedColumnName="id")},
			inverseJoinColumns={@JoinColumn(name="prev_id", referencedColumnName="id")})
	@XmlElement(name="PreviousEvent")
	List<PreviousEvents> prev;
	
	@ManyToMany
	@JoinTable(name="pat_ill",
			joinColumns={@JoinColumn(name="pat_id", referencedColumnName="id")},
			inverseJoinColumns={@JoinColumn(name="ill_id", referencedColumnName="id")})
	@XmlElement(name="Illness")
	List<Illness> illness;
	
	@ManyToMany
	@JoinTable(name="pat_drugs",
	joinColumns={@JoinColumn(name="pat_id", referencedColumnName="id")},
	inverseJoinColumns={@JoinColumn(name="drugs_id", referencedColumnName="id")})
	@XmlElement(name="Drug")
	List<Drugs> drugs;
	
	@ManyToMany
	@JoinTable(name="pat_symptons",
	joinColumns={@JoinColumn(name="pat_id", referencedColumnName="id")},
	inverseJoinColumns={@JoinColumn(name="symptons_id", referencedColumnName="id")})
	@XmlElement(name="Sympton")
	List<Symptons> symptons;
	
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
