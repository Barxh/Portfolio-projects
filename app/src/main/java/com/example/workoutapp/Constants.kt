package com.example.workoutapp

import com.example.workoutapp.models.ExerciseModel

object Constants {

    fun defaultExerciseList(): ArrayList<ExerciseModel>{

        val exercise = arrayListOf<ExerciseModel>(
            ExerciseModel(
                1,
                "Jumping Jacks",
                R.drawable.ic_jumping_jacks,
                false,
                false
            ),
            ExerciseModel(
                2,
                "Abdominal Crunch",
                R.drawable.ic_abdominal_crunch,
                false,
                false
            ),
            ExerciseModel(
                3,
                "High Knees Running In Place",
                R.drawable.ic_high_knees_running_in_place,
                false,
                false
            ),
            ExerciseModel(
                4,
                "Lunge",
                R.drawable.ic_lunge,
                false,
                false
            ), ExerciseModel(
                5,
                "Plank",
                R.drawable.ic_plank,
                false,
                false
            ), ExerciseModel(
                6,
                "Push Up",
                R.drawable.ic_push_up,
                false,
                false
            ),
            ExerciseModel(
                7,
                "Push Up And Rotation",
                R.drawable.ic_push_up_and_rotation,
                false,
                false
            ),
            ExerciseModel(
                8,
                "Side Plank",
                R.drawable.ic_side_plank,
                false,
                false
            ),
            ExerciseModel(
                9,
                "Squat",
                R.drawable.ic_squat,
                false,
                false
            ),
            ExerciseModel(
                10,
                "Step Up Onto Cheir",
                R.drawable.ic_step_up_onto_chair,
                false,
                false
            ),
            ExerciseModel(
                11,
                "Triceps Dip On Chair",
                R.drawable.ic_triceps_dip_on_chair,
                false,
                false
            ),
            ExerciseModel(
                12,
                "Wall Sit",
                R.drawable.ic_wall_sit,
                false,
                false
            )
        )

        return exercise
    }

}