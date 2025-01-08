package com.fetch.lynnwilliam


import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import dagger.hilt.android.testing.HiltTestApplication

// Make sure your test runner is set up to use Hilt:
class TestRunner : AndroidJUnitRunner() {
    override fun newApplication(cl: ClassLoader, className: String, context: Context): Application {
        // Use the Hilt test application class here:
        return super.newApplication(cl, HiltTestApplication::class.java.name, context)
    }
}