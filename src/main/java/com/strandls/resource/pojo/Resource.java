/**
 * 
 */
package com.strandls.resource.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModel;

/**
 * @author Abhishek Rudra
 *
 */
@ApiModel
@Entity
@Table(name = "resource")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Resource implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4877757562860141754L;

	private Long id;
	private String fileName;
	private String type;
	private Long uploaderId;
	private String context;
	private Long licenseId;

	@Id
	@GeneratedValue
	@Column(name = "id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "file_name")
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Column(name = "type")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "context")
	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	@Column(name = "license_id")
	public Long getLicenseId() {
		return licenseId;
	}

	public void setLicenseId(Long licenseId) {
		this.licenseId = licenseId;
	}

	@Column(name = "uploader_id")
	public Long getUploaderId() {
		return uploaderId;
	}

	public void setUploaderId(Long uploaderId) {
		this.uploaderId = uploaderId;
	}

}
