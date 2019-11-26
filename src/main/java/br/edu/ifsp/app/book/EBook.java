package br.edu.ifsp.app.book;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name="book")
public class EBook implements BaseBook {	

	private static final long serialVersionUID = 1L;

	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	private long id;
	private String name;
	private String publisher;
	private String category;
	private Integer stock;	
	private Double price;
	private String downloadLink;
	
	public EBook() {}

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

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String pusblisher) {
		this.publisher = pusblisher;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public String getDownladLink() {
		
		return downloadLink;
	}

	@Override
	public void setDownloadLink(String downloadLink) {
		this.downloadLink = downloadLink;
		
	}
	
	
}
