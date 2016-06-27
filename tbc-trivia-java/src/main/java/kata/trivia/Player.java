package kata.trivia;

/**
 * Created by Switch on 2016-06-27.
 */
public class Player {
    private String playerName;
    private int place = 0;
    private int sumOfGoldCoins = 0;
    private boolean inPenaltyBox = false;
    private boolean isGettingOutOfPenaltyBox = false;

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

    public String getCurrentCategory() {
        if (this.place == 0) return "Pop";
        if (this.place == 4) return "Pop";
        if (this.place == 8) return "Pop";
        if (this.place == 1) return "Science";
        if (this.place == 5) return "Science";
        if (this.place == 9) return "Science";
        if (this.place == 2) return "Sports";
        if (this.place == 6) return "Sports";
        if (this.place == 10) return "Sports";
        return "Rock";
    }

    public void winAGoldCoin() {
        this.sumOfGoldCoins++;
    }

    public int countGoldCoins() {
        return this.sumOfGoldCoins;
    }

    public boolean isInPenaltyBox() {
        return this.inPenaltyBox;
    }

    public void getOutOfPenaltyBox() {
         this.isGettingOutOfPenaltyBox = true;
    }

    public void stayInPenaltyBox() {
        this.isGettingOutOfPenaltyBox = false;
    }
}
