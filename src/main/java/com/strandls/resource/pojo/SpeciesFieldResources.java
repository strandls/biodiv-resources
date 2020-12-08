/**
 * 
 */
package com.strandls.resource.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

/**
 * @author Abhishek Rudra
 *
 * 
 */

@Entity
@Table(name = "species_field_resources")
@IdClass(SpeciesFieldResourcesCompositeKey.class)
public class SpeciesFieldResources implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8470152061019023476L;
	private Long speciesFieldId;
	private Long resourceId;

	/**
	 * 
	 */
	public SpeciesFieldResources() {
		super();
	}

	/**
	 * @param speciesFieldId
	 * @param resourceId
	 */
	public SpeciesFieldResources(Long speciesFieldId, Long resourceId) {
		super();
		this.speciesFieldId = speciesFieldId;
		this.resourceId = resourceId;
	}

	@Id
	@Column(name = "species_field_id")
	public Long getSpeciesFieldId() {
		return speciesFieldId;
	}

	public void setSpeciesFieldId(Long speciesFieldId) {
		this.speciesFieldId = speciesFieldId;
	}

	@Id
	@Column(name = "resource_id")
	public Long getResourceId() {
		return resourceId;
	}

	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}

}
