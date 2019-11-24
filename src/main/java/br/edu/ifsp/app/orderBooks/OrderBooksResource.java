package br.edu.ifsp.app.orderBooks;

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


@Path("orders-books")
public class OrderBooksResource {

	OrderBooksRepository orderBooksRepository = new OrderBooksRepository();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/all")
	public List<OrderBooks> getOrders() {
		System.out.println("no order/resource/all");

		List<OrderBooks> users = orderBooksRepository.getOrders();
		return users;
	}
	
	@POST
	@Path("/order")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public OrderBooks createOrder(OrderBooks orderBooks) {
		OrderBooks createdOrder = orderBooksRepository.create(orderBooks);
		return createdOrder;
	}
	
	@PUT
	@Path("/order")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public OrderBooks updateOrder(OrderBooks orderBooks) {
		OrderBooks updateOrder = orderBooksRepository.update(orderBooks);
		return updateOrder;
	}
	
	@DELETE
	@Path("/order/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteOrder(@PathParam("id") int id) {
		OrderBooks orderBooks = orderBooksRepository.getOrder(id);
		
		if (orderBooksRepository.delete(id)) {
			return "OrderBooks " + orderBooks.getId() + " deleted.";
		} else {
			return "OrderBooks not found.";		
		}
	}
}