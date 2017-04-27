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

@Entity
@Table(name="Drugs")
public class Drugs implements Serializable {
	
	private static final long serialVersionUID = -6779814022757918122L;
	
	@Id
	@GeneratedValue(generator="Drugs")
	@TableGenerator(name="Drugs",table="sqlite_sequence",
			pkColumnName="name", valueColumnName="seq",pkColumnValue="Drugs")
	private Integer id;
	private String name;
	private String warnings;
	
	@ManyToMany(mappedBy="drugs")
	List<Patient> patient;
	
	//Empty constructor
	public Drugs() {
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

	public String getWarnings() {
		return warnings;
	}

	public void setWarnings(String warnings) {
		this.warnings = warnings;
	}

	//Hashcode

	

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
		Drugs other = (Drugs) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	//ToString
	@Override
	public String toString() {
		return "Drugs [id=" + id + ", name=" + name + ", warnings=" + warnings + "]";
	}
}
