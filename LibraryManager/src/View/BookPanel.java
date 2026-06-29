package View;

import ObserverUI.ObserverBook;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.http.WebSocket.Listener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import Adapter.*;
import BookModel.*;
import Controller.IController;

public class BookPanel extends JPanel implements ObserverBook {
	JPanel bookJPanel, containBookPanel;
	JTextField searchBook, textNameTitle, textNumOfBook, textAuthor, textPublisher, textPrice, textCategory, textId;

	JButton btnRefesh, btnSearch, btnCreateBookTitle, btnAddTitle, btnRemoveTitle, btnEditTitle, btnSaveTitle;
	JScrollPane jScroll;

	JComboBox<String> comboBox, comboCategory, comBoxViewMode;

	DefaultTableModel model = new DefaultTableModel();
	JTable table = new JTable(model);
	JScrollPane scrollBookItem = new JScrollPane(table);

	JLabel labelTitleName, labelAuthor, labelQuantity, labelPublisher, labelPrice, labelCategory, labelId;
	JButton btnCreateBook;
	List<JTextField> fields;
	int stt = 0;

	IController controller;

	ActionListener actionRemoveTitle = e -> controller.removeTitle();
	ActionListener actionRemoveItem = e -> controller.removeItem();

	public BookPanel() {
		setLayout(new BorderLayout());
		bookJPanel = new JPanel(null);

		// thanh tim kiem sach
		searchBook = new JTextField();
		searchBook.setBounds(30, 210, 350, 30);

		searchBook.setText("Tìm kiếm sách");
		searchBook.setForeground(Color.GRAY);
		searchBook.setFont(new Font("Inter", Font.PLAIN, 13));
		searchBook.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				super.focusGained(e);
				if (searchBook.getText().equals("Tìm kiếm sách")) {
					searchBook.setText("");
					searchBook.setForeground(Color.BLACK);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				super.focusLost(e);
				if (searchBook.getText().isEmpty()) {
					searchBook.setText("Tìm kiếm sách");
					searchBook.setForeground(Color.GRAY);
				}
			}
		});

		comboBox = new JComboBox<String>();
		comboBox.addItem("Tên sách");
		comboBox.addItem("Thể loại");
		comboBox.addItem("Tác giả");

		comboBox.setBounds(482, 210, 100, 29);
		comboBox.setBackground(Color.WHITE);

		comboBox.addActionListener(e -> controller.searchBook());

		btnSearch = new JButton("Tìm kiếm");
		btnRefesh = new JButton("LÀM MỚI");

		textNameTitle = new JTextField();
		textNumOfBook = new JTextField();
		textAuthor = new JTextField();
		textPublisher = new JTextField();
		textPrice = new JTextField();
		textCategory = new JTextField();
		textId = new JTextField();

		comboCategory = new JComboBox<String>();

		comBoxViewMode = new JComboBox<>();
		comBoxViewMode.addItem("Đầu sách");
		comBoxViewMode.addItem("Bản sách");

		comBoxViewMode.setBounds(390, 210, 80, 29);
		comBoxViewMode.setBackground(Color.WHITE);
		comBoxViewMode.addActionListener(e -> {
			controller.transitionViewModeBook();
		});

		textNameTitle.setBounds(260, 50, 200, 30);
		textNumOfBook.setBounds(30, 120, 200, 30);
		textAuthor.setBounds(720, 50, 200, 30);
		textPublisher.setBounds(260, 120, 200, 30);
		textPrice.setBounds(490, 50, 200, 30);
		textCategory.setBounds(490, 120, 200, 30);
		textId.setBounds(30, 50, 200, 30);

		fields = new ArrayList<>();
		fields.add(textId);
		fields.add(textNameTitle);
		fields.add(textPrice);
		fields.add(textAuthor);
		fields.add(textPublisher);
		fields.add(textCategory);
		fields.add(textNumOfBook);

		for (JTextField text : fields) {
			text.setBorder(
					BorderFactory.createCompoundBorder(text.getBorder(), BorderFactory.createEmptyBorder(0, 5, 0, 0)));
			bookJPanel.add(text);
		}

		labelTitleName = new JLabel("Tên đầu sách");
		labelQuantity = new JLabel("Số lượng");
		labelAuthor = new JLabel("Tác giả");
		labelPublisher = new JLabel("Nhà xuất bản");
		labelPrice = new JLabel("Giá");
		labelCategory = new JLabel("Thể loại");
		labelId = new JLabel("ID");

		labelId.setBounds(30, 30, labelId.getPreferredSize().width, labelId.getPreferredSize().height);
		labelTitleName.setBounds(260, 30, labelTitleName.getPreferredSize().width,
				labelTitleName.getPreferredSize().height);
		labelQuantity.setBounds(30, 100, labelTitleName.getPreferredSize().width,
				labelTitleName.getPreferredSize().height);
		labelAuthor.setBounds(720, 30, labelTitleName.getPreferredSize().width,
				labelTitleName.getPreferredSize().height);
		labelPublisher.setBounds(260, 100, labelTitleName.getPreferredSize().width,
				labelTitleName.getPreferredSize().height);
		labelPrice.setBounds(490, 30, labelPrice.getPreferredSize().width, labelPrice.getPreferredSize().height);
		labelCategory.setBounds(490, 100, labelCategory.getPreferredSize().width,
				labelCategory.getPreferredSize().height);

		List<JLabel> labels = Arrays.asList(labelTitleName, labelQuantity, labelAuthor, labelPublisher, labelPrice,
				labelCategory, labelId);
		for (JLabel jLabel : labels) {
			bookJPanel.add(jLabel);
		}

		btnAddTitle = new JButton("Thêm đầu sách");
		btnRemoveTitle = new JButton("Xóa đầu sách");
		btnEditTitle = new JButton("Sửa");
		btnSaveTitle = new JButton("Lưu");
		btnSaveTitle.setVisible(false);

		btnAddTitle.setBounds(30, 170, 125, 30);
		btnRemoveTitle.setBounds(160, 170, 115, 30);
		btnEditTitle.setBounds(280, 170, 80, 30);
		btnRefesh.setBounds(365, 170, 93, 30);
		btnSaveTitle.setBounds(btnEditTitle.getBounds());

		btnCreateBook = new JButton("+");
		btnCreateBook.setBounds(935, 195, 45, 45);
		btnCreateBook.setFont(new Font("Inter", Font.BOLD, 18));
		btnCreateBook.setBackground(Color.getHSBColor(0.56f, 1, 1));
		btnCreateBook.setForeground(Color.WHITE);
		btnCreateBook.addActionListener(e -> controller.enbleAddBookTitle());

		List<JButton> buttons = Arrays.asList(btnAddTitle, btnRemoveTitle, btnEditTitle, btnSaveTitle, btnRefesh);

		for (JButton jButton : buttons) {
			jButton.setBackground(Color.getHSBColor(0.56f, 1, 1));
			jButton.setForeground(Color.WHITE);
			bookJPanel.add(jButton);
		}

		btnAddTitle.addActionListener(e -> controller.addBookTitle());

		btnRemoveTitle.addActionListener(actionRemoveTitle);

		btnRefesh.addActionListener(e -> {
			controller.refeshBook();
		});

		btnEditTitle.addActionListener(e -> controller.enbleEditBookTitle());
		btnSaveTitle.addActionListener(e -> controller.disableEditBookTitle());

		disableAddBook();

		searchBook.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				super.keyTyped(e);
				controller.searchBook();
			}
		});

		table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

				if (!isSelected) {
					String status = table.getValueAt(row, 3).toString();
					if ("false".equals(status)) {
						c.setBackground(Color.RED);
						c.setForeground(Color.WHITE);
					} else if ("true".equals(status)) {
						c.setBackground(Color.WHITE);
						c.setForeground(Color.BLACK);
					}
				}
				return c;
			}
		});

		model.addColumn("STT");
		model.addColumn("ID");
		model.addColumn("Thuộc đầu sách");
		model.addColumn("Trong kho");
		model.addColumn("Trạng thái");

		containBookPanel = new JPanel();
		containBookPanel.setLayout(new BoxLayout(containBookPanel, BoxLayout.Y_AXIS));

		containBookPanel.add(Box.createVerticalStrut(Integer.MAX_VALUE));

		jScroll = new JScrollPane(containBookPanel);
		jScroll.setBounds(0, 250, 1005, 424);
		jScroll.setVisible(true);
		jScroll.setViewportView(containBookPanel);

		scrollBookItem.setBounds(0, 250, 1005, 410);
		scrollBookItem.setVisible(false);

		table.getTableHeader().setReorderingAllowed(false);

		bookJPanel.add(comboBox);
		bookJPanel.add(scrollBookItem);
		bookJPanel.add(comBoxViewMode);
		bookJPanel.add(jScroll);
		bookJPanel.add(searchBook);
		bookJPanel.add(btnCreateBook);

		add(bookJPanel, BorderLayout.CENTER);
	}

	@Override
	public void updateDisplayBookTitle(List<BookTitle> bookTitles) {
		containBookPanel.removeAll();
		for (BookTitle b : bookTitles) {
			Adapter panel = new BookTitleAdapter(b);
			BookTitleCard card = (BookTitleCard) panel.createPanel();
			card.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					super.mouseClicked(e);
					controller.displayBookTitleOnTextField(card.getIdTitleOnCard());
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					super.mouseEntered(e);
					card.setBackground(Color.getHSBColor(0.56f, 1, 1));
				}

				@Override
				public void mouseExited(MouseEvent e) {
					super.mouseExited(e);
					card.setBackground(Color.WHITE);
				}
			});

			containBookPanel.add(card);
		}
		containBookPanel.revalidate();
		containBookPanel.repaint();
	}

	public String getSeachBook() {
		return searchBook.getText();
	}

	public void addRowBookItem(String id, String titleName, String available, String status) {
		model.addRow(new Object[] { stt++, id, titleName, available, status });
	}

	public String getTextMode() {
		return comBoxViewMode.getSelectedItem().toString();
	}

	public void resetTable() {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		stt = 1;
	}

	public void showBookTitle() {
		scrollBookItem.setVisible(false);
		jScroll.setVisible(true);
		btnAddTitle.setText("THÊM ĐẦU SÁCH");
		btnRemoveTitle.setText("XÓA ĐẦU SÁCH");
		btnRemoveTitle.removeActionListener(actionRemoveItem);
		btnRemoveTitle.addActionListener(actionRemoveTitle);
	}

	public String[] getIdItemOnTable() {
		int[] rowSelect = table.getSelectedRows();
		String[] listIdItem = new String[rowSelect.length];
		for (int i = 0; i < listIdItem.length; i++) {
			listIdItem[i] = model.getValueAt(rowSelect[i], 1) + "";
		}
		return listIdItem;
	}

	public void showBookItem() {
		scrollBookItem.setVisible(true);
		jScroll.setVisible(false);
		btnRemoveTitle.setText("XÓA BẢN SAO");
		btnRemoveTitle.removeActionListener(actionRemoveTitle);
		btnRemoveTitle.addActionListener(actionRemoveItem);
	}

	public void setIController(IController controller) {
		this.controller = controller;
	}

	@Override
	public void updateDisplayBookItem(List<BookItem> bookItems) {
		resetTable();
		for (BookItem bookItem : bookItems) {
			addRowBookItem(bookItem.getId(), bookItem.getBookTitle().getTitle(), bookItem.isStatus() + "",
					bookItem.getCondition().getDecription());
		}
	}

	public String getNameTitle() {
		return textNameTitle.getText();
	}

	public String getAuthorName() {
		return textAuthor.getText();
	}

	public String getPublisherName() {
		return textPublisher.getText();
	}

	public int getNumOfBook() {
		try {
			return Integer.parseInt(textNumOfBook.getText());
		} catch (NumberFormatException e) {
			return 0;
		}
	}

	public void notifiAddBookNoSuccess() {
		JOptionPane.showMessageDialog(null, "Thêm không thành công");
	}

	public void notifiAddBookSuccess() {
		JOptionPane.showMessageDialog(null, "Thêm thành công");
	}

	public void notifiRemoveBookNoSuccess() {
		JOptionPane.showMessageDialog(null, "Xóa không thành công");
	}

	public void notifiRemoveBookSuccess() {
		JOptionPane.showMessageDialog(null, "Xóa thành công");
	}

	public void notifiNotEnough() {
		JOptionPane.showMessageDialog(null, "Vui lòng điền đủ thông tin");
	}

	public void notifiBirth() {
		JOptionPane.showMessageDialog(null, "Sai định dạng ngày sinh! Vui lòng nhập lại thông tin");
	}

	public double getPriceTitle() {
		try {
			return Double.parseDouble(textPrice.getText());
		} catch (NumberFormatException e) {
			return 0;
		}
	}

	public String getIdTitle() {
		return textId.getText();
	}

	public String getCategory() {
		return textCategory.getText();
	}

	public String getComboBoxSeach() {
		return comboBox.getSelectedItem().toString();
	}

	public void notifiNumberPhone() {
		JOptionPane.showMessageDialog(null, "Số điện thoại không hợp lê!");
	}

	public void clearPanel() {
		fields.stream().forEach(i -> i.setText(""));
	}

	public void setTextBook(List<String> element) {
		for (int i = 0; i < element.size(); i++) {
			if (element.get(i) == null || element.get(i).isEmpty())
				continue;
			fields.get(i).setText(element.get(i));
		}
	}

	public boolean checkRemove() {
		int choice = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn xóa đầu sách!", "Cảnh báo",
				JOptionPane.YES_NO_OPTION);
		return choice == JOptionPane.YES_OPTION;
	}

	public void disableAddBook() {
		for (JTextField textField : fields) {
			textField.setEnabled(false);
		}
		btnRemoveTitle.setVisible(true);
		btnEditTitle.setVisible(true);
		btnRefesh.setVisible(true);
		btnAddTitle.setEnabled(false);
		btnAddTitle.setBackground(Color.WHITE);
		btnAddTitle.setForeground(Color.BLACK);
	}

	public void enbleAddBook() {
		for (JTextField textField : fields) {
			textField.setEnabled(true);
		}
		btnEditTitle.setVisible(false);
		btnRefesh.setVisible(false);
		btnRemoveTitle.setVisible(false);
		btnAddTitle.setEnabled(true);
		btnAddTitle.setBackground(Color.getHSBColor(0.56f, 1, 1));
		btnAddTitle.setForeground(Color.WHITE);
	}

	public void enbleEditBookTitle() {
		for (JTextField textField : fields) {
			textField.setEnabled(true);
		}
		textId.setEditable(false);
		textNumOfBook.setEnabled(false);
		btnEditTitle.setVisible(false);
		btnAddTitle.setVisible(false);
		btnRefesh.setVisible(false);
		btnRemoveTitle.setVisible(false);
		btnSaveTitle.setVisible(true);
	}

	public void disableEditBookTitle() {
		for (JTextField textField : fields) {
			textField.setEnabled(false);
		}
		btnEditTitle.setVisible(true);
		btnRefesh.setVisible(true);
		btnRemoveTitle.setVisible(true);
		btnSaveTitle.setVisible(false);
	}

	public void notifiChoiceBookTitle() {
		JOptionPane.showMessageDialog(null, "Vui lòng chọn sách");
	}

	public void notifiEditBookTitleSuccess() {
		JOptionPane.showMessageDialog(null, "Đã sửa thành cônng");
	}

	public void notifiNotFoundTitle() {
		JOptionPane.showMessageDialog(null, "Không tìm thấy sách");
	}
}
