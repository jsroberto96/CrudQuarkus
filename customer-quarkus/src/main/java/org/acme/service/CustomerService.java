package org.acme.service;

import java.util.List;

import org.acme.model.Customer;
import org.acme.repository.CustomerRepository;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class CustomerService {
	
	@Inject
	CustomerRepository customerRepository;
	
	public Response findAllCustomer(){
		
		if(customerRepository.findAll().count() == 0)
			throw new WebApplicationException("Customers not found!", Response.Status.NOT_FOUND);
			
		return Response
					.ok(customerRepository.findAll().list())
					.status(Response.Status.FOUND)
					.build();		
	}
	
	public Response findByIdCustomer(Long id){
	
		Customer cust = customerRepository.findById(id);
			
			if(cust == null) 
				throw new WebApplicationException("Customers not found!", Response.Status.NOT_FOUND);
						
			return Response
						.ok(cust)
						.status(Response.Status.FOUND)
						.build();		
	}
	
	public Response saveCustomer(Customer customer) {
		
		 customerRepository.persist(customer);
		 
		 return Response
				 	.ok(customer.getId())
				 	.status(Response.Status.CREATED)
				 	.build();
	}
	
	public Response updateCustomer(Customer customer) {
		
		Customer cust = customerRepository.findById(customer.getId());
		
		if(cust == null) 
			throw new WebApplicationException("Customers not found!", Response.Status.NOT_FOUND);
		
		 cust.setAge(customer.getAge());
		 cust.setEmail(customer.getEmail());
		 cust.setLastName(customer.getLastName());
		 cust.setName(customer.getName());
		 
		 return Response
				 	.ok(cust)
				 	.status(Response.Status.ACCEPTED)
				 	.build();	
	}
	
public Response deleteCustomer(Long id) {
		
		Customer cust = customerRepository.findById(id);
		
		if(cust == null) 
			throw new WebApplicationException("Customers not found!", Response.Status.NOT_FOUND);
		
		customerRepository.deleteById(id);
		 
		 return Response
				 	.ok(cust)
				 	.status(Response.Status.GONE)
				 	.build();	
	}
	

}
