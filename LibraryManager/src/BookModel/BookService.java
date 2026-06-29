package BookModel;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Observer;
import java.util.stream.Collectors;

import javax.naming.directory.InvalidAttributeIdentifierException;

import BookTitleSearch.BookTitleSearch;
import BookTitleSearch.SearchByAuthor;
import BookTitleSearch.SearchByCategory;
import BookTitleSearch.SearchByName;
import ObserverUI.ObserverBook;

public class BookService {
	private BookTitleRepository bookTitleRepository;
	private BookItemRepository bookItemRepository;
	private BookTitleSearch searchStrategy;

	List<ObserverBook> observers = new ArrayList<>();

	public BookService(BookTitleRepository bookTitleRepository, BookItemRepository bookItemRepository) {
		this.bookTitleRepository = bookTitleRepository;
		this.bookItemRepository = bookItemRepository;
		searchStrategy = new SearchByName(getAllBookTitle());
	}

	// observer
	public void registerObserver(ObserverBook o) {
		observers.add(o);
	}

	public void removeObserver(ObserverBook o) {
		observers.remove(o);
	}

	public void notifyDisplayBookTitle(List<BookTitle> book) {
		observers.stream().forEach(o -> o.updateDisplayBookTitle(book));
	}

	public void notifyDisplayBookItem(List<BookItem> bookItems) {
		observers.stream().forEach(o -> o.updateDisplayBookItem(bookItems));
	}

	public List<BookTitle> getAllBookTitle() {
		return this.getBookTitleRepository().getBookTitles();
	}

	public List<BookItem> getAllItem() {
		return this.getBookItemRepository().getAllItem();
	}

	public List<BookTitle> findByTitle(String keyword) {
		if (keyword == null || keyword.trim().isEmpty()) {
			notifyDisplayBookTitle(new ArrayList<BookTitle>());
			return new ArrayList<>();
		}

		if (this.searchStrategy == null) {
			this.searchStrategy = new SearchByName(this.getAllBookTitle());
		}
		List<BookTitle> list = this.searchStrategy.search(keyword);
		notifyDisplayBookTitle(list);
		return list;
	}

	// get set
	public BookTitleRepository getBookTitleRepository() {
		return bookTitleRepository;
	}
	
	public void editBookTitle(String id, String title, double price, String authorName, String publisher, String category) {
		if (id == null || id.isEmpty()) return;
		this.findBookTitleById(id).setAll(title, price, authorName, publisher, category);
		for (BookItem bi : this.getAllItem()) {
			if (bi.getBookTitle().getTitleID().equals(id)) {
				bi.getBookTitle().setAll(title, price, authorName, publisher, category);
			}
		}
		this.notifyDisplayBookTitle(this.getAllBookTitle());
		this.notifyDisplayBookItem(this.getAllItem());
	}

	public void setBookTitleRepository(BookTitleRepository bookTitleRepository) {
		this.bookTitleRepository = bookTitleRepository;
	}

	public BookItemRepository getBookItemRepository() {
		return bookItemRepository;
	}

	public void setBookItemRepository(BookItemRepository bookItemRepository) {
		this.bookItemRepository = bookItemRepository;
	}

	public BookTitle findBookTitle(String title, String author) {
		return this.bookTitleRepository.findBook(title, author);
	}

	// lay cuon sach hop le (chua duoc muon)
	public BookItem findAvailableItem(BookTitle t) {
		for (BookItem b : this.bookItemRepository.getAllItem()) {
			if (b.isStatus() && b.getBookTitle().equals(t))
				return b;
		}
		return null;
	}

	// add title
	public boolean addBookTitle(String id, String title, double price, String author, String publisher, String category,
			int number) {
		BookTitle b;
		if (id == null || id.isEmpty()) {
			b = new BookTitle(title, price, author, publisher, category);
		} else {
			b = new BookTitle(id, title, price, author, publisher, category);
		}

		if (this.findBookTitle(title, author) == null) {
			this.bookTitleRepository.addBookTitle(b);
		}

		for (int i = 0; i < number; i++) {
			this.addBookItem(title, author);
		}

		this.notifyDisplayBookTitle(this.getAllBookTitle());
		return true;
	}

	public boolean removeBookTitle(String idBook) {
		if (idBook == null || idBook.isEmpty()) {
			return false;
		}
		BookTitle bookTitle = findBookTitleById(idBook);
		if (bookTitle == null) {
			return false;
		}
		boolean remove = this.getBookTitleRepository().removeBookTitle(idBook);
		if (remove) {
			for (BookItem bi : this.getAllItem()) {
				if (bi.getBookTitle().getAuthors().equals(bookTitle.getAuthors()) && bi.getBookTitle().getTitle().equals(bookTitle.getTitle())) {
					bi.setStatus(false);
				}
			}
			this.notifyDisplayBookTitle(this.getAllBookTitle());
			this.notifyDisplayBookItem(this.getAllItem());
		} return remove;
	}
	
	public void setItemDisable(String idTitle) {
		for (BookItem item : this.getBookItemRepository().getAllItem()) {
			if (item.getBookTitle().getTitleID().equals(idTitle)) {
				item.setStatus(false);
				System.out.println(item.isStatus());
			}
		}
	}
	
	public List<BookItem> findItemByIdTitle(String id) {
		List<BookItem> list = new ArrayList<>();
		if (id == null || id.isEmpty()) return list;
		for (BookItem item : this.getAllItem()) {
			if (item.getBookTitle().getTitleID().equals(id) && item != null) {
				list.add(item);
			}
		}
		return list;
	}

	public boolean addBookItem(String title, String author) {
		if (findBookTitle(title, author) == null)
			return false;
		BookTitle book = findBookTitle(title, author);

		if (this.bookItemRepository.addBookItem(new BookItem(book))) {
			book.setNumOfItem(book.getNumOfItem() + 1);
			this.notifyDisplayBookTitle(getAllBookTitle());
			this.notifyDisplayBookItem(getAllItem());
		}
		return true;
	}

	public boolean removeBookItem(String barcode) {
		if (barcode == null || barcode.isEmpty()) return false;
		BookItem item = findItemByIdItem(barcode);
		if (item == null || !item.isStatus()) return false;
		if (this.getBookItemRepository().removeBookItem(barcode)) {
			this.getBookTitleRepository().findBook(item.getBookTitle().getTitle(), item.getBookTitle().getAuthors()).discrea();
			this.notifyDisplayBookItem(getAllItem());
			this.notifyDisplayBookTitle(getAllBookTitle());
			return true;
		}
		return false;
	}

	public int getNumOfItem(String idTitle) {
		return this.bookItemRepository.getNumOfTitle(idTitle);
	}

	public BookItem findItemByIdItem(String idItem) {
		return this.getBookItemRepository().findByID(idItem);
	}

	// thong ke
	public List<BookItem> getBookItemNew() {
		return this.getBookItemRepository().getAllItem().stream().filter(i -> i.getCondition().equals(Condition.NEW))
				.collect(Collectors.toList());
	}

	public List<BookItem> getBookItemNewByTitle(String idTitle) {
		if (idTitle == null || idTitle.isEmpty())
			return new ArrayList<>();
		return this.getBookItemRepository().getAllItem().stream()
				.filter(i -> i.getBookTitle().getTitleID().equals(idTitle) && i.getCondition().equals(Condition.NEW))
				.collect(Collectors.toList());
	}

	public List<BookItem> getBookItemDamaged() {
		return this.getBookItemRepository().getAllItem().stream()
				.filter(i -> i.getCondition().equals(Condition.DAMAGED)).collect(Collectors.toList());
	}

	public List<BookItem> getBookItemDamagedByTitle(String idTitle) {
		if (idTitle == null || idTitle.isEmpty())
			return new ArrayList<>();
		return this.getBookItemRepository().getAllItem().stream().filter(
				i -> i.getBookTitle().getTitleID().equals(idTitle) && i.getCondition().equals(Condition.DAMAGED))
				.collect(Collectors.toList());
	}

	public List<BookItem> getBookItemLost() {
		return this.getBookItemRepository().getAllItem().stream().filter(i -> i.getCondition().equals(Condition.LOST))
				.collect(Collectors.toList());
	}

	public List<BookItem> getBookItemLostByTitle(String idTitle) {
		if (idTitle == null || idTitle.isEmpty())
			return new ArrayList<>();
		return this.getBookItemRepository().getAllItem().stream()
				.filter(i -> i.getBookTitle().getTitleID().equals(idTitle) && i.getCondition().equals(Condition.LOST))
				.collect(Collectors.toList());
	}

	public List<BookItem> findItemByTitle(String title) {
		List<BookItem> bookItems = new ArrayList<>();
		if (title == null || title.isEmpty()) {
			this.notifyDisplayBookItem(bookItems);
			return bookItems;
		}
		if (searchStrategy == null) searchStrategy = new SearchByName(this.getAllBookTitle());
		for (BookItem item : this.getAllItem()) {
			for (BookTitle bt : searchStrategy.search(title)) {
				if (item.getBookTitle().getTitle().equals(bt.getTitle())) {
					bookItems.add(item);
				}
			}
		}
		
		this.notifyDisplayBookItem(bookItems);
		return bookItems;
	}

	public BookTitle findBookTitleById(String id) {
		return this.getAllBookTitle().stream().filter(b ->b.getTitleID().equals(id)).findFirst().orElse(null);
	}
	
	public List<String> getListInforOfBookTitle(String id) {
		List<String> element = new ArrayList<>();
		if (id == null || id.isEmpty()) return element;
		BookTitle b = this.findBookTitleById(id);
		if (b == null) return element;
		element = Arrays.asList(b.getTitleID(), b.getTitle(), b.getPrice() + "", b.getAuthors(), b.getPublisher(), b.getCategory(), b.getNumOfItem() + "");
		return element;
	}

	public void setBookSearch(String s) {
		if (s.equals("Tên sách"))
			searchStrategy = new SearchByName(this.getAllBookTitle());
		else if (s.equals("Thể loại"))
			searchStrategy = new SearchByCategory(this.getAllBookTitle());
		else if (s.equals("Tác giả"))
			searchStrategy = new SearchByAuthor(this.getAllBookTitle());
		else
			searchStrategy = new SearchByName(getAllBookTitle());
	}

}
