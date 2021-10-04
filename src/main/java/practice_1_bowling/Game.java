package practice_1_bowling;

import java.util.HashMap;
import java.util.Map;

public class Game {

    private int totalScore;
    private final Map<Integer, Integer> roundsScope;
    private final Map<Integer, Integer> bonusPoints;

    public Game() {
        this.totalScore = 0;
        this.roundsScope = new HashMap<>();
        this.bonusPoints = new HashMap<>();
    }

    /**
     * @param pins array of pins taken down by each throw
     */
    public void roll(int[] pins) {
        int currentRound = 0;
        int currentThrow = 0;
        for (int pin : pins) {
            // if the current round has already had a throw
            if (this.roundsScope.containsKey(currentRound)) {
                // Add this throw's score to the total score
                this.totalScore += pin;

                // Update total score of this round
                this.roundsScope.put(currentRound, roundsScope.get(currentRound) + pin);

                // Add bonus scores to totalScore
                int bonusMultiplier = getBonusMultiplier(currentThrow);
                this.totalScore += pin * bonusMultiplier;

                // if this throw was a 'Spare'
                if (this.roundsScope.get(currentRound) == 10) {
                    int nextThrow = currentThrow+1;

                    // after round 9 (idx based), bonuses are hard reset
                    if (currentRound < 10) {
                        if (nextThrow < 10) {
                            this.bonusPoints.put(nextThrow, getBonusMultiplier(nextThrow) + 1);
                        }
                    } else {
                        this.bonusPoints.put(nextThrow, getBonusMultiplier(nextThrow) + 1);
                    }
                }
                currentRound += 1;
            } else {
                // Add this throw's score to the total score
                this.totalScore += pin;

                // Update total score of this round
                this.roundsScope.put(currentRound, pin);

                // Add bonus scores to totalScore
                int bonusMultiplier = getBonusMultiplier(currentThrow);
                this.totalScore += pin * bonusMultiplier;

                // if this throw was a 'Strike'
                if (pin == 10) {
                    int nextThrow = currentThrow+1;
                    int nextNextThrow = currentThrow+2;

                    // after round 9 (idx based), bonuses are hard reset
                    if (currentRound < 10) {
                        if (nextThrow < 10) {
                            this.bonusPoints.put(nextThrow, getBonusMultiplier(nextThrow) + 1);
                        }
                        if (nextNextThrow < 10) {
                            this.bonusPoints.put(nextNextThrow, getBonusMultiplier(nextNextThrow) + 1);
                        }
                    } else {
                        this.bonusPoints.put(nextThrow, getBonusMultiplier(nextThrow) + 1);
                        this.bonusPoints.put(nextNextThrow, getBonusMultiplier(nextNextThrow) + 1);
                    }
                    currentRound += 1;
                }
            }
            currentThrow += 1;
        }
    }

    /**
     * @return total score in this bowling game
     */
    public int score() {
        return this.totalScore;
    }

    /**
     * @param throwNumber is the throw count in the game
     * @return bonus multiplier of the given throw
     */
    public int getBonusMultiplier(int throwNumber) {
        return this.bonusPoints.getOrDefault(throwNumber, 0);
    }

}
