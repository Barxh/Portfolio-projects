package com.example.workoutapp.activities

import android.app.Dialog
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.workoutapp.Constants
import com.example.workoutapp.models.ExerciseModel
import com.example.workoutapp.adapters.ExerciseStatusAdapter
import com.example.workoutapp.R
import com.example.workoutapp.databinding.ActivityExerciseBinding
import com.example.workoutapp.databinding.DialogStopWorkoutBinding
import java.util.Locale

class ExerciseActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    private lateinit var binding: ActivityExerciseBinding
    private var restTimer: CountDownTimer? = null
    private var restProgress: Int = 0

    private var exerciseTimer: CountDownTimer? = null
    private var exerciseProgress: Int = 0

    private var exerciseList : ArrayList<ExerciseModel>? = null
    private var currentExercisePosition = 0

    private var exerciseAdapter : ExerciseStatusAdapter? = null
    lateinit var tts: TextToSpeech
    private var mediaPlayer: MediaPlayer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        tts = TextToSpeech(this@ExerciseActivity, this@ExerciseActivity)
        setSupportActionBar(binding.toolbarExercise)


        if(supportActionBar!=null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        binding.toolbarExercise.setNavigationOnClickListener {

            customDialogForBackButton()
        }

        exerciseList = Constants.defaultExerciseList()
        setupExerciseStatusAdapter()
        setupRestView()
    }

    private fun customDialogForBackButton() {
        val dialog  = Dialog(this)
        val dialogBinding = DialogStopWorkoutBinding.inflate(layoutInflater)
        dialog.setContentView(dialogBinding.root)

        dialog.setCanceledOnTouchOutside(false)
        dialogBinding.buttonYes.setOnClickListener {
            this@ExerciseActivity.finish()
            dialog.dismiss()
        }
        dialogBinding.buttonNo.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }

    override fun onBackPressed() {
        customDialogForBackButton()

    }
    private fun setupExerciseStatusAdapter(){
        binding.recyclerViewExerciseStatus.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)
        exerciseAdapter = ExerciseStatusAdapter(exerciseList!!)
        binding.recyclerViewExerciseStatus.adapter =exerciseAdapter
    }
    private fun setProgressBar(){
        binding.progressBar.progress = restProgress
        restTimer = object : CountDownTimer(10000, 1000){
            override fun onTick(p0: Long) {
                restProgress++
                binding.progressBar.progress = 10 - restProgress
                binding.textViewTimer.text = (10 - restProgress).toString()

            }

            override fun onFinish() {
                exerciseList!![currentExercisePosition].setIsSelected(true)
                exerciseAdapter!!.notifyDataSetChanged()
                for(exercise: ExerciseModel in exerciseList!!){
                    Log.d("Recyclerview", "onFinish: ${exercise.getIsCompleted()}   ${exercise.getIsSelected()}")
                }
                setupExerciseView()

            }


        }.start()
    }


    private fun setProgressBarExercise(){
        binding.progressBarExercise.progress = exerciseProgress
        exerciseTimer = object : CountDownTimer(30000, 1000){
            override fun onTick(p0: Long) {
                exerciseProgress++
                binding.progressBarExercise.progress = 30 - exerciseProgress
                binding.textViewTimerExercise.text = (30 - exerciseProgress).toString()
            }

            override fun onFinish() {
                exerciseList!![currentExercisePosition].setIsSelected(false)
                exerciseList!![currentExercisePosition].setIsCompleted(true)

                exerciseAdapter!!.notifyDataSetChanged()
                for(exercise: ExerciseModel in exerciseList!!){
                    Log.d("Recyclerview", "onFinishExercise: ${exercise.getIsCompleted()}   ${exercise.getIsSelected()}")
                }
                currentExercisePosition++



                exerciseProgress = 0
                if(exerciseList!!.size > currentExercisePosition){
                    setupRestView()

                }else{
                    finish()
                    val intent = Intent(this@ExerciseActivity, FinishActivity::class.java)
                    startActivity(intent)
                }


            }


        }.start()
    }


    override fun onDestroy() {
        super.onDestroy()
        if(restTimer!=null){
            restTimer!!.cancel()
            restProgress = 0
        }

        if(exerciseTimer!=null){
            restTimer!!.cancel()
            exerciseProgress = 0

        }
        tts.stop()
        tts.shutdown()

        mediaPlayer?.stop()

    }

    private fun setupRestView() {

        try{
            val soundURI = Uri.parse("android.resource://com.example.workoutapp/"+ R.raw.press_start)
            mediaPlayer = MediaPlayer.create(applicationContext, soundURI)
            mediaPlayer?.isLooping = false
            mediaPlayer?.start()
        }catch (e:Exception){
            e.stackTrace
        }
        binding.textViewNextExercise.text = exerciseList!![currentExercisePosition].getName()
        binding.layoutProgressBar.visibility = View.VISIBLE
        binding.Title.visibility = View.VISIBLE
        binding.textViewUpcoming.visibility = View.VISIBLE
        binding.textViewNextExercise.visibility = View.VISIBLE
        binding.layoutProgressBarExercise.visibility = View.GONE

        binding.textViewExerciseName.visibility = View.GONE
        binding.imageExercise.visibility = View.GONE

        if (restTimer != null) {
            restTimer?.cancel()
            restProgress = 0
        }
        setProgressBar()
    }
    private fun setupExerciseView(){
        binding.layoutProgressBar.visibility = View.GONE
        binding.Title.visibility = View.GONE
        binding.textViewUpcoming.visibility = View.GONE
        binding.textViewNextExercise.visibility = View.GONE
        binding.layoutProgressBarExercise.visibility= View.VISIBLE

        binding.textViewExerciseName.visibility = View.VISIBLE
        binding.imageExercise.visibility = View.VISIBLE

        if (exerciseTimer != null) {
            exerciseTimer?.cancel()
            exerciseProgress = 0
        }
        binding.imageExercise.setImageResource(exerciseList!![currentExercisePosition].getImage())
        binding.textViewExerciseName.text = exerciseList!![currentExercisePosition].getName()

        tts.speak("Do: ${binding.textViewExerciseName.text}", TextToSpeech.QUEUE_FLUSH, null, "")

        setProgressBarExercise()
    }

    override fun onInit(status: Int) {
        if(status == TextToSpeech.SUCCESS){
            tts.language = Locale.US
        }
    }

}