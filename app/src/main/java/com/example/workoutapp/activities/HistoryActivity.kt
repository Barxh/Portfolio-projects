package com.example.workoutapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.workoutapp.adapters.HistoryAdapter
import com.example.workoutapp.database.HistoryDao
import com.example.workoutapp.database.WorkoutApp
import com.example.workoutapp.databinding.ActivityHistoryBinding
import kotlinx.coroutines.launch

class HistoryActivity : AppCompatActivity() {
    lateinit var binding: ActivityHistoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbarHistory)
        if(supportActionBar!=null){
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        }else{
            binding.toolbarHistory.setNavigationOnClickListener {
                onBackPressedDispatcher.onBackPressed()
            }
        }
        val dao = (application as WorkoutApp).db.historyDao()
        getAllCompletedDatabases(dao)
    }

    private fun getAllCompletedDatabases(historyDao: HistoryDao){
        lifecycleScope.launch {
            historyDao.fetchAllDates().collect{
                allCompletedDatesList->
                if(allCompletedDatesList.isNotEmpty()){
                    binding.recyclerViewHistory.layoutManager = LinearLayoutManager(this@HistoryActivity,LinearLayoutManager.VERTICAL,false)
                    val dates = ArrayList<String>()
                    for(i in allCompletedDatesList)
                        dates.add(i.date)

                    val historyAdapter = HistoryAdapter(dates)
                    binding.recyclerViewHistory.adapter = historyAdapter
                }
            }
        }
    }
}