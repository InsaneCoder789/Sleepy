# 💤 Sleep Tracker

A sophisticated, modern **Android application** built with **Kotlin** that enables users to monitor their sleep health, track real-time sleep sessions, and analyze their consistency against personal goals.

Developed as a comprehensive project for the **Android Development Lab Experiment**.

---

## 🌟 Features

### 🕒 Smart Tracking
* **One-Tap Session**: Start and stop sleep tracking with a high-precision chronometer.
* **Stage Detection**: Logic-based sleep stage monitoring:
    * **Light Sleep**: The restorative initial 90-minute phase.
    * **Deep Sleep**: The intensive recovery phase entered after 90 minutes.
* **Reset Logic**: Easily discard accidental or incorrect sessions.

### 🎯 Goal Management
* **Material TimePicker**: A sleek, futuristic interface to set daily sleep targets.
* **Persistence**: Goals and settings are saved securely in `SharedPreferences`, ensuring data isn't lost when the app closes.

### 📊 Advanced Analytics
* **Average Calculation**: Automatically computes your average rest duration over multiple sessions.
* **Goal Comparison**: Instant feedback on your sleep performance:
    * *Example: "+15m vs goal"* (Met) or *"-40m vs goal"* (Deficit).
* **Dynamic UI**: The interface adapts its messaging and colors based on your sleep health.

---

## 📱 Screen Breakdown

### 1. Main Dashboard
The control center of the app. It features a live chronometer, current sleep stage indicator, and quick-glance statistics for the last session and overall averages.

### 2. Settings Screen
Designed for customization. Users can toggle haptic feedback, enable smart alarms, and adjust their sleep goals via a Material 3 Time Picker.

### 3. Summary Screen
A dedicated "Done" screen that appears after a session. It provides a deep dive into the session data, compares it against the user's set goal, and offers insights into REM cycle consistency.

---

## 📂 Project Architecture

The app is built using the **MVVM (Model-View-ViewModel)** architecture pattern, promoting clean separation of concerns and testability.

```text
com.example.sleeptracker
├── model
│   └── SleepSession.kt       # Data structure for sleep entries
├── repository
│   └── SleepRepository.kt    # Single source of truth (SharedPreferences)
├── uiscale                   # UI/View Layer
│   ├── MainActivity.kt       # Main Tracking Logic
│   ├── SettingsActivity.kt   # Goal & App Configuration
│   └── SummaryActivity.kt    # Post-Sleep Data Analysis
├── viewmodel
│   └── SleepViewModel.kt     # Business logic & Data stream management
└── res
    └── layout                # XML UI definitions (ConstraintLayout)

```

## 🛠️ Technical Stack
**Language**: Kotlin

**UI Framework**: Material Design 3 (Material Components)

**Architecture**: MVVM

**Storage** : SharedPreferences (Persistent Key-Value Storage)

**Layout Engine**: ConstraintLayout for flat, performant UI hierarchies

**Components**: Chronometer, MaterialCardView, MaterialSwitch, MaterialTimePicker

