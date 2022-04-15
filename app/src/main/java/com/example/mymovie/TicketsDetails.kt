package com.example.mymovie

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog
import org.w3c.dom.Text

class TicketsDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tickets_details)

        val actionBar: ActionBar? = supportActionBar
        actionBar?.hide()

        //*****************************************
        // getting all the reservation user inputs and set them to text views
        val intent = getIntent()
        val date = intent.getStringExtra("date")
        val dateText = findViewById<TextView>(R.id.date)
        var text = dateText.text.toString()
        dateText?.text = "$text: $date"
        val theater = intent.getStringExtra("theater")
        val theaterText = findViewById<TextView>(R.id.theater)
        text = theaterText.text.toString()
        theaterText?.text = "$text: $theater"
        val numOfAdultTickets = intent.getStringExtra("numOfAdultTickets")
        val numOfAdultTicketsText = findViewById<TextView>(R.id.numOfAdultTickets)
        text = numOfAdultTicketsText.text.toString()
        numOfAdultTicketsText?.text = "$text: $numOfAdultTickets"
        val numOfChildTickets = intent.getStringExtra("numOfChildTickets")
        val numOfChildTicketsText = findViewById<TextView>(R.id.numOfChildTickets)
        text = numOfChildTicketsText.text.toString()
        numOfChildTicketsText?.text = "$text: $numOfChildTickets"
        val totalPay = findViewById<TextView>(R.id.totalPayment)
        text = totalPay.text.toString()
        val Apay = Integer.parseInt(numOfAdultTickets)
        val Cpay = Integer.parseInt(numOfChildTickets)
        val totalPayCalc = 10 * Apay + 5 * Cpay
        totalPay?.text = "$text: $totalPayCalc$"
    }
    //*****************************************

    //*****************************************
    // move back to TicketConfermationActivity
    fun back(view: View) {
        intent = Intent(this, TicketConfermationActivity::class.java)
        startActivity(intent)
    }
    //*****************************************

    //*****************************************
    // move back to MainActivity without buying a ticket
    fun cancel(view: View) {
        intent = Intent(this, MainActivity::class.java)
        intent.putExtra("boughtTicket", 0)
        startActivity(intent)
    }
    //*****************************************

    //*****************************************
    // open a dialog when buying a ticket that from there the user will move back to MainActivity
    fun confirm(view: View) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Why So Serious Son?")
        builder.setMessage("Enjoy the movie")
        builder.setPositiveButton("Thanks",
            DialogInterface.OnClickListener { dialog, id ->
                intent = Intent(this, MainActivity::class.java)
                intent.putExtra("boughtTicket", 1)
                startActivity(intent)
        })
        builder.show()
    }
    //*****************************************
}