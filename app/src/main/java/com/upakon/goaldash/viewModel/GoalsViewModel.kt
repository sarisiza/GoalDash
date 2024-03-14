package com.upakon.goaldash.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.upakon.goaldash.goal.Goal
import com.upakon.goaldash.goal.Metric
import com.upakon.goaldash.goal.QualitativeMetric
import com.upakon.goaldash.goal.QuantitativeMetric
import com.upakon.goaldash.goal.Task
import com.upakon.goaldash.intents.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import javax.inject.Inject

/**
 * ViewModel of the app
 * Exposes the use cases to the UI
 * Injected with Hilt
 *
 * @property ioDispatcher Exposes IO Dispatchers for the Coroutine Scope
 */
@HiltViewModel
class GoalsViewModel @Inject constructor(
    private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {


    private val _currentGoal : MutableStateFlow<UiState<Goal>> = MutableStateFlow(UiState.LOADING)
    val currentGoal: StateFlow<UiState<Goal>> get() = _currentGoal
    private var selectedGoal : Goal? = null

    private val _allGoals = mutableListOf<Goal>()

    /**
     * Adds a new goal to the list
     *
     * @param goal Goal to add
     */
    fun addGoal(goal: Goal){
        viewModelScope.launch(ioDispatcher) {
            _allGoals.add(goal)
        }
    }

    /**
     * Removes a goal from the list
     *
     * @param goal Goal to remove
     */
    fun removeGoal(goal: Goal){
        viewModelScope.launch(ioDispatcher) {
            _allGoals.remove(goal)
        }
    }

    /**
     * Sets the selected goal
     *
     * @param goal Goal that is selected
     */
    fun setCurrentGoal(goal: Goal){
        selectedGoal = goal
    }

    /**
     * Updates the state flow with the selected goal
     */
    fun showGoal(){
        _currentGoal.value = UiState.LOADING
        selectedGoal?.let {
            _currentGoal.value = UiState.SUCCESS(it)
        } ?: run {
            _currentGoal.value = UiState.ERROR(Exception("No goal selected"))
        }
    }

    /**
     * Updates a specific field from the goal
     *
     * @param field represents the field to be updated
     * @param value value to be updated
     *
     * Possible values:
     * - WHAT: String
     * - WHY: String
     * - IMPORTANCE: Int
     * - END_DATE: LocalDateTime
     * - REWARD: String
     *
     */
    fun updateCurrentGoal(field: String, value: Any){
        viewModelScope.launch(ioDispatcher) {
            try{
                selectedGoal?.let {goal ->
                    when(field){
                        "WHAT" -> goal.what = value as String
                        "WHY" -> goal.why = value as String
                        "IMPORTANCE" -> goal.importance = value as Int
                        "END_DATE" -> goal.endDate = value as LocalDateTime
                        "REWARD" -> goal.reward = value as String
                        else -> throw Exception("Field doesn't exist")
                    }
                    showGoal()
                } ?: throw Exception("No goal selected")
            } catch (e: Exception){
                _currentGoal.value = UiState.ERROR(e)
            }
        }
    }

    /**
     * Creates a new metric and adds it to the goal
     *
     * @param metric New metric created
     */
    fun createNewMetric(metric: Metric){
        viewModelScope.launch(ioDispatcher) {
            selectedGoal?.metricList?.add(metric)
            showGoal()
        }
    }

    /**
     * Modifies the requirement for a quantitative metric
     *
     * @param metric Metric to be modified
     * @param requirement New requirement
     */
    fun modifyRequirement(metric: QuantitativeMetric, requirement : Double){
        viewModelScope.launch(ioDispatcher) {
            try {
                selectedGoal?.let {goal ->
                    require(goal.metricList.contains(metric)) {
                        "${metric.achievement} is not on the metrics list"
                    }
                    metric.required = requirement
                    showGoal()
                } ?: throw Exception("No goal selected")
            } catch (e: Exception){
                _currentGoal.value = UiState.ERROR(e)
            }
        }
    }

    /**
     * Updates the progress of a quantitative metric
     *
     * @param metric Metric to be updated
     * @param progress Progress to add
     * @param date Date of update
     */
    fun updateQuantitativeMetric(
        metric: QuantitativeMetric,
        progress: Double,
        date: LocalDate = LocalDate.now()
    ){
        viewModelScope.launch(ioDispatcher) {
            try {
                selectedGoal?.let {goal ->
                    require(goal.metricList.contains(metric)) {
                        "${metric.achievement} is not on the metrics list"
                    }
                    metric.addProgress(progress,date)
                    showGoal()
                } ?: throw Exception("No goal selected")
            } catch (e: Exception){
                _currentGoal.value = UiState.ERROR(e)
            }
        }
    }

    /**
     * Update completion of Qualitative Metric
     *
     * @param metric Metric to be updated
     */
    fun updateQualitativeMetric(
        metric: QualitativeMetric
    ){
        viewModelScope.launch(ioDispatcher) {
            try {
                selectedGoal?.let {goal ->
                    require(goal.metricList.contains(metric)) {
                        "${metric.achievement} is not on the metrics list"
                    }
                    metric.toggleCompletion()
                    showGoal()
                } ?: throw Exception("No goal selected")
            } catch (e: Exception){
                _currentGoal.value = UiState.ERROR(e)
            }
        }
    }

    /**
     * Add new task to goal
     *
     * @param task Task to be added
     */
    fun addTask(task: Task){
        viewModelScope.launch(ioDispatcher) {
            selectedGoal?.taskList?.add(task)
            showGoal()
        }
    }

}