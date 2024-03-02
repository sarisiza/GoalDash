package com.upakon.goaldash.goal

import com.upakon.goaldash.di.GoalsApp
import com.upakon.goaldash.utils.Date
import java.util.Calendar


/**
 * Represents a Goal
 * @author Sara Iza
 *
 * This class represents Goal or an objective
 *
 * @property what The name of the goal
 * @property why Why do you want to achieve this?
 * @property metrics How will you know you achieve it?
 * @property endDate When should it be achieved?
 * @property importance How bad do you want it? (1 to 10)
 * @property taskList List of tasks on this goal
 * @constructor Creates a new goal
 */
data class Goal(
    var what: String,
    var why: String,
    val metrics: MutableList<AchievementType>,
    var importance: Int,
    var endDate: Date,
    val reward: String,
    val taskList: MutableList<Task> = mutableListOf()
){

    private val endDateCalendar = Calendar.Builder()
        .setDate(endDate.year,endDate.month,endDate.day)
        .build()

    fun getTimer() : Int {
        return endDateCalendar.compareTo(Calendar.getInstance()) / 86400000
    }

    fun changeEndDate(newDate: Date){
        endDate = newDate
        endDateCalendar.set(newDate.year,newDate.month,newDate.day)
    }

}
