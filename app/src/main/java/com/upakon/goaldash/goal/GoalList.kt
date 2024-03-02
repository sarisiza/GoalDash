package com.upakon.goaldash.goal

class GoalList {

    private val _goals = mutableListOf<Goal>()
    val goals: List<Goal> get() = _goals

    fun addGoal(goal: Goal){
        _goals.add(goal)
    }

}