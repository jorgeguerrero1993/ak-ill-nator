package akillnator.pojo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name="DrugsTreatment")
@IdClass(DrugsTreatmentId.class)
public class DrugsTreatment implements Serializable{
	@Id
	private Integer drugs_id;
	@Id
	private Integer treatment_id;
	//@ManyToMany
	
}
