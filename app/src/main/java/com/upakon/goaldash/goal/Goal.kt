package com.upakon.goaldash.goal

import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import java.util.UUID


/**
 * Represents a Goal
 * @author Sara Iza
 *
 * This class represents Goal or an objective
 *
 * @property what The name of the goal
 * @property why Why do you want to achieve this?
 * @property metricList How will you know you achieve it?
 * @property endDate When should it be achieved?
 * @property importance How bad do you want it? (1 to 10)
 * @property taskList List of tasks on this goal
 * @constructor Creates a new goal
 */
data class Goal(
    var what: String,
    var why: String,
    val metricList: MutableList<Metric>,
    var importance: Int,
    var endDate: LocalDateTime,
    var reward: String,
    val taskList: MutableList<Task> = mutableListOf(),
    val id: UUID = UUID.randomUUID()
){

    /**
     * Method to get the days that are left until the end date
     *
     * @return days until end date
     */
    fun getTimer() : Long {
        return LocalDateTime.now().until(endDate,ChronoUnit.DAYS) + 1
    }


}
