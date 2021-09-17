package com.techspekle.vaccinecard

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.DatePicker
import android.widget.LinearLayout
import androidx.core.content.ContentProviderCompat.requireContext
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.techspekle.vaccinecard.databinding.ActivityDashboardBinding
import java.time.Month
import java.util.*

class DashboardActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener {

    var day = 0
    var month = 0
    var year = 0

    var savedDay = 0
    var savedMonth = 0
    var savedYear = 0

    private lateinit var binding : ActivityDashboardBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        cityFun()
        districtFun()
        streetFun()
        hopsitalFun()

        pickDate()

        binding.scheduleButton.setOnClickListener {
            val bottomSheetDialog = BottomSheetDialog(
                this@DashboardActivity, R.style.BottomSheetDialogTheme
            )

            val bottomSheetView = LayoutInflater.from(applicationContext).inflate(
                R.layout.layout_bottom_sheet,
                findViewById<LinearLayout>(R.id.bottom_sheet)
            )

            bottomSheetDialog.show()
        }


    }

    private fun pickDate() {
        binding.datePicker.setOnClickListener {
            pickDateCalender()

            DatePickerDialog(this, this, year,month,day).show()
        }
    }

    private fun pickDateCalender() {
        val Cal : Calendar = Calendar.getInstance()
        day = Cal.get(Calendar.DAY_OF_MONTH)
        month = Cal.get(Calendar.MONTH)
        year = Cal.get(Calendar.YEAR)
    }

    override fun onDateSet(p0: DatePicker?, year: Int, month: Int, dayOmonth: Int) {
        savedDay = dayOmonth
        savedMonth = month
        savedYear = year

        binding.etDate.text = "$savedDay - $savedMonth - $savedYear "
    }

    private fun streetFun() {
        val items = listOf("Janseen", "Arusha Mjini", "Dodoma", "Bombo","KCMC", "Nyegezi")
        val adapter = ArrayAdapter(this, R.layout.street_list, items)
        binding.streetDropdown.setAdapter(adapter)
    }

    private fun hopsitalFun() {
        val items = listOf("Mwnanyamala Referal Hosp", "Mount Meru Reginal Hosp", "Dodoma Cental Hosp", "Bombo Hosp","KCMC", "Nyegezi Hosp")
        val adapter = ArrayAdapter(this, R.layout.hospital_list, items)
        binding.hospitalDropdown.setAdapter(adapter)
    }

    private fun districtFun() {
        val items = listOf("Kinondoni Municipal Council", "Arusha Municipal Council",
            "Dodoma Municipal Council", "Tanga Municipal Council","Kilimanjaro Municipal Council",
            "Mwanza Municipal Council")
        val adapter = ArrayAdapter(this, R.layout.district_list, items)
        binding.districtDropdown.setAdapter(adapter)
    }

    private fun cityFun() {
        val items = listOf("Dar Es Salaam", "Arusha", "Dodoma", "Tanga","Kilimanjaro", "Mwanza")
        val adapter = ArrayAdapter(this, R.layout.city_list, items)
        binding.cityDropdown.setAdapter(adapter)
    }
}