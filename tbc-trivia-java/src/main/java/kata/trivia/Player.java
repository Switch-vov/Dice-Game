package kata.trivia;

/**
 * Created by Switch on 2016-06-27.
 */
public class Player {
    private String playerName;
    private int place = 0;

    public Player(String playerName) {
        this.playerName = playerName;
    }

    @Override
    public String toString() {
        return this.playerName;
    }

    public void moveForwardSteps(int steps) {
        this.place += steps;
        if(this.place > 11) {
            this.place -= 12;
        }
    }

    public int getPlace() {
        return this.place;
    }
}
