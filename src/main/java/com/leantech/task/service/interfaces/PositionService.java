package com.leantech.task.service.interfaces;

import com.leantech.task.model.Position;

import java.util.List;

public interface PositionService {

  List<Position> getAllPositions();

  Position getByNameOrCreate(Position position);
}
