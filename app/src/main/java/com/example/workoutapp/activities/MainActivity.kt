package com.example.workoutapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.workoutapp.R
import com.example.workoutapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.layoutStartButton.setOnClickListener {

            val intent: Intent = Intent(this, ExerciseActivity::class.java)
            startActivity(intent)

        }
        binding.layoutBmiButton.setOnClickListener {
            startActivity(Intent(this, BMICalculatorActivity::class.java))
        }

        binding.layoutHistoryButton.setOnClickListener {
            startActivity(Intent(this, HistoryActivity::class.java))
        }

    }
}