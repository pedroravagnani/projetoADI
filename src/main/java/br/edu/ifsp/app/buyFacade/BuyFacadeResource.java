package br.edu.ifsp.app.buyFacade;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

import br.edu.ifsp.app.user.User;


@Path("purchase")
public class BuyFacadeResource {
	@POST
	@Path("/order")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public User purchaseOrder(String json) {
		if (null == json) {
	        return null;
	    }

	    Gson gson = new Gson();
	    User user = gson.fromJson(json, User.class);

	    return user;
	}

	public Object checkStoks(Object bookItems) {
		
		return bookItems;
	}
}