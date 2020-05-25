/**
 * 
 */
package com.strandls.resource.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import javax.inject.Inject;

import com.strandls.authentication_utility.filter.ValidateUser;
import com.strandls.resource.ApiConstants;
import com.strandls.resource.pojo.License;
import com.strandls.resource.pojo.ObservationResourceUser;
import com.strandls.resource.pojo.Resource;
import com.strandls.resource.pojo.ResourceRating;
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
@Path(ApiConstants.V1 + ApiConstants.RESOURCE)
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
	@Path(ApiConstants.GETPATH + "/{observationId}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)

	@ApiOperation(value = "Find Media Reource by Observation ID", notes = "Returns Path of the Resources", response = ObservationResourceUser.class, responseContainer = "List")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid ID", response = String.class) })

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

	@POST
	@Path(ApiConstants.CREATE + "/{objectType}/{objectId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)

	@ValidateUser
	@ApiOperation(value = "Create Resources against a objectId", notes = "Returns list of uncreated resources", response = Resource.class, responseContainer = "List")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid ID", response = String.class) })

	public Response createResource(@Context HttpServletRequest request, @PathParam("objectType") String objectType,
			@PathParam("objectId") String objectId, @ApiParam(name = "resources") List<Resource> resources) {
		try {
			Long id = Long.parseLong(objectId);
			List<Resource> result = service.createResource(objectType, id, resources);
			if (result.isEmpty())
				return Response.status(Status.CREATED).entity(null).build();
			return Response.status(206).entity(result).build();
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}

	@PUT
	@Path(ApiConstants.UPDATE + "/{objectType}/{objectId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)

	@ValidateUser
	@ApiOperation(value = "Update Resources against a objectId", notes = "Returns list of uncreated resources", response = Resource.class, responseContainer = "List")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid ID", response = String.class) })

	public Response updateResources(@Context HttpServletRequest request, @PathParam("objectType") String objectType,
			@PathParam("objectId") String objectId, @ApiParam(name = "resources") List<Resource> resources) {
		try {
			Long objId = Long.parseLong(objectId);
			List<Resource> result = service.updateResource(objectType, objId, resources);
			return Response.status(Status.OK).entity(result).build();
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}

	@PUT
	@Path(ApiConstants.UPDATE + ApiConstants.RATING + "/{objectType}/{objectId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)

	@ValidateUser

	@ApiOperation(value = "update the rating of the resource", notes = "Returns all the resource", response = Resource.class, responseContainer = "List")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "unable to update the rating", response = String.class) })

	public Response updateRating(@Context HttpServletRequest request, @PathParam("objectType") String objectType,
			@PathParam("objectId") String objectId, @ApiParam(name = "resourceRating") ResourceRating resourceRating) {
		try {
			Long objId = Long.parseLong(objectId);
			List<Resource> result = service.updateResourceRating(objectType, objId, resourceRating);
			return Response.status(Status.OK).entity(result).build();
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}

	}

	@GET
	@Path(ApiConstants.LICENSE + "/{licenseId}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)

	@ApiOperation(value = "Find Media Reource of License by ID", notes = "Returns Path of the license", response = License.class)
	@ApiResponses(value = { @ApiResponse(code = 404, message = "License not found", response = String.class),
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
