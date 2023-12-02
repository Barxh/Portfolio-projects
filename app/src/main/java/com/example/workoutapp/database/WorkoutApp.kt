package com.example.workoutapp.database

import android.app.Application
import com.example.workoutapp.database.HistoryDatabase

class WorkoutApp : Application(){

    val db by lazy{
        HistoryDatabase.getInstance(this)
    }
}