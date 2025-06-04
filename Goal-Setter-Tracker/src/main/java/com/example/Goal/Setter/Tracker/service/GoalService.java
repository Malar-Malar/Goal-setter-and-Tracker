package com.example.Goal.Setter.Tracker.service;

import com.example.Goal.Setter.Tracker.model.Goal;
import com.example.Goal.Setter.Tracker.repository.GoalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoalService {

    private final GoalRepository goalRepository;

    public GoalService(GoalRepository goalRepository) {
        this.goalRepository = goalRepository;
    }

    public Goal addGoal(Goal goal) {
        return goalRepository.save(goal);
    }

    public List<Goal> getAllGoals() {
        return goalRepository.findAll();
    }

    public Goal updateGoalProgress(Long goalId, int newProgress) {
        Goal goal = goalRepository.findById(goalId)
                .orElseThrow(() -> new RuntimeException("Goal not found with id: " + goalId));
        goal.setProgress(newProgress);
        return goalRepository.save(goal);
    }

    public List<Goal> getGoalsByUserId(Long userId) {
        return goalRepository.findByUserId(userId);
    }

    public Goal getGoalById(Long goalId) {
        return goalRepository.findById(goalId)
                .orElseThrow(() -> new RuntimeException("Goal not found"));
    }

    public List<Goal> getCompletedGoals(Long userId) {
        return goalRepository.findByUserIdAndProgressGreaterThanEqual(userId, 100);
    }

    public List<Goal> findByUserIdAndProgressLessThan(Long userId, int progress) {
        return goalRepository.findByUserIdAndProgressLessThan(userId, progress);
    }
}
