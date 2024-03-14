package com.upakon.goaldash.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.upakon.goaldash.goal.Goal
import com.upakon.goaldash.goal.QuantitativeMetric
import com.upakon.goaldash.intents.UiState
import io.mockk.clearAllMocks
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.time.LocalDateTime

@OptIn(ExperimentalCoroutinesApi::class)
class GoalsViewModelTest {

    @get:Rule private val instantTask = InstantTaskExecutorRule()

    private lateinit var testViewModel: GoalsViewModel

    private val testDispatcher = UnconfinedTestDispatcher()
    private val testScope = TestScope(testDispatcher)

    @Before
    fun setUp() {
        testViewModel = GoalsViewModel(testDispatcher)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `test modifyRequirement success`() = runTest(testDispatcher){
        val goal = Goal(
            "test",
            "test",
            mutableListOf(),
            1,
            LocalDateTime.of(2025,6,3,12,12),
            "test"
        )
        testViewModel.setCurrentGoal(goal)
        val metric = QuantitativeMetric(
            "test metric",
            10.0,
            "test"
        )
        testViewModel.createNewMetric(metric)
        advanceUntilIdle()
        var newGoal = (testViewModel.currentGoal.value as UiState.SUCCESS).information
        assertEquals(1,newGoal.metricList.size)
        assertEquals(10.0,(newGoal.metricList[0] as QuantitativeMetric).required,0.0)
        testViewModel.modifyRequirement(metric,11.0)
        advanceUntilIdle()
        newGoal = (testViewModel.currentGoal.value as UiState.SUCCESS).information
        assertEquals(11.0,(newGoal.metricList[0] as QuantitativeMetric).required,0.0)
    }

}