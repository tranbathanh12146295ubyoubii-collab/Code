package BookModel;



import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import ObserverUI.ObserverBook;

public class BookTitle {
	private String titleID;
	private double price;
	private String title;
	private String publisher;
	private String authors;
	private String category;
	private int numOfItem;
	
	
	/**
	 * This is a ...... 
	 * @param titleID
	 * @param title
	 * @param publisher
	 * @param authors
	 */
	public BookTitle(String title, double price, String author, String publisher, String categoey) {
		this.titleID = Long.toString(System.nanoTime());
		this.price = price;
		this.title = title;
		this.publisher = publisher;
		this.authors = author;
		this.category = categoey;
		this.numOfItem = 0;
	}
	public BookTitle(String id, String title, double price, String author, String publisher, String categoey) {
		this.titleID = id;
		this.price = price;
		this.title = title;
		this.publisher = publisher;
		this.authors = author;
		this.category = categoey;
		this.numOfItem = 0;
	}
	
	
	public int getNumOfItem() {
		return numOfItem;
	}


	public void setNumOfItem(int numOfItem) {
		this.numOfItem = numOfItem;
	}


	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getTitleID() {
		return titleID;
	}
	public void setTitleID(String titleID) {
		this.titleID = titleID;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getAuthors() {
		return authors;
	}
	public void setAuthors(String authors) {
		this.authors = authors;
	}
	
	public void discrea() {
		numOfItem--;
	}
	
	public void increa() {
		numOfItem++;
	}
	

	
	
	

	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	@Override
	public int hashCode() {
		return Objects.hash(title, titleID);
	}

	public void setAll(String titleName, double price, String authorName, String publisher, String category) {
		setTitle(titleName);
		setPrice(price);
		setAuthors(authorName);
		setCategory(category);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookTitle other = (BookTitle) obj;
		return Objects.equals(title, other.title) && Objects.equals(titleID, other.titleID);
	}
	
	
}
