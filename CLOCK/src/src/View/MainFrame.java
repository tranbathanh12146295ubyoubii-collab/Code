
package View;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Model.Clock;

public class MainFrame extends JFrame {
	CardPanel cardPanel;
	JPanel topPanel, bottomPanel;
	JButton btnAnalog, btnDigital, btnExit;
	public MainFrame(CardPanel cardPanel) {
		this.cardPanel = cardPanel;
		setTitle("CLOCK");
		setSize(300, 310);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setFocusable(true);
		
		
		topPanel = new JPanel();
		bottomPanel = new JPanel();
		
		btnAnalog = new JButton("Analog");
		btnDigital = new JButton("Digital");
		
		btnAnalog.addActionListener(e -> cardPanel.showPanelByName(btnAnalog.getText()));
		btnDigital.addActionListener(e -> cardPanel.showPanelByName(btnDigital.getText()));
		
		topPanel.add(btnAnalog);
		topPanel.add(btnDigital);
		
		btnExit = new JButton("Exit");
		btnExit.addActionListener(e -> System.exit(0));
		
		bottomPanel.add(btnExit);
		
		Container win = getContentPane();
		win.add(topPanel, BorderLayout.NORTH);
		win.add(cardPanel, BorderLayout.CENTER);
		win.add(bottomPanel, BorderLayout.SOUTH);
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		AnalogClock analogClock = new AnalogClock();
		DigitalClock digitalClock = new DigitalClock();
		CardPanel cardPanel = new CardPanel(analogClock, digitalClock);

		Clock clock = new Clock();
		clock.registerObserver(analogClock);
		clock.registerObserver(digitalClock);

		MainFrame mainFrame = new MainFrame(cardPanel);
	}
}
