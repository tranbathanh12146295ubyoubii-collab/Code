package View;

import java.awt.Container;
import javax.swing.JFrame;

public class MainFrame extends JFrame {
	CardPanel cardPanel;

	public MainFrame(CardPanel cardPanel) {
		setCardPanel(cardPanel);
		setTitle("Library System Management");
		setSize(1200, 800);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setFocusable(true);
		setMaximizedBounds(getBounds());
		setResizable(false);

		Container win = getContentPane();
		win.add(cardPanel);
		setVisible(true);
	}

	public void setCardPanel(CardPanel cardPanel) {
		this.cardPanel = cardPanel;
	}
	public CardPanel getCardPanel() {
		return this.cardPanel;
	}
}
