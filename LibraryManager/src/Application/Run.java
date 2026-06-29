package Application;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import BookModel.BookItem;
import BookModel.BookItemRepository;
import BookModel.BookService;
import BookModel.BookTitle;
import BookModel.BookTitleRepository;
import Controller.ControllerProcessUI;
import Controller.ControllerService;
import Controller.IController;
import Controller.IControllerProcessUI;
import Facade.FacadeLibrary;
import LibrarianModel.LibrarianAccount;
import LibrarianModel.LibrarianRepository;
import LibrarianModel.LibrarianService;
import LoanModel.Loan;
import LoanModel.LoanDetail;
import LoanModel.LoanRepository;
import LoanModel.LoanService;
import ReaderModel.Reader;
import ReaderModel.ReaderRepository;
import ReaderModel.ReaderService;
import View.MasterView;

public class Run {
	public static void buildProgram() {
		ReaderRepository readerRepository = ReaderRepository.getInstance();
		readerRepository.setReaders((List<Reader>) dataTest().get(2));
		
		BookTitleRepository bookTitleRepository = BookTitleRepository.getInstance();
		bookTitleRepository.setBookTitles((List<BookTitle>) dataTest().get(0));
		
		BookItemRepository bookItemRepository = BookItemRepository.getInstance();
		bookItemRepository.setBookItems((List<BookItem>) dataTest().get(1));
		
		LoanRepository loanRepository = LoanRepository.getInstance();
		
		LibrarianRepository librarianRepository = LibrarianRepository.getInstance();
		librarianRepository.setLibrarianAccounts(dataLibrarian());
		
		


		// khoi tao model
		BookService bookService = new BookService(bookTitleRepository, bookItemRepository);
		ReaderService readerService = new ReaderService(readerRepository);
		LoanService loanService = new LoanService(loanRepository,readerService,bookService);
		LibrarianService librarianService = new LibrarianService(librarianRepository);

		// tao implement model
		FacadeLibrary facadeLibrary = new FacadeLibrary(bookService, readerService, loanService, librarianService);


		// tao controller (UI, service)
		IControllerProcessUI controllerProcessUI = new ControllerProcessUI();
		IController controller = new ControllerService(facadeLibrary);
		
		// tao view
		MasterView masterView = new MasterView(controllerProcessUI, controller);
		


		// Đăng ký Observer Login
		librarianService.registerObserver(masterView.getHomePagePanel());
		librarianService.registerObserver(masterView.getCardPanel().getMainPanel());
		
		
		
		
		// Đăng ký Observer Reader
		readerService.registerObserver(masterView.getReaderPanel());
		readerService.registerObserver(masterView.getHomePagePanel());
		
		
		// Đăng ký Observer Book
		bookService.registerObserver(masterView.getBookPanel());
		bookService.registerObserver(masterView.getHomePagePanel());

		
		// Đăng ký Observer Loan
		loanService.registerObserver(masterView.getHomePagePanel());
		loanService.registerObserver(masterView.getLoanPanel());


		
		// rap Controller va view
		controllerProcessUI.setCardPanel(masterView.getCardPanel());
		controllerProcessUI.setCardPanelService(masterView.getCardPanelService());	
		controller.setMasterView(masterView);
		
		
	} 
	private static List<LibrarianAccount> dataLibrarian() {
		List<LibrarianAccount> accounts = new ArrayList<>();
		accounts.add(new LibrarianAccount("TT001", "Trần Bá Thành", "1"));
		accounts.add(new LibrarianAccount("TT002", "Nguyễn Hoàng Song Thiên", "2"));
		accounts.add(new LibrarianAccount("TT003", "Nguyễn Thanh Trung", "3"));
		accounts.add(new LibrarianAccount("TT004", "Lê Đăng Tri", "4"));
		accounts.add(new LibrarianAccount("TT005", "Phạm Minh Thiện", "5"));
		return accounts;
	}
	
	private static List<Object> dataTest() {
		List<BookTitle> bookTitles = new ArrayList<>();
		List<BookItem> bookItems = new ArrayList<>();
		
		
		bookTitles.add(new BookTitle("Truyện Kiều", 85000, "Nguyễn Du", "Literature", "Văn Học"));
		bookTitles.add(new BookTitle("Lão Hạc", 65000, "Nam Cao", "Literature", "Văn Học"));
		bookTitles.add(new BookTitle("Tắt Đèn", 70000, "Ngô Tất Tố", "Literature", "Văn Học"));
		bookTitles.add(new BookTitle("Dế Mèn Phiêu Lưu Ký", 90000, "Tô Hoài", "Children", "Thiếu Nhi"));
		bookTitles.add(new BookTitle("Không Gia Đình", 110000, "Hector Malot", "Children", "Thiếu Nhi"));

		bookTitles.add(new BookTitle("Harry Potter and the Philosopher's Stone", 180000, "J.K. Rowling", "Fantasy", "Tiểu Thuyết"));
		bookTitles.add(new BookTitle("Harry Potter and the Chamber of Secrets", 185000, "J.K. Rowling", "Fantasy", "Tiểu Thuyết"));
		bookTitles.add(new BookTitle("The Hobbit", 170000, "J.R.R. Tolkien", "Fantasy", "Tiểu Thuyết"));
		bookTitles.add(new BookTitle("The Lord of the Rings", 350000, "J.R.R. Tolkien", "Fantasy", "Tiểu Thuyết"));
		bookTitles.add(new BookTitle("A Game of Thrones", 280000, "George R.R. Martin", "Fantasy", "Tiểu Thuyết"));

		bookTitles.add(new BookTitle("Sherlock Holmes", 150000, "Arthur Conan Doyle", "Mystery", "Trinh Thám"));
		bookTitles.add(new BookTitle("Murder on the Orient Express", 160000, "Agatha Christie", "Mystery", "Trinh Thám"));
		bookTitles.add(new BookTitle("The Da Vinci Code", 190000, "Dan Brown", "Mystery", "Trinh Thám"));
		bookTitles.add(new BookTitle("Inferno", 200000, "Dan Brown", "Mystery", "Trinh Thám"));
		bookTitles.add(new BookTitle("Angels and Demons", 195000, "Dan Brown", "Mystery", "Trinh Thám"));

		bookTitles.add(new BookTitle("Đắc Nhân Tâm", 120000, "Dale Carnegie", "Self Help", "Kỹ Năng"));
		bookTitles.add(new BookTitle("Atomic Habits", 220000, "James Clear", "Self Help", "Kỹ Năng"));
		bookTitles.add(new BookTitle("Think and Grow Rich", 180000, "Napoleon Hill", "Self Help", "Kỹ Năng"));
		bookTitles.add(new BookTitle("The 7 Habits of Highly Effective People", 250000, "Stephen Covey", "Self Help", "Kỹ Năng"));
		bookTitles.add(new BookTitle("Deep Work", 210000, "Cal Newport", "Self Help", "Kỹ Năng"));

		bookTitles.add(new BookTitle("Clean Code", 350000, "Robert C. Martin", "Technology", "CNTT"));
		bookTitles.add(new BookTitle("Clean Architecture", 360000, "Robert C. Martin", "Technology", "CNTT"));
		bookTitles.add(new BookTitle("Effective Java", 420000, "Joshua Bloch", "Technology", "CNTT"));
		bookTitles.add(new BookTitle("Head First Java", 320000, "Kathy Sierra", "Technology", "CNTT"));
		bookTitles.add(new BookTitle("Java Concurrency in Practice", 450000, "Brian Goetz", "Technology", "CNTT"));

		bookTitles.add(new BookTitle("Spring in Action", 480000, "Craig Walls", "Technology", "CNTT"));
		bookTitles.add(new BookTitle("Learning SQL", 300000, "Alan Beaulieu", "Technology", "CNTT"));
		bookTitles.add(new BookTitle("Database System Concepts", 520000, "Abraham Silberschatz", "Technology", "CNTT"));
		bookTitles.add(new BookTitle("Computer Networks", 550000, "Andrew Tanenbaum", "Technology", "CNTT"));
		bookTitles.add(new BookTitle("Operating System Concepts", 580000, "Abraham Silberschatz", "Technology", "CNTT"));
		
		
		for (BookTitle bt : bookTitles) {
			bt.setNumOfItem(5);
			for (int i = 0; i < 5; i++) {
				bookItems.add(new BookItem(bt));
			}
		}
		
		List<ReaderModel.Reader> readers = new ArrayList<>();
		readers.add(new ReaderModel.Reader("DG001", "Trần Bá Thành", "Nam", LocalDate.of(2006, 3, 14), "TP HCM", "12345", "0900000000"));
		readers.add(new ReaderModel.Reader("DG002", "Nguyễn Hoàng Song Thiên", "Nam", LocalDate.of(2006, 5, 20), "Hà Nội", "12346", "0900000001"));
		readers.add(new ReaderModel.Reader("DG003", "Lê Đăng Trí", "Nam", LocalDate.of(2002, 8, 15), "Đà Nẵng", "12347", "0900000002"));
		readers.add(new ReaderModel.Reader("DG004", "Nguyễn Thanh Trung", "Nam", LocalDate.of(2001, 11, 10), "Cần Thơ", "12348", "0900000003"));
		readers.add(new ReaderModel.Reader("DG005", "Phạm Minh Thiện", "Nam", LocalDate.of(2004, 1, 25), "Huế", "12349", "0900000004"));
		readers.add(new ReaderModel.Reader("DG006", "Võ Minh Khang", "Nam", LocalDate.of(2000, 7, 18), "Bình Dương", "12350", "0900000005"));
		readers.add(new ReaderModel.Reader("DG007", "Đặng Thị Hương", "Nữ", LocalDate.of(2005, 4, 12), "Vũng Tàu", "12351", "0900000006"));
		readers.add(new ReaderModel.Reader("DG008", "Ngô Hoàng Nam", "Nam", LocalDate.of(1999, 9, 30), "Long An", "12352", "0900000007"));
		readers.add(new ReaderModel.Reader("DG009", "Bùi Thanh Tâm", "Nữ", LocalDate.of(2003, 2, 5), "Tiền Giang", "12353", "0900000008"));
		readers.add(new ReaderModel.Reader("DG010", "Phan Gia Huy", "Nam", LocalDate.of(2001, 12, 22), "An Giang", "12354", "0900000009"));
		readers.add(new ReaderModel.Reader("DG011", "Lý Mỹ Linh", "Nữ", LocalDate.of(2004, 6, 9), "Kiên Giang", "12355", "0900000010"));
		readers.add(new ReaderModel.Reader("DG012", "Huỳnh Nhật Minh", "Nam", LocalDate.of(2002, 10, 14), "Bến Tre", "12356", "0900000011"));
		readers.add(new ReaderModel.Reader("DG013", "Dương Thu Trang", "Nữ", LocalDate.of(2005, 3, 28), "Đồng Nai", "12357", "0900000012"));
		readers.add(new ReaderModel.Reader("DG014", "Đỗ Anh Tuấn", "Nam", LocalDate.of(2000, 8, 17), "Nha Trang", "12358", "0900000013"));
		readers.add(new ReaderModel.Reader("DG015", "Trịnh Kim Ngân", "Nữ", LocalDate.of(2003, 11, 3), "Đắk Lắk", "12359", "0900000014"));
		readers.add(new ReaderModel.Reader("DG016", "Mai Quốc Việt", "Nam", LocalDate.of(2001, 1, 19), "Quảng Nam", "12360", "0900000015"));
		readers.add(new ReaderModel.Reader("DG017", "Nguyễn Thảo Vy", "Nữ", LocalDate.of(2004, 9, 7), "Quảng Ngãi", "12361", "0900000016"));
		readers.add(new ReaderModel.Reader("DG018", "Lâm Thành Đạt", "Nam", LocalDate.of(2002, 4, 26), "Sóc Trăng", "12362", "0900000017"));
		readers.add(new ReaderModel.Reader("DG019", "Phạm Bảo Châu", "Nữ", LocalDate.of(2005, 7, 13), "Cà Mau", "12363", "0900000018"));
		readers.add(new ReaderModel.Reader("DG020", "Vũ Đức Long", "Nam", LocalDate.of(2000, 12, 1), "Hải Phòng", "12364", "0900000019"));	
		
		
		
		
		
		List<Loan> loans = new ArrayList<>();
		Loan loan1 = new Loan("0900000000", LocalDate.of(2026, 6, 18));
		LoanDetail loanDetail1 = new LoanDetail(bookItems.get(0));
		LoanDetail loanDetail2 = new LoanDetail(bookItems.get(1));
		LoanDetail loanDetail3 = new LoanDetail(bookItems.get(2));
		List<LoanDetail> loanDetails1 = Arrays.asList(loanDetail1, loanDetail2, loanDetail3);
		
		Loan loan2 = new Loan("0900000001", LocalDate.of(2026, 6, 21));
		LoanDetail loanDetail4 = new LoanDetail(bookItems.get(3));
		LoanDetail loanDetail5 = new LoanDetail(bookItems.get(4));
		LoanDetail loanDetail6 = new LoanDetail(bookItems.get(5));
		List<LoanDetail> loanDetails2 = Arrays.asList(loanDetail4, loanDetail5, loanDetail6);
		
		Loan loan3 = new Loan("0900000002", LocalDate.of(2026, 6, 28));
		LoanDetail loanDetail7 = new LoanDetail(bookItems.get(6));
		LoanDetail loanDetail8 = new LoanDetail(bookItems.get(7));
		LoanDetail loanDetail9 = new LoanDetail(bookItems.get(8));
		List<LoanDetail> loanDetails3 = Arrays.asList(loanDetail7, loanDetail8, loanDetail9);
		
		
		
		loans.add(loan1);
		loans.add(loan2);
		loans.add(loan3);
		
		
		
		
		
		
		List<Object> list = Arrays.asList(bookTitles, bookItems, readers);
		return list;
	}
	
	
	public static void main(String[] args) {
		buildProgram();
	}
}
