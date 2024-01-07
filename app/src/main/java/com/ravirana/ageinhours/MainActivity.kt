package com.ravirana.ageinhours

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private var tvSelectedDate : TextView? = null
    private var tvAgeInHours : TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnDatePicker : Button = findViewById(R.id.buttonDatePicker)
        tvSelectedDate = findViewById(R.id.tvSelectedDate)
        tvAgeInHours = findViewById(R.id.tvAgeInHours)

        btnDatePicker.setOnClickListener{
            clickDatePicker()
        }
    }
    private fun clickDatePicker(){
        val myCalendar = Calendar.getInstance() // Creating calendar object
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val dayOfMonth = myCalendar.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(this,
            { _, Selectedyear, Selectedmonth, SelecteddayOfMonth ->

                Toast.makeText(this,
                    "selected date is $SelecteddayOfMonth .${Selectedmonth+1} .$Selectedyear",
                    Toast.LENGTH_LONG).show()
                val selectedDate = "$SelecteddayOfMonth/${Selectedmonth+1}/$Selectedyear"
                tvSelectedDate?.text=selectedDate

                val sdf = SimpleDateFormat("dd/mm/yyyy", Locale.ENGLISH)
                val theDate = sdf.parse(selectedDate)
                theDate?.let {
                    val selectedDateInHours = theDate.time/60000
                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                    currentDate?.let{
                        val currentDateInHour = currentDate.time/ 60000
                        var differenceInHours = currentDateInHour - selectedDateInHours
                        differenceInHours /=60
                        tvAgeInHours?.text = differenceInHours.toString()
                    }

                }

            }, // this is lambda expression
            year, month, dayOfMonth
        )
        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()
    }


}