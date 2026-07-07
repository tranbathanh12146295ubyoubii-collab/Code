package View;

import java.awt.CardLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CardPanel extends JPanel {
	AnalogClock analogClock;
	DigitalClock digitalClock;
	public CardPanel(AnalogClock analogClock, DigitalClock digitalClock) {
		this.analogClock = analogClock;
		this.digitalClock = digitalClock;
		
		setLayout(new CardLayout());
		this.add(analogClock, "Analog");
		this.add(digitalClock, "Digital", JLabel.CENTER);
	}
	
	public AnalogClock getAnalogClock() {
		return analogClock;
	}

	public void setAnalogClock(AnalogClock analogClock) {
		this.analogClock = analogClock;
	}

	public DigitalClock getDigitalClock() {
		return digitalClock;
	}

	public void setDigitalClock(DigitalClock digitalClock) {
		this.digitalClock = digitalClock;
	}
	// 
	public void showPanelByName(String name) {
		CardLayout cardLayout = (CardLayout) getLayout();
		cardLayout.show(this, name);
	}
}
