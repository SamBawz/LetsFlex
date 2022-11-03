package com.example.letsflex

import android.app.AlertDialog
import android.app.TimePickerDialog
import android.app.TimePickerDialog.OnTimeSetListener
import android.app.usage.UsageEvents
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.letsflex.databinding.ActivityExerciseDetailsBinding
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import java.lang.String
import java.util.*


private lateinit var binding: ActivityExerciseDetailsBinding


class ExerciseDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityExerciseDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Youtube player
        val youTubePlayerView = binding.youtubePlayerView
        lifecycle.addObserver(youTubePlayerView)
        youTubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                val videoId = "S0Q4gqBUs7c"
                youTubePlayer.loadVideo(videoId, 0f)
            }
        })

        //Time picker
        val timeButton : TextView = binding.txtExerciseNotificationTimePicker
        var hour : Int = 0
        var minute : Int = 0

        timeButton.setOnClickListener() {
            val onTimeSetListener = OnTimeSetListener { timePicker, selectedHour, selectedMinute ->
                    hour = selectedHour
                    minute = selectedMinute
                    timeButton.text = String.format(Locale.getDefault(), "%02d:%02d", hour, minute)
            }

            val timePickerDialog = TimePickerDialog(this,  /*style,*/onTimeSetListener, hour, minute, true)

            timePickerDialog.setTitle("Select Time")
            timePickerDialog.show()
        }

        //Start exercise button
        binding.btnStartExercise.setOnClickListener() {
            startExercise()
        }
    }

    private fun startExercise() {
        val builder = AlertDialog.Builder(this@ExerciseDetailsActivity)
        builder.setTitle("Do you want to start this exercise?")
        //Possible to set a message as well
        //builder.setMessage(getString(R.string.yes))
        builder.setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, id ->
            //If yes is pressed
            val intent = Intent(this@ExerciseDetailsActivity, CountdownTimer::class.java)
            startActivity(intent)
            dialog.cancel()
        })
        builder.setNegativeButton("No", DialogInterface.OnClickListener { dialog, id ->
            //If no is pressed
            dialog.cancel()
        })
        val alert = builder.create()
        alert.show()
    }
}