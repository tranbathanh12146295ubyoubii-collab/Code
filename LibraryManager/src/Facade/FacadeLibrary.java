package Facade;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import BookModel.*;
import FineModel.*;
import LibrarianModel.*;
import LoanModel.*;
import ReaderModel.*;

public class FacadeLibrary {
	BookService bookService;
	ReaderService readerService;
	LoanService loanService;
	LibrarianService librarianService;

	public FacadeLibrary(BookService bookService, ReaderService readerService, LoanService loanService,
			LibrarianService librarianService) {
		this.bookService = bookService;
		this.readerService = readerService;
		this.loanService = loanService;
		this.librarianService = librarianService;
	}

	public BookService getBookService() {
		return bookService;
	}

	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}

	public ReaderService getReaderService() {
		return readerService;
	}

	public void setReaderService(ReaderService readerService) {
		this.readerService = readerService;
	}

	public LoanService getLoanService() {
		return loanService;
	}

	public void setLoanService(LoanService loanService) {
		this.loanService = loanService;
	}

	public LibrarianService getLibrarianService() {
		return librarianService;
	}

	public void setLibrarianService(LibrarianService librarianService) {
		this.librarianService = librarianService;
	}

	public void loadDataFromDisplay() {
		this.readerService.notifyUpdateReader(readerService.getReaderRepository().getReaders());
		this.bookService.notifyDisplayBookTitle(this.getAllBookTitle());
		this.bookService.notifyDisplayBookItem(this.getAllItem());
		this.getLoanService().updateDisplay(this.getAllLoan());
	}

	public List<BookTitle> getAllBookTitle() {
		return this.getBookService().getAllBookTitle();
	}

	public void setBookTitle(String id, String title, double price, String author, String publsher, String category) {
		this.getBookService().editBookTitle(id, title, price, author, publsher, category);
	}

	public boolean login(String id, String passCode) {
		if (librarianService.login(id, passCode)) {
			loadDataFromDisplay();
			return true;
		}
		return false;
	}

	// READER method
	public void refeshReader() {
		this.getReaderService().notifyUpdateReader(this.getAllReader());
	}

	public boolean addReader(Reader reader) {
		return getReaderService().addReader(reader);
	}

	public boolean removeReader(String id) {
		if (id == null || id.isEmpty())
			return false;
		Reader reader = this.getReaderService().findByID(id);
		if (reader == null)
			return false;
		for (Loan loan : this.getBorrowingLoan()) {
			if (loan.getNumberPhone().equals(reader.getNumberPhone())) {
				return false;
			}
		}
		return this.getReaderService().removeReader(reader);
	}

	public Reader createReader(String id, String name, String sex, LocalDate birth, String address, String cccd,
			String numberphone) {
		return this.getReaderService().createReader(id, name, sex, birth, address, cccd, numberphone);
	}

	public List<Reader> findByNameReader(String readerName) {
		return this.getReaderService().findByName(readerName);
	}

	public Reader findByCccdReader(String cccd) {
		return this.getReaderService().findByCCCD(cccd);
	}

	public Reader findByNumberPhoneReader(String num) {
		return this.getReaderService().findByNumberPhone(num);
	}

	public List<Reader> getAllReader() {
		return this.getReaderService().getReaderRepository().getReaders();
	}

	public boolean editReader(String id, String name, String sex, LocalDate birth, String cccd, String address,
			String numberPhone) {
		return this.getReaderService().editReader(id, name, sex, birth, cccd, address, numberPhone);
	}

	public void setCondition(List<String> cond, String id) {
		this.getLoanService().setConditionLoan(cond, id);
	}

	public boolean borrowBook(String sdt, List<String> id, LocalDate duedate) {
		return this.loanService.borrowBook(sdt, id, duedate);
	}

	public double totalFine(String id) {
		return this.getLoanService().totalFineAmount(id);
	}

	public boolean returnBook(String idLoan) {
		return this.loanService.returnBook(idLoan);
	}

	public void setBookSearch(String s) {
		this.getBookService().setBookSearch(s);
	}

	// Loan
	public List<Loan> getAllLoan() {
		return this.getLoanService().getAllLoan();
	}

	public List<Loan> getBorrowingLoan() {
		return this.getLoanService().getBorrowingLoan();
	}

	//
	public List<String> getListIdItemByLoan(Loan loan) {
		return this.getLoanService().getIdItemDetailByLoan(loan);
	}

	public Loan findLoanById(String id) {
		return this.getLoanService().findByIdLoan(id);
	}

	public boolean containLoan(String id) {
		return this.getLoanService().containLoan(id);
	}

	public List<Loan> getLoanByType(String type) {
		return this.getLoanService().getLoanByType(type);
	}

	public boolean removeLoanById(String idLoan) {
		return this.getLoanService().removeLoanByIḍ̣(idLoan);
	}

	// BOOK
	public List<BookTitle> findBookTitleByTitle(String title) {
		return this.getBookService().findByTitle(title);
	}

	public void setDisableItem(String idTitle) {
		this.getBookService().setItemDisable(idTitle);
	}

	public List<BookItem> getAllItem() {
		return this.getBookService().getAllItem();
	}

	public List<BookItem> findItemByTitle(String title) {
		return this.getBookService().findItemByTitle(title);
	}

	public BookTitle findBookTitleById(String id) {
		return this.getBookService().findBookTitleById(id);
	}

	public List<String> getListInforOfTitle(String id) {
		return this.getBookService().getListInforOfBookTitle(id);
	}

	public boolean removeBookTitle(String id) {
		return this.getBookService().removeBookTitle(id);
	}

	public boolean removeItem(String idItem) {
		return this.getBookService().removeBookItem(idItem);
	}

	public boolean addBookTitle(String id, String title, double price, String author, String publisher, String category,
			int number) {
		boolean add = this.getBookService().addBookTitle(id, title, price, author, publisher, category, number);
		return add;
	}

	public void notifiAllBookTitle(List<BookTitle> b) {
		this.getBookService().notifyDisplayBookTitle(b);
	}

	public void notifiAllBookItem(List<BookItem> b) {
		this.getBookService().notifyDisplayBookItem(b);
	}

	public void refeshBook() {
		this.getBookService().notifyDisplayBookItem(this.getAllItem());
		this.getBookService().notifyDisplayBookTitle(this.getAllBookTitle());
	}

	public List<Reader> findReaderBorrowing() {
		return this.getReaderService().findListReaderById(this.getLoanService().getIdReaderBorrowing());
	}

	public boolean addBookItem(String title, String author) {
		return this.getBookService().addBookItem(title, author);
	}
}
