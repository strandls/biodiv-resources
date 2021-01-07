package com.strandls.resource.services.Impl;

import com.strandls.resource.dao.LicenseDao;
import com.strandls.resource.pojo.License;
import com.strandls.resource.services.LicenseServices;

import javax.inject.Inject;
import java.util.List;

public class LicenseServicesImpl implements LicenseServices {

    @Inject
    LicenseDao licenseDao;

    @Override
    public List<License> getAllLicenses() {
        return licenseDao.findAll();
    }
}
