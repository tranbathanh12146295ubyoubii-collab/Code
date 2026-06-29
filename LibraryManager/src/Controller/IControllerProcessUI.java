package Controller;

import View.CardPanel;
import View.CardPanelService;

public interface IControllerProcessUI {
	public void showLogin();

	public void showMain();

	public void showHome();

	public void showBook();

	public void showReader();

	public void showLoan();

	public void setCardPanel(CardPanel cardPanel);

	public void setCardPanelService(CardPanelService cardPanelService);
}
