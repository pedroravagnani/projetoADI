package br.edu.ifsp.app.book;

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

@Path("books")
public class BookResource {

	BookRepository bookRepository = new BookRepository();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/all")
	public List<Book> getBooks() {
		System.out.println("no book/resource/all");

		List<Book> users = bookRepository.getBooks();
		return users;
	}
	
	@POST
	@Path("/book")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Book createBook(Book book) {
		Book createdBook = bookRepository.create(book);
		return createdBook;
	}
	
	@PUT
	@Path("/book")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Book updateBook(Book book) {
		Book updateBook = bookRepository.update(book);
		return updateBook;
	}
	
	@DELETE
	@Path("/book/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteBook(@PathParam("id") int id) {
		Book book = bookRepository.getBook(id);
		
		if (bookRepository.delete(id)) {
			return "Book " + book.getName() + " deleted.";
		} else {
			return "Book not found.";		
		}
	}
}
