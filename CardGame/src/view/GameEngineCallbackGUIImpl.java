package view;

import model.interfaces.PlayingCard;
import view.interfaces.GameEngineCallbackGUI;

public class GameEngineCallbackGUIImpl implements GameEngineCallbackGUI {

	/*AppFrame appFrame;
	
	public GameEngineCallbackGUIImpl(AppFrame appFrame) {
		
		this.appFrame = appFrame;
		
	}*/
	
	@Override
	public void nextCard(AppFrame appFrame, PlayingCard card) {
		
		appFrame.statusbar.getStatus().setText("  Dealing");
		appFrame.cardpanel.addBetTotal(card.getScore());
		String nextCardFileString = card.getValue() + "_" + card.getSuit() + ".png";
		appFrame.cardpanel.changeCardImage(nextCardFileString);
		
	}

	@Override
	public void bustCard(AppFrame appFrame, PlayingCard card) {

		appFrame.statusbar.getStatus().setText("  Busted");
		// bust information
		
	}

	@Override
	public void result(AppFrame appFrame) {
		
		appFrame.statusbar.getStatus().setText("  Idle");
		appFrame.cardpanel.changeCardImage("back_red_card.jpg");
		
	}

}
