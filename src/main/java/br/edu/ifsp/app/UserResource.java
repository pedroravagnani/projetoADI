package br.edu.ifsp.app;

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

@Path("users")
public class UserResource {

	UserRepository userRepository = new UserRepository();
		
	@GET
	@Path("user/{id}")
	@Produces(MediaType.APPLICATION_XML)
	public User getUser(@PathParam("id") int id) {
		User user = userRepository.getUser(id);
		return user;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/all")
	public List<User> getUsers() {
		System.out.println("no user/resource/all");
		
		List<User> users = userRepository.getUsers();
		return users;
	}
	
	@POST
	@Path("/user")
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public User createUser(User user) {
		User createdUser = userRepository.create(user);
		return createdUser;
	}
	
	@PUT
	@Path("/user")
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public User updateUser(User user) {
		User updateUser = userRepository.update(user);
		return updateUser;
	}
	
	@DELETE
	@Path("/user/{id}")
	public String deleteUser(@PathParam("id") int id) {
		User user = userRepository.getUser(id);
		
		if (userRepository.delete(user)) {
			return "User " + user.getName() + " deleted.";
		} else {
			return "User not found.";		
		}
	}
}
