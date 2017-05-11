package akillnator.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@Entity
@Table(name="DrugsTreatment")
@IdClass(DrugsTreatmentId.class)
@XmlAccessorType(XmlAccessType.FIELD)

public class DrugsTreatment implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private Integer drugs_id;
	@Id
	private Integer treatment_id;
	@Column(name="Quantity")
	private int quantity;
	@Column(name="Frequency")
	private String frequency;
	@ManyToOne
	@PrimaryKeyJoinColumn(name="drugs_id", referencedColumnName="id")
	private Drugs drugs;
	@ManyToOne
	@PrimaryKeyJoinColumn(name="treatment_id", referencedColumnName="id")
	private Treatment treatment;
	public Integer getDrugs_id() {
		return drugs_id;
	}
	public void setDrugs_id(Integer drugs_id) {
		this.drugs_id = drugs_id;
	}
	public Integer getTreatment_id() {
		return treatment_id;
	}
	public void setTreatment_id(Integer treatment_id) {
		this.treatment_id = treatment_id;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getFrequency() {
		return frequency;
	}
	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}
	public Drugs getDrugs() {
		return drugs;
	}
	public void setDrugs(Drugs drugs) {
		this.drugs = drugs;
	}
	public Treatment getTreatment() {
		return treatment;
	}
	public void setTreatment(Treatment treatment) {
		this.treatment = treatment;
	}
	
	
}
