package com.felipe.forum.repository;

import com.felipe.forum.model.Curse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurseRepository extends JpaRepository<Curse, Long> {
    Curse findByName(String curseName);
}
