package com.car.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;

@Entity
public class Car {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	 @NotBlank(message = "Name is mandatory")
	    private String name;
	    
	    @NotBlank(message = "Model is mandatory")
	    private String model;
	    
	    @Min(value = 1886, message = "Year must be later than 1885")
	    @Max(value = 2100, message = "Year must be realistic")
	    private int year;
	    
	    @Positive(message = "Price must be a positive value")
	    private double price;
	    
	    @NotBlank(message = "Color is mandatory")
	    private String color;
	    
	    @NotBlank(message = "Fuel type is mandatory")
	    private String fuelType;
	    
		public Car() {
			super();
			// TODO Auto-generated constructor stub
		}
		public Car(Long id, String name, String model, int year, double price, String color, String fuelType) {
			super();
			this.id = id;
			this.name = name;
			this.model = model;
			this.year = year;
			this.price = price;
			this.color = color;
			this.fuelType = fuelType;
		}
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getModel() {
			return model;
		}
		public void setModel(String model) {
			this.model = model;
		}
		public int getYear() {
			return year;
		}
		public void setYear(int year) {
			this.year = year;
		}
		public double getPrice() {
			return price;
		}
		public void setPrice(double price) {
			this.price = price;
		}
		public String getColor() {
			return color;
		}
		public void setColor(String color) {
			this.color = color;
		}
		public String getFuelType() {
			return fuelType;
		}
		public void setFuelType(String fuelType) {
			this.fuelType = fuelType;
		}
		@Override
		public String toString() {
			return "Car [id=" + id + ", name=" + name + ", model=" + model + ", year=" + year + ", price=" + price
					+ ", color=" + color + ", fuelType=" + fuelType + "]";
		}

	    
	    
}
