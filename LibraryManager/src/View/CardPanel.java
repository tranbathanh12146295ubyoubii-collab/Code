package View;

import java.awt.CardLayout;

import javax.swing.JPanel;

public class CardPanel extends JPanel {
	LoginPanel loginPanel;
	MainPanel mainPanel;

	public CardPanel(LoginPanel loginPanel, MainPanel mainPanel) {
		this.loginPanel = loginPanel;
		this.mainPanel = mainPanel;

		this.setLayout(new CardLayout());
		this.add(loginPanel, "Login");
		this.add(mainPanel, "Main");
	}

	public LoginPanel getLoginPanel() {
		return loginPanel;
	}

	public void setLoginPanel(LoginPanel loginPanel) {
		this.loginPanel = loginPanel;
	}

	public MainPanel getMainPanel() {
		return mainPanel;
	}

	public void setMainPanel(MainPanel mainPanel) {
		this.mainPanel = mainPanel;
	}

	public void showLogin() {
		((CardLayout) this.getLayout()).show(this, "Login");
	}

	public void showMain() {
		((CardLayout) this.getLayout()).show(this, "Main");
	}
}
