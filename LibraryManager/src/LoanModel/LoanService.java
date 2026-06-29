package LoanModel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Observer;
import java.util.Set;

import BookModel.BookItem;
import BookModel.BookService;
import BookModel.Condition;
import LoanModel.*;
import ObserverUI.ObserverLoan;
import ReaderModel.Reader;
import ReaderModel.ReaderService;

public class LoanService {
	private LoanRepository loanRepository;
	private ReaderService readerService;
	private BookService bookService;
	
	
	List<ObserverLoan> observerLoans = new ArrayList<>();
	
	public LoanService(LoanRepository loanRepository,ReaderService readerService,BookService bookService) {
		this.loanRepository = loanRepository;
		this.readerService = readerService;
		this.bookService = bookService;
	}
	public void registerObserver(ObserverLoan o) {
		observerLoans.add(o);
	}
	
	public void removeObserver(ObserverLoan o) {
		observerLoans.remove(o);
	}
	
	public void updateDisplay(List<Loan> list) {
		observerLoans.stream().forEach(o -> o.updateDisplayLoan(list));
	}
	public LoanRepository getLoanRepository() {
		return loanRepository;
	}
	public void setLoanRepository(LoanRepository loanRepository) {
		this.loanRepository = loanRepository;
	}
	
	public boolean addLoan(Loan loan) {
		if (this.loanRepository.addLoan(loan)) {
			updateDisplay(this.getLoanRepository().getLoans());
			return true;
		} return false;
	}
	
	
	
	public List<Loan> getAllLoan() {
		return this.getLoanRepository().getLoans();
	}
	
	public List<Loan> getBorrowingLoan() {
		List<Loan> list = new ArrayList<>();
		for (Loan loan : this.getAllLoan()) {
			if (loan.getStatus().equals(LoanStatus.BORROWING)) {
				list.add(loan);
			}
		}
		updateDisplay(list);
		return list;
	}
	
	public List<Loan> getReturnesLoan() {
		List<Loan> list = new ArrayList<>(); 
		for (Loan loan : this.getAllLoan()) {
			if (loan.getStatus().equals(LoanStatus.RETURNED)) {
				list.add(loan);
			}
		} 
		updateDisplay(list);
		return list;
	}
	
	public List<Loan> getLoanOverDueBorowwing() {
		List<Loan> list = new ArrayList<>();
		for (Loan loan : this.getBorrowingLoan()) {
			if (loan.getReturnDate() == null && loan.getDueDate().isBefore(LocalDate.now())) {
				list.add(loan);
			}
		}
		updateDisplay(list);
		return list;
	}
	
	public List<Loan> getLoanOverDueReturned() {
		List<Loan> list = new ArrayList<>();
		for (Loan loan : this.getReturnesLoan()) {
			if (loan.getDueDate().isBefore(loan.getReturnDate())) {
				list.add(loan);
			}
		} 
		updateDisplay(list);
		return list;
	}
	public void setConditionLoan(List<String> condition, String id) {
		Loan loan = findByIdLoan(id);
		if (loan == null) return;
		loan.setCondition(condition);
	}
	
	
	public List<String> getIdReaderBorrowing() {
		List<String> re = new ArrayList<>();
		for (Loan loan : this.getBorrowingLoan()) {
			re.add(loan.getNumberPhone());
		}
		return re;
	}
	
	public Loan findByIdLoan(String id) {
		return this.getLoanRepository().findById(id);
	}
	
	public List<String> getIdItemDetailByLoan(Loan loan) {
		List<String> id = new ArrayList<>();
		if (loan == null || loan.getItemList() == null || loan.getItemList().isEmpty()) return id;
		for (LoanDetail dt : loan.getItemList()) {
			if (dt.getBookItem().getId() != null) {
				id.add(dt.getBookItem().getId());
			}
		}
		return id;
	}
	
	public boolean removeLoan(Loan loan) {
		return this.getLoanRepository().removeLoan(loan);
	}
	public boolean removeLoanByIḍ̣(String idLoan) {
		Loan loan = this.findByIdLoan(idLoan);
		if (loan == null) return false;
		if (loan.getStatus().equals(LoanStatus.RETURNED)) {
			this.updateDisplay(this.getAllLoan());
			return this.removeLoan(loan);
		} 
		this.updateDisplay(this.getAllLoan());
		return false;
	}
	
	public boolean containLoan(String id) {
		return this.getAllLoan().contains(findByIdLoan(id));
	}
	
	public double totalFineAmount(String id) {
		Loan loan = findByIdLoan(id);
		if (loan == null || loan.getItemList() == null || loan.getItemList().isEmpty()) return 0;
		return loan.totalFine();
	}
	
	
	public double totalFineLoan(Loan loan) {
		return loan.totalFine();
	}
	
	public List<Loan> getAllOverDueLoan() {
		List<Loan> list = new ArrayList<>();
		if (this.getAllLoan() == null || this.getAllLoan().isEmpty()) {
			this.updateDisplay(list);
			return list;
		}
		for (Loan loan : this.getAllLoan()) {
			if (loan != null && loan.isOverDue()) {
				list.add(loan);
			}
		} 
		this.updateDisplay(list);
		return list;
	}
	
	
	public List<Loan> getLoanByType(String type) {
		if (type.equals("Đang mượn")) return this.getBorrowingLoan();
		else if (type.equals("Đã trả")) return this.getReturnesLoan();
		else if (type.equals("Quá hạn")) return this.getAllOverDueLoan();
		else return this.getAllLoan();
	}
	public boolean borrowBook(String sdt, List<String> id, LocalDate duedate) {
		if (sdt == null || sdt.isEmpty() || id == null || id.isEmpty() || duedate == null) return false;
    	Set<String> idItems = new HashSet<String>(id);
    	
    	Reader reader = readerService.findByNumberPhone(sdt);
    	
    	if (reader == null || !reader.isAvailable()) return false;
    	if (duedate.isBefore(LocalDate.now())) return false;
    	
    	String numberPhone = reader.getNumberPhone();
    	Loan loan = new Loan(numberPhone, duedate);
    	loan.setReaderName(reader.getName());
    	loan.setIdReader(reader.getId());
    	
    	for (String i : idItems) {
    		BookItem bi = bookService.findItemByIdItem(i);
    		if (bi != null && bi.getCondition().equals(Condition.NEW) && bi.isStatus()) {
    			bi.setBorrowed();
    			loan.addDetail(new LoanDetail(bi));
    			
    		}
    	}	
    	
    	if (loan.getItemList().isEmpty()) return false;
    	
    	boolean isAddSuccess = addLoan(loan);
    	
    	if (isAddSuccess) {
    		reader.setAvailable(false);
    		for (LoanDetail detail : loan.getItemList()) {
				detail.getBookItem().setBorrowed();
			}
    		this.bookService.notifyDisplayBookTitle(bookService.getAllBookTitle());
    		this.bookService.notifyDisplayBookItem(bookService.getAllItem());
        	updateDisplay(this.getAllLoan());
    	}
    	return isAddSuccess;
    	
    }
	
	
	
	public boolean returnBook(String idLoan) {
		if (idLoan == null || idLoan.isBlank())
			return false;

		Loan loan = findByIdLoan(idLoan);

		if (loan == null || !getBorrowingLoan().contains(loan)) {
			return false;
		}
		
		loan.returned();
		loan.setReturnDate(LocalDate.now());
		loan.checkOverDue();
		loan.setReturn();
		
		

		Reader reader = readerService.findByNumberPhone(loan.getNumberPhone());

		if (reader != null) {
			reader.setAvailable(true);
			loan.getItemList().forEach(detail -> detail.getBookItem().setReturned());
			this.bookService.notifyDisplayBookTitle(bookService.getAllBookTitle());
			this.bookService.notifyDisplayBookItem(bookService.getAllItem());
			this.readerService.notifyUpdateReader(readerService.getAllReader());
			updateDisplay(this.getAllLoan());
			return true;
		}
		return false;
	}
}
