package akillnator.pojo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="DrugsTreatment")
@IdClass(DrugsTreatmentId.class)
public class DrugsTreatment implements Serializable{
	@Id
	private Integer drugs_id;
	@Id
	private Integer treatment_id;
	@ManyToOne
	@PrimaryKeyJoinColumn(name="drugs_id", referencedColumnName="id")
	private Drugs drugs;
	@ManyToOne
	@PrimaryKeyJoinColumn(name="treatment_id", referencedColumnName="id")
	private Treatment treatment;
}
