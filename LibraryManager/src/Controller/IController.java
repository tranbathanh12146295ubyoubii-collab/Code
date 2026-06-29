package Controller;

import View.*;

public interface IController {
	public void setMasterView(MasterView masterView);

	public void displayTime();

	public void logout();

	public void login();

	// Reader
	public void addReader();

	public void removeReader();

	public void refeshReader();

	void checkBorrowing();

	void editReader();

	void exitEditReader();

	// search
	void searchReader();

	// Loan
	void getLoanByType();

	void removeLoan();

	// Book
	void transitionViewModeBook();

	void addBookTitle();

	void removeTitle();

	void searchBook();

	void refeshBook();

	void removeItem();

	void displayBookTitleOnTextField(String idTitle);

	void enbleAddBookTitle();

	void disaleAddBookTitle();

	void enbleEditBookTitle();

	void disableEditBookTitle();

	// Service
	void borrowBook();

	void returnBook();

	void findReturnLoan();

	void calFine();

}
