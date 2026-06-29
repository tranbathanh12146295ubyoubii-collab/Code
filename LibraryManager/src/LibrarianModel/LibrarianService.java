package LibrarianModel;

import ObserverUI.ObserverLogin;
import java.util.ArrayList;
import java.util.List;

public class LibrarianService {
	private LibrarianRepository librarianRepository;
	private LibrarianAccount curreAccount;

	List<ObserverLogin> observerLogins = new ArrayList<>();

	public LibrarianService(LibrarianRepository librarianRepository) {
		this.librarianRepository = librarianRepository;
	}

	public void registerObserver(ObserverLogin ol) {
		observerLogins.add(ol);
	}

	public void removeObserver(ObserverLogin ol) {
		observerLogins.remove(ol);
	}

	public void notifyLogin(String username) {
		observerLogins.stream().forEach(o -> o.login(username));
	}

	public boolean login(String id, String passCode) {
		LibrarianAccount account = librarianRepository.findByID(id);
		if (account == null)
			return false;

		if (account.getPassCode().equals(passCode)) {
			curreAccount = account;

			// goi Observer
			notifyLogin(curreAccount.getName());
			return true;
		}
		return false;
	}

	public boolean logout() {
		if (curreAccount == null)
			return false;
		curreAccount = null;
		notifyLogin("");
		return true;
	}

	public void print() {
		this.librarianRepository.print();
	}

	/**
	 * @return LibrarianRepository return the librarianRepository
	 */
	public LibrarianRepository getLibrarianRepository() {
		return librarianRepository;
	}

	/**
	 * @param librarianRepository the librarianRepository to set
	 */
	public void setLibrarianRepository(LibrarianRepository librarianRepository) {
		this.librarianRepository = librarianRepository;
	}

	/**
	 * @return LibrarianAccount return the curreAccount
	 */
	public LibrarianAccount getCurreAccount() {
		return curreAccount;
	}

	/**
	 * @param curreAccount the curreAccount to set
	 */
	public void setCurreAccount(LibrarianAccount curreAccount) {
		this.curreAccount = curreAccount;
	}

}
