package Command;

import View.CardPanel;

public class ImplementMain implements CommandMain {
	CardPanel cardPanel;

	public ImplementMain(CardPanel cardPanel) {
		this.cardPanel = cardPanel;
	}

	@Override
	public void excute() {
		cardPanel.showMain();
	}
	
	
}
