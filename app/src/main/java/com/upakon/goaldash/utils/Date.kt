package com.upakon.goaldash.utils

import java.util.Calendar
import java.util.TimeZone

/**
 * Helper class to manage dates from calendar
 *
 * Using this helper class to create and access dates and times from the calendar easily
 */

class Date internal constructor(){

    private val timeZone = TimeZone.getDefault()

    private val calendar: Calendar = Calendar.getInstance(timeZone)

    val day: Int get() = calendar.get(Calendar.DATE)

    val month: Int get() = calendar.get(Calendar.MONTH)

    val year: Int get() = calendar.get(Calendar.YEAR)

    val hour: Int get() = calendar.get(Calendar.HOUR)

    val minute: Int get() = calendar.get(Calendar.HOUR)

    /**
     * Returns weather or not the date is within a certain day
     *
     * @param date The day you want to check
     * @return true if this has the same day, month and year as the given date
     */
    fun isInDay(date: Date): Boolean{
        return this.day == date.day && this.month == date.month && this.year == date.year
    }

    /**
     * Tells you if the date is before a given date
     *
     * @param date Date you want to check
     * @return true if this is before the given date
     */
    fun isBefore(date: Date) = calendar.before(date.calendar)

    /**
     * Tells you if the date is after a given date
     *
     * @param date Date you want to check
     * @return true if this is after the given date
     */
    fun isAfter(date: Date) = calendar.after(date.calendar)

    /**
     * Using a Factory pattern to create a new date
     */
    object Builder{

        private val date = Date()

        fun setDay(day: Int) : Builder{
            date.calendar.set(Calendar.DATE,day)
            return this
        }

        fun setMonth(month: Int) : Builder{
            date.calendar.set(Calendar.MONTH,month)
            return this
        }

        fun setYear(year: Int) : Builder{
            date.calendar.set(Calendar.YEAR, year)
            return this
        }

        fun setTime(hour: Int, minute: Int) : Builder{
            date.calendar.apply {
                set(Calendar.HOUR,hour)
                set(Calendar.MINUTE,minute)
                set(Calendar.SECOND,0)
            }
            return this
        }

        fun build(): Date = date

    }

    companion object{
        fun now() : Date{
            return Date()
        }
    }

}