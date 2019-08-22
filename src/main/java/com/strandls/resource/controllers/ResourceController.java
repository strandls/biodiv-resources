/**
 * 
 */
package com.strandls.resource.controllers;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.google.inject.Inject;
import com.strandls.resource.ApiConstants;
import com.strandls.resource.pojo.License;
import com.strandls.resource.pojo.ObservationResourceUser;
import com.strandls.resource.services.ResourceServices;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author Abhishek Rudra
 *
 */

@Api("Resource Services")
@Path(ApiConstants.V1 + ApiConstants.GETPATH)
public class ResourceController {

	@Inject
	private ResourceServices service;

	@ApiOperation(value = "Dummy API Ping", notes = "Checks validity of war file at deployment", response = String.class)

	@GET
	@Path(ApiConstants.PING)
	@Produces(MediaType.TEXT_PLAIN)
	public String getPong() {
		return "PONG";
	}

	@GET
	@Path(ApiConstants.RESOURCE + "/{observationId}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)

	@ApiOperation(value = "Find Media Reource by Observation ID", notes = "Returns Path of the Resources", response = ObservationResourceUser.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Success", response = ObservationResourceUser.class, responseContainer = "List"),
			@ApiResponse(code = 400, message = "Invalid ID", response = String.class) })

	public Response getImageResource(
			@ApiParam(value = "ID Observation for Resource", required = true) @PathParam("observationId") String obvId) {
		try {
			Long id = Long.parseLong(obvId);
			List<ObservationResourceUser> resource = service.getResouceURL(id);
			return Response.status(Status.OK).entity(resource).build();
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).build();
		}
	}

	@GET
	@Path(ApiConstants.LICENSE + "/{licenseId}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)

	@ApiOperation(value = "Find Media Reource of License by ID", notes = "Returns Path of the license", response = License.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = License.class),
			@ApiResponse(code = 404, message = "License not found", response = String.class),
			@ApiResponse(code = 400, message = "Invalid ID", response = String.class) })

	public Response getLicenseResource(
			@ApiParam(value = "ID for License Resource", required = true) @PathParam("licenseId") String licenseId) {
		try {
			Long id = Long.parseLong(licenseId);
			License license = service.getLicenseResouce(id);

			if (license != null)
				return Response.status(Status.OK).entity(license).build();
			else
				return Response.status(Status.NOT_FOUND).build();

		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).build();
		}
	}
}
