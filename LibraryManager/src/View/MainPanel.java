package View;

import Controller.IController;
import Controller.IControllerProcessUI;
import ObserverUI.ObserverLogin;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class MainPanel extends JPanel implements ObserverLogin {
	JPanel main, panelTacvu, panelStatus, panelDanhMuc, panelNghiepVu;
	JButton btnBook, btnReader, btnLoan, btnUser, btnAuthor, 
	btnHomePage;
	Font searchFont, titleFont;
	JLabel accountJLabel, titleDanhMuc, titleNghiepVu, timeNote;
	JLabel labelBook, labelReader, labelLoan, labelUser, labelHomePage;
	JPanel panelFrame, cardPanelDanhMuc, panelBook;
	JLabel labelLogout;
	JLabel labelUsername;
	
	
	
	IControllerProcessUI controllerProcessUI;	
	CardPanelService cardPanelService;
	IController controller;
	
	public MainPanel(IControllerProcessUI controllerProcessUI, CardPanelService cardPanelService) {	
		// set panel nền
		setLayout(new BorderLayout());
		main = new JPanel();
		main.setLayout(null);
		main.setBackground(Color.WHITE);
		
		this.controllerProcessUI = controllerProcessUI;
		this.cardPanelService = cardPanelService;
		
		
		// button nằm ở hàng ngang trên 
		btnHomePage = new JButton("Trang chủ");
		btnBook = new JButton("Sách");
		btnReader = new JButton("Độc giả");
		btnLoan = new JButton("Phiếu");
		btnAuthor = new JButton("Tác giả");
		
		
		
		
		// nhóm các nút lại 
		List<JButton> buttons = new ArrayList<>();
		buttons.add(btnHomePage);
		buttons.add(btnBook);
		buttons.add(btnReader);
		buttons.add(btnLoan);
		
		
		
		

		
		// set hiệu ứng nút
		for (JButton b : buttons) {
			b.setFont(new Font("Inter", Font.BOLD, 11));
			b.setBackground(Color.WHITE);
			b.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					super.mouseEntered(e);
					b.setBackground(Color.getHSBColor(0.56f, 1, 0.7f));
					b.setForeground(Color.WHITE);
				}

				@Override
				public void mouseExited(MouseEvent e) {
					super.mouseExited(e);
					b.setBackground(Color.WHITE);
					b.setForeground(Color.BLACK);
				}
			});
		}
		
		
		// command pattern
		btnHomePage.addActionListener(e -> {
			controllerProcessUI.showHome();
		});

		btnBook.addActionListener(e -> {
			controllerProcessUI.showBook();
		});

		btnReader.addActionListener(e -> {
			controllerProcessUI.showReader();
		});

		btnLoan.addActionListener(e -> {
			controllerProcessUI.showLoan();
		});



		
		
		
		// set Panel
		panelTacvu = new JPanel(new GridLayout(1, buttons.size()));
		panelTacvu.setBounds(0, 0, 700, 75);
		
		
		
		
		// thêm các nút vào panel tác vụ
		for (JButton b : buttons) {
			panelTacvu.add(b);
		}

		
		
		
		
		// set các tiêu đề Lớn (Danh mục, Nghiệp vụ)
		// Danh mục
		titleFont = new Font("Inter", Font.BOLD, 13);
		titleDanhMuc = new JLabel("📖 DANH MỤC");
		titleDanhMuc.setFont(titleFont);
		titleDanhMuc.setForeground(Color.WHITE);
		titleDanhMuc.setBounds(30, 90, titleDanhMuc.getPreferredSize().width, titleDanhMuc.getPreferredSize().height);

		
		// Nghiệp vụ
		titleNghiepVu = new JLabel("⚖ NGHIỆP VỤ");
		titleNghiepVu.setFont(titleFont);
		titleNghiepVu.setForeground(Color.WHITE);
		titleNghiepVu.setBounds(30, 220, titleNghiepVu.getPreferredSize().width,
				titleNghiepVu.getPreferredSize().height);
		
		
		
		
		// Thời gian làmm việc Display
		panelStatus = new JPanel();
		timeNote = new JLabel("Ngày làm việc: ");
		panelStatus.add(timeNote);
		panelStatus.setBounds(0, 732, 1200, 30);
		panelStatus.setBorder(BorderFactory.createEtchedBorder());

		
		// user
		btnUser = new JButton("👪");
		btnUser.setActionCommand("User");
		btnUser.addActionListener(e -> {
			// TODO

		});
		btnUser.setBounds(1100, 10, 60, 60);
		btnUser.setBackground(Color.WHITE);
		btnUser.setToolTipText("Người dùng");
		
		
		
		
		// dải tiêu đề danh mục
		labelHomePage = new JLabel("Trang chủ");
		labelBook = new JLabel("Sách");
		labelReader = new JLabel("Độc giả");
		

		
		
		// nhóm các danh mục lại
		List<JLabel> danhMucs = new ArrayList<>();
		danhMucs.add(labelHomePage);
		danhMucs.add(labelBook);
		danhMucs.add(labelReader);
		
		
		
		// listener danh muc
		labelHomePage.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				controllerProcessUI.showHome();
			}
		});	

		labelBook.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				controllerProcessUI.showBook();
			}
		});	
		labelReader.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				controllerProcessUI.showReader();
			}
		});
		
		
		
		// set Vị trí danh mục và hiệu ứng 
		int i = 120;
		Font fontDanhMuc = new Font("Inter", Font.BOLD, 12);
		for (JLabel jLabel : danhMucs) {
			jLabel.setFont(fontDanhMuc);
			jLabel.setForeground(Color.WHITE);
			jLabel.setBounds(30, i, jLabel.getPreferredSize().width, jLabel.getPreferredSize().height);
			String s = jLabel.getText();

			jLabel.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					super.mouseEntered(e);
					jLabel.setForeground(Color.CYAN);
					jLabel.setText("<html><u>" + s + "</u></html>");
				}
				@Override
				public void mouseExited(MouseEvent e) {
					super.mouseExited(e);
					jLabel.setForeground(Color.WHITE);
					jLabel.setText(s);
				}
			});
			main.add(jLabel);
			i += 30;
		}
		
		
		
		
		
		// set dải tiêu đề nghiệp vụ
		labelLoan = new JLabel("Phiếu");
		
		
		// nhóm lại
		List<JLabel> nghiepVus = new ArrayList<>();
		nghiepVus.add(labelLoan);
		
		
		
		// listener nghiep vu
		labelLoan.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				controllerProcessUI.showLoan();
			}
		});
		
		
		// set vị trí và hiệu ứng nhóm nghiệp vụ 
		int j = 250;
		for (JLabel jLabel : nghiepVus) {
			jLabel.setForeground(Color.WHITE);
			jLabel.setBounds(30, j, jLabel.getPreferredSize().width, jLabel.getPreferredSize().height);
			jLabel.setFont(fontDanhMuc);
			String s = jLabel.getText();
			jLabel.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					super.mouseEntered(e);
					jLabel.setForeground(Color.CYAN);
					jLabel.setText("<html><u>" + s + "</u></html>");
				}
				@Override
				public void mouseExited(MouseEvent e) {
					super.mouseExited(e);
					jLabel.setForeground(Color.WHITE);
					jLabel.setText(s);
				}
			});
			main.add(jLabel);
			j += 30;
		}

		
		labelUsername = new JLabel("Admin: ");
		panelStatus.add(labelUsername);


		
		// set màn hình chính 
		panelFrame = new JPanel(new CardLayout());
		setPanel(panelFrame, 180, 75, 1020, 657, Color.WHITE);
		cardPanelDanhMuc = new JPanel(new CardLayout());

		
		
		panelFrame.add(this.cardPanelService);
		
		
		
		
		labelLogout = new JLabel("ĐĂNG XUẤT 🚪");
		labelLogout.setFont(new Font("Inter", Font.BOLD, 13));
		labelLogout.setBounds(30, 680, labelLogout.getPreferredSize().width, labelLogout.getPreferredSize().height);
		labelLogout.setForeground(Color.WHITE);
		labelLogout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				super.mouseEntered(e);
				labelLogout.setForeground(Color.YELLOW);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				super.mouseExited(e);
				labelLogout.setForeground(Color.WHITE);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				// TODO
				controller.logout();
			}
		});
		
		
		
		// thêm tất cả vào panel chính
		// main.add(btnUser);
		main.add(titleDanhMuc);
		main.add(titleNghiepVu);
		main.add(panelTacvu);
		main.add(panelStatus);
		main.add(panelFrame);
		main.add(labelLogout);
		
		
		
		// lên màu
		float l = 0;
		for (int c = 0; c < 180; c++) {
			JPanel jPanel = new JPanel();
			jPanel.setBounds(c, 0, 1, 800);
			jPanel.setBackground(Color.getHSBColor(0.56f, 1, l));
			main.add(jPanel);
			l += 0.005;
		}
		float t = 0;
		for (int s = 0; s <= 75; s++) {
			JPanel jPanel = new JPanel();
			jPanel.setBounds(700, s, 500, 1);
			jPanel.setBackground(Color.getHSBColor(0.56f, 1, t));
			main.add(jPanel);
			t += 0.013;
		}
		// thêm tất cả vào
		add(main, BorderLayout.CENTER);
	}
	public void displayTime(LocalDateTime d) {
		int second = d.getSecond();
		String s = second + "";
		if (second < 10) {
			s = "0" + second;
		}
		timeNote.setText("Ngày làm việc: " +  d.getDayOfWeek() + " | " +  d.getDayOfMonth() + "/" + d.getMonthValue() + "/" + d.getYear() + " | " + d.getHour() + ":" + d.getMinute() + ":" + s);
	}
	
	
	public void setController(IController controller) {
		this.controller = controller;
		
	}
	
	public void setControllerProcessUI(IControllerProcessUI controllerProcessUI) {
		this.controllerProcessUI = controllerProcessUI;
	}
	
	public void setCardPanelService(CardPanelService cardPanelService) {
		this.cardPanelService = cardPanelService;
	}
	
	private void setPanel(JPanel panel, int x, int y, int w, int h, Color color) {
		panel.setBounds(x, y, w, h);
		panel.setBackground(color);
	}

	

	public void setAdmin(String admin) {
		labelUsername.setText("Admin: " + admin);
	}


	public CardPanelService getCardPanelService() {
		return this.cardPanelService;
	}
	@Override
	public void login(String username) {
		// TODO Auto-generated method stub
		setAdmin(username);
	}
	@Override
	public void logout() {
		// TODO Auto-generated method stub
		setAdmin("");
	}


}
