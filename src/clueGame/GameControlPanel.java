package clueGame;

import javax.swing.*;

public class GameControlPanel extends JPanel {

	/**
	 * Constructor for the panel, it does 90% of the work
	 */
	public GameControlPanel()  {
		
	}
	
	private void setGuessResult(String string) {
		
		
	}

	private void setGuess(String string) {
		
		
	}

	private void setTurn(ComputerPlayer computerPlayer, int i) {
		
		
	}
	
	/**
	 * Main to test the panel
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		GameControlPanel panel = new GameControlPanel();  // create the panel
		JFrame frame = new JFrame();  // create the frame 
		frame.setContentPane(panel); // put the panel in the frame
		frame.setSize(750, 180);  // size the frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // allow it to close
		frame.setVisible(true); // make it visible
		
		// test filling in the data
		panel.setTurn(new ComputerPlayer( "Col. Mustard", "orange", 0, 0), 5);
		panel.setGuess( "I have no guess!");
		panel.setGuessResult( "So you have nothing?");
	}
}
