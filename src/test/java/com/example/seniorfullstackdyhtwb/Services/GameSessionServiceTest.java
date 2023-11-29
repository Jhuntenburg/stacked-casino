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

}