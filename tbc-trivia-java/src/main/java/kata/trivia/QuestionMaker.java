package kata.trivia;

import java.util.LinkedList;

/**
 * Created by Switch on 2016-06-27.
 */
public class QuestionMaker {
    private LinkedList<String> popQuestions = new LinkedList<String>();
    private LinkedList<String> scienceQuestions = new LinkedList<String>();
    private LinkedList<String> sportsQuestions = new LinkedList<String>();
    private LinkedList<String> rockQuestions = new LinkedList<String>();

    public static final int MAX_NUMBER_OF_QUESTIONS = 50;

    public QuestionMaker() {
        for (int i = 0; i < MAX_NUMBER_OF_QUESTIONS; i++) {
            addPopQuestions("Pop Question " + i);
            addScienceQuestions(("Science Question " + i));
            addSportsQuestions(("Sports Question " + i));
            addRockQuestions("Rock Question " + i);
        }
    }

    public void addPopQuestions(String popQuestion) {
        popQuestions.add(popQuestion);
    }

    public void addScienceQuestions(String scienceQuestion) {
        scienceQuestions.add(scienceQuestion);
    }

    public void addSportsQuestions(String sportsQuestion) {
        sportsQuestions.add(sportsQuestion);
    }

    public void addRockQuestions(String rockQuestion) {
        rockQuestions.add(rockQuestion);
    }

    public String removeFirstPopQuestion() {
        return popQuestions.removeFirst();
    }

    public String removeFirstScienceQuestion() {
        return scienceQuestions.removeFirst();
    }

    public String removeFirstSportsQuestion() {
        return sportsQuestions.removeFirst();
    }

    public String removeFirstRockQuestion() {
        return rockQuestions.removeFirst();
    }
}
