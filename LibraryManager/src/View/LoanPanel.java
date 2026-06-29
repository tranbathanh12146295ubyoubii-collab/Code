package View;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.File;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.StyledEditorKit.ForegroundAction;

import Controller.IController;
import LoanModel.Loan;
import ObserverUI.ObserverLoan;

public class LoanPanel extends JPanel implements ObserverLoan {
	JTextField textSdtReader, textIdBook1, textIdBook2, textIdBook3, textDueDate;
	JPanel createLoanPanel, loanMangerPanel, loanPanel;
	JTextField textSearchLoan;
	
	DefaultTableModel model = new DefaultTableModel();
	JTable table = new JTable(model);
	JScrollPane scrollPane = new JScrollPane(table);
	
	JLabel labelSdtReader, labelBook1, labelBook2, labelBook3, labelDueDate;
	Font fontLabel = new Font("Inter", Font.BOLD, 12);
	int stt = 0;
	
	JButton btnCreateLoan, btnEditLoan, btnRemoveLoan, btnSaveLoan, btnRefeshLoan;
	JComboBox<String> comboBoxTypeLoan, comboStatusBook1, comboStatusBook2, comboStatusBook3;
	int [] sizeColums = {50, 150, 150, 250, 140, 120, 160};
	JTextArea inforLoan;
	JLabel labelCreateLoan, labelReturnLoan;
	JTextField idReturnLoan, returnBook1, returnBook2, returnBook3, textCostFine;
	JButton btnFindLoan, btnCalFine, btnSaveReturnLoan;
	JLabel idLable, labelReBook1, labelReBook2, labelReBook3;
	
	List<JTextField> displayIdBook; 
	List<JComboBox<String>> listComboBoxs;
	
	
	IController controller;
	
	public LoanPanel() {
		setLayout(new BorderLayout());
		loanPanel = new JPanel(null);
		
		createLoanPanel = new JPanel(null);
		loanMangerPanel = new JPanel(null);
		
		
		createLoanPanel.setBounds(0, 0, 450, 658);
		createLoanPanel.setBackground(Color.RED);
		
		
		labelCreateLoan  = new JLabel("TẠO PHIẾU MƯỢN");
		labelCreateLoan.setFont(new Font("Inter", Font.BOLD, 20));
		labelCreateLoan.setBounds(30, 40, labelCreateLoan.getPreferredSize().width, labelCreateLoan.getPreferredSize().height);
		
		
		
		
		
		
		
		labelSdtReader = new JLabel("Số điện thoại");
		labelBook1 = new JLabel("Mã sách 1");
		labelBook2 = new JLabel("Mã sách 2");
		labelBook3 = new JLabel("Mã sách 3");
		labelDueDate = new JLabel("Hạn trả");
		
		
		List<JLabel> labelTitle = new ArrayList<>();
		labelTitle.add(labelSdtReader);
		labelTitle.add(labelBook1);
		labelTitle.add(labelBook2);
		labelTitle.add(labelBook3);
		labelTitle.add(labelDueDate);
		
		
		for (JLabel jLabel : labelTitle) {
			jLabel.setFont(fontLabel);
			loanPanel.add(jLabel);
		}
		
		labelSdtReader.setBounds(30, 110, labelSdtReader.getPreferredSize().width, labelSdtReader.getPreferredSize().height);
		labelDueDate.setBounds(230, 110, labelDueDate.getPreferredSize().width, labelDueDate.getPreferredSize().height);
		labelBook1.setBounds(30, 180, labelBook1.getPreferredSize().width, labelBook1.getPreferredSize().height);
		labelBook2.setBounds(230, 180, labelBook2.getPreferredSize().width, labelBook2.getPreferredSize().height);
		labelBook3.setBounds(30, 250, labelBook3.getPreferredSize().width, labelBook3.getPreferredSize().height);
		
		

		List<JTextField> fields = new ArrayList<>(); 
		
		
		textSdtReader = new JTextField();
		textIdBook1 = new JTextField();
		textIdBook2 = new JTextField();
		textIdBook3 = new JTextField();
		textDueDate = new JTextField();
		
		fields.add(textSdtReader);
		fields.add(textIdBook1);
		fields.add(textIdBook2);
		fields.add(textIdBook3);
		fields.add(textDueDate);
		
		
		textSdtReader.setBounds(30, 130, 170, 30);
		textDueDate.setBounds(230, 130, 170, 30);
		textIdBook1.setBounds(30, 200, 170, 30);
		textIdBook2.setBounds(230, 200, 170, 30);
		textIdBook3.setBounds(30, 270, 170, 30);
		
		
		textDueDate.setText("YYYY-MM-DD");
		textDueDate.setForeground(Color.GRAY);
		textDueDate.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				super.focusGained(e);
				if (textDueDate.getText().equals("YYYY-MM-DD")) {
					textDueDate.setText("");
					textDueDate.setForeground(Color.BLACK);
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				super.focusLost(e);
				if (textDueDate.getText().isEmpty()) {
					textDueDate.setText("YYYY-MM-DD");
					textDueDate.setForeground(Color.GRAY);
				}
			}
		});
		
		
		
		
		
		for (JTextField jTextField : fields) {
			loanPanel.add(jTextField);
		}
		
		
		textSearchLoan = new JTextField();
		textSearchLoan.setBounds(30, 360, 263, 28);
		textSearchLoan.setText("Tìm kiếm");
		textSearchLoan.setForeground(Color.GRAY);
		textSearchLoan.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				super.focusGained(e);
				if (textSearchLoan.getText().equals("Tìm kiếm")) {
					textSearchLoan.setText("");
					textSearchLoan.setForeground(Color.BLACK);
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				super.focusLost(e);
				if (textSearchLoan.getText().isEmpty()) {
					textSearchLoan.setText("Tìm kiếm");
					textSearchLoan.setForeground(Color.GRAY);
				}
			}
		});
		
		
		btnCreateLoan = new JButton("THÊM");  	
		btnRemoveLoan = new JButton("XÓA");   	
		btnEditLoan = new JButton("SỬA");     	
		btnSaveLoan = new JButton("LƯU");		
		btnRefeshLoan = new JButton("LÀM MỚI"); 
		
		btnCreateLoan.setBounds(30, 320, 80, 30);
		btnRemoveLoan.setBounds(120, 320, 80, 30);
		btnEditLoan.setBounds(210, 320, 80, 30);
		btnRefeshLoan.setBounds(300, 320, 100, 30);
		btnSaveLoan.setBounds(btnEditLoan.getBounds());
		
		
		
		btnCreateLoan.addActionListener(e -> {
			controller.borrowBook();
		});
		
		btnRemoveLoan.addActionListener(e -> controller.removeLoan());
		
		
		
		
		btnSaveLoan.setVisible(false);
		
		List<JButton> buttons = new ArrayList<>();
		buttons.add(btnCreateLoan); 
		buttons.add(btnRemoveLoan);
//		buttons.add(btnEditLoan);
		buttons.add(btnSaveLoan);
		buttons.add(btnRefeshLoan);
		
		Font fontBtn = new Font("Inter", Font.BOLD, 11);
		
		
		for (JButton btn : buttons) {
			btn.setBackground(Color.getHSBColor(0.56f, 1, 1));
			btn.setForeground(Color.WHITE);		
			loanPanel.add(btn);
		}
		JLabel labelTotalCost = new JLabel("Tông phạt");
		labelTotalCost.setBounds(810, 320, 150, labelTotalCost.getPreferredSize().height);
		
		idLable = new JLabel("Nhập mã phiếu trả");
		idLable.setBounds(500, 110, 150, idLable.getPreferredSize().height);
		
		textCostFine = new JTextField("VND");
		textCostFine.setBounds(810, 340, 150, 28);
		
		
		
		
		
		
		
		comboBoxTypeLoan = new JComboBox<>();
		comboBoxTypeLoan.addItem("Tất cả");
		comboBoxTypeLoan.addItem("Đang mượn");
		comboBoxTypeLoan.addItem("Đã trả");
		comboBoxTypeLoan.addItem("Quá hạn");
		
		
		
		comboBoxTypeLoan.setBounds(300, 360, 100, 27);
		comboBoxTypeLoan.setBackground(Color.WHITE);
		comboBoxTypeLoan.addActionListener(e -> controller.getLoanByType());
		
		
		labelReturnLoan = new JLabel("TRẢ PHIẾU");
		labelReturnLoan.setFont(labelCreateLoan.getFont());
		labelReturnLoan.setBounds(500, 40, labelReturnLoan.getPreferredSize().width, labelReturnLoan.getPreferredSize().height);
		
		
		
		
		idReturnLoan = new JTextField();
		idReturnLoan.setBounds(500, 130, 300, 28);
		
		
		
		btnFindLoan = new JButton("TÌM");
		btnCalFine = new JButton("TỔNG PHẠT");
		btnSaveReturnLoan = new JButton("TRẢ");
		
		btnSaveReturnLoan.addActionListener(e -> {
			controller.returnBook();
		});
		
		btnFindLoan.addActionListener(e -> {
			controller.findReturnLoan();
		});
		
		btnCalFine.addActionListener(e -> {
			controller.calFine();
		});
		
		
		List<JButton> returnBtn = Arrays.asList(btnFindLoan, btnSaveReturnLoan);
		int yBtnReturn = 130;
		
		for (JButton btn : returnBtn) {
			btn.setBounds(810, yBtnReturn, 110, 28);
			btn.setBackground(Color.getHSBColor(0.56f, 1, 1));
			btn.setForeground(Color.WHITE);
			loanPanel.add(btn);
			yBtnReturn += 70;
		}
		

		
		
		textCostFine.setEditable(false);
		
		
		
		comboStatusBook1 = new JComboBox<>();
		comboStatusBook2 = new JComboBox<>();
		comboStatusBook3 = new JComboBox<>();
		
		
		listComboBoxs = Arrays.asList(comboStatusBook1, comboStatusBook2, comboStatusBook3);
		
		
		
		List<JComboBox<String>> comboBoxs = Arrays.asList(comboStatusBook1, comboStatusBook2, comboStatusBook3);
		int y = 200;
		for (JComboBox<String> c : comboBoxs) {
			c.addItem("NEW");
			c.addItem("DAMAGED");
			c.addItem("LOST");
			c.setBounds(720, y, 80, 28);
			c.addActionListener(e -> controller.calFine());
			loanPanel.add(c);
			y += 70;
		}
		
		returnBook1 = new JTextField();
		returnBook2 = new JTextField();
		returnBook3 = new JTextField();
		
		List<JTextField> fieldsReturnBook = Arrays.asList(returnBook1, returnBook2, returnBook3);
		
		labelBook1 = new JLabel("Mã sách 1");
		labelBook2 = new JLabel("Mã sách 2");
		labelBook3 = new JLabel("Mã sách 3");
		List<JLabel> jLabels = Arrays.asList(labelBook1, labelBook2, labelBook3);
		
		int yLable = 180;
		for (JLabel jLabel : jLabels) {
			jLabel.setBounds(500, yLable, 150, jLabel.getPreferredSize().height);
			loanPanel.add(jLabel);
			yLable += 70;
		}
		
		
		
		int Y_field = 200;
		for (JTextField tf : fieldsReturnBook) {
			tf.setBounds(500, Y_field, 200, 28);
			loanPanel.add(tf);
			Y_field += 70;
		}
		

		
		
		
		inforLoan = new JTextArea();
		inforLoan.setBounds(650, 0, 570, 400);
		inforLoan.setEditable(false);
		
		
		inforLoan.setFont(new Font("Inter", Font.PLAIN, 13));
		
		
		
		
		model.addColumn("STT");
		model.addColumn("ID Phiếu");
		model.addColumn("ID Độc Giả");
		model.addColumn("Tên Độc giả");
		model.addColumn("Ngày lập phiếu");
		model.addColumn("Hạn trả");
		model.addColumn("Ngày trả");		
		
		scrollPane.setBounds(0, 400, 1020, scrollPane.getPreferredSize().height);
		
		table.getTableHeader().setReorderingAllowed(false);
		
		for (int i = 0; i < sizeColums.length; i++) {
			
			table.getColumnModel().getColumn(i).setPreferredWidth(sizeColums[i]);
			table.getColumnModel().getColumn(i).setMinWidth(sizeColums[i]);
			table.getColumnModel().getColumn(i).setMaxWidth(sizeColums[i]);
		}
		
		
		displayIdBook = Arrays.asList(returnBook1, returnBook2, returnBook3);
		
		
		
		
		
		
		loanPanel.add(idLable);
		loanPanel.add(labelTotalCost);
		loanPanel.add(textCostFine);
		loanPanel.add(idReturnLoan);
		loanPanel.add(labelReturnLoan);
		loanPanel.add(labelCreateLoan);
		loanPanel.add(comboBoxTypeLoan);
		loanPanel.add(scrollPane);
		loanPanel.add(textSearchLoan);
		
		
		add(loanPanel, BorderLayout.CENTER);
	}
	
	public boolean checkTextFieldQuantity(JTextField field) {
		for (char c : field.getText().toCharArray()) {
			if (!Character.isDigit(c)) {
				return false;
			} 
		} return true;
	}
	
	private String checkDate(LocalDate date) {
		if (date == null) return "NULL";
		try {
			return date.toString();
		} catch (DateTimeParseException e) {
			return "NULL";
		}
	}

	@Override
	public void updateDisplayLoan(List<Loan> list) {
		clearTable();
		for (Loan l : list) {
			if (l != null) {
				String returnDate = checkDate(l.getReturnDate());
				addRowTableLoan(l.getId(), l.getIdReader(), l.getReaderName(), l.getBorrowDate().toString(), l.getDueDate().toString(), returnDate);
			}
		}
	}
	public String getNumberPhoneLoanPanel() {
		return textSdtReader.getText();
	}
	
	public List<String> getListBookItem() {
		return new ArrayList<>(Arrays.asList(textIdBook1.getText(), textIdBook2.getText(), textIdBook3.getText()));
	}
	
	public String getSearchLoan() {
		return textSearchLoan.getText();
	}
	
	public LocalDate getDueDate() {
		 try {
		        return LocalDate.parse(textDueDate.getText());
		    } catch (DateTimeParseException e) {
		        return LocalDate.now().plusDays(14);
		    }
	}
	
	/**
	 * 
	 */
	public void setPriceDiaplay(double price) {
		textCostFine.setText(price + "VND");
	}
	
	
	public String getIdLoan() {
		return idReturnLoan.getText();
	}
	
	
	
	
	public void editLoan() {
		btnCreateLoan.setVisible(false);
		btnRefeshLoan.setVisible(false);
		btnRemoveLoan.setVisible(false);
		btnSaveLoan.setVisible(true);
	}
	
	public void exitEditLoan() {
		btnCreateLoan.setVisible(true);
		btnRefeshLoan.setVisible(true);
		btnRemoveLoan.setVisible(true);
		btnSaveLoan.setVisible(false);
	}
	
	public void setIController(IController controller) {
		this.controller = controller;
	}
	
	public void displayIdBookReturn(List<String> id) {
		if (id == null || id.isEmpty() || id.size() > 3) return;
		for (int i = 0; i < id.size(); i++) {
			displayIdBook.get(i).setText(id.get(i));
		}
	}
	
	
	public void addRowTableLoan(String idLoan, String idReader, String readerName, String ngayLapPhieu, String dueDate, String returnDate) {
		model.addRow(new Object[] {stt++ + "" ,idLoan, idReader, readerName, ngayLapPhieu, dueDate, returnDate});
	}
	
	public void clearTable() {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		stt = 1;
	}
	public void notifiNotEnoughLoan() {
		JOptionPane.showMessageDialog(null, "Vui lòng điền đủ thông tin");
	}
	
	public void notifiBorrowSuccess() {
		JOptionPane.showMessageDialog(null, "Mượn thành công");
	}
	
	public void notifiBorrowNotSuccess() {
		JOptionPane.showMessageDialog(null, "Mượn không thành công");
	}
	
	public void notifiNotFoundLoan() {
		JOptionPane.showMessageDialog(null, "Không tìm thấy phiếu mượn!");
	}
	
	public void notifiNotEnterId() {
		JOptionPane.showMessageDialog(null, "Vui lòng nhập id");
	}
	
	public void notifiReturnSuccess() {
		JOptionPane.showMessageDialog(null, "Trả thành cồng");
	}
	
	public void notifiReturnNotSuccess() {
		JOptionPane.showMessageDialog(null, "Trả không thành công");
	}
	
	public List<String> getListCondition() {
		List<String> status = new ArrayList<>();
		for (int i = 0; i < listComboBoxs.size(); i++) {
			status.add(listComboBoxs.get(i).getSelectedItem().toString());
		}
		return status;
	}
	
	public void setFineAmountDisplay(double amount) {
		textCostFine.setText(amount + "VND");
	}
	
	public String getBoxLoan() {
		return comboBoxTypeLoan.getSelectedItem().toString();
	}
	public void resetPanel() {
		textCostFine.setText("VND");
		textDueDate.setText("YYYY-MM-DD");
		textIdBook1.setText("");
		textIdBook2.setText("");
		textIdBook3.setText("");
		textSdtReader.setText("");
		textSearchLoan.setText("");
		textDueDate.setForeground(Color.GRAY);
		returnBook1.setText("");
		returnBook2.setText("");
		returnBook3.setText("");
		idReturnLoan.setText("");
	}
	
	public String getSearchLoanId() {
		if (textSearchLoan.getText().equals("Tìm kiếm")) return "";
		return textSearchLoan.getText();
	}
	
	public String getIdLoanOnTable() {
		int row = table.getSelectedRow();
		return this.model.getValueAt(row, 1).toString();
	}
	
	public void notifiRemoveLoanSuccess() {
		JOptionPane.showMessageDialog(null, "Xóa phiếu thành công");
	}
	public void notifiRemoveLoanNoSuccess() { 
		JOptionPane.showMessageDialog(null, "Thất bại, phiếu hiện đang được mượn");
	}
	public void notifiChoiceLoaṇ̣() {
		JOptionPane.showMessageDialog(null, "Vui lòng chọn phiếu mượn");
	}
}
