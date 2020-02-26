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
import com.strandls.resource.dao.ObservationResourceDao;
import com.strandls.resource.dao.ResourceDao;
import com.strandls.resource.pojo.License;
import com.strandls.resource.pojo.ObservationResource;
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

	@Inject
	private ObservationResourceDao observationResourceDao;

	@Override
	public List<ObservationResourceUser> getResouceURL(Long obvId) {
		List<ObservationResourceUser> observationResourceUsers = new ArrayList<ObservationResourceUser>();
		List<Resource> resource = resourceDao.findByObservationId(obvId);
		for (Resource r : resource) {
			try {
				User u = userService.getUser(r.getUploaderId().toString());
				observationResourceUsers.add(new ObservationResourceUser(r, u));
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

	@Override
	public List<Resource> createResource(String objectType, Long objectId, List<Resource> resources) {
		for (Resource resource : resources) {
			Resource result = resourceDao.save(resource);
			if (result != null) {
				logger.debug("Resource Created with ID :" + result.getId());

				if (objectType.equalsIgnoreCase("observation")) {
					ObservationResource entity = new ObservationResource(objectId, result.getId());
					ObservationResource mappingResult = observationResourceDao.save(entity);
					logger.debug("Observation Resource Mapping Created: " + mappingResult.getObservationId() + " and "
							+ mappingResult.getResourceId());
				}
			}

		}
		resources = resourceDao.findByObservationId(objectId);
		return resources;

	}

	@Override
	public List<Resource> updateResource(String objectType, Long objectId, List<Resource> newResources) {

		List<Resource> resourceList = new ArrayList<Resource>();
		int flag = 0;
		List<Resource> oldResourcesList = resourceDao.findByObservationId(objectId);
		for (Resource resource : newResources) {
			flag = 0;
			for (Resource oldResource : oldResourcesList) {
				if (oldResource.getFileName().equals(resource.getFileName())) {
					flag = 1;
					resource.setId(oldResource.getId());
					resourceDao.update(resource);
					break;
				}
			}
			if (flag == 0) {
				resource = resourceDao.save(resource);

				if (objectType.equalsIgnoreCase("observation")) {
					ObservationResource entity = new ObservationResource(objectId, resource.getId());
					ObservationResource mappingResult = observationResourceDao.save(entity);
					logger.debug("Observation Resource Mapping Created: " + mappingResult.getObservationId() + " and "
							+ mappingResult.getResourceId());
				}
			}
		}
		for (Resource oldResource : oldResourcesList) {
			flag = 0;
			for (Resource resource : newResources) {
				if (oldResource.getFileName().equals(resource.getFileName())) {
					flag = 1;
				}
			}
			if (flag == 0) {
				ObservationResource observationResource = observationResourceDao.findByPair(objectId,
						oldResource.getId());
				observationResourceDao.delete(observationResource);
			}
		}

		resourceList = resourceDao.findByObservationId(objectId);
		return resourceList;
	}

}
