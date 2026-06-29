package CommandService;

import View.CardPanelService;

public class ImplementReaderCommand implements CommandService {
	CardPanelService cardPanelService;

	public ImplementReaderCommand(CardPanelService cardPanelService) {
		this.cardPanelService = cardPanelService;
	}

	@Override
	public void excute() {
		cardPanelService.showReader();
	}
	
	
}
