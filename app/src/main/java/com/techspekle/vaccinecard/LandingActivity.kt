package com.techspekle.vaccinecard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.techspekle.vaccinecard.databinding.ActivityLandingBinding

class LandingActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLandingBinding
    private val db : FirebaseFirestore = FirebaseFirestore.getInstance()
    private val auth : FirebaseAuth = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLandingBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.nextButton.setOnClickListener {
            registerActivity()
        }

        showIdsList()
    }

    private fun showIdsList() {
        val items = listOf("National ID", "Voters Id", "Workers ID", "Barua ya kijiji")
        val adapter = ArrayAdapter(this, R.layout.ids_list_item, items)
        binding.idsDropdown.setAdapter(adapter)
    }

    private fun registerActivity() {
        startActivity(Intent(this, RegisterActivity::class.java))
    }


}

