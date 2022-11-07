package tests;

import static org.junit.Assert.*;
import org.junit.jupiter.api.*;
import java.util.*;
import clueGame.*;

public class GameSolutionTest {
	private static Board board;
	private static Solution solution;
	private static Player player;
	private static Card livingroom, bedroom, kitchen, garden, diningroom, atrium, bathroom, office, pool,
	alCapone, kenichiShinoda, pabloEscobar, eddieMcGrath, benjaminSiegel, matteoDenaro,
	pistol, poison, katana, throwingStars, wire, golfClub;

	@BeforeAll
	public static void setUp() {
		// Board is singleton, get the only instance
		board = Board.getInstance();
		// set the file names to use my config files
		board.setConfigFiles("ClueLayout.csv", "ClueSetup.txt");		
		// Initialize will load config files 
		board.initialize();
		
		//player
		player = new HumanPlayer("Al Capone", "cyan", 23, 10);
		
		//room cards
		livingroom = new Card("Living Room", CardType.ROOM);
		bedroom = new Card("Bedroom", CardType.ROOM);
		kitchen = new Card("Kitchen", CardType.ROOM);
		garden = new Card("Garden", CardType.ROOM);
		diningroom = new Card("Dining Room", CardType.ROOM);
		atrium = new Card("Atrium", CardType.ROOM);
		bathroom = new Card("Bathroom", CardType.ROOM);
		office = new Card("Office", CardType.ROOM);
		pool = new Card("Pool", CardType.ROOM);
		
		//person cards
		alCapone = new Card("Al Capone", CardType.PERSON);
		kenichiShinoda = new Card("Kenichi Shinoda", CardType.PERSON);
		pabloEscobar = new Card("Pablo Escobar", CardType.PERSON);
		eddieMcGrath = new Card("Eddie McGrath", CardType.PERSON);
		benjaminSiegel = new Card("Benjamin Siegel", CardType.PERSON);
		matteoDenaro = new Card("Matteo Denaro", CardType.PERSON);
		
		//weapon cards
		pistol = new Card("Pistol", CardType.WEAPON);
		poison = new Card("Poison", CardType.WEAPON);
		katana = new Card("Katana", CardType.WEAPON);
		throwingStars = new Card("Throwing Stars", CardType.WEAPON);
		wire = new Card("Wire", CardType.WEAPON);
		golfClub = new Card("Golf Club", CardType.WEAPON);

		//solution
		solution = new Solution(pool, pabloEscobar, wire);
		
	}
	
	@Test
	public void checkAccusation() {
		player.hand.clear();
		player.updateHand(pool);
		player.updateHand(pabloEscobar);
		player.updateHand(wire);
		assertTrue(board.checkAccusation(solution, pool, pabloEscobar, wire));
	}
	
	@Test
	public void disproveSuggestion() {
		//tests person suggestion
		player.hand.clear();
		player.updateHand(garden);
		player.updateHand(pabloEscobar);
		player.updateHand(katana);
		assertEquals(solution.getPerson(), player.disproveSuggestion(pool, pabloEscobar, wire));
		
		//tests room suggestion
		player.hand.clear();
		player.updateHand(garden);
		player.updateHand(pool);
		player.updateHand(katana);
		assertEquals(solution.getRoom(), player.disproveSuggestion(pool, pabloEscobar, wire));
		
		//tests weapon suggestion
		player.hand.clear();
		player.updateHand(garden);
		player.updateHand(wire);
		player.updateHand(katana);
		assertEquals(solution.getWeapon(), player.disproveSuggestion(pool, pabloEscobar, wire));
		
		//test null
		player.hand.clear();
		player.updateHand(garden);
		player.updateHand(alCapone);
		player.updateHand(katana);
		assertEquals(null, player.disproveSuggestion(pool, pabloEscobar, wire));
		
		//test multiple suggestions
		player.hand.clear();
		player.updateHand(garden);
		player.updateHand(pabloEscobar);
		player.updateHand(pool);
		assertTrue(player.hand.contains(player.disproveSuggestion(pool, pabloEscobar, wire)));
	}
	
	@Test
	public void handleSuggestion() {
		//tests person suggestion
		player.hand.clear();
		player.updateHand(garden);
		player.updateHand(pabloEscobar);
		player.updateHand(katana);
		assertEquals(solution.getPerson(), board.handleSuggestion(player, pool, pabloEscobar, wire));
		
		//tests room suggestion
		player.hand.clear();
		player.updateHand(garden);
		player.updateHand(pool);
		player.updateHand(katana);
		assertEquals(solution.getRoom(), board.handleSuggestion(player, pool, pabloEscobar, wire));
		
		//tests weapon suggestion
		player.hand.clear();
		player.updateHand(garden);
		player.updateHand(wire);
		player.updateHand(katana);
		assertEquals(solution.getWeapon(), board.handleSuggestion(player, pool, pabloEscobar, wire));
	}
}
