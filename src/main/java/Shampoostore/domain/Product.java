package Shampoostore.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;


import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long productid;
	
	@NotBlank(message="Tuotteella täytyy olla nimi")
	private String productname;
	
	@Size(min=10, max=150, message="Kuvauksessa oltava vähintään 10 merkkiä")
	private String description;
	
	
	@NotNull(message="Tuotteella oltava hinta")
	@DecimalMin(value="0.1", message="Tuotteen myyntihinta oltava enemmän kuin 0")
	private double sellingprice;
	
	
	@DecimalMin(value="0.1", message="Tuotteen ostohinta oltava enemmän kuin 0")
	private double buyingprice;
	
	@NotNull(message="Tuotteella täytyy olla kategoria")
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="categoryid")
	private Category category;
	
	public Product() {
		super();
	}
	public Product(String productname,String description, double sellingprice, double buyingprice, Category category) {
		super();
		this.productname=productname;
		this.description=description;
		this.sellingprice=sellingprice;
		this.buyingprice=buyingprice;
		this.category=category;
	}
	public Long getProductid() {
		return productid;
	}
	public void setProductid(Long productid) {
		this.productid = productid;
	}
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getSellingprice() {
		return sellingprice;
	}
	public void setSellingprice(double sellingprice) {
		this.sellingprice = sellingprice;
	}
	public double getBuyingprice() {
		return buyingprice;
	}
	public void setBuyingprice(double buyingprice) {
		this.buyingprice = buyingprice;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	@Override
	public String toString() {
		return "Product [productid=" + productid + ", productname=" + productname + ", description=" + description
				+ ", sellingprice=" + sellingprice + ", buyingprice=" + buyingprice + ", category=" + category + "]";
	}
	
}

