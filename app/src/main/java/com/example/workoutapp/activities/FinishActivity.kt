package com.example.workoutapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.workoutapp.database.HistoryDao
import com.example.workoutapp.database.HistoryEntity
import com.example.workoutapp.database.WorkoutApp
import com.example.workoutapp.databinding.ActivityFinishBinding
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class FinishActivity : AppCompatActivity() {

    lateinit var binding: ActivityFinishBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFinishBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbarFinish)
        if(supportActionBar!=null){
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }
        binding.toolbarFinish.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
        binding.buttonFinish.setOnClickListener {
            finish()
        }

        val dao = (application as WorkoutApp).db.historyDao()

        addToDatabase(dao)

    }

    private fun addToDatabase(historyDao: HistoryDao){

        val c = Calendar.getInstance()
        val dateTime = c.time
        val sdf = SimpleDateFormat("dd MMM yyyy HH:mm:ss", Locale.getDefault())
        val date = sdf.format(dateTime)
        lifecycleScope.launch {
            historyDao.insert(HistoryEntity(date))
        }

    }
}