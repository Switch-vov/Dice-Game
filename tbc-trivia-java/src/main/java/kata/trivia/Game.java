package kata.trivia;

import sun.rmi.runtime.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Game {
    private final QuestionMaker questionMaker = new QuestionMaker();
    // TODO: Move playerName, places and inPenaltyBox to new class Player
    // TODO: Make player list type-safe
    private ArrayList players = new ArrayList();
    private int[] places = new int[6];
    private int[] purses = new int[6];
    private boolean[] inPenaltyBox = new boolean[6];

    private int currentPlayer = 0;
    private boolean isGettingOutOfPenaltyBox;

    private static Logger logger = Logger.getLogger("kata.trivia-Switch.Game");
    private static FileHandler fileHandler = null;

    public Game() {
        try {
            fileHandler = new FileHandler("%h/Game-logging.log", 10000000, 1, true);
            fileHandler.setFormatter(new SimpleFormatter());
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.addHandler(fileHandler);

        for (int i = 0; i < 50; i++) {
            questionMaker.addPopQuestions("Pop Question " + i);
            questionMaker.addScienceQuestions(("Science Question " + i));
            questionMaker.addSportsQuestions(("Sports Question " + i));
            questionMaker.addRockQuestions("Rock Question " + i);
        }
    }

    public void add(String playerName) {
        players.add(playerName);
        places[howManyPlayers()] = 0;
        purses[howManyPlayers()] = 0;
        inPenaltyBox[howManyPlayers()] = false;

        logger.info(playerName + " was added");
        logger.info("They are player number " + players.size());
    }

    private int howManyPlayers() {
        return players.size();
    }

    public void roll(int rollingNumber) {
        logger.info(players.get(currentPlayer) + " is the current player");
        logger.info("They have rolled a " + rollingNumber);

        if (inPenaltyBox[currentPlayer]) {
            if (rollingNumber % 2 != 0) {
                isGettingOutOfPenaltyBox = true;

                System.out.println(players.get(currentPlayer) + " is getting out of the penalty box");
                currentPlayerMovesToNewPlaceAnswersAQuestion(rollingNumber);
            } else {
                System.out.println(players.get(currentPlayer) + " is not getting out of the penalty box");
                isGettingOutOfPenaltyBox = false;
            }

        } else {
            currentPlayerMovesToNewPlaceAnswersAQuestion(rollingNumber);
        }

    }

    private void currentPlayerMovesToNewPlaceAnswersAQuestion(int rollingNumber) {
        places[currentPlayer] = places[currentPlayer] + rollingNumber;
        if (places[currentPlayer] > 11) places[currentPlayer] = places[currentPlayer] - 12;

        logger.info(players.get(currentPlayer)
                + "'s new location is "
                + places[currentPlayer]);
        logger.info("The category is " + currentCategory());
        askQuestion();
    }

    private void askQuestion() {
        if (currentCategory() == "Pop")
            logger.info(questionMaker.removeFirstPopQuestion());
        if (currentCategory() == "Science")
            logger.info(questionMaker.removeFirstScienceQuestion());
        if (currentCategory() == "Sports")
            logger.info(questionMaker.removeFirstSportsQuestion());
        if (currentCategory() == "Rock")
            logger.info(questionMaker.removeFirstRockQuestion());
    }


    private String currentCategory() {
        if (places[currentPlayer] == 0) return "Pop";
        if (places[currentPlayer] == 4) return "Pop";
        if (places[currentPlayer] == 8) return "Pop";
        if (places[currentPlayer] == 1) return "Science";
        if (places[currentPlayer] == 5) return "Science";
        if (places[currentPlayer] == 9) return "Science";
        if (places[currentPlayer] == 2) return "Sports";
        if (places[currentPlayer] == 6) return "Sports";
        if (places[currentPlayer] == 10) return "Sports";
        return "Rock";
    }

    public boolean wasCorrectlyAnswered() {
        if (inPenaltyBox[currentPlayer]) {
            if (isGettingOutOfPenaltyBox) {
                return currentPlayerGetsAGoldCoinAndSelectNextPlayer();
            } else {
                nextPlayer();
                return true;
            }
        } else {
            return currentPlayerGetsAGoldCoinAndSelectNextPlayer();
        }
    }

    private boolean currentPlayerGetsAGoldCoinAndSelectNextPlayer() {
        logger.info("Answer was correct!!!!");
        purses[currentPlayer]++;
        logger.info(players.get(currentPlayer)
                + " now has "
                + purses[currentPlayer]
                + " Gold Coins.");

        boolean isGameStillInProgress = isGameStillInProgress();
        nextPlayer();

        return isGameStillInProgress;
    }

    private void nextPlayer() {
        currentPlayer++;
        if (currentPlayer == players.size()) currentPlayer = 0;
    }

    public boolean wrongAnswer() {
        logger.info("Question was incorrectly answered");
        logger.info(players.get(currentPlayer) + " was sent to the penalty box");
        inPenaltyBox[currentPlayer] = true;

        nextPlayer();
        // TODO-later: The return value of method Game.wrongAnswer() is unnecessary and should be eliminated
        return true;
    }

    private boolean isGameStillInProgress() {
        return !(purses[currentPlayer] == 6);
    }
}
