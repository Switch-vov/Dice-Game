package kata.trivia;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Game {
    public static final int NUMBER_OF_GOLD_COINS_TO_WON_AND_GAME_OVER = 6;
    public static final int MAX_NUMBER_OF_BYTES_WRITING_TO_ONE_FILE = 10000000;
    public static final int NUMBER_OF_FILES_TO_USE = 1;
    public static final int MAX_NUMBER_OF_QUESTIONS = 50;

    private final QuestionMaker questionMaker = new QuestionMaker();
    private ArrayList<Player> players = new ArrayList<Player>();

    private int currentPlayer = 0;

    private static Logger logger = Logger.getLogger("kata.trivia-Switch.Game");
    private static FileHandler fileHandler = null;

    public Game() {
        try {
            fileHandler = new FileHandler("%h/Game-logging.log"
                    , MAX_NUMBER_OF_BYTES_WRITING_TO_ONE_FILE
                    , NUMBER_OF_FILES_TO_USE, true);
            fileHandler.setFormatter(new SimpleFormatter());
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.addHandler(fileHandler);

        for (int i = 0; i < MAX_NUMBER_OF_QUESTIONS; i++) {
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

        if (players.get(currentPlayer).isInPenaltyBox()) {
            boolean isRollingNumberOdd = rollingNumber % 2 != 0;
            if (isRollingNumberOdd) {
                players.get(currentPlayer).getOutOfPenaltyBox();

                logger.info(players.get(currentPlayer) + " is getting out of the penalty box");
                currentPlayerMovesToNewPlaceAnswersAQuestion(rollingNumber);
            } else {
                logger.info(players.get(currentPlayer) + " is not getting out of the penalty box");
                players.get(currentPlayer).stayInPenaltyBox();
            }

        } else {
            currentPlayerMovesToNewPlaceAnswersAQuestion(rollingNumber);
        }

    }

    private void currentPlayerMovesToNewPlaceAnswersAQuestion(int rollingNumber) {
        players.get(currentPlayer).moveForwardSteps(rollingNumber);

        logger.info(players.get(currentPlayer)
                + "'s new location is "
                + players.get(currentPlayer).getPlace());
        logger.info("The category is " + players.get(currentPlayer).getCurrentCategory());
        askQuestion();
    }

    private void askQuestion() {
        if (players.get(currentPlayer).getCurrentCategory() == "Pop")
            logger.info(questionMaker.removeFirstPopQuestion());
        if (players.get(currentPlayer).getCurrentCategory() == "Science")
            logger.info(questionMaker.removeFirstScienceQuestion());
        if (players.get(currentPlayer).getCurrentCategory() == "Sports")
            logger.info(questionMaker.removeFirstSportsQuestion());
        if (players.get(currentPlayer).getCurrentCategory() == "Rock")
            logger.info(questionMaker.removeFirstRockQuestion());
    }


    public boolean wasCorrectlyAnswered() {
        if (players.get(currentPlayer).isInPenaltyBox()) {
            nextPlayer();
            return true;
        }
        return currentPlayerGetsAGoldCoinAndSelectNextPlayer();
    }

    private boolean currentPlayerGetsAGoldCoinAndSelectNextPlayer() {
        logger.info("Answer was correct!!!!");
        players.get(currentPlayer).winAGoldCoin();
        logger.info(players.get(currentPlayer)
                + " now has "
                + players.get(currentPlayer).countGoldCoins()
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
        players.get(currentPlayer).sentToPenaltyBox();

        nextPlayer();
        // TODO-later: The return value of method Game.wrongAnswer() is unnecessary and should be eliminated
        return true;
    }

    private boolean isGameStillInProgress() {
        return !(players.get(currentPlayer).countGoldCoins() == NUMBER_OF_GOLD_COINS_TO_WON_AND_GAME_OVER);
    }
}
