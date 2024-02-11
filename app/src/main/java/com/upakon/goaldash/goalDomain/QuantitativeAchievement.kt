package com.upakon.goaldash.goalDomain

import com.upakon.goaldash.utils.Date
import java.util.Calendar


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
class QuantitativeAchievement(
    var required: Double,
    val measure: String
) : AchievementType {

    //saves the current value
    private val current get() = tracker.values.sum()

    //saves the progress by date
    private val tracker: MutableMap<Date,Double> = mutableMapOf()

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
        date: Date,
        amount: Double
    ){
        tracker[date] = amount
    }

    /**
     * Gets the total that was tracked in a day
     *
     * @param date The day you want to check
     * @return how much have you tracked that day
     */
    fun getAmountTrackedInDay(date: Date) : Double {
        return tracker.filterKeys {
            it.isInDay(date)
        }.values.sum()
    }

}