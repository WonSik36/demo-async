package com.example.demoasync;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SomeRepository extends JpaRepository<Some, Long> {
}
