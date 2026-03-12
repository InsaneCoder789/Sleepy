package com.example.sleeptracker.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.SystemClock
import android.widget.Chronometer
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.sleeptracker.R
import com.example.sleeptracker.repository.SleepRepository
import com.example.sleeptracker.uiscreen.SummaryActivity
import com.example.sleeptracker.viewmodel.SleepViewModel
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {

    private lateinit var chronometer: Chronometer
    private lateinit var sleepStageText: TextView

    private lateinit var btnStart: MaterialButton
    private lateinit var btnStop: MaterialButton
    private lateinit var btnReset: MaterialButton
    private lateinit var btnSettings: MaterialButton

    private lateinit var viewModel: SleepViewModel
    private lateinit var repository: SleepRepository

    private val handler = Handler(Looper.getMainLooper())

    private val sleepStageChecker = object : Runnable {
        override fun run() {

            val stage = viewModel.getStage()
            sleepStageText.text = stage

            handler.postDelayed(this, 1000)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        chronometer = findViewById(R.id.chronometer)
        sleepStageText = findViewById(R.id.sleepStageText)

        btnStart = findViewById(R.id.btnStart)
        btnStop = findViewById(R.id.btnStop)
        btnReset = findViewById(R.id.btnReset)
        btnSettings = findViewById(R.id.btnSettings)

        viewModel = ViewModelProvider(this)[SleepViewModel::class.java]
        repository = SleepRepository(this)

        btnStart.setOnClickListener { startSession() }
        btnStop.setOnClickListener { stopSession() }
        btnReset.setOnClickListener { resetSession() }

        btnSettings.setOnClickListener {

            startActivity(Intent(this, SettingsActivity::class.java))
        }
    }

    private fun startSession() {

        if (viewModel.isRunning()) {
            Toast.makeText(this, "Session already running", Toast.LENGTH_SHORT).show()
            return
        }

        val startTime = viewModel.startSleep()

        chronometer.base = startTime
        chronometer.start()

        sleepStageText.text = "Light Sleep"

        handler.post(sleepStageChecker)
    }

    private fun stopSession() {

        val session = viewModel.stopSleep()

        if (session == null) {

            Toast.makeText(this, "Start session first", Toast.LENGTH_SHORT).show()
            return
        }

        chronometer.stop()

        repository.saveSleep(session.duration)

        val avg = repository.getAverageSleep() / 60000

        Toast.makeText(
            this,
            "Sleep saved. Avg sleep: $avg minutes",
            Toast.LENGTH_LONG
        ).show()

        handler.removeCallbacks(sleepStageChecker)

        startActivity(Intent(this, SummaryActivity::class.java))
    }

    private fun resetSession() {

        viewModel.reset()

        chronometer.stop()
        chronometer.base = SystemClock.elapsedRealtime()

        sleepStageText.text = "Light Sleep"

        handler.removeCallbacks(sleepStageChecker)

        Toast.makeText(this, "Session reset", Toast.LENGTH_SHORT).show()
    }
}