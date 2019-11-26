package br.edu.ifsp.app.book;

import java.util.Arrays;
import java.util.List;

import javax.persistence.Convert;
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
import com.google.gson.GsonBuilder;

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
	public BaseBook createBook(String book) {
		GsonBuilder builder = new GsonBuilder();
		builder.registerTypeAdapter(BaseBook.class, new InterfaceAdapter());
		Gson gson = builder.create();

		
		System.out.println("criando book");
		
		if(book.contains("downloadLink")) {
			System.out.println("Ebook!");
			String json = gson.toJson(book, BaseBook.class);
			EBook json1 = gson.fromJson(json, EBook.class);
			BaseBook createdBook = bookRepository.create(json1);
			return createdBook;
		}
		else {
			System.out.println("Book!");
			String json = gson.toJson(book, BaseBook.class);
			Book json1 = gson.fromJson(json, Book.class);
			BaseBook createdBook = bookRepository.create(json1);
			return createdBook;
		}
		
		//String json = gson.toJson(book, BaseBook.class);
		//System.out.println(book);
		//System.out.println("Deserealizing");
		//System.out.println(json);
		//BaseBook booksClass = gson.fromJson(json, BaseBook.class);
		//BaseBook booksClass = gson.fromJson(book, BaseBook.class);
		//System.out.println(booksClass);
		
		//BaseBook createdBook = null;
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
