package akillnator.pojo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name="PreviousEvents")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="PreviousEvents")
public class PreviousEvents implements Serializable{

	private static final long serialVersionUID = 2900707925162426155L;
	
	@Id
	@GeneratedValue(generator="PreviousEvents")
	@TableGenerator(name="PreviousEvents", table="sqlite_sequence",
			pkColumnName="name", valueColumnName="seq", pkColumnValue="PreviousEvents")
	@XmlTransient
	private Integer id;
	@XmlElement
	private String environment;
	@XmlElement
	private String accident;
	
	@ManyToMany(mappedBy="prev")
	@XmlElement(name="Patient")
	List<Patient> patient;
	
	@ManyToMany(mappedBy="prev")
	@XmlElement(name="Sympton")
	List<Symptons> symptons;
	public PreviousEvents(){
		super();
	}
	
	public PreviousEvents(Integer id, String environment, String accident){
		this.id=id;
		this.environment=environment;
		this.accident=accident;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEnvironment() {
		return environment;
	}

	public void setEnvironment(String environment) {
		this.environment = environment;
	}

	public String getAccident() {
		return accident;
	}

	public void setAccident(String accident) {
		this.accident = accident;
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
		PreviousEvents other = (PreviousEvents) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PreviousEvents [id=" + id + ", environment=" + environment + ", accident=" + accident + "]";
	}
	
	
}
