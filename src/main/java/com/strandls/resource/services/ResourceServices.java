/**
 * 
 */
package com.strandls.resource.services;

import java.util.List;

import com.strandls.resource.pojo.License;
import com.strandls.resource.pojo.ObservationResourceUser;
import com.strandls.resource.pojo.Resource;
import com.strandls.resource.pojo.ResourceRating;
import com.strandls.resource.pojo.UFile;
import com.strandls.resource.pojo.UFileCreateData;

/**
 * @author Abhishek Rudra
 *
 */
public interface ResourceServices {

	public List<ObservationResourceUser> getResouceURL(Long obvId);

	public License getLicenseResouce(Long licenseId);

	public List<Resource> createResource(String objectType, Long objectId, List<Resource> resources);

	public List<Resource> updateResource(String objectType, Long objectId, List<Resource> newResources);

	public List<Resource> updateResourceRating(String objectType, Long objectId, ResourceRating resourceRating);

	public UFile uFileFindById(Long id);

	public UFile createUFile(UFileCreateData ufileCreateData);

}
