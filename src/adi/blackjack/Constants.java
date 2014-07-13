package adi.blackjack;

public class Constants {
	/*************************************
	 * Specify all game constants here
	 *************************************/
	public static final int NUM_DECKS = 1;
	public static final int STARTING_NUMBER_OF_CHIPS = 100;
	public static final int MIN_BET_CHIPS = 1;

	public static final char ACTION_KEY_HIT = 'h';
	public static final char ACTION_KEY_STAND = 's';
	public static final char ACTION_KEY_SURRENDER = 'u';
	public static final char ACTION_KEY_DOUBLE_DOWN = 'd';
	public static final char ACTION_KEY_SPLIT = 'p';
	public static final char ACTION_KEY_QUIT = 'q';

	public static final String ACTION_STRING_HIT = "(h)it";
	public static final String ACTION_STRING_STAND = "(s)tand";
	public static final String ACTION_STRING_SURRENDER = "s(u)rrender";
	public static final String ACTION_STRING_DOUBLE_DOWN = "(d)oubledown";
	public static final String ACTION_STRING_SPLIT = "s(p)lit";
	public static final String ACTION_STRING_QUIT = "(q)uit";

	public static final String WELCOME_MESSAGE = "*** Welcome to a new game of blackjack ***\n"
			+ "Player starts with "
			+ STARTING_NUMBER_OF_CHIPS
			+ " chips.\n"
			+ "Current shoe has "
			+ NUM_DECKS
			+ " deck(s).\n"
			+ "Minimum bet is " + MIN_BET_CHIPS + " chip(s).\n";

	public static final String BET_MESSAGE = "You may " + ACTION_STRING_QUIT
			+ " or bet by typing a number, "
			+ "or hit [Return] to bet the minimum of " + MIN_BET_CHIPS
			+ " chip(s).\nCurrent Balance: ";
	public static final String HAND_COMPLETE = "\nHand completed!\n";
	public static final String END_MESSAGE = "*** Thank you for playing! ***";
}
