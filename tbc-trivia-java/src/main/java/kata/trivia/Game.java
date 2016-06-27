package kata.trivia;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Game {
    private final QuestionMaker questionMaker = new QuestionMaker();
    private ArrayList<Player> players = new ArrayList<Player>();
    // TODO-working-on: Move places into class Player
    private int[] places = new int[6];

    // TODO: Move purses into class Player
    private int[] purses = new int[6];

    // TODO: Move inPenaltyBox into class Player
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
        // TODO: Move playerName into class Player
        players.add(new Player(playerName));

        logger.info(playerName + " was added");

        logger.info("The total amount of players is " + players.size());
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
        places[currentPlayer] += rollingNumber;
        if (places[currentPlayer] > 11) places[currentPlayer] -= 12;

        players.get(currentPlayer).moveForwardSteps(rollingNumber);

        logger.info(players.get(currentPlayer)
                + "'s new location is "
                + places[currentPlayer]);
        logger.info(players.get(currentPlayer)
                + "'s new location is "
                + players.get(currentPlayer).getPlace());
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
