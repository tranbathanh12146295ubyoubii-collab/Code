package Controller;

import Command.*;
import CommandService.*;
import View.*;

/**
 * set cardpanel, cardpanelService;
 */
public class ControllerProcessUI implements IControllerProcessUI {
	CardPanel cardPanel;
	CommandMain commandMain;

	CommandService commandService;
	CardPanelService cardPanelService;

	public ControllerProcessUI() {

	}

	@Override
	public void showLogin() {
		commandMain = new ImplementLogin(cardPanel);
		commandMain.excute();
	}

	@Override
	public void showMain() {
		commandMain = new ImplementMain(cardPanel);
		commandMain.excute();
	}

	@Override
	public void showHome() {
		commandService = new ImplementHomeCommand(cardPanelService);
		commandService.excute();
	}

	@Override
	public void showBook() {
		commandService = new ImplementBookCommand(cardPanelService);
		commandService.excute();
	}

	@Override
	public void showReader() {
		commandService = new ImplementReaderCommand(cardPanelService);
		commandService.excute();
	}

	@Override
	public void showLoan() {
		commandService = new ImplementLoanCommand(cardPanelService);
		commandService.excute();
	}

	public CardPanel getCardPanel() {
		return cardPanel;
	}

	public void setCardPanel(CardPanel cardPanel) {
		this.cardPanel = cardPanel;
	}

	public CardPanelService getCardPanelService() {
		return cardPanelService;
	}

	public void setCardPanelService(CardPanelService cardPanelService) {
		this.cardPanelService = cardPanelService;
	}

	public CommandMain getCommandMain() {
		return commandMain;
	}

	public void setCommandMain(CommandMain commandMain) {
		this.commandMain = commandMain;
	}

	public CommandService getCommandService() {
		return commandService;
	}

	public void setCommandService(CommandService commandService) {
		this.commandService = commandService;
	}

}
