package View;

import Controller.IController;
import Controller.IControllerProcessUI;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class LoginPanel extends JPanel {
	JLabel labelLogin, labelNoteLogin;
	JTextField tfAcc, tfPass;
	JButton btnLogin;
	Font loginFont, borFont;
	JPanel login;
	
	IControllerProcessUI controllerProcessUI;
	IController controller;
	
	public LoginPanel(IControllerProcessUI controllerProcessUI) {
		// set Layout, Panel nền
		setLayout(new BorderLayout());
		login = new JPanel();
		login.setLayout(null);
		login.setSize(getPreferredSize());
		
		this.controllerProcessUI = controllerProcessUI;
		
		
		
		// Tiêu đề lớn
		JLabel titleLabel = new JLabel("HỆ THỐNG QUẢN LÝ THƯ VIỆN");
		JLabel borderText = new JLabel("_______________Đăng nhập_______________");
		
		
		
		
		
		
		
		
		// set Thuộc tính tiêu đề lớn
		titleLabel.setFont(new Font("Inter", Font.BOLD, 40));		
		titleLabel.setBounds((1200 - titleLabel.getPreferredSize().width) / 2, 150, titleLabel.getPreferredSize().width, titleLabel.getPreferredSize().height);
		titleLabel.setForeground(Color.WHITE);
		
		
		
		
		
		
		
		
		
		
		// tiêu đề đăng nhập
		borderText.setFont(new Font("Inter", Font.PLAIN, 13));
		borderText.setForeground(Color.WHITE);
		borderText.setBounds((1200 - borderText.getPreferredSize().width) / 2, 270, borderText.getPreferredSize().width, borderText.getPreferredSize().height);
		
		
		
		
		
		
		
		
		// font text đăng nhập	
		loginFont = new Font("Inter", Font.PLAIN, 13);
		
		
		
		
		
		
		
		
		
		
		
		// set Hiệu ứng nút đăng nhập, thuộc tính textField
		tfAcc = new JTextField();
		tfPass = new JTextField();
		btnLogin = new JButton("ĐĂNG NHẬP");
		btnLogin.setActionCommand("Main");
		btnLogin.setBackground(Color.WHITE);
		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				super.mouseEntered(e);
				btnLogin.setBackground(Color.getHSBColor(0.56f, 1, 1f));
				btnLogin.setForeground(Color.WHITE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				super.mouseExited(e);
				btnLogin.setBackground(Color.WHITE);
				btnLogin.setForeground(Color.BLACK);
			}			
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				// TODO
				controller.login();
			}
		});

		
		
		
		
		
		
		// thông báo đăng nhập
		labelNoteLogin = new JLabel("*Tài khoản hoặc mật khẩu không chính xác!");
		labelNoteLogin.setForeground(Color.RED);
		labelNoteLogin.setFont(new Font("Inter", Font.BOLD, 13));
		labelNoteLogin.setBounds(300, 420, labelNoteLogin.getPreferredSize().width, labelNoteLogin.getPreferredSize().height);
		labelNoteLogin.setVisible(false);
		
		
		
		
		
		
		
		
		
		// set thuộc tính 2 textField đăng nhập
		tfAcc.setBounds(300, 300, 600, 50);
		tfPass.setBounds(300, 360, 600, 50);
		btnLogin.setBounds((1200 - 120) / 2, 450, 120, 40);
		//
		tfAcc.setFont(loginFont);
		tfPass.setFont(loginFont);
		//
		tfAcc.setText("Nhập ID");
		tfAcc.setForeground(Color.LIGHT_GRAY);
		//
		tfPass.setText("Mật khẩu");
		tfPass.setForeground(Color.LIGHT_GRAY);
		//
		tfAcc.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				super.focusGained(e); 
				labelNoteLogin.setVisible(false);
				if (tfAcc.getText().equals("Nhập ID")) {
					tfAcc.setText("");
					tfAcc.setForeground(Color.BLACK);
				}
			}		
			@Override
			public void focusLost(FocusEvent e) {
				super.focusLost(e);
				labelNoteLogin.setVisible(false);
				if (tfAcc.getText().isEmpty()) {
					tfAcc.setText("Nhập ID");
					tfAcc.setForeground(Color.LIGHT_GRAY);
				}	 
			}	
		});
		tfPass.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				super.focusGained(e); 
				if (tfPass.getText().equals("Mật khẩu")) {
					tfPass.setText("");
					tfPass.setForeground(Color.BLACK);
				}
			}
			
			@Override
			public void focusLost(FocusEvent e) {
				super.focusLost(e);
				if (tfPass.getText().isEmpty()) {
					tfPass.setText("Mật khẩu");
					tfPass.setForeground(Color.LIGHT_GRAY);
				}
			}
		});
		
		tfAcc.setBorder(BorderFactory.createCompoundBorder(tfAcc.getBorder(), BorderFactory.createEmptyBorder(0, 15, 0, 0)));
		tfPass.setBorder(BorderFactory.createCompoundBorder(tfPass.getBorder(), BorderFactory.createEmptyBorder(0, 15, 0, 0)));
		
		
		
		
		
		
		
		// add component vào panel nền
		login.add(titleLabel);
		login.add(borderText);
		login.add(labelNoteLogin);
		login.add(tfAcc);
		login.add(tfPass);
		login.add(btnLogin);
		
		
		
		
		//lên màu 
		int c = 0;
		float color = 0f;
		for (int i = 0; i < 135; i++) {			
			JPanel p1 = new JPanel();
			p1.setBackground(Color.getHSBColor(0.56f, 1, color));
			p1.setBounds(0, c, 1200, 3);
			JPanel p2 = new JPanel();
			p2.setBackground(Color.getHSBColor(0.56f, 1, color));
			p2.setBounds(0, 800 - c, 1200, 3);
			login.add(p1);
			login.add(p2);
			c += 3;
			color += 0.0074;
		}	
		
	
		// add panel vao pane chính
		add(login, BorderLayout.CENTER);	
	}
	
	public void setControllerProcessUI(IControllerProcessUI controllerProcessUI) {
		this.controllerProcessUI = controllerProcessUI;
	}
	
	
	public IController getController() {
		return controller;
	}

	public void setController(IController controller) {
		this.controller = controller;
	}

	// login
	public String getIdAccount() {
		return tfAcc.getText();
	}
	
	
	public String getPasscode() {
		return tfPass.getText();
	}
	
	//
	
	public void resetLog() {
		tfAcc.setText("");
		tfPass.setText("");
	}

	// login faild
	public void failLogin() {
		labelNoteLogin.setVisible(true);
	}

	public void loginSucess() {
		resetLog();
		labelNoteLogin.setVisible(false);
	}
}
