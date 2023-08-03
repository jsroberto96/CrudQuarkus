package org.acme.controller;

import java.util.ArrayList;
import java.util.List;

import org.acme.model.Customer;
import org.acme.service.CustomerService;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

@Path("/api/customer")
@Produces(MediaType.APPLICATION_JSON)
public class CustomerController {
	
	@Context
	UriInfo uriInfo;
	
	@Inject
	CustomerService customerService;
	
	@GET
	public Response getAllCustomers(){	
		return customerService.findAllCustomer();	
	}
	
	@GET
	@Path("{id}")
	public Response getByIdCustomers(@PathParam("id") Long id){	
		return customerService.findByIdCustomer(id);	
	}
	
	@POST
	@Path("/insere")
	@Transactional
	public Response saveCustomer(Customer customer) {
		return customerService.saveCustomer(customer);
	}
	
	@PUT
	@Path("/atualiza")
	@Transactional
	public Response updateCustomer(Customer customer) {
		return customerService.updateCustomer(customer);
	}
	
	@DELETE
	@Path("{id}")
	@Transactional
	public Response deleteCustomer(@PathParam("id") Long id) {
		return customerService.deleteCustomer(id);
	}
	

	

}
