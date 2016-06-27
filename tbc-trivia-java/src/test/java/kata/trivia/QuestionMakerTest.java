package kata.trivia;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Switch on 2016-06-27.
 */
public class QuestionMakerTest {
    public static final String POP_QUESTION_1 = "Pop Question 1";
    public static final String SCIENCE_QUESTION_1 = "Science Question 1";
    public static final String SPORTS_QUESTION_1 = "Sports Question 1";
    public static final String ROCK_QUESTION_1 = "Rock Question 1";

    QuestionMaker questionMaker = null;

    @Before
    public void initialize() {
        // Arrange
        questionMaker = new QuestionMaker();
    }

    @Test
    public void add_two_pop_questions_and_could_remove_the_first_one() {
        // Act
        questionMaker.addPopQuestions(POP_QUESTION_1);
        questionMaker.addPopQuestions("Pop Question 2");

        // Assert
        assertEquals(POP_QUESTION_1, questionMaker.removeFirstPopQuestion());
    }

    @Test
    public void add_two_science_questions_and_could_remove_the_first_one() {
        // Act
        questionMaker.addScienceQuestions(SCIENCE_QUESTION_1);
        questionMaker.addScienceQuestions("Science Question 2");

        // Assert
        assertEquals(SCIENCE_QUESTION_1, questionMaker.removeFirstScienceQuestion());
    }

    @Test
    public void add_two_sports_questions_and_could_remove_the_first_one() {
        // Act
        questionMaker.addSportsQuestions(SPORTS_QUESTION_1);
        questionMaker.addSportsQuestions("Sports Question 2");

        // Assert
        assertEquals(SPORTS_QUESTION_1, questionMaker.removeFirstSportsQuestion());
    }

    @Test
    public void add_two_rock_questions_and_could_remove_the_first_one() {
        // Act
        questionMaker.addRockQuestions(ROCK_QUESTION_1);
        questionMaker.addRockQuestions("Rock Question 2");

        // Assert
        assertEquals(ROCK_QUESTION_1, questionMaker.removeFirstRockQuestion());
    }
}
