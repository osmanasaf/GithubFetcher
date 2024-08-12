package com.hitit.demo.repository;

import com.hitit.demo.data.entity.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryRepository extends JpaRepository<Repository, Long> {
    Repository findByName(String name);
}
