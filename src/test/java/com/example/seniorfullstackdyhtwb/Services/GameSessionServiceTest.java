package com.example.seniorfullstackdyhtwb.Services;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameSessionServiceTest {

    @Test
    public void testCalculateReward() {
        GameSessionService gameSessionService = new GameSessionService();

        // Test case 1: All symbols are the same (winning)
        String[] winningResult = {"cherry", "cherry", "cherry"};
        int reward1 = gameSessionService.calculateReward(winningResult);
        assertEquals(10, reward1);

        // Test case 2: All symbols are different (losing)
        String[] losingResult = {"cherry", "lemon", "orange"};
        int reward2 = gameSessionService.calculateReward(losingResult);
        assertEquals(0, reward2);

        // Test case 3: All symbols are the same, different symbol (winning)
        String[] winningResult2 = {"lemon", "lemon", "lemon"};
        int reward3 = gameSessionService.calculateReward(winningResult2);
        assertEquals(20, reward3);


    }
    @Test
    void testIsWinWhenAllElementsAreEqual() {
        // Arrange
        GameSessionService gameSessionService = new GameSessionService();
        String[] winningRoll = {"cherry", "cherry", "cherry"};

        // Act
        boolean result = gameSessionService.isWin(winningRoll);

        // Assert
        assertTrue(result, "Expected a win for a roll where all elements are equal");
    }

    @Test
    void testIsWinWhenAllElementsNotEqual() {
        // Arrange
        GameSessionService gameSessionService = new GameSessionService();
        String[] winningRoll = {"cherry", "lemon", "cherry"};

        // Act
        boolean result = gameSessionService.isWin(winningRoll);

        // Assert
        assertFalse(result, "Expected a loss for a roll where all elements are equal");
    }


}