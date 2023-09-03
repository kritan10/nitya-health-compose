package com.kritan.nityahealth.feature_exercise.presentation.exercise_home_screen.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kritan.nityahealth.ui.modifiers.mShadow
import com.kritan.nityahealth.ui.theme.mRoundedCorner
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MyCalendar() {
    val (currentDate, dateList) = getCurrentDateAndDateList()

    Row(
        Modifier
            .fillMaxWidth()
            .then(mShadow()),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        dateList.forEach { date ->
            val modifier = Modifier
                .then(
                    if (currentDate.dayOfMonth == date.dayOfMonth) {
                        Modifier.background(
                            MaterialTheme.colorScheme.primary,
                            shape = mRoundedCorner
                        )
                    } else {
                        Modifier
                    }
                )
                .padding(horizontal = 16.dp, vertical = 8.dp)

            MyCalendarItem(modifier, date.dayOfWeek.name, date.dayOfMonth)
        }
    }
}


@Composable
private fun MyCalendarItem(modifier: Modifier, day: String, date: Int) {
    Column(modifier, Arrangement.Center, Alignment.CenterHorizontally) {
        Text(day.substring(0, 3))
        Spacer(modifier = Modifier.height(8.dp))
        Text(date.toString())
    }
}


@RequiresApi(Build.VERSION_CODES.O)
private fun getCurrentDateAndDateList(): Pair<LocalDate, List<LocalDate>> {
    // Get the current date
    val currentDate = LocalDate.now()


    // Find the start date of the current week (Sunday)
//    val startOfWeek = currentDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY))

    // Find the end date of the current week (Saturday)
//    val endOfWeek = currentDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.SATURDAY))

    // Create an ArrayList to store the range of dates
    val dateRange: MutableList<LocalDate> = mutableListOf()

    for (i in -2..2) {
        val date = currentDate.plusDays(i.toLong())
        dateRange.add(date)
    }

    // Iterate through the days of the week and add them to the ArrayList
//    var currentDay = startOfWeek
//    while (!currentDay.isAfter(endOfWeek)) {
//        dateRange.add(currentDay)
//        currentDay = currentDay.plusDays(1)
//    }

    return Pair(currentDate, dateRange)
}

