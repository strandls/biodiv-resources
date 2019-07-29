/**
 * 
 */
package com.strandls.resource.services.Impl;

import java.util.List;

import com.google.inject.Inject;
import com.strandls.resource.dao.LicenseDao;
import com.strandls.resource.dao.ResourceDao;
import com.strandls.resource.pojo.License;
import com.strandls.resource.pojo.Resource;
import com.strandls.resource.services.ResourceServices;

/**
 * @author Abhishek Rudra
 *
 */
public class ResourceServicesImpl implements ResourceServices {

	@Inject
	private ResourceDao resourceDao;
	
	@Inject
	private LicenseDao licenseDao;

	@Override
	public List<Resource> getResouceURL(Long obvId) {
		List<Resource> resource = resourceDao.findByObservationId(obvId);
		return resource;
	}

	@Override
	public License getLicenseResouce(Long licenseId) {
		License license = licenseDao.findById(licenseId);
		return license;
	}

}
