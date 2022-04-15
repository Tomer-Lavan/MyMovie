package com.example.mymovie

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.example.mymovie.databinding.ActivityMainBinding
import com.example.mymovie.databinding.ActivityTicketConfermationBinding
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener

class TicketConfermationActivity : AppCompatActivity() {
    companion object {
        const val PREFS = "details"
        var dateFromPicker = "dd/mm/yyyy"
        var theaterText = "Did not choose theater"
        var adultTicketsText = "0"
        var  childTicketsText = "0"
    }

    private lateinit var binding : ActivityTicketConfermationBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTicketConfermationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val actionBar: ActionBar? = supportActionBar
        actionBar?.hide()

        //*****************************************
        //creating a datePicker when pressing on date input edit text
        //from some reason need to click twice for the datePicker show up
        val dateInput = findViewById<EditText>(R.id.date)
        dateInput.setOnClickListener {
            val datePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select date")
                .setSelection(MaterialDatePicker.thisMonthInUtcMilliseconds())
                .build()

            datePicker.addOnPositiveButtonClickListener(MaterialPickerOnPositiveButtonClickListener() {
                dateFromPicker = datePicker.headerText
                dateInput.setText(dateFromPicker)
            })
            datePicker.show(supportFragmentManager, "Tag")
        }
        //*****************************************

    }

    //*****************************************
    // move to TicketsDetailsActivity with the user inputs
    fun ticketsDetailsActivity(view: View) {
        getSharedPreferences(PREFS, MODE_PRIVATE).edit().apply() {
            putString("date",  dateFromPicker)
            if (!binding.theater.text.toString().isNullOrEmpty()) theaterText = binding.theater.text.toString()
            putString("theater",  theaterText)
            if (!binding.numOfAdultTickets.text.toString().isNullOrEmpty())  adultTicketsText = binding.numOfAdultTickets.text.toString()
            putString("adultTickets", adultTicketsText)
            if (!binding.numOfChildTickets.text.toString().isNullOrEmpty())  childTicketsText = binding.numOfChildTickets.text.toString()
            putString("childTickets", childTicketsText)
        }.apply()
        intent = Intent(this, TicketsDetails::class.java)
        startActivity(intent)
    }
    //*****************************************

    //*****************************************
    // move back to MainActivity
    fun goBack(view: View) {
        intent = Intent(this, MainActivity::class.java)
        intent.putExtra("boughtTicket", 0)
        startActivity(intent)
    }
    //*****************************************

}