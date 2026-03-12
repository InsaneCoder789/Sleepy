package com.example.sleeptracker.ui

import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sleeptracker.R
import com.example.sleeptracker.repository.SleepRepository
import com.google.android.material.button.MaterialButton
import java.util.Calendar

class SettingsActivity : AppCompatActivity() {

    private lateinit var repository: SleepRepository
    private lateinit var btnGoal: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        repository = SleepRepository(this)

        btnGoal = findViewById(R.id.btnGoal)

        btnGoal.setOnClickListener {

            val calendar = Calendar.getInstance()

            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)

            val timePicker = TimePickerDialog(
                this,
                { _, selectedHour, selectedMinute ->

                    val goalMinutes = selectedHour * 60 + selectedMinute

                    repository.saveGoal(goalMinutes)

                    Toast.makeText(
                        this,
                        "Goal set: $selectedHour h $selectedMinute m",
                        Toast.LENGTH_SHORT
                    ).show()
                },
                hour,
                minute,
                true
            )

            timePicker.show()
        }
    }
}