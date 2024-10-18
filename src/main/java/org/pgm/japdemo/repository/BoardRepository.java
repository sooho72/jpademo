package org.pgm.japdemo.repository;

import org.pgm.japdemo.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
