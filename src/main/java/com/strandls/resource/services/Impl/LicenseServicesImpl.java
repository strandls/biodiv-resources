package com.strandls.resource.services.Impl;

import java.util.List;

import javax.inject.Inject;

import com.strandls.resource.dao.LicenseDao;
import com.strandls.resource.pojo.License;
import com.strandls.resource.services.LicenseServices;

public class LicenseServicesImpl implements LicenseServices {

	@Inject
	LicenseDao licenseDao;

	@Override
	public List<License> getAllLicenses() {
		return licenseDao.findAll();
	}

	@Override
	public License getLicenseById(Long licenseId) {
		return licenseDao.findById(licenseId);
	}
}
