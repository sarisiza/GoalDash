package com.upakon.goaldash.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.upakon.goaldash.goal.Goal
import com.upakon.goaldash.goal.GoalList
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GoalsViewModel @Inject constructor() : ViewModel() {

    private val _goalList: MutableState<GoalList> = mutableStateOf(GoalList())
    val goalList : State<GoalList> get() = _goalList

    fun addGoal(goal: Goal){
        _goalList.value.addGoal(goal)
    }

}