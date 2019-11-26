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
public interface BaseBook extends Serializable {	

	static final long serialVersionUID = 1L;

	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	public static final long id = 0;

	 public static final String name = "";
	 public static final String publisher = "";
	 public static final String category = "";
	 public static final Integer stock = null;	
	 public static final Double price = null;
	 public static final String downloadLink = null;
	
	public long getId();

	public void setId(long id);

	public String getName();

	public void setName(String name);

	public String getPublisher();

	public void setPublisher(String pusblisher);

	public String getCategory();

	public void setCategory(String category);

	public Integer getStock();

	public void setStock(Integer stock);

	public Double getPrice();

	public void setPrice(Double price);
	
	public String getDownladLink();
	
	public void setDownloadLink(String downloadLink);
	
}
