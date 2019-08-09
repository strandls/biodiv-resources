/**
 * 
 */
package com.strandls.resource.services;

import java.util.List;

import com.strandls.resource.pojo.License;
import com.strandls.resource.pojo.ObservationResourceUser;

/**
 * @author Abhishek Rudra
 *
 */
public interface ResourceServices {

	public List<ObservationResourceUser> getResouceURL(Long obvId);
	
	public License getLicenseResouce(Long licenseId);
}
