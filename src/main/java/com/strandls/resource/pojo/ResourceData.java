/**
 * 
 */
package com.strandls.resource.pojo;

import com.strandls.user.pojo.User;

import io.swagger.annotations.ApiModel;

/**
 * @author Abhishek Rudra
 *
 */

@ApiModel
public class ResourceData {

	private Resource resource;
	private User user;

	/**
	 * @param resource
	 * @param user
	 */
	public ResourceData(Resource resource, User user) {
		super();
		this.resource = resource;
		this.user = user;
	}

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
