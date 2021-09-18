package com.techspekle.vaccinecard

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.core.view.setMargins
import androidx.core.view.setPadding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.techspekle.vaccinecard.databinding.ActivityDashboardBinding
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.indexOf as indexOf1

class DashboardActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDashboardBinding
    private val months = listOf("January","February","March","April","May","June","July","August","September","October","November","December")
    private val times = listOf("8:30 AM","9:00 AM","9:30 AM","10:00 AM","10:30 AM","11:00 AM","11:30 AM","12:00 PM","12:30 PM","1:00 PM","1:30 PM","2:00 PM","2:30 PM","3:00 PM","3:30 PM","4:00 PM","4:30 PM","5:00 PM")
    private val now: Calendar = Calendar.getInstance()
    private val currentMonth = now.get(Calendar.MONTH)
    private val currentDate = now.get(Calendar.DATE)
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
        timeRadioButtons()
        dateRadioButtons()
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
        for(monthIndex in currentMonth until currentMonth + 3 ){
            var curIndex = monthIndex
            if(monthIndex > months.size -1 ){
                curIndex = monthIndex - months.size
            }
            val id = curIndex
            val buttonText = months[curIndex]
            createRadioButton(id,buttonText,binding.rgMonthList,currentMonth)
        }
    }
    private fun dateRadioButtons(){
        for(dateIndex in currentDate until 30 + currentDate){
            var curIndex = dateIndex
            if(dateIndex > 30){
                curIndex = dateIndex - 30
            }
            val id = curIndex
            val buttonText = "$curIndex"
            createRadioButton(id,buttonText,binding.rgDateList,currentDate)
        }
    }
    private fun timeRadioButtons(){
        for(time in times){
            val id:Int = times.indexOf(time)
            createRadioButton(id,time,binding.rgTimeList,0)
        }
    }



    @SuppressLint("SetTextI18n", "UseCompatLoadingForColorStateLists", "ResourceType")
    private fun createRadioButton(id:Int,text:String,radioGroup: RadioGroup,initalSelcted:Int){
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
        radioBtn.isChecked = id == initalSelcted
        radioBtn.layoutParams = params
        radioGroup.addView(radioBtn)
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