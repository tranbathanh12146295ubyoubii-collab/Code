package View;

import Controller.IController;
import ObserverUI.ObserverReader;
import ReaderModel.Reader;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class ReaderPanel extends JPanel implements ObserverReader {
	JPanel readerPanel; 
	JTextField textId, textName, textNumberPhone, textAddress, textSex, textCccd, textBirth;
	JLabel idLabel, nameLabel, numLabel, addressLabel, sexLabel, cccdLabel, birthLabel;
	JLabel labelCreateReader;
	JComboBox<String> comboGT;
	JTextField readerSearch;
	
	JCheckBox borrowing;

	JButton btnEdit, btnAdd, btnRemove, btnRefesh, btnSave;
	
	int stt = 1;



	// bảng danh sách độ giả
	DefaultTableModel model = new DefaultTableModel();
	JTable table = new JTable(model) {
		@Override
		public boolean isCellEditable(int row, int column) {
	        return false;
	    }
	};
	JScrollPane jScrollPane = new JScrollPane(table);
	

	Font fontLabel = new Font("Inter", Font.BOLD, 12);



	IController controller;

	int [] sizeColums = {40, 60, 200, 60, 130, 180, 180};
	

	public ReaderPanel() {
		setLayout(new BorderLayout());

		readerPanel = new JPanel(null);
		readerPanel.setSize(getPreferredSize());
		readerPanel.setBackground(Color.WHITE);


		// textField

		textId = new JTextField();
		textName = new JTextField();
		comboGT = new JComboBox<>();
		comboGT.addItem("Nam");
		comboGT.addItem("Nữ");
		textBirth = new JTextField();
		textAddress = new JTextField();
		textNumberPhone = new JTextField();
		textCccd = new JTextField();


		idLabel = new JLabel("Mã độc giả");
		nameLabel = new JLabel("Họ và tên");
		sexLabel = new JLabel("Giới tính");
		birthLabel = new JLabel("Ngày sinh");
		cccdLabel = new JLabel("CCCD");
		addressLabel = new JLabel("Địa chỉ");
		numLabel = new JLabel("Só điện thoại");


		idLabel.setBounds(30, 80, idLabel.getPreferredSize().width, idLabel.getPreferredSize().height);
		nameLabel.setBounds(30, 140, nameLabel.getPreferredSize().width, nameLabel.getPreferredSize().height);
		sexLabel.setBounds(30, 200, sexLabel.getPreferredSize().width, sexLabel.getPreferredSize().height);
		birthLabel.setBounds(30, 260, birthLabel.getPreferredSize().width, birthLabel.getPreferredSize().height);
		cccdLabel.setBounds(260, 80, cccdLabel.getPreferredSize().width, cccdLabel.getPreferredSize().height);
		addressLabel.setBounds(260, 140, addressLabel.getPreferredSize().width, addressLabel.getPreferredSize().height);
		numLabel.setBounds(260, 200, numLabel.getPreferredSize().width, numLabel.getPreferredSize().height);



		List<JLabel> labelManager = new ArrayList<>();
		labelManager.add(idLabel);
		labelManager.add(nameLabel);
		labelManager.add(sexLabel);
		labelManager.add(birthLabel);
		labelManager.add(cccdLabel);
		labelManager.add(addressLabel);
		labelManager.add(numLabel);
		labelManager.add(birthLabel);

		for (JLabel jLabel : labelManager) {
			jLabel.setFont(fontLabel);
			readerPanel.add(jLabel);
		}

		borrowing = new JCheckBox("Đang mượn");	
		borrowing.setBackground(Color.WHITE);
		borrowing.setBounds(470, 330, 130, 50);
		borrowing.addActionListener(e -> {
			controller.checkBorrowing();
		});


		textId.setBounds(30, 100, 200, 28);
		textName.setBounds(30, 160, 200, 28);
		comboGT.setBounds(30, 220, 200, 28);
		textBirth.setBounds(30, 280, 200, 28);

		textCccd.setBounds(260, 100, 200, 28);
		textAddress.setBounds(260, 160, 200, 28);
		textNumberPhone.setBounds(260, 220, 200, 28);

		List<JTextField> fields = new ArrayList<>();
		fields.add(textId);
		fields.add(textName);
		fields.add(textNumberPhone);
		fields.add(textCccd);
		fields.add(textAddress);
		fields.add(textBirth);
		
		textBirth.setText("YYYY-MM-DD");
		textBirth.setForeground(Color.GRAY);
		
		
		textBirth.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				super.focusGained(e);
				if (textBirth.getText().equals("YYYY-MM-DD")) {
					textBirth.setText("");
					textBirth.setForeground(Color.BLACK);
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				super.focusLost(e);
				if (textBirth.getText().isEmpty()) {
					textBirth.setText("YYYY-MM-DD");
					textBirth.setForeground(Color.GRAY);
				}
			}
		});


		labelCreateReader = new JLabel("TẠO ĐỘC GIẢ");
		labelCreateReader.setFont(new Font("Inter", Font.BOLD, 20));
		labelCreateReader.setBounds(30, 40, labelCreateReader.getPreferredSize().width, labelCreateReader.getPreferredSize().height);

		
		// add All
		for (JTextField jTextField : fields) {
			readerPanel.add(jTextField);
		}

		readerSearch = new JTextField();
		readerSearch.setBounds(30, 340, 290, 30);
		readerSearch.setFont(new Font("Inter", Font.PLAIN, 13));
		readerSearch.setBorder(BorderFactory.createCompoundBorder(readerSearch.getBorder(), BorderFactory.createEmptyBorder(0, 10, 0, 0)));
		readerSearch.setText("Tìm kiếm độc giả");
		readerSearch.setForeground(Color.GRAY);
		readerSearch.addFocusListener(new FocusAdapter() {
                    @Override
                    public void focusGained(FocusEvent e) {
                        super.focusGained(e);
						if (readerSearch.getText().equals("Tìm kiếm độc giả")) {
							readerSearch.setText("");
							readerSearch.setForeground(Color.BLACK);
						}
                    }
                    @Override
                    public void focusLost(FocusEvent e) {
                        super.focusLost(e);
						readerSearch.setText("Tìm kiếm độc giả");
						readerSearch.setForeground(Color.GRAY);
                    }
		});

		btnAdd = new JButton("THÊM");
		btnRemove = new JButton("XÓA");
		btnEdit = new JButton("SỬA");
		btnRefesh = new JButton("LÀM MỚI");
		btnSave = new JButton("LƯU");
	

		btnAdd.setBounds(260, 280, 60, 28);
		btnRemove.setBounds(330, 280, 60, 28);
		btnEdit.setBounds(400, 280, 60, 28);
		btnRefesh.setBounds(330, 340, 130, 29);
		btnSave.setBounds(btnEdit.getBounds());
		

		List<JButton> btnOperator = new ArrayList<>();
		btnOperator.add(btnAdd);
		btnOperator.add(btnRemove);
		btnOperator.add(btnEdit);
		btnOperator.add(btnRefesh);
		btnOperator.add(btnSave);
	

		for (JButton btn : btnOperator) {
			btn.setBackground(Color.getHSBColor(0.56f, 1, 1));
			btn.setForeground(Color.WHITE);
			btn.setFont(new Font("Inter", Font.BOLD, 9));
			btn.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    super.mouseEntered(e);
					btn.setBackground(Color.DARK_GRAY);
			   		btn.setForeground(Color.WHITE);
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    super.mouseExited(e);
					btn.setBackground(Color.getHSBColor(0.56f, 1, 1));
			   		btn.setForeground(Color.WHITE);
                }
			});
			readerPanel.add(btn);
		}
		
		btnSave.setVisible(false);
		
		
		

		btnAdd.addActionListener(e -> {
			controller.addReader();
		});
		
		btnRemove.addActionListener(e -> {
			controller.removeReader();
		});

		btnEdit.addActionListener(e -> {
			controller.editReader();
		});

		btnRefesh.addActionListener(e -> {
			controller.refeshReader();
		});
		
		btnSave.addActionListener(e -> {
			controller.exitEditReader();
		});
		

		readerSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				super.keyReleased(e);
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					controller.searchReader();
				}
			}
			@Override
			public void keyTyped(KeyEvent e) {
				super.keyTyped(e);
				controller.searchReader();
			}

		});

		List<String> listColumsName = Arrays.asList("STT", "ID", "Họ và tên", "Giới tính", "Ngày sinh", "Số CCCD", "Địa chỉ", "Số điện thoại");

		for (String ele : listColumsName) {
			model.addColumn(ele);
		}
		table.getTableHeader().setReorderingAllowed(false);
		for (int i = 0; i < sizeColums.length; i++) {
			
			table.getColumnModel().getColumn(i).setPreferredWidth(sizeColums[i]);
			table.getColumnModel().getColumn(i).setMinWidth(sizeColums[i]);
			table.getColumnModel().getColumn(i).setMaxWidth(sizeColums[i]);
		}
		
		

		jScrollPane.setBounds(0, 380, 1020, 279);
		
		
		readerPanel.add(borrowing);
		readerPanel.add(readerSearch);
		readerPanel.add(comboGT);
		readerPanel.add(labelCreateReader);
		readerPanel.add(jScrollPane);

		add(readerPanel, BorderLayout.CENTER);
	}

	public void setController(IController c) {
		this.controller = c;
	}
	public void addReaderOnTable(String id, String name, String sex, String birth, String cccd, String address, String numberPhone) {
		model.addRow(new Object[] {stt++ + "", id, name, sex, birth, cccd, address,numberPhone});
	}
	public String getIdReader() {
		return textId.getText();
	}
	public String getNameReader() {
		return textName.getText();
	}
	public LocalDate getBirth() {
		try {
			return LocalDate.parse(textBirth.getText());
		} catch (DateTimeParseException e) {
			return LocalDate.MIN;
		}
		
	}
	public String getSex() {
		return comboGT.getSelectedItem().toString();
	}
	public String getNumberPhone() {
		return textNumberPhone.getText();
	}
	public String getAddressReader() {
		return textAddress.getText();
	}
	public String getCccd() {
		return textCccd.getText();
	}


	public List<String> removeReader() {
		List<String> idReader = new ArrayList<>();
		for (int i = 0; i < model.getRowCount(); i++) {
			if (table.isRowSelected(i)) {
				idReader.add((String) model.getValueAt(i, 1));
			}
		}
		return idReader;
	}

	public void resetTable() {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		stt = 1;
	}

	public String getSearch() {
		return readerSearch.getText();
	}

	@Override
	public void updateDisplay(List<Reader> list) {
		resetTable();
		for (Reader reader : list) {
			addReaderOnTable(reader.getId(), reader.getName(), reader.getSex(), reader.getBirth().toString(), reader.getCccd(), reader.getAddress(), reader.getNumberPhone());
		}
	}

	// thong bao 
	public void notifiNotEnoughInformation() {
		JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin");
	}

	public void notifiNotCreateReaderById() {
		JOptionPane.showMessageDialog(null, "ID đã tồn tại! Không thể tạo độc giả");
	}

	public void notifiNotCreateReaderByCccd() {
		JOptionPane.showMessageDialog(null, "Chứng minh thư đã tồn tại! Không thể tạo độc giả");
	}

	public void notifiNotCreateReaderByNumberPhone() {
		JOptionPane.showMessageDialog(null, "Số điện thoại đã tồn tại! Không thể tạo độc giả");
	}


	public void notifilAddReaderSuccess() {
		JOptionPane.showMessageDialog(null, "Đã thêm thành công!");
	}
	public void notifiExistedReader() {
		JOptionPane.showMessageDialog(null, "Tài khoản đã tồn tại!");
	}

	public void notifiRemoveReaderSuccess() {
		JOptionPane.showMessageDialog(null, "Đã xóa thành công!");
	}
	
	public void notifiFaildRemove() {
		JOptionPane.showMessageDialog(null, "Không thể xóa, độc giả đang trong thời gian mượn sách");
	}
	
	public void notifiNotSelectRowReader() {
		JOptionPane.showMessageDialog(null, "Vui lòng chọn độc giả muốn xóa!");
	}
	
	public void notifiUpdateReaderSuccess() {
		JOptionPane.showMessageDialog(null, "Đã cập nhật thành công");
	}
	
	public void notifiChoiceReader() {
		JOptionPane.showMessageDialog(null, "Vui lòng chọn độc giả");
	}


	// lay du lieu ban
	
	// dua su lieu le
	public void setIdReader(String idReader) {
		textId.setText(idReader);
	}
	
	public void setNameReader(String readerName) {
		textName.setText(readerName);
	}
	
	public void setBirthReader(String birth) {
		textBirth.setText(birth);
	}
	
	public void setSexReader(String sex) {
		if (sex.equals("Nam")) {
			comboGT.setSelectedIndex(0);
		} else {
			comboGT.setSelectedIndex(1);
		}
	}
	
	public void setCccdReader(String cccd) {
		textCccd.setText(cccd);
	}
	
	public void setAddressReader(String address) {
		textAddress.setText(address);
	}
	
	public void setNumberPhone(String num) {
		textNumberPhone.setText(num);
	}
	
	public void editReader() {
		btnAdd.setVisible(false);
		btnRemove.setVisible(false);
		textId.setEditable(false);
		btnEdit.setVisible(false);
		table.setEnabled(false);
		btnSave.setVisible(true);
		textBirth.setForeground(Color.BLACK);
		btnRefesh.setVisible(false);
		readerSearch.setEnabled(false);
	}	
	public void exitEditReader() {
		btnSave.setVisible(false);
		btnAdd.setVisible(true);
		btnRemove.setVisible(true);
		btnEdit.setVisible(true);
		textId.setEditable(true);
		clearTextFieldReader();
		table.setEnabled(true);
		btnRefesh.setVisible(true);
		readerSearch.setEnabled(true);
	}
	public void clearTextFieldReader() {
		textId.setText("");
		textName.setText("");
		textBirth.setText("YYYY-MM-DD");
		textBirth.setForeground(Color.GRAY);
		textCccd.setText("");
		textAddress.setText("");
		textNumberPhone.setText("");
	}
	public DefaultTableModel getDefaultTableModel() {
		return model;
	}
	public int getSelectTable() {
		return table.getSelectedRow();
	}
	public String getRowTableReader() {
		return (String) model.getValueAt(table.getSelectedRow(), 1);
	}
	public JTable getTable() {
		return table;
	}	
	public boolean checkBorrowing() {
		return borrowing.isSelected();
	}
	
	
}
