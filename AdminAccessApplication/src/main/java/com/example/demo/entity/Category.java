package com.example.demo.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

@Entity
public class Category {
	
	@Id
	@GeneratedValue
	private int id;
	
	@NotEmpty(message = "This feild is required")
	private String name;
	
	private String description;
	
	@OneToMany(mappedBy = "category", cascade = CascadeType.REMOVE)
	private Set<Product> products;
	
	public Category() {
			
	}

	public Category(int id, String name, String description) {
		super();
		this.name = name;
		this.description = description;
		this.id = id;
	}
	public  Category(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
		
	public Category(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
