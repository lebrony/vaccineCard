package com.techspekle.vaccinecard

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.view.setMargins
import androidx.core.view.setPadding
import com.techspekle.vaccinecard.databinding.ActivityDashboardBinding
import java.util.*

class DashboardActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDashboardBinding
    private val months = listOf("January","February","March","April","May","June","July","August","September","October","November","December")
    private val times = listOf("8:30 AM","9:00 AM","9:30 AM","10:00 AM","10:30 AM","11:00 AM","11:30 AM","12:00 PM","12:30 PM","1:00 PM","1:30 PM","2:00 PM","2:30 PM","3:00 PM","3:30 PM","4:00 PM","4:30 PM","5:00 PM")
    private val now: Calendar = Calendar.getInstance()
    private val currentMonth = now.get(Calendar.MONTH)
    private val currentDate = now.get(Calendar.DATE)
    private var selectedDate:Int = currentDate
    private var selectedMonth:Int = currentMonth
    private var selectedTime:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        cityFun()
        districtFun()
        streetFun()
        hopsitalFun()

        //to display the radio buttons for month,date and time
        //TODO: Refactor the calls to one function
        monthRadioButtons()
        timeRadioButtons()
        dateRadioButtons()

        //listeners for the radioGroups of Month,Date and Time

        binding.rgMonthList.setOnCheckedChangeListener { _, checkedId -> selectedMonth = checkedId }
        binding.rgDateList.setOnCheckedChangeListener { _, checkedId -> selectedDate = checkedId }
        binding.rgTimeList.setOnCheckedChangeListener { _, checkedId -> selectedTime = checkedId }

        binding.scheduleButton.setOnClickListener {
//            val bottomSheetDialog = BottomSheetDialog(
//                this@DashboardActivity, R.style.BottomSheetDialogTheme
//            )
//
//            val bottomSheetView = LayoutInflater.from(applicationContext).inflate(
//                R.layout.layout_bottom_sheet,
//                findViewById<LinearLayout>(R.id.bottom_sheet)
//            )
//
//            bottomSheetDialog.show()

            //TODO: use the toast format to understand how to pull data
            val toastText = "SelectedMonth: ${months[selectedMonth]}, SelectedDate: $selectedDate, SelectedTime: ${times[selectedTime]}"
            Toast.makeText(this,toastText,Toast.LENGTH_LONG).show()
        }

    }


    //TODO: Adam, this is how to override the onBackPressed
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
            createRadioButton(id,buttonText,binding.rgMonthList,selectedMonth)
        }
    }
    private fun dateRadioButtons(){
        var numberOfDates = 31
        // TODO: figure out update list of dates, the one below works, just doesn't redraw on change
        //taking indexes of February, April, September, June and November -- Assuming Feb is 30 days
        val monthsWith30Days = listOf(1,3,5,8,10)
        if(selectedMonth in monthsWith30Days){
            numberOfDates = 30
        }

        for(dateIndex in currentDate until numberOfDates + currentDate){
            var curIndex = dateIndex
            if(dateIndex > numberOfDates){
                curIndex = dateIndex - numberOfDates
            }
            val id = curIndex
            val buttonText = "$curIndex"
            createRadioButton(id,buttonText,binding.rgDateList,selectedDate)
        }
    }
    private fun timeRadioButtons(){
        for(time in times){
            val id:Int = times.indexOf(time)
            createRadioButton(id,time,binding.rgTimeList,selectedTime)
        }
    }



    @SuppressLint("SetTextI18n", "UseCompatLoadingForColorStateLists", "ResourceType")
    private fun createRadioButton(id:Int, text:String, radioGroup: RadioGroup, initialSelected:Int){
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
        radioBtn.isChecked = id == initialSelected
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