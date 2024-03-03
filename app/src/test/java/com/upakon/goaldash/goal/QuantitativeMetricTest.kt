package com.upakon.goaldash.goal

import io.mockk.clearAllMocks
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test
import java.time.LocalDate

class QuantitativeMetricTest {

    private lateinit var testMetric: QuantitativeMetric

    @Before
    fun setUp() {
        testMetric = QuantitativeMetric(
            "test metric",
            8.0,
            "glasses"
        )
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `test addProgress`(){
        testMetric.addProgress(
            1.0
        )
        assertEquals(1.0,testMetric.current,0.0)
        testMetric.addProgress(
            1.0
        )
        assertEquals(2.0,testMetric.current,0.0)
    }

    @Test
    fun `test checkCompletion when is not completed`(){
        assertFalse(testMetric.isCompleted())
    }

    @Test
    fun `test checkCompletion when is completed`(){
        testMetric.addProgress(8.0)
        assertTrue(testMetric.isCompleted())
    }

}