package LoanModel;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import BookModel.BookItem;

/**
 * 
 */
public class LoanDetail {
	private BookItem bookItem;
	
	public LoanDetail(BookItem bookItem) {
		this.bookItem = bookItem;
	}

	public BookItem getBookItem() {
		return bookItem;
	}

	public void setBookItem(BookItem bookItem) {
		this.bookItem = bookItem;
	}
	
	
	// Tinh loi tưng chi tiet
	public double fineAmount() {
		return bookItem.getCondition().getFinePolicy().calculateFine(this);
	}
}
