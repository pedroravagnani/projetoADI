package br.edu.ifsp.app.orderBooks;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.annotations.SerializedName;

@XmlRootElement
@Entity
@Table(name="orders_books")
public class OrderBooks implements Serializable {	

	private static final long serialVersionUID = 1L;

	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	@SerializedName("orderBooks")
	private long id;
	private long id_order;
	private long id_book;
	private Integer quantity;
	private Double price;
	
	public OrderBooks() {}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getId_order() {
		return id_order;
	}

	public void setId_order(long id_order) {
		this.id_order = id_order;
	}

	public long getId_book() {
		return id_book;
	}

	public void setId_book(long id_book) {
		this.id_book = id_book;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	
	
}
