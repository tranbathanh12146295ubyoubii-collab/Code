package LoanModel;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import BookModel.Condition;

public class Loan {
	private String id;
	private String numberPhone;
	private String readerName;
	private List<LoanDetail> itemList;
	private LocalDate borrowDate;
	private LocalDate dueDate;
	private LocalDate returnDate;
	private LoanStatus status;
	private String idReader;
	private boolean isOverDue;
	
	
	


	public Loan(String numberPhone, LocalDate dueDate) {
		this.id = Long.toString(System.nanoTime());
		this.numberPhone = numberPhone;
		this.itemList = new ArrayList<>();
		this.borrowDate = LocalDate.now();
		this.dueDate = dueDate;
		this.returnDate = null;
		this.status = LoanStatus.BORROWING;
		this.isOverDue = false;
	}	
	
	
	public String getIdReader() {
		return idReader;
	}
	
	public void setCondition(List<String> condition) {
		if (condition == null || condition.isEmpty()) return;
		for (int i = 0; i < itemList.size(); i++) {
			itemList.get(i).getBookItem().setCondition(Condition.valueOf(condition.get(i)));
		}
	}
	


	public void setIdReader(String idReader) {
		this.idReader = idReader;
	}
	
	public void setListCondition(List<String> list) {
		if (list == null || list.isEmpty() || getItemList().isEmpty()) return;
		for (int i = 0; i < itemList.size(); i++) {
			itemList.get(i).getBookItem().setCondition(Condition.valueOf(list.get(i)));
		}
	}

	public String getId() {
		return id;
	}
	public boolean isOverDue() {
		return isOverDue;
	}
	public String getNumberPhone() {
		return numberPhone;
	}
	public void setNumberPhone(String numberPhone) {
		this.numberPhone = numberPhone;
	}
	public void setOverDue(boolean isOverDue) {
		this.isOverDue = isOverDue;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getReaderName() {
		return readerName;
	}
	public void setReaderName(String name) {
		this.readerName = name;
	}
	public List<LoanDetail> getItemList() {
		return itemList;
	}
	public void setItemList(List<LoanDetail> itemList) {
		this.itemList = itemList;
	}
	public LocalDate getBorrowDate() {
		return borrowDate;
	}
	public void setBorrowDate(LocalDate borrowDate) {
		this.borrowDate = borrowDate;
	}
	public LocalDate getDueDate() {
		return dueDate;
	}
	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}
	public LoanStatus getStatus() {
		return status;
	}
	public void setStatus(LoanStatus status) {
		this.status = status;
	}
	public LocalDate getReturnDate() {
		return returnDate;
	}
	public void List(ArrayList<LoanDetail> itemList) {
		this.itemList = itemList;
	}
	public void setReturnDate(LocalDate bookReturnDate) {
		this.returnDate = bookReturnDate;
	}	
	public void addDetail(LoanDetail detail) {
		this.itemList.add(detail);
	}
	
	public void returned() {
		status = LoanStatus.RETURNED;
	}
	public void setReturn() {
		for (LoanDetail detail : itemList) {
			detail.getBookItem().setReturned();
		}
	}
	/**
	 * 
	 */
	public void checkOverDue() {
		if (dueDate.isBefore(LocalDate.now())) {
			isOverDue = true;
		}
	}
	
	public double overDueFine() {
		long days = ChronoUnit.DAYS.between(dueDate, returnDate);
		if (days > 0) {
			return days * 5000;
		} return 0;
	}
	
	public double totalFine() {
		double tt = 0;
		for (LoanDetail loanDetail : itemList) {
			tt += loanDetail.fineAmount();
		}
		return tt + overDueFine();
	}


	@Override
	public String toString() {
		return "Loan [id=" + id + ", numberPhone=" + numberPhone + ", readerName=" + readerName + ", itemList="
				+ itemList + ", borrowDate=" + borrowDate + ", dueDate=" + dueDate + ", returnDate=" + returnDate
				+ ", status=" + status + ", idReader=" + idReader + ", isOverDue=" + isOverDue + "]";
	}
	
	
}
