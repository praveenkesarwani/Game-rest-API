package com.simpleviralgames.simpleviralgames.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.simpleviralgames.simpleviralgames.entities.Game;
import com.simpleviralgames.simpleviralgames.services.GameService;

import java.util.List;

@RestController
@RequestMapping("/api/games")
public class GameController {

  @Autowired
  private GameService gameService;

  @PostMapping
  public ResponseEntity<Game> createGame(@RequestBody Game game) {
    return ResponseEntity.ok(gameService.createGame(game));
  }

  @GetMapping("/{id}")
  public ResponseEntity<Game> getGame(@PathVariable Long id) {
    Game game = gameService.getGame(id);
    if (game == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(game);
  }

  @GetMapping
  public ResponseEntity<List<Game>> getAllGames() {
    return ResponseEntity.ok(gameService.getAllGames());
  }

  @PutMapping("/{id}")
  public ResponseEntity<Game> updateGame(@PathVariable Long id, @RequestBody Game game) {
    game.setId(id);
    Game updatedGame = gameService.updateGame(game);
    if (updatedGame == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(updatedGame);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity deleteGame(@PathVariable Long id) {
    if (gameService.deleteGame(id)) {
      return ResponseEntity.ok().build();
    }
    return ResponseEntity.notFound().build();
  }
}

