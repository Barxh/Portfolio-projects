package com.example.workoutapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.workoutapp.databinding.ActivityBmicalculatorBinding
import kotlin.math.roundToInt

class BMICalculatorActivity : AppCompatActivity() {

    lateinit var binding: ActivityBmicalculatorBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBmicalculatorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbarBmi)


        if(supportActionBar!=null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        binding.toolbarBmi.setNavigationOnClickListener {

            onBackPressedDispatcher.onBackPressed()
        }
        binding.buttonCalculate.setOnClickListener {
            setupCalculator()
        }





    }

    private fun setupCalculator() {

        val height: Int? = binding.editTextHeight.text.toString().toIntOrNull()
        val weight: Int? = binding.editTextWeight.text.toString().toIntOrNull()
        if(height!=null && weight!=null){
            val heightDouble: Double = height / 100.00
            val bmi = (weight/(heightDouble*heightDouble)*100).roundToInt()/100.0
            binding.textViewResult.text = "YOUR BMI\n${bmi}"
            binding.textViewResult.visibility = View.VISIBLE
            var description = when{
                bmi < 15 -> "Very severely underweight"
                (bmi.compareTo(15f) > 0 && bmi.compareTo(16f) <= 0) -> "Severely underweight"
                (bmi.compareTo(16f) > 0 && bmi.compareTo(18.5f) <= 0) -> "Underweight"
                (bmi.compareTo(18.5f) > 0 && bmi.compareTo(25f) <= 0) -> "Normal"
                (bmi.compareTo(25f) > 0 && bmi.compareTo(30f) <= 0) -> "Overweight"
                (bmi.compareTo(30f) > 0 && bmi.compareTo(35f) <= 0) -> "Obese Class | (Moderately obese)"
                (bmi.compareTo(35f) > 0 && bmi.compareTo(40f) <= 0) -> "Obese Class || (Severely obese)"
                else -> "Obese Class ||| (Very Severely obese)"


            }
            binding.textViewAdvice.text = description
            binding.textViewAdvice.visibility = View.VISIBLE

        }else{
            Toast.makeText(this@BMICalculatorActivity, "Invalid input! Please, try again!",Toast.LENGTH_LONG).show()

        }

    }
}