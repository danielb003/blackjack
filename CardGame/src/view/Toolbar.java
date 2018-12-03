package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JToolBar;

import controller.AddPlayer;
import model.interfaces.GameEngine;
import model.interfaces.Player;

public class Toolbar extends JToolBar implements ActionListener {

	private static final long serialVersionUID = 1L;
	Player[] playerList = new Player[6];
	private JButton exit, addPlayer;
	public JComboBox<Player> playerSelect;
	private JLabel emptyLabel;
	private AppFrame appFrame;
	private int newId = 0, playerCount = 0;

	public Toolbar(GameEngine gameEngine, AppFrame appFrame) {

		this.appFrame = appFrame;

		// set layout
		setLayout(new FlowLayout(FlowLayout.CENTER, 10, 3));
		setFloatable(false);

		// create components
		exit = new JButton("    Exit    ");
		addPlayer = new JButton("  Add Player  ");
		emptyLabel = new JLabel("                                                                       ");
		playerSelect = new JComboBox<Player>(playerList);
		playerSelect.removeAllItems();
		playerSelect.setPreferredSize(new Dimension(85, 20));

		add(exit);
		add(addPlayer);
		add(emptyLabel);
		add(playerSelect);

		// action listeners for exit and add player
		exit.addActionListener(this);
		addPlayer.addActionListener(new AddPlayer(gameEngine, this));

	}

	public void addNameDropdown(Player player) {

		playerSelect.addItem(player);

	}

	public void actionPerformed(ActionEvent e) {

		appFrame.dispose();

	}

	public Player getPlayerSelect() {

		Player selectedPlayer = (Player) playerSelect.getSelectedItem();

		return selectedPlayer;
	
	}

	public int getNewId() {

		return newId;

	}
	
	public void increasePlayerCount() {
		
		this.playerCount++;
		
	}
	
	public int getPlayerCount() {
		
		return this.playerCount;
		
	}

}
