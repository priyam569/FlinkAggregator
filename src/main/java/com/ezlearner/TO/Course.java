package com.ezlearner.TO;

public class Course {

	private int id;
	private String name;
	private String author;
	private String type;
	private int duration;
	private int price;
	private int rating;
	private String image;
	private String description;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getImage() {
		return image;
	}
	public Course() {}
	/**
	 * @param id
	 * @param name
	 * @param author
	 * @param type
	 * @param duration
	 * @param price
	 * @param rating
	 * @param image
	 * @param description
	 */
	public Course(int id, String name, String author, String type, int duration, int price, int rating,
			String image, String description) {
		super();
		this.id = id;
		this.name = name;
		this.author = author;
		this.type = type;
		this.duration = duration;
		this.price = price;
		this.rating = rating;
		this.image = image;
		this.description = description;
	}
	
	
	public void setImage(String image) {
		this.image = image;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}

