package CommandService;

import View.CardPanelService;

public class ImplementBookCommand implements CommandService {
	CardPanelService cardPanelService;

	public ImplementBookCommand(CardPanelService cardPanelService) {
		this.cardPanelService = cardPanelService;
	}

	@Override
	public void excute() {
		cardPanelService.showBook();
	}
	
}
