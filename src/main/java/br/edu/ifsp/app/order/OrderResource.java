package br.edu.ifsp.app.order;

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
public class OrderResource {

	OrderRepository orderRepository = new OrderRepository();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/all")
	public int getOrders() {
		System.out.println("no order/resource/all");
		return 1;

	//	List<Order> users = orderRepository.getOrders();
	//	return users;
	}
	
	@POST
	@Path("/order")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Order createOrder(Order order) {
		Order createdOrder = orderRepository.create(order);
		return createdOrder;
	}
	
	@PUT
	@Path("/order")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Order updateOrder(Order order) {
		Order updateOrder = orderRepository.update(order);
		return updateOrder;
	}
	
	@DELETE
	@Path("/order/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteOrder(@PathParam("id") int id) {
		Order order = orderRepository.getOrder(id);
		
		if (orderRepository.delete(id)) {
			return "Order " + order.getId() + " deleted.";
		} else {
			return "Order not found.";		
		}
	}
}