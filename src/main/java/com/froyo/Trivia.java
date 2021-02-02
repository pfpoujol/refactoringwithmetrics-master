package com.froyo;

import java.util.ArrayList;
import java.util.LinkedList;
import org.apache.log4j.Logger;

public class Trivia {
	private static final String POP = "Pop";
	private static final String SCIENCE = "Science";
	private static final String SPORTS = "Sports";
	private static final String ROCK = "Rock";
	Logger logger = Logger.getLogger(Trivia.class);
    ArrayList<String> players;
    int[] places = new int[6];
    int[] purses  = new int[6];
    boolean[] inPenaltyBox  = new boolean[6];
    
    LinkedList<String> popQuestions;
    LinkedList<String> scienceQuestions;
    LinkedList<String> sportsQuestions;
    LinkedList<String> rockQuestions;
    
    int currentPlayer = 0;
    boolean isGettingOutOfPenaltyBox;
    
    public  Trivia(){
    	players = new ArrayList<>();
		popQuestions = new LinkedList<>();
		scienceQuestions = new LinkedList<>();
		sportsQuestions = new LinkedList<>();
		rockQuestions = new LinkedList<>();
    	for (int i = 0; i < 50; i++) {
			popQuestions.addLast("Pop Question " + i);
			scienceQuestions.addLast(("Science Question " + i));
			sportsQuestions.addLast(("Sports Question " + i));
			rockQuestions.addLast(createRockQuestion(i));
    	}
    }

	public String createRockQuestion(int index){
		return "Rock Question " + index;
	}
	
	public boolean isPlayable() {
		return (howManyPlayers() >= 2);
	}

	public boolean add(String playerName) {
		
		
	    players.add(playerName);
	    places[howManyPlayers()] = 0;
	    purses[howManyPlayers()] = 0;
	    inPenaltyBox[howManyPlayers()] = false;
	    
	    logger.info(playerName + " was added");
	    logger.info("They are player number " + players.size());
		return true;
	}
	
	public int howManyPlayers() {
		return players.size();
	}

	public void roll(int roll) {
		logger.info(players.get(currentPlayer) + " is the current player");
		logger.info("They have rolled a " + roll);
		
		if (inPenaltyBox[currentPlayer]) {
			if (roll % 2 != 0) {
				isGettingOutOfPenaltyBox = true;
				
				logger.info(players.get(currentPlayer) + " is getting out of the penalty box");
				places[currentPlayer] = places[currentPlayer] + roll;
				if (places[currentPlayer] > 11) places[currentPlayer] = places[currentPlayer] - 12;
				
				logger.info(players.get(currentPlayer) 
						+ "'s new location is " 
						+ places[currentPlayer]);
				logger.info("The category is " + currentCategory());
				askQuestion();
			} else {
				logger.info(players.get(currentPlayer) + " is not getting out of the penalty box");
				isGettingOutOfPenaltyBox = false;
				}
			
		} else {
		
			places[currentPlayer] = places[currentPlayer] + roll;
			if (places[currentPlayer] > 11) places[currentPlayer] = places[currentPlayer] - 12;
			
			logger.info(players.get(currentPlayer) 
					+ "'s new location is " 
					+ places[currentPlayer]);
			logger.info("The category is " + currentCategory());
			askQuestion();
		}
		
	}

	private void askQuestion() {
		if (currentCategory().equals(POP))
			logger.info(popQuestions.removeFirst());
		if (currentCategory().equals(SCIENCE))
			logger.info(scienceQuestions.removeFirst());
		if (currentCategory().equals(SPORTS))
			logger.info(sportsQuestions.removeFirst());
		if (currentCategory().equals(ROCK))
			logger.info(rockQuestions.removeFirst());		
	}
	
	
	private String currentCategory() {
    	int value = places[currentPlayer];
    	if (value == 0 || value == 4 || value == 8) return POP;
    	if (value == 1 || value == 5 || value == 9) return SCIENCE;
    	if (value == 2 || value == 6 || value == 10) return SPORTS;
		return ROCK;
	}

	public boolean wasCorrectlyAnswered() {
		if (inPenaltyBox[currentPlayer]){
			if (isGettingOutOfPenaltyBox) {
				logger.info("Answer was correct!!!!");
				purses[currentPlayer]++;
				logger.info(players.get(currentPlayer) 
						+ " now has "
						+ purses[currentPlayer]
						+ " Gold Coins.");
				
				boolean winner = didPlayerWin();
				currentPlayer++;
				if (currentPlayer == players.size()) currentPlayer = 0;
				
				return winner;
			} else {
				currentPlayer++;
				if (currentPlayer == players.size()) currentPlayer = 0;
				return true;
			}
			
			
			
		} else {
		
			logger.info("Answer was corrent!!!!");
			purses[currentPlayer]++;
			logger.info(players.get(currentPlayer) 
					+ " now has "
					+ purses[currentPlayer]
					+ " Gold Coins.");
			
			boolean winner = didPlayerWin();
			currentPlayer++;
			if (currentPlayer == players.size()) currentPlayer = 0;
			
			return winner;
		}
	}
	
	public boolean wrongAnswer(){
		logger.info("Question was incorrectly answered");
		logger.info(players.get(currentPlayer)+ " was sent to the penalty box");
		inPenaltyBox[currentPlayer] = true;
		
		currentPlayer++;
		if (currentPlayer == players.size()) currentPlayer = 0;
		return true;
	}


	private boolean didPlayerWin() {
		return (purses[currentPlayer] != 6);
	}
}
