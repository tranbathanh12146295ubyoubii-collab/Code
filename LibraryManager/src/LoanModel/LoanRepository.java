package LoanModel;


import java.util.ArrayList;

public class LoanRepository {
	private ArrayList<Loan> loans;
	public static LoanRepository instance;
	/**
	 * 
	 */
	private LoanRepository() {
		this.loans = new ArrayList<Loan>();
	}
	public static LoanRepository getInstance() {
		if (instance == null) {
			instance = new LoanRepository();
		} return instance;
	}
	public ArrayList<Loan> getLoans() {
		return loans;
	}
	public void setLoans(ArrayList<Loan> loans) {
		this.loans = loans;
	}
	
	public boolean addLoan(Loan loan) {
		if (!loans.contains(loan)) {
			loans.add(loan);
			return true;
		}return false;
	}
	
	public boolean removeLoan(Loan loan) {
		return this.loans.remove(loan);
	}
	
	public void removeById(String id) {
		for(Loan l : loans) {
			if (l.getId().equals(id))
				loans.remove(l);
		}
	}
	
	
	// tim boi id
	public Loan findById(String id) {
		for(Loan l : loans) {
			if (l.getId().equals(id))
				return l;
		}
		return null;
	}
	

	
	
	
	
}
