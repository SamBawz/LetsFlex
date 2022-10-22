package com.example.letsflex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView

class CountdownTimer : AppCompatActivity() {

    private lateinit var timer: CountDownTimer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_countdown_timer)

        val textView = findViewById<TextView>(R.id.textView)

        object : CountDownTimer(5_000, 1){
            override fun onTick(remaining: Long) {
                textView.text = remaining.toString()
            }
            override fun onFinish() {
                textView.text = "Done!"
            }
        }
    }

    override fun onStart(){
        super.onStart()
        timer.start()
    }

    override fun onStop(){
        super.onStop()
        timer.cancel()
    }
}