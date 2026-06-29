package BookModel;

import java.util.ArrayList;
import java.util.List;

public class BookTitleRepository {
	private static BookTitleRepository instance;
	private List<BookTitle> bookTitles;
	/**
	 * 
	 */
	private BookTitleRepository() {
		bookTitles = new ArrayList<BookTitle>();
	}
	
	public static BookTitleRepository getInstance() {
		if (instance == null) {
			instance = new BookTitleRepository();
		} return instance;
	}
	
	

	public List<BookTitle> getBookTitles() {
		return bookTitles;
	}

	public void setBookTitles(List<BookTitle> bookTitles) {
		this.bookTitles = bookTitles;
	}
	
	public BookTitle findBook(String title,String author) {
		for(BookTitle b : this.bookTitles) {
			if (b.getTitle().equals(title) && b.getAuthors().equals(author))
				return b;
		}
		return null;
	}
	
	public boolean addBookTitle(BookTitle book) {
		for(BookTitle b : this.bookTitles) {
			if (b.equals(book))
				return false;
		}
		return this.bookTitles.add(book);
	}
	
	public boolean removeBookTitle(String idBook) {
		for(BookTitle b : this.bookTitles) {
			if(b.getTitleID().equals(idBook)) {
				return this.bookTitles.remove(b);
			}				
		}
		return false;
		
	}
	
	
	
}

