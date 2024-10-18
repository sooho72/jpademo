package org.pgm.japdemo.repository;

import org.pgm.japdemo.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
