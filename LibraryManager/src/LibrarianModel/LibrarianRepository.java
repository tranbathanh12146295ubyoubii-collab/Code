package LibrarianModel;

import java.util.ArrayList;
import java.util.List;

public class LibrarianRepository {
	public static LibrarianRepository instance;

	private List<LibrarianAccount> librarianAccounts;

	private LibrarianRepository() {
		this.librarianAccounts = new ArrayList<>();
	}

	public static LibrarianRepository getInstance() {
		if (instance == null) {
			instance = new LibrarianRepository();
		}
		return instance;
	}

	public LibrarianAccount findByID(String id) {
		for (LibrarianAccount librarianAccount : librarianAccounts) {
			if (librarianAccount.getId().equals(id)) {
				return librarianAccount;
			}
		}
		return null;
	}

	public void setLibrarianAccounts(List<LibrarianAccount> librarianAccounts) {
		this.librarianAccounts = librarianAccounts;
	}

	public void print() {
		librarianAccounts.stream().forEach(l -> System.out.println(l.toString()));
	}

	public List<LibrarianAccount> geLibrarianAccounts() {
		return librarianAccounts;
	}
}
