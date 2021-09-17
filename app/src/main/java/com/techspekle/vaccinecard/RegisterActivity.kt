package com.techspekle.vaccinecard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.techspekle.vaccinecard.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding : ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.nextButton.setOnClickListener {
            dashboardActivity()
        }
    }

    private fun dashboardActivity() {
        startActivity(Intent(this, DashboardActivity::class.java))
    }
}