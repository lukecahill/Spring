package com.lukecahill.spring.repositories;

import com.lukecahill.spring.models.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Integer> {
}
