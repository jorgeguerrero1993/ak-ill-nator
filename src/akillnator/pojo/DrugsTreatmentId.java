package akillnator.pojo;

import java.io.Serializable;

public class DrugsTreatmentId implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer drugs_id;
	private Integer treatment_id;
	
	public int hashCode(){
		return(int) (drugs_id + treatment_id);
	}
	
	public boolean equals(Object object){
		if(object instanceof DrugsTreatmentId){
			DrugsTreatmentId otherId=(DrugsTreatmentId) object;
			return (otherId.drugs_id== this.drugs_id) && 
					(otherId.treatment_id==this.treatment_id);
		}
		return false;
	}
}
