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

	/**
	 * 
	 */
	public ResourceData() {
		super();
	}

	/**
	 * @param resource
	 * @param userIbp
	 */
	public ResourceData(Resource resource, UserIbp userIbp) {
		super();
		this.resource = resource;
		this.userIbp = userIbp;
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

}
