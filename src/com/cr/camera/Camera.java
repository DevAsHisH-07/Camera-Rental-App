package com.cr.camera;

public class Camera {
	private String brand;
	private String model;
	private double rentPerDay;

	public Camera(String brand, String model, double rentPerDay) {

		this.brand = brand;
		this.model = model;
		this.rentPerDay = rentPerDay;
	}
	
	public String getBrand() {
		return brand;
	}


	public String getModel() {
		return model;
	}


	public double getRentPerDay() {
		return rentPerDay;
	}

	@Override
	public String toString() {
		return "Brand: " + brand + ", Model: " + model + ", Rent per Day: Rs." + rentPerDay;
	}
}



