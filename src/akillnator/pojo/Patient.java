package akillnator.pojo;

import java.io.Serializable;

public class Patient implements Serializable{

	private static final long serialVersionUID = -8841012208499645685L;
	
	private  Integer id;
	private String name;
	private Integer age;
	private String gender;
	private float weight;
	
	
	public Patient() {
		super();
	}
	
	public Patient(Integer id, String name, Integer age, String gender, float weight){
		this.id=id;
		this.name=name;
		this.age=age;
		this.gender=gender;
		this.weight=weight;
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


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
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
		return "Patient [id=" + id + ", name=" + name + ", age=" + age + ", gender=" + gender + ", weight=" + weight
				+ "]";
	}
	
	
}
