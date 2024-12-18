package org.example.library.repository;

import org.example.library.entity.JournalOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<JournalOrder, Long> {
}
