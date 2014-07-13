package adi.blackjack;

import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * The Blackjack class provides the implementation for a game of blackjack. The
 * class contains implementation of methods to start a new game, set up a new
 * game and play a hand in the game.
 * 
 * @author Aditya
 * @version 1.0
 */
public class BlackJack {

	private static Scanner inputScanner;

	// Each game has a player and a dealer
	private Player player;
	private Dealer dealer;
	private float betChips;

	// Create a new BlackJack object. Create a player and a dealer
	public BlackJack() throws FileNotFoundException {
		player = new Player(Constants.STARTING_NUMBER_OF_CHIPS);
		dealer = new Dealer(Constants.NUM_DECKS);
		// dealer = new Dealer(true); // devMode ONLY!
	}

	// Play a new game. Display welcome message and get user to input bet value
	private void playGame(Player player, Dealer dealer) {
		String inputVal = "";
		System.out.println(Constants.WELCOME_MESSAGE);
		while (true) {
			if (player.getNumChips() >= Constants.MIN_BET_CHIPS) {
				System.out
						.println(Constants.BET_MESSAGE + player.getNumChips());
				inputVal = inputScanner.nextLine();
				if ((inputVal.length() == 1)
						&& (inputVal.charAt(0) == Constants.ACTION_KEY_QUIT))
					break;

				if (inputVal.equals("")) {
					betChips = 1;
				} else {
					try {
						betChips = Float.parseFloat(inputVal);
					} catch (NumberFormatException nfe) {
						System.out.println("Unrecognized number " + inputVal);
						continue;
					}
				}

				// invalid bet amount
				if (betChips < Constants.MIN_BET_CHIPS
						|| betChips > player.getNumChips()) {
					System.out.println("Invalid bet size: " + betChips);
					System.out.println("Bet must be between "
							+ Constants.MIN_BET_CHIPS + " and "
							+ player.getNumChips());
					continue;
				}
				System.out.println("Your bet: " + betChips + " chip(s)\n");
				newHand();
				playAllHands();
				calculateWinnings();
			} else {
				System.out
						.println("\n *** You do not have enough chips to play another round! ***\n");
				break;
			}

		}
		inputScanner.close();
	}

	// Create a new hand. This method is called before beginning a new round.
	private void newHand() {
		player.newHand();
		dealer.newHand();

		// deal 2 cards to the player
		player.getPlayerHand().get(0).addCard(dealer.dealCard());
		player.getPlayerHand().get(0).addCard(dealer.dealCard());

		// deal 2 cards to the dealer
		dealer.takeCard(dealer.dealCard());
		dealer.takeCard(dealer.dealCard());
	}

	/*
	 * Playing a hand. The game begins with the first hand of the player and
	 * continues till the player busts or stands/surrenders. If there is another
	 * hand the method processes each of them sequentially rather than in
	 * parallel.
	 */
	private void playAllHands() {
		int handsIndex = 0;

		// Start with hand 0 of the player;
		while (handsIndex < player.getPlayerHand().size()) {
			// while current hand is not finished keep looping
			while (!player.getPlayerHand().get(handsIndex).isHandFinished()) {
				Hand currentHand = player.getPlayerHand().get(handsIndex);
				String command = "";
				// Continue to display options until any one is available
				while (true) {
					// Display current player hand cards
					System.out.println("Your Cards: [Hand " + (handsIndex + 1)
							+ " of " + player.getPlayerHand().size() + "]");
					System.out.println(currentHand.showAllCards());

					// Show dealer's top card
					System.out.println("Dealer's Cards: ");
					System.out.println(dealer.getDealerHand().showTopCard());

					System.out.print("Choose an option: ");

					// Display all available options
					Collection<String> optionValues = getCurrentHandOptions(
							currentHand).values();
					for (Iterator<String> i = optionValues.iterator(); i
							.hasNext();) {
						System.out.print(i.next() + " ");
					}
					System.out.println();
					System.out.println();

					// read input
					command = inputScanner.nextLine();

					if (command.length() != 1) {
						continue;
					}

					Collection<Character> optionKeys = getCurrentHandOptions(
							currentHand).keySet();
					if (optionKeys.contains((Character) command.charAt(0))) {
						switch (command.charAt(0)) {
						case Constants.ACTION_KEY_HIT:
							currentHand.hit(dealer.dealCard());
							break;
						case Constants.ACTION_KEY_STAND:
							currentHand.stand();
							break;
						case Constants.ACTION_KEY_SURRENDER:
							currentHand.surrender();
							break;
						case Constants.ACTION_KEY_DOUBLE_DOWN:
							currentHand.doubleDown(player.getNumChips(),
									betChips);
							betChips = betChips * 2;
							break;
						case Constants.ACTION_KEY_SPLIT:
							player.split(player.getPlayerHand().get(handsIndex));
							currentHand.addCard(dealer.dealCard());
							player.getPlayerHand().get(handsIndex + 1)
									.addCard(dealer.dealCard());
							break;
						}
					} else {
						System.out.println("Invalid option selected!\n");
						continue;
					}
					break;
				}
			}
			handsIndex++;
			System.out.println("\nHand complete!\n");
		}
		// once all hands are finished, dealer hits until 17
		dealer.playoutHand();
	}

	private void calculateWinnings() {
		int k = 0;
		for (Iterator<Hand> i = player.getPlayerHand().iterator(); i.hasNext();) {
			Hand playerHand = i.next();
			float wonChips = getGameChips(playerHand, dealer.getDealerHand(),
					betChips);
			player.setNumChips(player.getNumChips() + wonChips);
			System.out.println("Your Cards: [Hand " + (++k) + " of "
					+ player.getPlayerHand().size() + "]");
			System.out.println(player.getPlayerHand().get(0).showAllCards());

			System.out.println("Dealer's Cards: ");
			System.out.println(dealer.getDealerHand().showAllCards());

			if (wonChips > 0) {
				System.out.println("You win " + wonChips + " chip(s)!\n\n");
			} else if (wonChips < 0) {
				System.out.println("You lose " + (-wonChips) + " chip(s)!\n\n");
			} else {
				System.out.println("Game tied!\n\n");
			}
		}
	}

	private float getGameChips(Hand playerHand, Hand dealerHand, float betChips) {
		// player surrendered and dealer does not have a blackjack
		if (playerHand.isHandSurrendered() && !dealerHand.isBlackJack())
			return -betChips / 2;

		// both player and dealer bust - player loses
		if (playerHand.isHandBust() && dealerHand.isHandBust())
			return -betChips;

		// player bust or value of player card < value of dealer cards
		if (playerHand.isHandBust()
				|| (!dealerHand.isHandBust() && playerHand.valueOfCards() < dealerHand
						.valueOfCards())) {
			return -betChips;
		}

		// dealer bust or value of player cards > value of dealer cards
		if (dealerHand.isHandBust()
				|| playerHand.valueOfCards() > dealerHand.valueOfCards()) {
			if (playerHand.isBlackJack()) // player has blackjack (Ace and
				// ten-value card)
				return 3 * betChips / 2;
			else
				return betChips;
		}

		if (playerHand.valueOfCards() == dealerHand.valueOfCards()) {
			if (playerHand.isBlackJack() && !dealerHand.isBlackJack())
				return 3 * betChips / 2; // player has blackjack and dealer
											// doesn't
			else
				return 0; // is a push
		}
		return 0;
	}

	private Map<Character, String> getCurrentHandOptions(Hand currentHand) {
		Map<Character, String> optionsMap = new TreeMap<Character, String>();
		if (currentHand.canHit())
			optionsMap.put(Constants.ACTION_KEY_HIT,
					Constants.ACTION_STRING_HIT);
		if (currentHand.canStand())
			optionsMap.put(Constants.ACTION_KEY_STAND,
					Constants.ACTION_STRING_STAND);
		if (currentHand.canSurrender())
			optionsMap.put(Constants.ACTION_KEY_SURRENDER,
					Constants.ACTION_STRING_SURRENDER);
		if (currentHand.canDoubleDown(player.getNumChips(), betChips))
			optionsMap.put(Constants.ACTION_KEY_DOUBLE_DOWN,
					Constants.ACTION_STRING_DOUBLE_DOWN);
		if (currentHand.canSplit(player.getNumChips(), betChips))
			optionsMap.put(Constants.ACTION_KEY_SPLIT,
					Constants.ACTION_STRING_SPLIT);
		return optionsMap;
	}

	public static void main(String args[]) throws FileNotFoundException {
		inputScanner = new Scanner(System.in);
		BlackJack blackjack = new BlackJack();
		blackjack.playGame(blackjack.player, blackjack.dealer);
		inputScanner.close();
		System.out.println(Constants.END_MESSAGE);
	}
}
