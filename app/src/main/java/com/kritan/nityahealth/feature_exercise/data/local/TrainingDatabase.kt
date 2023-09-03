package com.kritan.nityahealth.feature_exercise.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Training::class], version = 3)
@TypeConverters(Converters::class)
abstract class TrainingDatabase : RoomDatabase() {
    abstract fun trainingDao(): TrainingDao
}