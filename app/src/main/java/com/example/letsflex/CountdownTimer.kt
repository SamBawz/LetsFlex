package com.example.letsflex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import me.tankery.lib.circularseekbar.CircularSeekBar

class CountdownTimer : AppCompatActivity() {

    private lateinit var timer: CountDownTimer
    private lateinit var CircularSeekBar: CircularSeekBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_countdown_timer)

        val textView = findViewById<TextView>(R.id.txtTimer)
        val seekbar = findViewById<CircularSeekBar>(R.id.seekbar)
        val startButton = findViewById<Button>(R.id.btnStartTimer)

        //Time of the exercise in seconds
        val time = 120
        var minutes: Int
        var minutesString: String
        var seconds: Int
        var secondsString: String

        timer = object : CountDownTimer((time * 1000).toLong(), 1){
            override fun onTick(remaining: Long) {
                minutes = remaining.toInt() / 1000 / 60
                seconds = remaining.toInt() / 1000 % 60
                minutesString = minutes.toString()
                secondsString = seconds.toString()
                if (seconds < 10) {
                    secondsString = "0" + seconds.toString()
                }

                textView.text = minutesString + ":" + secondsString
                seekbar.progress = seekbar.max - remaining.toFloat() / 1000;
            }
            override fun onFinish() {
                textView.text = "Well done!"
            }
        }

        startButton.setOnClickListener() {
            seekbar.progress = 0.toFloat()
            seekbar.max = time.toFloat()
            timer.start()
        }
    }

    override fun onStop(){
        super.onStop()
        timer.cancel()
    }
}