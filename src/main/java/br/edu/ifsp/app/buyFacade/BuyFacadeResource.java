package br.edu.ifsp.app.buyFacade;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

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

import br.edu.ifsp.app.book.BookRepository;
import br.edu.ifsp.app.order.OrderRepository;
import br.edu.ifsp.app.orderBooks.OrderBooks;
import br.edu.ifsp.app.user.User;
import br.edu.ifsp.app.book.Book;

@Path("purchase")
public class BuyFacadeResource {
	@POST
	@Path("/order")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String purchaseOrder(String json) {
		if (null == json) {
	        return null;
	    }

		String response = "";
	    Gson gson = new Gson();
	    User user = gson.fromJson(json, User.class);

	    List<Book> noStock = this.checkStocks(user.getOrderBooks());

	    if (noStock.isEmpty() == false) {
	    	ListIterator<Book> booksIt = noStock.listIterator();

	    	response = "{"
	    		+ "\"error\": true,"
	    		+ "\"message\": \"Insuficient stock\","
	    		+ "\"data\": [";

	    	while(booksIt.hasNext()) {
	    		Book book = booksIt.next();

	    		response += "{\"id\": \"" + book.getId() + "\",";
	    		response += "\"name\": \"" + book.getName() + "\",";
	    		response += "\"stock\": \"" + book.getStock() + "\",";
	    		response += "\"category\": \"" + book.getCategory() + "\",";
	    		response += "\"price\": \"" + book.getPrice() + "\"}";
	    	}

	    	response += "]}]";

	    	return response;
	    }
	    
	    List<OrderBooks> orderBooks = this.getTotalItemBooks(user.getOrderBooks()); 

	    return gson.toJson(orderBooks);
	}

	private List<Book> checkStocks(List<OrderBooks> orderBooks) {
		BookRepository bookRepository = new BookRepository();
		
		List<Book> books = bookRepository.getOutStock(orderBooks);

		return books;
	}

	private List<OrderBooks> getTotalItemBooks(List<OrderBooks> orderBooks) {
		BookRepository bookRepository = new BookRepository();
		ListIterator<OrderBooks> booksIt = orderBooks.listIterator();

		List<Book> books = new ArrayList<Book>();
		while (booksIt.hasNext()) {
			Book book = new Book();

			OrderBooks orderB = booksIt.next();
			book.setId(orderB.getId_book());

			books.add(book);
		}

		List<Book> itemsBooks = bookRepository.getItems(books);

		ListIterator<Book> itemsBooksIt = itemsBooks.listIterator();

		ListIterator<OrderBooks> orderBooksIt = orderBooks.listIterator();

		List<OrderBooks> orderBooksResponse = new ArrayList<OrderBooks>();
		while (orderBooksIt.hasNext()) {
			OrderBooks orderBook = orderBooksIt.next();

			while (itemsBooksIt.hasNext()) {
				Book book = itemsBooksIt.next();

				if (book.getId() == orderBook.getId_book()) {
					Double total = orderBook.getQuantity() * book.getPrice();

					orderBook.setPrice(total);

					orderBooksResponse.add(orderBook);
					break;
				}
			}
		}

		return orderBooksResponse;
	}
}