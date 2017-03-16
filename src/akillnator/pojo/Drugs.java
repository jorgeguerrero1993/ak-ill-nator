package akillnator.pojo;
import java.io.Serializable;

public class Drugs implements Serializable {
	
	private static final long serialVersionUID = -6779814022757918122L;
	
	//Atributes
	private Integer ID;
	private String name;
	private String warnings;
	
	//Empty constructor
	public Drugs() {
		super();
	}

	//Getter and setter
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
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
		result = prime * result + ID;
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
		Drugs other = (Drugs) obj;
		if (ID != other.ID)
			return false;
		return true;
	}

	//ToString
	@Override
	public String toString() {
		return "Drugs [ID=" + ID + ", name=" + name + ", warnings=" + warnings + "]";
	}
	
	
}
