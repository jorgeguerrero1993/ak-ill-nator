package akillnator.pojo;

import java.io.Serializable;

public class Symptons implements Serializable{

	private static final long serialVersionUID = 4762337477636000539L;
	
	private Integer id;
	private String name;
	private String type;
	
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
