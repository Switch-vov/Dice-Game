package kata.trivia;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Switch on 2016-06-27.
 */
public class QuestionMakerTest {
    QuestionMaker questionMaker = null;

    @Before
    public void initialize() {
        // Arrange
        questionMaker = new QuestionMaker();
    }

    @Test
    public void add_two_pop_questions_and_could_remove_the_first_one() {
        // Act
        questionMaker.addPopQuestions("Pop Question 1");
        questionMaker.addPopQuestions("Pop Question 2");

        // Assert
        assertEquals("Pop Question 1", questionMaker.removeFirstPopQuestion());
    }

    @Test
    public void add_two_science_questions_and_could_remove_the_first_one() {
        // Act
        questionMaker.addScienceQuestions("Science Question 1");
        questionMaker.addScienceQuestions("Science Question 2");

        // Assert
        assertEquals("Science Question 1", questionMaker.removeFirstScienceQuestion());
    }

    @Test
    public void add_two_sports_questions_and_could_remove_the_first_one() {
        // Act
        questionMaker.addSportsQuestions("Sports Question 1");
        questionMaker.addSportsQuestions("Sports Question 2");

        // Assert
        assertEquals("Sports Question 1", questionMaker.removeFirstSportsQuestion());
    }

    @Test
    public void add_two_rock_questions_and_could_remove_the_first_one() {
        // Act
        questionMaker.addRockQuestions("Rock Question 1");
        questionMaker.addRockQuestions("Rock Question 2");

        // Assert
        assertEquals("Rock Question 1", questionMaker.removeFirstRockQuestion());
    }
}
