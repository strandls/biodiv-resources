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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Abhishek Rudra
 *
 */

@Entity
@Table(name = "observation_resource")
@JsonIgnoreProperties(ignoreUnknown = true)
@IdClass(CompositeKeyObservationResource.class)
public class ObservationResource implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7970642734279869311L;

	private Long observationId;
	private Long resourceId;

	/**
	 * @param observationId
	 * @param resourceId
	 */
	public ObservationResource(Long observationId, Long resourceId) {
		super();
		this.observationId = observationId;
		this.resourceId = resourceId;
	}

	@Id
	@Column(name = "observation_id")
	public Long getObservationId() {
		return observationId;
	}

	public void setObservationId(Long observationId) {
		this.observationId = observationId;
	}

	@Column(name = "resource_id")
	public Long getResourceId() {
		return resourceId;
	}

	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}

}
