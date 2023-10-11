package com.example.dobcalc

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private var tvSelectedDAte : TextView? = null
    private var tvageinminutes : TextView? = null


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker : Button = findViewById(R.id.btnDatepicker)

        tvSelectedDAte = findViewById(R.id.tvSelectedDate)
        tvageinminutes = findViewById(R.id.tvageinminutes)
        btnDatePicker.setOnClickListener {
            clickDatePicker()


        }


    }
    private fun clickDatePicker(){
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

       val dpd =  DatePickerDialog(this,
           { view, selectedYear, selectedMonth, selectedDayofMonth ->

               Toast.makeText(this,
                   "Year was $selectedYear, Month was ${selectedMonth + 1}" + "Day was ${selectedDayofMonth}",
                   Toast.LENGTH_LONG
               ).show()

               val selectedDate = "$selectedDayofMonth/${selectedMonth+1}/$selectedYear"
               tvSelectedDAte?.text = selectedDate
               val sdt = SimpleDateFormat("dd/mm/yyyy", Locale.ENGLISH)

               val theDate = sdt.parse(selectedDate)
                theDate?.let {
                    val selectedDateInMinutes = theDate.time / 60000

                    val currentDate = sdt.parse(sdt.format(System.currentTimeMillis()))
                    currentDate?.let {
                        val currentdateinminutes = currentDate.time / 60000
                        val differenceinminutes = currentdateinminutes - selectedDateInMinutes
                        tvageinminutes?.text = differenceinminutes.toString()
                    }

                }



           }
           ,year,
           month,
           day)
        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
            dpd.show()

    }


}