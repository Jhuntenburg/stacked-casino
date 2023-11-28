package com.example.seniorfullstackdyhtwb.Services;


import com.example.seniorfullstackdyhtwb.Model.GameSession;
import com.example.seniorfullstackdyhtwb.Model.UserAccount;
import com.example.seniorfullstackdyhtwb.Repositories.GameSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class GameSessionService {

    GameSessionRepository gameSessionRepository;
    UserAccountService userAccountService;

    @Autowired
    public GameSessionService(GameSessionRepository gameSessionRepository, UserAccountService userAccountService) {
        this.gameSessionRepository = gameSessionRepository;
        this.userAccountService = userAccountService;
    }

    public GameSession startNewGameSession() {
        // Initialize a new game session with 10 credits
        GameSession newGameSession = new GameSession();
        newGameSession.setCredits(10);

        // Save the new game session to the database
        GameSession savedGameSession = gameSessionRepository.save(newGameSession);

        return Optional.of(savedGameSession).get();
    }

    public GameSession getGameSessionById(Long gameSessionId) {
        return gameSessionRepository.findById(gameSessionId).get();
    }

    public GameSession rollSlots(Long gameSessionId, String[] results) {
        // Retrieve the game session
        GameSession gameSession = getGameSessionById(gameSessionId);


            // Deduct 1 credit for the roll
            gameSession.setCredits(gameSession.getCredits() - 1);

            // Simulate rolling the slots and determine the result
            String[] result = results;

            // Update credits based on the result
            int reward = calculateReward(result);
            gameSession.setCredits(gameSession.getCredits() + reward);

            // Save the updated game session
            gameSessionRepository.save(gameSession);

            return gameSession;
        }


    public String[] simulateRoll() {
        // Define the symbols on the slot machine
        String[] symbols = {"cherry", "lemon", "orange", "watermelon"};
        String[] rolls = new String[3];



        // Use a random number to determine the symbol
        Random random = new Random();
        rolls[0] = symbols[random.nextInt(4)];
        rolls[1] = symbols[random.nextInt(4)];
        rolls[2] = symbols[random.nextInt(4)];



                return rolls;

        }




    private int calculateReward(String[] result) {
        if(result[0] == result[1] && result[0] == result[2]) {
            switch (result[0]) {
                case "cherry":
                    return 10;
                case "lemon":
                    return 20;
                case "orange":
                    return 30;
                case "watermelon":
                    return 40;
                default:
                    return 0;
            }
        }
        return 0;
    }

    public void cashOut(Long gameSessionId) {
        // Retrieve the game session
        Optional<GameSession> optionalGameSession = gameSessionRepository.findById(gameSessionId);

        if (optionalGameSession.isPresent()) {
            GameSession gameSession = optionalGameSession.get();

            // Retrieve the associated user account
            UserAccount userAccount = gameSession.getUserAccount();

            if (userAccount != null) {
                // Move credits from the game session to the user's account
                userAccountService.addCreditsToUser(userAccount.getId(), gameSession.getCredits());

                // Close the game session only if adding credits is successful
                gameSessionRepository.delete(gameSession);
            }
        }
    }



}
