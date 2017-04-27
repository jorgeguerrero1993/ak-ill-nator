package akillnator.pojo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name="Treatment")
public class Treatment implements Serializable{

	private static final long serialVersionUID = 6224732364028493645L;
	
	@Id
	@GeneratedValue(generator="Treatment")
	@TableGenerator(name="Treatment", table="sqlite_sequence",
					pkColumnName="name", valueColumnName="seq", pkColumnValue="Treatment")
	private Integer id;
	private String name ;
	private String type;
	@OneToMany(mappedBy="treatment")
	private List <Illness> IllnessList;
	//Empty constructor
	public Treatment() {
		super();
	}
	
	

	public Treatment(Integer id, String name, String type) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
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

	
	//Equals
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
		Treatment other = (Treatment) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}
