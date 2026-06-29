package Command;

import View.CardPanel;

public class ImplementLogin implements CommandMain {
	CardPanel cardPanel;

	public ImplementLogin(CardPanel cardPanel) {
		this.cardPanel = cardPanel;
	}

	@Override
	public void excute() {
		cardPanel.showLogin();
	}
	
	
}
