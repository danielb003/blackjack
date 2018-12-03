package view.interfaces;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.PlayingCard;
import view.AppFrame;

public interface GameEngineCallbackGUI {

	public void nextCard(AppFrame appFrame, PlayingCard card);
	
	public void bustCard(AppFrame appFrame, PlayingCard card);
	
	public void result(AppFrame appFrame);
	
}
