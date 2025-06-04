package com.example.Goal.Setter.Tracker.controller;

import com.example.Goal.Setter.Tracker.model.Goal;
import com.example.Goal.Setter.Tracker.service.GoalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/goals")
@CrossOrigin(origins = "http://127.0.0.1:5500")

public class GoalController {

    private final GoalService goalService;

    public GoalController(GoalService goalService) {
        this.goalService = goalService;
    }

    @PostMapping
    public Goal addGoal(@RequestBody Goal goal) {
        return goalService.addGoal(goal);
    }

    @GetMapping
    public List<Goal> getAllGoals() {
        return goalService.getAllGoals();
    }

    @PutMapping("/progress/{goalId}")
    public ResponseEntity<Goal> updateProgress(@PathVariable Long goalId, @RequestBody Map<String, Integer> body) {
        int newProgress = body.get("progress");
        Goal updatedGoal = goalService.updateGoalProgress(goalId, newProgress);
        return ResponseEntity.ok(updatedGoal);
    }

    @GetMapping("/user/{userId}")
    public List<Goal> getGoalsByUserId(@PathVariable Long userId) {
        return goalService.getGoalsByUserId(userId);
    }

    @GetMapping("/{goalId}")
    public ResponseEntity<Goal> getGoalById(@PathVariable Long goalId) {
        Goal goal = goalService.getGoalById(goalId);
        return ResponseEntity.ok(goal);
    }

//    @GetMapping("/user/{userId}/completed")
//    public ResponseEntity<List<Goal>> getCompletedGoals(@PathVariable Long userId) {
//        List<Goal> completedGoals = goalService.getCompletedGoals(userId,100);
//        return ResponseEntity.ok(completedGoals);
//    }

    @GetMapping("/user/{userId}/completed")
    public ResponseEntity<List<Goal>> getCompletedGoals(@PathVariable Long userId) {
        List<Goal> completedGoals = goalService.getCompletedGoals(userId); // ✅ only one parameter
        return ResponseEntity.ok(completedGoals);
    }


    @GetMapping("/user/{userId}/active")
    public ResponseEntity<List<Goal>> getActiveGoals(@PathVariable Long userId) {
        List<Goal> activeGoals = goalService.findByUserIdAndProgressLessThan(userId, 100);
        return ResponseEntity.ok(activeGoals);
    }




}
