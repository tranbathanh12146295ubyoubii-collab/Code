package CommandService;

import View.CardPanelService;

public class ImplementHomeCommand implements CommandService {
    CardPanelService cardPanelService;

    public ImplementHomeCommand(CardPanelService cardPanelService) {
        this.cardPanelService = cardPanelService;
    }

    @Override
    public void excute() {
        cardPanelService.showHome();
    }
}
