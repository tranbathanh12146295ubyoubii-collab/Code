package Controller;

import Facade.*;
import LoanModel.Loan;
import ReaderModel.Reader;
import View.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JOptionPane;

import BookModel.BookTitle;

public class ControllerService implements IController {
	FacadeLibrary facadeLibrary;
	MasterView masterView;

	public ControllerService(FacadeLibrary facadeLibrary) {
		this.facadeLibrary = facadeLibrary;
		this.masterView = masterView;
	}

	public FacadeLibrary getFacadeLibrary() {
		return facadeLibrary;
	}

	public void setFacadeLibarary(FacadeLibrary facadeLibrary) {
		this.facadeLibrary = facadeLibrary;
	}

	public MasterView getMasterView() {
		return masterView;
	}

	@Override
	public void setMasterView(MasterView masterView) {
		this.masterView = masterView;
		displayTime();
	}

	/**
	 * 
	 */
	@Override
	public void displayTime() {
		while (true) {
			LocalDateTime dateTime = LocalDateTime.now().withNano(0);
			masterView.setTime(dateTime);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void logout() {
		int choice = JOptionPane.showConfirmDialog(masterView.getCardPanel().getMainPanel(), "Bạn có muốn đăng xuất?",
				"Đăng xuất", JOptionPane.YES_NO_OPTION);
		if (choice == JOptionPane.YES_OPTION) {
			masterView.getCardPanel().showLogin();
			masterView.getCardPanel().getLoginPanel().resetLog();
		}
	}

	@Override
	public void login() {
		boolean log = facadeLibrary.login(masterView.getTextAccount(), masterView.getPassCode());
		if (log) {
			masterView.loginSucsses();
		} else {
			masterView.faidLogin();
		}
	}

	@Override
	public void addReader() {
		if (masterView.getIdReader().isEmpty() || masterView.getNameReader().isEmpty()
				|| masterView.getCccdReader().isEmpty() || masterView.getSexReader().isEmpty()
				|| masterView.getBirthReader() == null || masterView.getAddressReader().isEmpty()
				|| masterView.getNumberPhone().isEmpty()) {
			masterView.notifiNotEnoughInformation();
		} else {
			if (this.getMasterView().getBirthReader() == LocalDate.MIN) {
				this.getMasterView().notifiBirth();
				return;
			}
			for (char c : masterView.getNumberPhone().toCharArray()) {
				if (!Character.isDigit(c)) {
					this.getMasterView().notifiNumberPhone();
					return;
				}
			}
			if (this.getMasterView().getNumberPhone().startsWith("09")
					|| this.getMasterView().getNumberPhone().startsWith("03")) {
				this.getMasterView().notifiNumberPhone();
				return;
			}
			Reader reader = this.getFacadeLibrary().createReader(masterView.getIdReader(), masterView.getNameReader(),
					masterView.getSexReader(), masterView.getBirthReader(), masterView.getAddressReader(),
					masterView.getCccdReader(), masterView.getNumberPhone());
			if (reader != null) {
				if (this.getFacadeLibrary().addReader(reader)) {
					masterView.clearTextFieldReader();
					masterView.notifiAddReaderSuccess();
				}
			} else
				masterView.notifiExistedReader();
		}
	}

	@Override
	public void removeReader() {
		int row = this.getMasterView().getRowIndexIsSelected();
		if (row < 0) {
			this.getMasterView().notifiChoiceReader();
		} else {
			String id = (String) this.getMasterView().getDefaultTableModelReader().getValueAt(row, 1);
			if (this.getFacadeLibrary().removeReader(id)) {
				this.getMasterView().notifiRemoveReaderSuccess();
			} else {
				this.getMasterView().notifiFailRemove();
			}
		}
	}

	@Override
	public void refeshReader() {
		this.getMasterView().resetTable();
		this.getFacadeLibrary().refeshReader();
	}

	@Override
	public void searchReader() {
		this.getFacadeLibrary().findByNameReader(masterView.getSearchReader());
	}

	@Override
	public void editReader() {
		if (this.getMasterView().getTableReader().getSelectedRowCount() <= 0) {
			return;
		}
		this.getMasterView().editReader();

		int length = this.getMasterView().getDefaultTableModelReader().getColumnCount() - 1;
		String[] str = new String[length];
		int r = this.getMasterView().getRowIndexIsSelected();

		for (int i = 0; i < str.length; i++) {
			str[i] = (String) this.getMasterView().getDefaultTableModelReader().getValueAt(r, i + 1);
		}

		this.getMasterView().setIdReader(str[0]);
		this.getMasterView().setNameReader(str[1]);
		this.getMasterView().setSexReader(str[2]);
		this.getMasterView().setBirthReader(str[3]);
		this.getMasterView().setCccdReader(str[4]);
		this.getMasterView().setAddressReader(str[5]);
		this.getMasterView().setNumberPhoneReader(str[6]);
	}

	@Override
	public void exitEditReader() {
		if (masterView.getIdReader().isEmpty() || masterView.getNameReader().isEmpty()
				|| masterView.getCccdReader().isEmpty() || masterView.getSexReader().isEmpty()
				|| masterView.getBirthReader() == null || masterView.getAddressReader().isEmpty()
				|| masterView.getNumberPhone().isEmpty()) {
			masterView.notifiNotEnoughInformation();
		} else if (facadeLibrary.editReader(masterView.getIdReader(), masterView.getNameReader(),
				masterView.getSexReader(), masterView.getBirthReader(), masterView.getCccdReader(),
				masterView.getAddressReader(), masterView.getNumberPhone())) {
			this.getMasterView().exitEditReader();
			this.masterView.notifiUpdateReaderSuccess();
		}
	}

	@Override
	public void transitionViewModeBook() {
		if (this.getMasterView().getTextMode().equals("Đầu sách")) {
			this.getMasterView().showBookTitle();
		} else {
			this.getMasterView().showBookItem();
		}
	}

	@Override
	public void addBookTitle() {
		if (this.getMasterView().getTitleName().isEmpty() || this.getMasterView().getAuthorName().isEmpty()
				|| this.getMasterView().getPublisherName().isEmpty()) {
			this.getMasterView().nofifiNotEnough();
		} else {
			if (this.getFacadeLibrary().addBookTitle(this.getMasterView().getIdTitleOnBookPanel(),
					this.getMasterView().getTitleName(), this.getMasterView().getPriceTitle(),
					this.getMasterView().getAuthorName(), this.getMasterView().getPublisherName(),
					this.getMasterView().getCategory(), this.getMasterView().getQuantityOfItem())) {
				this.getMasterView().clearBookPanel();
				this.getMasterView().notifiAddBookSuccess();
				disaleAddBookTitle();
			} else {
				this.getMasterView().notifiAddBookNoSuccess();

			}
		}
	}

	@Override
	public void searchBook() {
		this.getFacadeLibrary().setBookSearch(this.getMasterView().getComboTextSearch());
		this.getFacadeLibrary().findBookTitleByTitle(this.getMasterView().getSearchBook());
		this.getFacadeLibrary().findItemByTitle(this.getMasterView().getSearchBook());
	}

	@Override
	public void refeshBook() {
		this.getFacadeLibrary().refeshBook();
	}

	@Override
	public void checkBorrowing() {
		if (this.getMasterView().checkBorrow()) {
			this.getFacadeLibrary().findReaderBorrowing();
		} else {
			this.getFacadeLibrary().refeshReader();
		}
	}

	@Override
	public void removeTitle() {
		String id = this.getMasterView().getIdTitleOnBookPanel();
		if (id == null || id.isEmpty()) {
			this.getMasterView().notifiRemoveBookNoSuccess();
			return;
		}
		if (this.getMasterView().checkRemove()) {
			if (this.getFacadeLibrary().removeBookTitle(id)) {

				this.getMasterView().clearBookPanel();
				this.getMasterView().notifiRemoveBookSuccess();
			} else {
				this.getMasterView().notifiRemoveBookNoSuccess();
			}
		}
	}

	@Override
	public void removeItem() {
		for (String id : this.getMasterView().getIdItemOnTable()) {
			if (!this.getFacadeLibrary().removeItem(id)) {
				this.getMasterView().notifiRemoveBookNoSuccess();
			}
		}
	}

	@Override
	public void borrowBook() {
		if (this.getMasterView().getNumberPhongOfLoan().isEmpty() || this.getMasterView().getListItemOfLoan().isEmpty()
				|| this.getMasterView().getDueDateOfLoan() == null) {
			this.getMasterView().notifiNotEnoughLoan();
		} else {
			for (char c : this.getMasterView().getNumberPhongOfLoan().toCharArray()) {
				if (!Character.isDigit(c)) {
					this.getMasterView().notifiNumberPhone();
					return;
				}
			}
			if (this.getFacadeLibrary().borrowBook(this.getMasterView().getNumberPhongOfLoan(),
					this.getMasterView().getListItemOfLoan(), this.getMasterView().getDueDateOfLoan())) {
				this.getMasterView().resetLoanPanel();
				this.getMasterView().notifiBorrowSuccess();

			} else {
				this.getMasterView().notifiBorrowNotSuccess();
			}
		}

	}

	@Override
	public void returnBook() {
		if (this.getMasterView().getIdLoanReturn().isEmpty()) {
			this.getMasterView().notifiNotEnterId();
		} else {
			if (this.getFacadeLibrary().returnBook(this.getMasterView().getIdLoanReturn())) {
				this.getMasterView().notifiReturnSuccess();
				this.getMasterView().resetLoanPanel();

			} else {
				this.getMasterView().notifiReturnNotSuccess();
			}
		}

	}

	@Override
	public void findReturnLoan() {
		if (!this.getFacadeLibrary().containLoan(this.getMasterView().getIdLoanReturn())) {
			this.getMasterView().notifiNotFoundLoan();
		} else {
			this.getMasterView().displayReturnBook(this.getFacadeLibrary()
					.getListIdItemByLoan(this.getFacadeLibrary().findLoanById(this.getMasterView().getIdLoanReturn())));
			this.getFacadeLibrary().findLoanById(this.getMasterView().getIdLoanReturn()).setReturnDate(LocalDate.now());
			this.getFacadeLibrary().setCondition(this.getMasterView().listCondition(),
					this.getMasterView().getIdLoanReturn());
			this.getMasterView()
					.setFineAmountDisplay(this.getFacadeLibrary().totalFine(this.getMasterView().getIdLoanReturn()));
		}

	}

	@Override
	public void calFine() {
		if (this.getMasterView().getIdLoanReturn().isEmpty())
			return;
		this.getFacadeLibrary().setCondition(this.getMasterView().listCondition(),
				this.getMasterView().getIdLoanReturn());
		this.getMasterView()
				.setFineAmountDisplay(this.getFacadeLibrary().totalFine(this.getMasterView().getIdLoanReturn()));
	}

	@Override
	public void getLoanByType() {
		this.getFacadeLibrary().getLoanByType(this.getMasterView().getBoxLoan());
	}

	@Override
	public void displayBookTitleOnTextField(String idTitle) {
		if (idTitle == null || idTitle.isEmpty())
			return;
		List<String> element = this.getFacadeLibrary().getListInforOfTitle(idTitle);
		if (element != null && !element.isEmpty()) {
			this.getMasterView().setTextOnTextFieldBookPanel(element);
			System.out.println(element);
		}
	}

	@Override
	public void enbleAddBookTitle() {
		this.getMasterView().enbleAddBookTitle();
	}

	@Override
	public void disaleAddBookTitle() {
		this.getMasterView().disableAddBookTitle();
	}

	@Override
	public void enbleEditBookTitle() {
		if (this.getMasterView().getIdTitleOnBookPanel().isEmpty()) {
			this.getMasterView().notifiChoiceBookTitle();
			return;
		}
		this.getMasterView().enbleEditBookTitle();

	}

	@Override
	public void disableEditBookTitle() {
		if (this.getMasterView().getAuthorName().isEmpty() || this.getMasterView().getPublisherName().isEmpty()
				|| this.getMasterView().getCategory().isEmpty() || this.getMasterView().getTitleName().isEmpty()) {
			this.getMasterView().notifiNotEnoughInformation();
			return;
		}
		BookTitle b = this.getFacadeLibrary().findBookTitleById(this.getMasterView().getIdTitleOnBookPanel());
		if (b == null) {
			this.getMasterView().notifiNotFoundTitle();
		} else {
			this.getFacadeLibrary().setBookTitle(this.getMasterView().getIdTitleOnBookPanel(),
					this.getMasterView().getTitleName(), this.getMasterView().getPriceTitle(),
					this.getMasterView().getAuthorName(), this.getMasterView().getPublisherName(),
					this.getMasterView().getCategory());
			this.getMasterView().notifiEditSuccess();
		}
		this.getMasterView().disableEditBookTitle();
		this.getMasterView().clearBookPanel();
	}

	@Override
	public void removeLoan() {
		if (this.getMasterView().getIdLoanOnTable() == null || this.getMasterView().getIdLoanOnTable().isEmpty()) {
			this.getMasterView().notifiChoiceLoaṇ̣();
		} else {
			if (this.getFacadeLibrary().removeLoanById(this.getMasterView().getIdLoanOnTable())) {
				this.getMasterView().notifiRemoveLoanSuccess();
			} else {
				this.getMasterView().notifiRemoveLoanNoSuccess();
			}
		}

	}

}
