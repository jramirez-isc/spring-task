package com.leantech.task.repository;

import com.leantech.task.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PositionRepository extends JpaRepository<Position, Integer> {

  Optional<Position> findByName(String name);
}
