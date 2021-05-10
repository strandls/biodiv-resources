/**
 * 
 */
package com.strandls.resource.pojo;

import com.strandls.user.pojo.UserIbp;

import io.swagger.annotations.ApiModel;

/**
 * @author Abhishek Rudra
 *
 */

@ApiModel
public class ResourceData {

	private Resource resource;
	private UserIbp userIbp;
	private License license;

	/**
	 * 
	 */
	public ResourceData() {
		super();
	}

	/**
	 * @param resource
	 * @param userIbp
	 * @param license
	 */
	public ResourceData(Resource resource, UserIbp userIbp, License license) {
		super();
		this.resource = resource;
		this.userIbp = userIbp;
		this.license = license;
	}

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public UserIbp getUserIbp() {
		return userIbp;
	}

	public void setUserIbp(UserIbp userIbp) {
		this.userIbp = userIbp;
	}

	public License getLicense() {
		return license;
	}

	public void setLicense(License license) {
		this.license = license;
	}

}
