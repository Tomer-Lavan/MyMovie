package com.example.mymovie

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener

class TicketConfermationActivity : AppCompatActivity() {
    var dateFromPicker = "dd/mm/yyyy"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ticket_confermation)

        val actionBar: ActionBar? = supportActionBar
        actionBar?.hide()

        //*****************************************
        //creating a datePicker when pressing on date input edit text
        //from some reason need to click twice for the datePicker show up
        val dateInput = findViewById<EditText>(R.id.date)
        dateInput.setOnClickListener {
            var datePicker = MaterialDatePicker.Builder.datePicker()
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
        var theaterText = "Did not choose theater"
        var adultTicketsText = "0"
        var  childTicketsText = "0"
        intent = Intent(this, TicketsDetails::class.java)
        var dateText = dateFromPicker
        intent.putExtra("date", dateText)
        val theater : EditText = findViewById(R.id.theater)
        if (!theater.text.toString().isNullOrEmpty()) theaterText = theater.text.toString()
        intent.putExtra("theater", theaterText)
        val adultTickets : EditText = findViewById(R.id.numOfAdultTickets)
        if (!adultTickets.text.toString().isNullOrEmpty())  adultTicketsText = adultTickets.text.toString()
        intent.putExtra("numOfAdultTickets", adultTicketsText)
        val childTickets : EditText = findViewById(R.id.numOfChildTickets)
        if (!childTickets.text.toString().isNullOrEmpty())  childTicketsText = childTickets.text.toString()
        intent.putExtra("numOfChildTickets", childTicketsText)
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