package akillnator.pojo;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name="Analysis")
public class Analysis implements Serializable {

	private static final long serialVersionUID = -2545657309934633583L;
	
	@Id
	@GeneratedValue(generator="Analysis")
	@TableGenerator(name="Analysis", table="sqlite_sequence",
					pkColumnName="name", valueColumnName="seq", pkColumnValue="Analysis")
	private Integer id;
	private String name ;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="illness_id")
	private Illness illness;
	
	public Analysis() {
		super();
	}

	public Analysis(Integer id, String name, Illness illness) {
		super();
		this.id = id;
		this.name = name;
		this.illness=illness;
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

	public Illness getIllness() {
		return illness;
	}

	public void setIllness(Illness illness) {
		this.illness= illness;
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
