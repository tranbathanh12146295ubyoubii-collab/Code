 	package ObserverUI;

import java.util.List;

import BookModel.BookItem;
import BookModel.BookTitle;

public interface ObserverBook {
	public void updateDisplayBookTitle(List<BookTitle> bookTitles);
	public void updateDisplayBookItem(List<BookItem> bookItems);
}
