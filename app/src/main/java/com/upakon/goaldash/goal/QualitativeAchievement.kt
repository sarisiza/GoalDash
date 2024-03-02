package com.upakon.goaldash.goal

/**
 * Qualitative Achievements
 *
 * This would represent a project or a task that may or may not be completed
 * i.e. a trip
 *
 * @constructor Creates a Quality Achievement
 */
data class QualitativeAchievement(
    override val achievement: String
) : AchievementType {

    //tracks if the task or project has been completed
    var completed = false

    override fun isCompleted(): Boolean {
        return completed
    }
}