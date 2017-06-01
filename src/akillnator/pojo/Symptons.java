package akillnator.pojo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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
@Table(name="Symptons")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="Symptons")
public class Symptons implements Serializable{

	private static final long serialVersionUID = 4762337477636000539L;
	
	@Id
	@GeneratedValue(generator="Symptons")
	@TableGenerator(name="Symptons", table="sqlite_sequence",
			pkColumnName="name", valueColumnName="seq", pkColumnValue="Symptons")
	@XmlTransient
	private Integer id;
	@XmlElement
	private String name;
	@XmlElement
	private String type;
	
	@ManyToMany(mappedBy="symptons")
	@XmlElement(name="Illness")
	private List<Illness> illness;
	
	@ManyToMany
	@JoinTable(name="symp_prev",
			joinColumns={@JoinColumn(name="symp_id", referencedColumnName="id")},
			inverseJoinColumns={@JoinColumn(name="prev_id", referencedColumnName="id")})
	@XmlElement(name="PreviousEvent")
	private List<PreviousEvents> prev;
	
	@ManyToMany(mappedBy="symptons")
	@XmlElement(name="Patient")
	private List<Patient> patient;
	public Symptons(){
		super();
	}
	
	public Symptons(Integer id, String name, String type){
		this.id=id;
		this.name=name;
		this.type=type;
	}

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
		Symptons other = (Symptons) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Symptons [id=" + id + ", name=" + name + ", type=" + type + "]";
	}
	
	
}
