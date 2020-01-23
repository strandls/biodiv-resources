/**
 * 
 */
package com.strandls.resource.services;

import java.util.List;

import com.strandls.resource.pojo.License;
import com.strandls.resource.pojo.ObservationResourceUser;
import com.strandls.resource.pojo.Resource;

/**
 * @author Abhishek Rudra
 *
 */
public interface ResourceServices {

	public List<ObservationResourceUser> getResouceURL(Long obvId);

	public License getLicenseResouce(Long licenseId);

	public List<String> createResource(String objectType, Long objectId, List<Resource> resources);

	public List<Resource> updateResource(String objectType, Long objectId, List<Resource> newResources);
}
