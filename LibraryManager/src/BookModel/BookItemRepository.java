package BookModel;

import java.util.ArrayList;
import java.util.List;

public class BookItemRepository {
	private static BookItemRepository instance;
	private List<BookItem> bookItems;

	private BookItemRepository() {
		bookItems = new ArrayList<>();
	}

	public static BookItemRepository getInstance() {
		if (instance == null) {
			instance = new BookItemRepository();
		}
		return instance;
	}

	public List<BookItem> getBookItems() {
		return bookItems;
	}

	public void setBookItems(List<BookItem> bookItems) {
		this.bookItems = bookItems;
	}

	// them xoa
	public boolean addBookItem(BookItem item) {
		if (bookItems.contains(item)) {
			return false;
		}
		return bookItems.add(item);
	}

	public boolean removeBookItem(String id) {
		return bookItems.remove(findByID(id));
	}

	// search
	public BookItem findByID(String id) {
		for (BookItem bookItem : bookItems) {
			if (bookItem.getId().equals(id)) {
				return bookItem;
			}
		}
		return null;
	}

	public List<BookItem> getAllItem() {
		return bookItems;
	}

	// lay so luong cuon sach cua 1 dau sach
	public int getNumOfTitle(String idTitle) {
		int count = 0;
		for (BookItem b : this.bookItems) {
			if (b.getBookTitle().getTitleID().equals(idTitle))
				count++;
		}
		return count;
	}


}
