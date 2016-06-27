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
