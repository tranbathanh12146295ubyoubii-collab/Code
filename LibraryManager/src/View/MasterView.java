package View;

import Controller.IController;
import Controller.IControllerProcessUI;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class MasterView {
	MainFrame frame;
	CardPanel cardPanel;
	CardPanelService cardPanelService;
	
	IController controller;
	IControllerProcessUI controllerProcessUI;
	

	public MasterView(IControllerProcessUI controllerProcessUI, IController controller) {
		this.controllerProcessUI = controllerProcessUI;
		this.controller = controller;
		
		HomePagePanel homePagePanel = new HomePagePanel();
		BookPanel bookPanel = new BookPanel();
		ReaderPanel readerPanel = new ReaderPanel();
		LoanPanel loanPanel = new LoanPanel();
		
		this.cardPanelService = new CardPanelService(homePagePanel, bookPanel, readerPanel, loanPanel);
		cardPanelService.setController(controller);

		LoginPanel loginPanel = new LoginPanel(controllerProcessUI);
		loginPanel.setController(controller);
		MainPanel mainPanel = new MainPanel(controllerProcessUI, this.cardPanelService);	
		mainPanel.setController(controller);
		this.cardPanel = new CardPanel(loginPanel, mainPanel);
		this.frame = new MainFrame(this.cardPanel);
	}
	
	
	public void setController(IController controller) {
		this.controller = controller;
	}
	// TODO
	public void setControllerUI(IControllerProcessUI controllerProcessUI) {
		this.controllerProcessUI = controllerProcessUI;
	}
	


	public CardPanel getCardPanel() {
		return cardPanel;
	}
	public void setCardPanel(CardPanel cardPanel) {
		this.cardPanel = cardPanel;
	}
	public CardPanelService getCardPanelService() {
		return cardPanelService;
	}
	public void setCardPanelService(CardPanelService cardPanelService) {
		this.cardPanelService = cardPanelService;
	}
	
	
	



	public MainFrame getFrame() {
		return frame;
	}


	public void setFrame(MainFrame frame) {
		this.frame = frame;
	}
	
	public MainPanel getMainPanel() {
		return this.getCardPanel().getMainPanel();
	}
	public LoginPanel getLoginPanel() {
		return this.getCardPanel().getLoginPanel();
	}
	
	


	
	public void setTime(LocalDateTime dateTime) {
		this.cardPanel.getMainPanel().displayTime(dateTime);
		this.cardPanel.getMainPanel().getCardPanelService().getHomePagePanel().setDisplayTime(dateTime);
	}
	
	
	// show
	public void showLogin() {
		this.getCardPanel().showLogin();
	}
	public void showMain() {
		this.getCardPanel().showMain();
	}
	
	public void showBook() {
		this.getCardPanelService().showBook();
	}
	public void showReader() {
		this.getCardPanelService().showReader();
	}



	// login
	public String getTextAccount() {
		return cardPanel.getLoginPanel().getIdAccount();
	}

	public String getPassCode() {
		return cardPanel.getLoginPanel().getPasscode();
	}


	// display login
	public void faidLogin() {
		this.getCardPanel().getLoginPanel().failLogin();
	}

	public void loginSucsses() {
		this.getLoginPanel().loginSucess();
		controllerProcessUI.showMain();
	}
	
	
	// get Panel 
	public ReaderPanel getReaderPanel() {
		return this.getCardPanelService().getReaderPanel();
	}
	public BookPanel getBookPanel() {
		return this.getCardPanelService().getBookPanel();
	}
	public LoanPanel getLoanPanel() {
		return this.getCardPanelService().getLoanPanel();
	}
	
	public HomePagePanel getHomePagePanel() {
		return this.getCardPanelService().getHomePagePanel();
	}
	
	
	
	
	
	
	// READER  PANEL
	// get textfield
	public String getIdReader() {
		return this.getReaderPanel().getIdReader();
	}	
	public String getNameReader() {
		return this.getReaderPanel().getNameReader();
	}	
	public String getSexReader() {
		return this.getReaderPanel().getSex();
	}	
	public LocalDate getBirthReader() {
		return this.getReaderPanel().getBirth();
	}	
	public String getCccdReader() {
		return this.getReaderPanel().getCccd();
	}	
	public String getNumberPhone() {
		return this.getReaderPanel().getNumberPhone();
	}
	public String getSearchReader() {
		return this.getReaderPanel().getSearch();
	}
	public String getAddressReader() {
		return this.getReaderPanel().getAddressReader();
	}
	
	
	// set textfield
	public void setIdReader(String id) {
		this.getReaderPanel().setIdReader(id);
	}	
	public void setNameReader(String name) {
		this.getReaderPanel().setNameReader(name);
	}
	public void setSexReader(String sex) {
		this.getReaderPanel().setSexReader(sex);
	}	
	public void setBirthReader(String birth) {
		this.getReaderPanel().setBirthReader(birth);
	}	
	public void setCccdReader(String cccd) {
		this.getReaderPanel().setCccdReader(cccd);
	}	
	public void setAddressReader(String address) {
		this.getReaderPanel().setAddressReader(address);
	}	
	public void setNumberPhoneReader(String numberPhone) {
		this.getReaderPanel().setNumberPhone(numberPhone);
	}

	
	// table reader
	public JTable getTableReader() {
		return this.getReaderPanel().getTable();
	}
	public DefaultTableModel getDefaultTableModelReader() {
		return this.getReaderPanel().getDefaultTableModel();
	}	
	public int getRowIndexIsSelected() {
		return this.getReaderPanel().getSelectTable();
	}	
	public void editReader() {
		this.getReaderPanel().editReader();
	}
	public void exitEditReader() {
		this.getReaderPanel().exitEditReader();
	}
	public void resetTable() {
		this.getReaderPanel().resetTable();
	}
	
	// notify Reader
	public void notifiNotEnoughInformation() {
		this.getReaderPanel().notifiNotEnoughInformation();
	}
	public void notifiExistedReader() {
		this.getReaderPanel().notifiExistedReader();
	}
	public void notifiAddReaderSuccess() {
		this.getReaderPanel().notifilAddReaderSuccess();
	}
	public void clearTextFieldReader() {
		this.getReaderPanel().clearTextFieldReader();
	}
	public void notifiUpdateReaderSuccess() {
		this.getReaderPanel().notifiUpdateReaderSuccess();
	}
	public void notifiRemoveReaderSuccess() {
		this.getReaderPanel().notifiRemoveReaderSuccess();
	}
	public void notifiFailRemove() {
		this.getReaderPanel().notifiFaildRemove();
	}
	public void notifiChoiceReader() {
		this.getReaderPanel().notifiChoiceReader();
	}
	
	
	
	// Book panel
	public String getTextMode() {
		return this.getBookPanel().getTextMode();
	}
	
	public void showBookTitle() {
		this.getBookPanel().showBookTitle();
	}
	
	public void showBookItem() {
		this.getBookPanel().showBookItem();
	}
	
	public String getSearchBook() {
		return this.getBookPanel().getSeachBook();
	}
	
	
	public String getTitleName() {
		return this.getBookPanel().getNameTitle();
	}
	
	public String getAuthorName() {
		return this.getBookPanel().getAuthorName();
	}
	
	public String getPublisherName() {
		return this.getBookPanel().getPublisherName();
	}
	
	public int getQuantityOfItem() {
		return this.getBookPanel().getNumOfBook();
	}
	
	public void notifiAddBookNoSuccess() {
		this.getBookPanel().notifiAddBookNoSuccess();
	}
	
	public void notifiAddBookSuccess() {
		this.getBookPanel().notifiAddBookSuccess();
	}
	
	public void notifiRemoveBookNoSuccess() {
		this.getBookPanel().notifiRemoveBookNoSuccess();
	}
	
	public void notifiRemoveBookSuccess() {
		this.getBookPanel().notifiRemoveBookSuccess();
	}
	
	public void nofifiNotEnough() {
		this.getBookPanel().notifiNotEnough();
	}
	public double getPriceTitle() {
		return this.getBookPanel().getPriceTitle();
	}
	public String getCategory() {
		return this.getBookPanel().getCategory();
	}
	public boolean checkBorrow() {
		return this.getReaderPanel().checkBorrowing();
	}
	
	// LOAn
	public String getNumberPhongOfLoan() {
		return this.getLoanPanel().getNumberPhoneLoanPanel();
	}
	
	public List<String> getListItemOfLoan() {
		return this.getLoanPanel().getListBookItem();
	}
	public List<String> listCondition() {
		return this.getLoanPanel().getListCondition();
	}
	
	public LocalDate getDueDateOfLoan() {
		return this.getLoanPanel().getDueDate();
	}
	public void notifiNotEnoughLoan() {
		this.getLoanPanel().notifiNotEnoughLoan();
	}
	
	public void notifiBorrowSuccess() {
		this.getLoanPanel().notifiBorrowSuccess();
	}
	
	public void notifiBorrowNotSuccess() {
		this.getLoanPanel().notifiBorrowNotSuccess();
	}
	
	public String getIdLoanReturn() {
		return this.getLoanPanel().getIdLoan();
	}
	public void displayReturnBook(List<String> id) {
		this.getLoanPanel().displayIdBookReturn(id);
	}
	public void notifiNotFoundLoan() {
		this.getLoanPanel().notifiNotFoundLoan();
	}
	public void notifiNotEnterId() {
		this.getLoanPanel().notifiNotEnterId();
	}
	
	public void notifiReturnSuccess() {
		this.getLoanPanel().notifiReturnSuccess();
	}
	
	public void notifiReturnNotSuccess() {
		this.getLoanPanel().notifiReturnNotSuccess();
	}
	public void setFineAmountDisplay(double amount) {
		this.getLoanPanel().setFineAmountDisplay(amount);
	}
	public String getComboTextSearch() {
		return this.getBookPanel().getComboBoxSeach();
	}
	public String getBoxLoan() {
		return this.getLoanPanel().getBoxLoan();
	}
	public void resetLoanPanel() {
		this.getLoanPanel().resetPanel();
	}
	public String getSearchLoan() {
		return this.getLoanPanel().getSearchLoan();
	}
	public String getIdTitleOnBookPanel() {
		return this.getBookPanel().getIdTitle();
	}
	public void notifiBirth() {
		this.getBookPanel().notifiBirth();
	}
	public void notifiNumberPhone() {
		this.getBookPanel().notifiNumberPhone();
	}
	public void clearBookPanel() {
		this.getBookPanel().clearPanel();
	}
	public void setTextOnTextFieldBookPanel(List<String> element) {
		this.getBookPanel().setTextBook(element);
	}
	public boolean checkRemove() {
		return this.getBookPanel().checkRemove();
	}
	public String [] getIdItemOnTable() {
		return this.getBookPanel().getIdItemOnTable();
	}
	
	public void enbleAddBookTitle() {
		this.getBookPanel().enbleAddBook();
	}
	
	public void disableAddBookTitle() {
		this.getBookPanel().disableAddBook();
	}
	
	public void enbleEditBookTitle() {
		this.getBookPanel().enbleEditBookTitle();
	}
	public void disableEditBookTitle() {
		this.getBookPanel().disableAddBook();
	}
	public void notifiChoiceBookTitle() {
		this.getBookPanel().notifiChoiceBookTitle();
	}
	public void notifiEditSuccess() {
		this.getBookPanel().notifiEditBookTitleSuccess();
	}
	public void notifiNotFoundTitle() {
		this.getBookPanel().notifiNotFoundTitle();
	}
	public String getIdLoanOnTable() {
		return this.getLoanPanel().getIdLoanOnTable();
	}
	public void notifiRemoveLoanSuccess() {
		this.getLoanPanel().notifiRemoveLoanSuccess();
	}
	public void notifiRemoveLoanNoSuccess() { 
		this.getLoanPanel().notifiRemoveLoanNoSuccess();
	}
	public void notifiChoiceLoaṇ̣() {
		this.getLoanPanel().notifiChoiceLoaṇ̣();
	}
}

