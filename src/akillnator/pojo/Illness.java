package akillnator.pojo;
import java.io.Serializable;
import java.util.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name="Illness")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="Illness")
public class Illness implements Serializable {
	
	private static final long serialVersionUID = -6017147906365330498L;
	
	@Id
	@GeneratedValue(generator="Illness")
	@TableGenerator(name="Illness", table="sqlite_sequence",
					pkColumnName="name", valueColumnName="seq", pkColumnValue="Illness")
	@XmlTransient
	private Integer id;
	@XmlElement
	private String name;
	@XmlElement
	private String text;
	@XmlElement
	private String aprox_duration;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="treatment_id")
	@XmlElement(name="Treatment")
	private Treatment treatment; //% Es una fk, no meterlo en el toString
	
	@OneToMany(mappedBy="illness")
	@XmlElement(name="Analysis")
	private List <Analysis> AnalysisList; //% Tampoco meter en el toString
	
	@ManyToMany
	@JoinTable(name="symp_ill",
			joinColumns={@JoinColumn(name="ill_id", referencedColumnName="id")},
			inverseJoinColumns={@JoinColumn(name="symp_id", referencedColumnName="id")})
	@XmlElement(name="Sympton")
	private List<Symptons> symptons;
	//Empty constructor
	public Illness() {
		super();
	}
	
	//Getter and setter
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getAprox_duration() {
		return aprox_duration;
	}

	public void setAprox_duration(String aprox_duration) {
		this.aprox_duration = aprox_duration;
	}

	public Treatment getTreatment() {
		return treatment;
	}

	public void setTreatment(Treatment treatment) {
		this.treatment = treatment;
	}

	public List<Analysis> getAnalysisList() {
		return AnalysisList;
	}

	public void setAnalysisList(List<Analysis> analysisList) {
		AnalysisList = analysisList;
	}

	
	//Hashcode
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	
	//Equals
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Illness other = (Illness) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	//To String
	@Override
	public String toString() {
		return "Illness [id=" + id + ", name=" + name + ", text=" + text + ", aprox_duration=" + aprox_duration + "]";
	}
	
	
}
