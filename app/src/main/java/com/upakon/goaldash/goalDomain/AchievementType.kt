package com.upakon.goaldash.goalDomain

/**
 * Defines the type of achievements
 *
 * You can have several types of achievements, depending in what your goal is:
 *  - Qualitative
 *  - Quantitative
 */
interface AchievementType{

    /**
     * Returns if conditions of achievement have been completed
     *
     * @return if achievements have been completed
     */
    fun isCompleted() : Boolean

}
