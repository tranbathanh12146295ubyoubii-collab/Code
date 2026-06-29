package View;

import Controller.IController;
import java.awt.CardLayout;
import javax.swing.JPanel;

public class CardPanelService extends JPanel {
	HomePagePanel homePagePanel;
	BookPanel bookPanel;
	ReaderPanel readerPanel;
	LoanPanel loanPanel;
	

	
	IController controller;
	public CardPanelService(HomePagePanel homePagePanel, BookPanel bookPanel, ReaderPanel readerPanel, LoanPanel loanPanel) {
		this.homePagePanel = homePagePanel;
		this.bookPanel = bookPanel;
		this.readerPanel = readerPanel;
		this.loanPanel = loanPanel;
		setLayout(new CardLayout());
		init();
	}

	public void init() {
		this.add(homePagePanel, "Home");
		this.add(bookPanel, "Book");
		this.add(readerPanel, "Reader");
		this.add(loanPanel, "Loan");
	}
	
	
	public HomePagePanel getHomePagePanel() {
		return this.homePagePanel;
	}

	public void setHomePagePanel(HomePagePanel homePagePanel) {
		this.homePagePanel = homePagePanel;
	}
	
	public BookPanel getBookPanel() {
		return bookPanel;
	}

	public void setBookPanel(BookPanel bookPanel) {
		this.bookPanel = bookPanel;
	}

	public ReaderPanel getReaderPanel() {
		return readerPanel;
	}

	public void setReaderPanel(ReaderPanel readerPanel) {
		this.readerPanel = readerPanel;
	}

	public LoanPanel getLoanPanel() {
		return loanPanel;
	}

	public void setLoanPanel(LoanPanel loanPanel) {
		this.loanPanel = loanPanel;
	}
	/**
	 * 
	 */
	public void showHome() {
		((CardLayout) this.getLayout()).show(this, "Home");
	}
	
	public void showBook() {
		((CardLayout) this.getLayout()).show(this, "Book");
	}
	
	public void showReader() {
		((CardLayout) this.getLayout()).show(this, "Reader");
	}
	
	public void showLoan() {
		((CardLayout) this.getLayout()).show(this, "Loan");
	}
	
	
	

    
	// set controller
    public void setController(IController controller) {
        this.controller = controller;

		this.getReaderPanel().setController(controller);
		this.getBookPanel().setIController(controller);
		this.getLoanPanel().setIController(controller);
    }
}
