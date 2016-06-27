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
        // Act, Assert
        assertEquals("Pop Question 0", questionMaker.removeFirstPopQuestion());
    }

    @Test
    public void add_two_science_questions_and_could_remove_the_first_one() {
        // Act, Assert
        assertEquals("Science Question 0", questionMaker.removeFirstScienceQuestion());
    }

    @Test
    public void add_two_sports_questions_and_could_remove_the_first_one() {
        // Act, Assert
        assertEquals("Sports Question 0", questionMaker.removeFirstSportsQuestion());
    }

    @Test
    public void add_two_rock_questions_and_could_remove_the_first_one() {
        // Act, Assert
        assertEquals("Rock Question 0", questionMaker.removeFirstRockQuestion());
    }
}
