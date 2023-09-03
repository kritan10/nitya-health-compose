package com.kritan.nityahealth.feature_exercise.presentation.exercise_home_screen.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.kritan.nityahealth.feature_exercise.data.local.Training
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.temporal.TemporalAdjusters

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MyWeek(trainings: List<Training>) {
    val (currentDate, dateList) = getTodayAndCurrentWeek()
    Row(Modifier.fillMaxWidth(), Arrangement.SpaceBetween) {
        dateList.forEach { date ->
            val modifier = Modifier
                .then(
                    if (date.dayOfWeek == currentDate.dayOfWeek) {
                        Modifier.border(2.dp, MaterialTheme.colorScheme.primary, CircleShape)
                    } else {
                        Modifier.border(1.dp, Color.LightGray, CircleShape)
                    }
                )
                .size(36.dp)

            var textColor: Color = Color.LightGray
            trainings.forEach {
                if (it.completedOn.dayOfMonth == date.dayOfMonth) {
                    textColor = MaterialTheme.colorScheme.primary
                }
            }
            MyWeekItem(modifier, textColor, date.dayOfWeek.name)
        }
    }
}


@Composable
private fun MyWeekItem(modifier: Modifier, textColor: Color, day: String) {
    Box(modifier = modifier) {
        Text(
            text = day.first().toString(),
            modifier = Modifier.align(Alignment.Center),
            color = textColor
        )
    }

}


@RequiresApi(Build.VERSION_CODES.O)
private fun getTodayAndCurrentWeek(): Pair<LocalDate, List<LocalDate>> {
    // Get the current date
    val currentDate = LocalDate.now()


    // Find the start date of the current week (Sunday)
    val startOfWeek = currentDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY))

    // Find the end date of the current week (Saturday)
    val endOfWeek = currentDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.SATURDAY))

    // Create an ArrayList to store the range of dates
    val dateRange: MutableList<LocalDate> = mutableListOf()


    // Iterate through the days of the week and add them to the ArrayList
    var currentDay = startOfWeek
    while (!currentDay.isAfter(endOfWeek)) {
        dateRange.add(currentDay)
        currentDay = currentDay.plusDays(1)
    }

    return Pair(currentDate, dateRange)
}