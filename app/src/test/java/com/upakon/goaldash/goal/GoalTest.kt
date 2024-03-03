package com.upakon.goaldash.goal

import io.mockk.clearAllMocks
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test
import java.time.LocalDateTime

class GoalTest {

    private lateinit var testGoal: Goal

    @Before
    fun setUp() {
        testGoal = Goal(
            "test goal",
            "because test",
            mutableListOf(),
            5,
            LocalDateTime.now().plusDays(40),
            "test reward"
        )
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `test getTimer`(){
        val result = testGoal.getTimer()
        assertEquals(40,result)
    }

}