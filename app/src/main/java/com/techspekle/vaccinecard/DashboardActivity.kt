package com.techspekle.vaccinecard

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.marginRight
import androidx.core.view.setMargins
import androidx.core.view.setPadding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.techspekle.vaccinecard.databinding.ActivityDashboardBinding
import java.time.Month
import java.util.*

class DashboardActivity : AppCompatActivity() {
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
        monthRadioButtons()
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


//    }
//    override fun onBackPressed() {
//
//    }

    private fun monthRadioButtons(){
        for(i in 1..5){
            val buttonText = "Month $i"
            createRadioButton(i,buttonText)
        }
    }

    @SuppressLint("SetTextI18n", "UseCompatLoadingForColorStateLists", "ResourceType")
    private fun createRadioButton(id:Int,text:String){
        val radioBtn = RadioButton(this)
        val params = RadioGroup.LayoutParams(RadioGroup.LayoutParams.WRAP_CONTENT,RadioGroup.LayoutParams.WRAP_CONTENT)
        radioBtn.id = id
        radioBtn.setBackgroundResource(R.drawable.radio_selector)
        radioBtn.setTextColor(resources.getColorStateList(R.drawable.text_color))
        radioBtn.text = text
        radioBtn.textAlignment = View.TEXT_ALIGNMENT_CENTER
        radioBtn.buttonDrawable = null
        radioBtn.setPadding(30)
        radioBtn.minWidth = 200
        params.setMargins(20)
        radioBtn.layoutParams = params
        binding.rgMonthList.addView(radioBtn)
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