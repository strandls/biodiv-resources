package com.strandls.resource.controllers;

import com.strandls.resource.ApiConstants;
import com.strandls.resource.pojo.License;
import com.strandls.resource.services.LicenseServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Api("License Controller")
@Path(ApiConstants.V1 + ApiConstants.LICENSE)
public class LicenseController {

    @Inject
    LicenseServices licenseServices;

    @GET
    @Path(ApiConstants.ALL)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Fetch all licenses", notes = "Returns all licenses", response = License.class, responseContainer = "List")
    @ApiResponses(value = {@ApiResponse(code = 400, message = "Could not fetch licenses", response = String.class)})
    public Response getAllLicenses() {
        try {
            return Response.status(Status.OK).entity(licenseServices.getAllLicenses()).build();
        } catch (Exception ex) {
            return Response.status(Status.BAD_REQUEST).entity(ex.getMessage()).build();
        }
    }
}
