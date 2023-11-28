package com.example.seniorfullstackdyhtwb.Repositories;

import com.example.seniorfullstackdyhtwb.Model.GameSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameSessionRepository extends JpaRepository<GameSession, Long> {
}
