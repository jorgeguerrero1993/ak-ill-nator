package akillnator.pojo;
import java.io.Serializable;

public class Illness {
	
	//Atributes
	private Integer id;
	private String name;
	private String text;
	private String aprox_duration;
	private Treatment treatment_id; //Es una fk, no meterlo en el toString
	
	//Empty constructor
	public Illness() {
		super();
	}
	
	//Getter and seter
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
/**	public Integer getTreatment_id() {
		return treatment_id;
	}
	public void setTreatment_id(Integer treatment_id) {
		this.Treatment = treatment_id;
	}
**/
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
