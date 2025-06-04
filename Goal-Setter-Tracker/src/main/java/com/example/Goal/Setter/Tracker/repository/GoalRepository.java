package com.example.Goal.Setter.Tracker.repository;

import com.example.Goal.Setter.Tracker.model.Goal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GoalRepository extends JpaRepository<Goal, Long> {
    List<Goal> findByUserId(Long userId);
    List<Goal> findByUserIdAndProgressGreaterThanEqual(Long userId, int progress);
    List<Goal> findByUserIdAndProgressLessThan(Long userId, int progress);
    List<Goal> findByUserIdAndProgress(Long userId, int progress);

}
