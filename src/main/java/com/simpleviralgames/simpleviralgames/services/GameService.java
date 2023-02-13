package com.simpleviralgames.simpleviralgames.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simpleviralgames.simpleviralgames.entities.Game;
import com.simpleviralgames.simpleviralgames.repositories.GameRepository;

import java.util.List;
import java.util.Optional;

@Service
public class GameService {

  @Autowired
  private GameRepository gameRepository;

  public Game createGame(Game game) {
    return gameRepository.save(game);
  }

  public Game getGame(Long id) {
    Optional<Game> optionalGame = gameRepository.findById(id);
    return optionalGame.orElse(null);
  }

  public List<Game> getAllGames() {
    return gameRepository.findAll();
  }

  public Game updateGame(Game game) {
    Optional<Game> optionalGame = gameRepository.findById(game.getId());
    if (optionalGame.isPresent()) {
      Game existingGame = optionalGame.get();
      existingGame.setName(game.getName());
      existingGame.setUrl(game.getUrl());
      existingGame.setAuthor(game.getAuthor());
      existingGame.setPublishedDate(game.getPublishedDate());
      return gameRepository.save(existingGame);
    }
    return null;
  }

  public boolean deleteGame(Long id) {
    Optional<Game> optionalGame = gameRepository.findById(id);
    if (optionalGame.isPresent()) {
      gameRepository.delete(optionalGame.get());
      return true;
    }
    return false;
  }
}
