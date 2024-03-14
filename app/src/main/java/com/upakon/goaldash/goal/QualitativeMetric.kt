package com.upakon.goaldash.goal

import java.time.LocalDate

/**
 * Qualitative Achievements
 *
 * This would represent a project or a task that may or may not be completed
 * i.e. a trip
 *
 */
data class QualitativeMetric(
    override val achievement: String
) : Metric {

    //tracks if the task or project has been completed
    private var completed = false
    var completionDate: LocalDate? = null

    override fun isCompleted(): Boolean {
        return completed
    }

    fun toggleCompletion(){
        completed = !completed
        if(completed){
            completionDate = LocalDate.now()
        } else{
            completionDate = null
        }
    }

}