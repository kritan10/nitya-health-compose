package com.kritan.nityahealth.feature_fitness.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "training")
data class Training(
    @PrimaryKey val packageId: Int,
    val completedOn: LocalDate
)