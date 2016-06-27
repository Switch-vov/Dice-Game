package kata.trivia;

import java.util.LinkedList;

/**
 * Created by Switch on 2016-06-27.
 */
public class QuestionMaker {
    // TODO-working-on: Move question lists to a new class QuestionMaker
    // TODO: Make question lists type-safe
    private LinkedList popQuestions = new LinkedList();
    private LinkedList scienceQuestions = new LinkedList();
    private LinkedList sportsQuestions = new LinkedList();
    private LinkedList rockQuestions = new LinkedList();

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
}
