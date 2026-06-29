package CommandService;

import View.CardPanelService;

public class ImplementLoanCommand implements CommandService {
	CardPanelService cardPanelService;

	public ImplementLoanCommand(CardPanelService cardPanelService) {
		this.cardPanelService = cardPanelService;
	}

	@Override
	public void excute() {
		cardPanelService.showLoan();
	}
	
}
