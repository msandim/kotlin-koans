package iii_conventions

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate> {
    override operator fun compareTo(other: MyDate): Int = when {
        year != other.year -> year - other.year
        month != other.month -> month - other.month
        dayOfMonth != other.dayOfMonth -> dayOfMonth - other.dayOfMonth
        else -> 0
    }

     operator fun rangeTo(other: MyDate) = DateRange(this, other)
}

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR;
}

class RepeatedTimeInterval(val timeInterval: TimeInterval, val times: Int)
operator fun TimeInterval.times(value: Int) = RepeatedTimeInterval(this, value)
operator fun MyDate.plus(repetition: RepeatedTimeInterval) = addTimeIntervals(repetition.timeInterval, repetition.times)

class DateRange(override val start: MyDate, override val endInclusive: MyDate) : ClosedRange<MyDate>, Iterable<MyDate> {

    class MyDateIterator(val start: MyDate, val end: MyDate, var current: MyDate = start.copy()) : Iterator<MyDate> {
        override fun hasNext(): Boolean {
            return current <= end
        }

        override fun next(): MyDate {
            // Advance one day and return it:
            val returnValue = current
            current = current.nextDay()
            return returnValue
        }

    }

    override fun iterator(): Iterator<MyDate> {
        return MyDateIterator(start, endInclusive)
    }
}

operator fun MyDate.plus(timeInterval: TimeInterval): MyDate = addTimeIntervals(timeInterval, 1)