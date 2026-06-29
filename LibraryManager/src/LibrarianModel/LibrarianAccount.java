package LibrarianModel;

public class LibrarianAccount {
	private String id;
	private String name;
	private String passCode;

	public LibrarianAccount(String id, String name, String passCode) {
		this.id = id;
		this.name = name;
		this.passCode = passCode;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassCode() {
		return passCode;
	}

	public void setPassCode(String passCode) {
		this.passCode = passCode;
	}

	@Override
	public String toString() {
		return "ID: " + id + " | Name: " + name;
	}

}
