package kata.trivia;

/**
 * Created by Switch on 2016-06-27.
 */
public class Player {
    private String playerName;

    public Player(String playerName) {
        this.playerName = playerName;
    }

    @Override
    public String toString() {
        return this.playerName;
    }
}
