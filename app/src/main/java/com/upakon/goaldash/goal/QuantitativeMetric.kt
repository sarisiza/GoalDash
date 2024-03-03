package com.upakon.goaldash.goal

import java.time.LocalDate
import java.time.LocalDateTime


/**
 * Quantitative Achievements
 *
 * This would represent something that needs to be numerically tracked over time
 * i.e. Saving Goal
 *
 * @property required the goal amount
 * @property measure description of what are you tracking
 * @constructor Creates a Quantity Achievement
 */
data class QuantitativeMetric(
    override val achievement: String,
    var required: Double,
    val measure: String
) : Metric {

    //saves the current value
    val current get() = tracker.values.sum()

    //saves the progress by date
    private val tracker: MutableMap<LocalDate,Double> = mutableMapOf()

    override fun isCompleted(): Boolean {
        return current >= required
    }

    /**
     * Adds a new progress track
     *
     * @param date When are you making the progress
     * @param amount How much progress did you make
     */
    fun addProgress(
        amount: Double,
        date: LocalDate = LocalDate.now()
    ){
        tracker[date] = tracker[date]?.plus(amount) ?: amount
    }


}