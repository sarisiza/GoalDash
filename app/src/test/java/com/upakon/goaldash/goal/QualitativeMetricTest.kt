package com.upakon.goaldash.goal

import io.mockk.clearAllMocks
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test

class QualitativeMetricTest {

    private lateinit var testMetric: QualitativeMetric

    @Before
    fun setUp() {
        testMetric = QualitativeMetric("test")
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `test isCompleted when metric is not completed`(){
        assertFalse(testMetric.isCompleted())
    }

    @Test
    fun `test isCompleted when metric is completed`(){
        testMetric.toggleCompletion()
        assertTrue(testMetric.isCompleted())
    }

}