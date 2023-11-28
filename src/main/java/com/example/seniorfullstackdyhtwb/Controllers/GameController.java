package com.example.seniorfullstackdyhtwb.Controllers;

import com.example.seniorfullstackdyhtwb.Model.GameSession;
import com.example.seniorfullstackdyhtwb.Services.GameSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/game")
public class GameController {

    private final GameSessionService gameSessionService;

    @Autowired
    public GameController(GameSessionService gameSessionService) {
        this.gameSessionService = gameSessionService;
    }

    @PostMapping(value = "/start")
    public ResponseEntity<GameSession> startNewGameSession() {
        GameSession newGameSession = gameSessionService.startNewGameSession();
        return new ResponseEntity<>(newGameSession, HttpStatus.OK);

//                newGameSession.map(session -> ResponseEntity.ok().body(session))
//                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PostMapping("/results")
    public ResponseEntity<String[]> rollResults(){
       return new ResponseEntity<>( gameSessionService.simulateRoll(), HttpStatus.OK);
    }

    @PostMapping("/roll/{gameSessionId}")
    public ResponseEntity<GameSession> rollSlots(@PathVariable Long gameSessionId, @RequestBody String[] rollResults) {
        GameSession rolledGameSession = gameSessionService.rollSlots(gameSessionId, rollResults);
        return new ResponseEntity<>(rolledGameSession, HttpStatus.OK);


    }

    @PostMapping("/cash-out/{gameSessionId}")
    public ResponseEntity<String> cashOut(@PathVariable Long gameSessionId) {
        gameSessionService.cashOut(gameSessionId);
        return ResponseEntity.ok("Cash-out successful");
    }
}

