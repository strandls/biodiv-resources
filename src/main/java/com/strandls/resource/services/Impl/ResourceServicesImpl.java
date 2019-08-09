/**
 * 
 */
package com.strandls.resource.services.Impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.strandls.resource.dao.LicenseDao;
import com.strandls.resource.dao.ResourceDao;
import com.strandls.resource.pojo.License;
import com.strandls.resource.pojo.ObservationResourceUser;
import com.strandls.resource.pojo.Resource;
import com.strandls.resource.services.ResourceServices;
import com.strandls.user.ApiException;
import com.strandls.user.controller.UserServiceApi;
import com.strandls.user.pojo.User;

/**
 * @author Abhishek Rudra
 *
 */
public class ResourceServicesImpl implements ResourceServices {

	private static final Logger logger = LoggerFactory.getLogger(ResourceServicesImpl.class);
	
	@Inject
	private ResourceDao resourceDao;
	
	@Inject
	private LicenseDao licenseDao;
	
	@Inject
	private UserServiceApi userService;

	@Override
	public List<ObservationResourceUser> getResouceURL(Long obvId) {
		List<ObservationResourceUser> observationResourceUsers = new ArrayList<ObservationResourceUser>();
		List<Resource> resource = resourceDao.findByObservationId(obvId);
		for(Resource r: resource) {
			try {
				User u= userService.getUser(r.getUploaderId().toString());
				observationResourceUsers.add(new ObservationResourceUser(r,u));
			} catch (ApiException e) {
				logger.error(e.getMessage());
			}
			
		}
		return observationResourceUsers;
	}

	@Override
	public License getLicenseResouce(Long licenseId) {
		License license = licenseDao.findById(licenseId);
		return license;
	}

}
