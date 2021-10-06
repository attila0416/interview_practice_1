package practice_1_bowling;

import java.util.HashMap;
import java.util.Map;

public class Game {

    private int totalScore;
    private final Map<Integer, Integer> roundsScore;
    private final Map<Integer, Integer> throwBonusPoints;


    public Game() {
        this.totalScore = 0;
        this.roundsScore = new HashMap<>();
        this.throwBonusPoints = new HashMap<>();
    }


    /**
     * Calculates the total score a bowling game based on scores of each throw.
     * Runs in O(n)
     * @param pins array of pins taken down by each throw
     */
    public void roll(int[] pins) {
        // check if invalid number of throws
        if (pins.length == 0 || pins.length > 20) {
            this.totalScore = 0;
            return;
        }

        int currentRound = 0;
        int currentThrow = 0;
        for (int pin : pins) {
            // check if invalid number of pins
            if (pin < 0 || pin > 10) {
                this.totalScore = 0;
                return;
            }
            updateTotalGameScore(pin, currentThrow);

            // if the current round has already had a throw
            if (this.roundsScore.containsKey(currentRound)) {
                int newCurrentRoundScore = roundsScore.get(currentRound) + pin;
                updateRoundScore(currentRound, newCurrentRoundScore);

                // if this throw was a 'Spare', else if invalid score
                if (newCurrentRoundScore == 10) {
                    updateBonusPointsSpare(currentThrow, currentRound);
                } else if (newCurrentRoundScore > 10) {
                    // if invalid final round score
                    this.totalScore = 0;
                    return;
                }
                currentRound += 1;
            } else {
                updateRoundScore(currentRound, pin);

                // if this throw was a 'Strike'
                if (pin == 10) {
                    updateBonusPointsStrike(currentThrow, currentRound);
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
     * Updates bonus points for the next throw
     * @param currentThrow is the idx-based number of the throw
     * @param currentRound is the idx-based number of the round
     */
    private void updateBonusPointsSpare(int currentThrow, int currentRound) {
        int nextThrow = currentThrow+1;

        // after round 9 (idx based), bonuses are hard reset
        if (currentRound < 10) {
            if (nextThrow < 10) {
                this.throwBonusPoints.put(nextThrow, getBonusMultiplier(nextThrow) + 1);
            }
        } else {
            this.throwBonusPoints.put(nextThrow, getBonusMultiplier(nextThrow) + 1);
        }
    }


    /**
     * Updates bonus points for the next two throws
     * @param currentThrow is the idx-based number of the throw
     * @param currentRound is the idx-based number of the round
     */
    private void updateBonusPointsStrike(int currentThrow, int currentRound) {
        int nextThrow = currentThrow+1;
        int nextNextThrow = currentThrow+2;

        // after round 9 (idx based), bonuses are hard reset
        if (currentRound < 10) {
            if (nextThrow < 10) {
                this.throwBonusPoints.put(nextThrow, getBonusMultiplier(nextThrow) + 1);
            }
            if (nextNextThrow < 10) {
                this.throwBonusPoints.put(nextNextThrow, getBonusMultiplier(nextNextThrow) + 1);
            }
        } else {
            this.throwBonusPoints.put(nextThrow, getBonusMultiplier(nextThrow) + 1);
            this.throwBonusPoints.put(nextNextThrow, getBonusMultiplier(nextNextThrow) + 1);
        }
    }


    /**
     * Adds bonus scores and current throw's score to totalScore
     * @param pin is the score of this throw
     * @param currentThrow is the idx-based number of the throw
     */
    private void updateTotalGameScore(int pin, int currentThrow) {
        int bonusMultiplier = getBonusMultiplier(currentThrow);
        this.totalScore += pin * bonusMultiplier + pin;
    }


    /**
     * Updates total score of this round
     * @param round is the idx-based number of the round
     * @param score is the score achieved in this round
     */
    private void updateRoundScore(int round, int score) {
        this.roundsScore.put(round, score);
    }


    /**
     * @param throwNumber is the throw count in the game
     * @return bonus multiplier of the given throw
     */
    private int getBonusMultiplier(int throwNumber) {
        return this.throwBonusPoints.getOrDefault(throwNumber, 0);
    }

}
