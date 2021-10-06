package practice_1_bowling;

import org.junit.Assert;
import org.junit.Test;

public class GameTest {

    @Test
    public void scoreTestOneRound() {
        Game game = new Game();
        int[] pins = {1, 4};
        game.roll(pins);
        int actual = game.score();
        Assert.assertEquals(5, actual);
    }

    @Test
    public void scoreTestSpareBonus() {
        Game game = new Game();
        int[] pins = {1, 4, 5, 5, 1, 4};
        game.roll(pins);
        int actual = game.score();
        Assert.assertEquals(21, actual);
    }

    @Test
    public void scoreTestStrikeBonus() {
        Game game = new Game();
        int[] pins = {1, 4, 10, 1, 4};
        game.roll(pins);
        int actual = game.score();
        Assert.assertEquals(25, actual);
    }

    @Test
    public void scoreTestSpareAndStrikeBonus() {
        Game game = new Game();
        int[] pins = {1, 4, 4, 5, 6, 4, 5, 5, 10, 0, 1};
        game.roll(pins);
        int actual = game.score();
        Assert.assertEquals(60, actual);
    }

    @Test
    public void scoreTestEndGameStrikesBonus() {
        Game game = new Game();
        int[] pins = {10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10};
        game.roll(pins);
        int actual = game.score();
        Assert.assertEquals(300, actual);
    }

    @Test
    public void scoreTestEndGameSpareBonus() {
        Game game = new Game();
        int[] pins = {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 9, 1};
        game.roll(pins);
        int actual = game.score();
        Assert.assertEquals(19, actual);
    }

    @Test
    public void rollTestInvalidThrowsNumber() {
        Game game = new Game();
        int[] emptyPins = {};
        game.roll(emptyPins);
        int actual = game.score();
        Assert.assertEquals(0, actual);

        int[] pins = {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0};
        game.roll(pins);
        actual = game.score();
        Assert.assertEquals(0, actual);
    }

    @Test
    public void rollTestInvalidPinScore() {
        Game game = new Game();
        int[] lowPins = {-1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 9, 1};
        game.roll(lowPins);
        int actual = game.score();
        Assert.assertEquals(0, actual);

        int[] highPins = {11, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 9, 1};
        game.roll(highPins);
        actual = game.score();
        Assert.assertEquals(0, actual);
    }

    @Test
    public void rollTestInvalidRoundScore() {
        Game game = new Game();
        int[] pins = {9, 9, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 9, 1};
        game.roll(pins);
        int actual = game.score();
        Assert.assertEquals(0, actual);
    }

}
