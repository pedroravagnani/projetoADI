package br.edu.ifsp.app.user;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.annotations.SerializedName;

import br.edu.ifsp.app.book.Book;
import br.edu.ifsp.app.orderBooks.OrderBooks;

@XmlRootElement
@Entity
@Table(name="user")
public class User implements Serializable {	

	private static final long serialVersionUID = 1L;
	
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	@SerializedName("user")
	private long id;
	private String name;
	private String username;
	private String email;
	private String password;
	private List<OrderBooks> orderBooks;

	public User() {}

	public List<OrderBooks> getOrderBooks() {
		return orderBooks;
	}

	public void setOrderBooks(List<OrderBooks> orderBooks) {
		this.orderBooks = orderBooks;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
