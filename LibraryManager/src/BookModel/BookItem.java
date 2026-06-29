package BookModel;

public class BookItem {
	private String id;
	private BookTitle title;
	private boolean status;
	private Condition condition;
	/**
	 * 
	 * @param id
	 * @param title
	 * @param bookShelf
	 * @param status : true = available , false = unavailable
	 */
	public BookItem(BookTitle title) {
		this.id = Long.toString(System.nanoTime());
		this.title = title;
		this.status = true;
		this.condition = Condition.NEW;
	}
	
	

	public Condition getCondition() {
		return condition;
	}
	

	public void setCondition(Condition condition) {
		this.condition = condition;
	}



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public BookTitle getBookTitle() {
		return title;
	}

	public void setTitle(BookTitle title) {
		this.title = title;
	}

	

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public void setBorrowed() {
		status = false;
	}
	
	public void setReturned() {
		status = true;
	}

	@Override
	public String toString() {
		return "BookItem: " + "id: " + id + " - " + " Title: " + title.getTitle() + " - " + "Status: " + status + " - " + "Condition: " + condition;
	}

	

}

