package br.edu.ifsp.app.facadeBuy;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("orders")
public class FacadeBuyResource {

	FacadeBuyRepository facadeBuyRepository = new FacadeBuyRepository();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/all")
	public List<Order> getOrders() {
		System.out.println("no order/resource/all");

		List<Order> users = facadeBuyRepository.getOrders();
		return users;
	}
	
	@POST
	@Path("/order")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Order createOrder(Order order) {
		Order createdOrder = facadeBuyRepository.create(order);
		return createdOrder;
	}
	
	@PUT
	@Path("/order")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Order updateOrder(Order order) {
		Order updateOrder = facadeBuyRepository.update(order);
		return updateOrder;
	}
	
	@DELETE
	@Path("/order/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteOrder(@PathParam("id") int id) {
		Order order = facadeBuyRepository.getOrder(id);
		
		if (facadeBuyRepository.delete(id)) {
			return "OrderBooks " + order.getId() + " deleted.";
		} else {
			return "OrderBooks not found.";		
		}
	}
}