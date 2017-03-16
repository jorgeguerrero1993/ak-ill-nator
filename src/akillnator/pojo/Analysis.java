package akillnator.pojo;
import java.io.Serializable;

public class Analysis implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2545657309934633583L;
	private Integer id;
	private String name ;
	private Integer illness_id;
	
	//
	public Illness FK= new Illness ();
	
	public Analysis() {
		super();
	}

	public Analysis(Integer id, String name, Integer illness_id) {
		super();
		this.id = id;
		this.name = name;
		this.illness_id = illness_id;
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

	public Integer getIllness_id() {
		return illness_id;
	}

	public void setIllness_id(Integer illness_id) {
		this.illness_id = illness_id;
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
		Analysis other = (Analysis) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}
