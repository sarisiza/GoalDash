package com.upakon.goaldash.goalDomain

import com.upakon.goaldash.utils.Date


/**
 * Represents a Goal
 * @author Sara Iza
 *
 * This class represents Goal or an objective
 *
 * @property name The name of the goal
 * @property description What do you want to achieve?
 * @property reason Why do you want to achieve this?
 * @property measure How will you know you achieve it?
 * @property startDate When are you starting?
 * @property endDate When should it be achieved?
 * @property priority Low, Medium or High priority
 * @constructor Creates a new goal
 */
class Goal(
    var name: String,
    var description: String,
    var reason: String,
    val measure: AchievementType,
    var startDate: Date,
    var endDate: Date,
    val priority: Priority
){

    //actionPlan for the goal
    val actionPlan: MutableList<Goal> = mutableListOf()

    //the goal is achieved
    val completed get() = measure.isCompleted()

    //notes of the Goal
    var notes = ""

    /**
     * Adds an [actionItem] to the [actionPlan]
     */
    fun addActionItem(actionItem: Goal){
        actionPlan.add(actionItem)
    }

    /**
     * Removes an [actionItem] from the [actionPlan]
     */
    fun removeActionItem(actionItem: Goal){
        actionPlan.remove(actionItem)
    }

    fun isExpired(): Boolean{
        val now = Date.now()
        return (endDate.isBefore(now) && !completed)
    }

}
