package com.example.mymovie

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog
import com.example.mymovie.databinding.ActivityTicketConfermationBinding
import com.example.mymovie.databinding.ActivityTicketsDetailsBinding
import org.w3c.dom.Text

class TicketsDetails : AppCompatActivity() {

    private lateinit var binding : ActivityTicketsDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTicketsDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val actionBar: ActionBar? = supportActionBar
        actionBar?.hide()

        //*****************************************
        // getting all the reservation user inputs and set them to text views
        getSharedPreferences(TicketConfermationActivity.PREFS, MODE_PRIVATE).apply {
            var adultTickets = getString("adultTickets","0")
            var childTickets = getString("childTickets","0")
            binding.date.setText("${binding.date.text}: ${getString("date","")}")
            binding.theater.setText("${binding.theater.text}: ${getString("theater","")}")
            binding.numOfAdultTickets.setText("${binding.numOfAdultTickets.text}: ${adultTickets}")
            binding.numOfChildTickets.setText("${binding.numOfChildTickets.text}: ${childTickets}")
            val Apay = Integer.parseInt(adultTickets)
            val Cpay = Integer.parseInt(childTickets)
            val totalPayCalc = 10 * Apay + 5 * Cpay
            binding.totalPayment.setText("${binding.totalPayment.text}: $totalPayCalc$")
        }
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