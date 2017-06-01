package akillnator.pojo;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
//import sample.db.xml.utils.SQLDateAdapter;

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
	@XmlElement
	private String name;
	
	@XmlJavaTypeAdapter(SQLDateAdapter.class)
	private Date birthDate;
	
	
	@XmlElement
	private String gender;
	@XmlElement
	private float weight;
	
	@ManyToMany
	@JoinTable(name="pat_prevevent",
			joinColumns={@JoinColumn(name="pat_id", referencedColumnName="id")},
			inverseJoinColumns={@JoinColumn(name="prev_id", referencedColumnName="id")})
	@XmlElement(name="PreviousEvent")
	@XmlElementWrapper(name="PreviousEvents")
	List<PreviousEvents> prev;
	
	@ManyToMany
	@JoinTable(name="pat_ill",
			joinColumns={@JoinColumn(name="pat_id", referencedColumnName="id")},
			inverseJoinColumns={@JoinColumn(name="ill_id", referencedColumnName="id")})
	@XmlElement(name="Illness")
	@XmlElementWrapper(name="Illnesses")
	List<Illness> illness;
	
	@ManyToMany
	@JoinTable(name="pat_drugs",
	joinColumns={@JoinColumn(name="pat_id", referencedColumnName="id")},
	inverseJoinColumns={@JoinColumn(name="drugs_id", referencedColumnName="id")})
	@XmlElement(name="Drug")
	@XmlElementWrapper(name="Drugs")
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
