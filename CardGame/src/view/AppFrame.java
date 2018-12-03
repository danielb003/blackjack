package view;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;

import model.interfaces.GameEngine;

public class AppFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	public Toolbar toolbar;
	public CardPanel cardpanel;
	public Summary summary;
	public StatusBar statusbar;
	
	public AppFrame(GameEngine gameEngine) {
		super("Card Game");
		setBounds(360,50,640,640);
		setSize(500,520);
		
		// create views
		toolbar = new Toolbar(gameEngine, this);
		cardpanel = new CardPanel(gameEngine, this);
		summary = new Summary(gameEngine, this);
		statusbar = new StatusBar(gameEngine, this);
		
		// set border
		cardpanel.setBorder(BorderFactory.createLoweredBevelBorder());
		
		// add sections
		add(toolbar, BorderLayout.NORTH);
		add(cardpanel, BorderLayout.CENTER);
		add(summary, BorderLayout.WEST);
		add(statusbar, BorderLayout.SOUTH);
		
		setMinimumSize(getSize());		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		gameEngine.setAppFrame(this);
		
	}

}
