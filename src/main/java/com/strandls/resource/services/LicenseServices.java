package com.strandls.resource.services;

import com.strandls.resource.pojo.License;

import java.util.List;

public interface LicenseServices {

	public List<License> getAllLicenses();

	public License getLicenseById(Long licenseId);
}
