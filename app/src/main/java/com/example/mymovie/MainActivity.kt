package com.example.mymovie

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.marginTop
import com.example.mymovie.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*


class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //*****************************************
        //creating an image slider
        val image_slide: ImageView = findViewById(R.id.image_slide)
        val animationDrawable: AnimationDrawable = image_slide.getDrawable() as AnimationDrawable
        animationDrawable.start()
        //*****************************************

        //*****************************************
        //hiding the action bar
        val actionBar: ActionBar? = supportActionBar
        actionBar?.hide()
        //*****************************************

        //*****************************************
        //creating bat icon animation
        val firstAnimation: Animation = AnimationUtils.loadAnimation(applicationContext, R.anim.my_animation)
        binding.batIcon.startAnimation(firstAnimation)
        //*****************************************


        //*****************************************
        //when coming back from TicketsDetailsActivity creating a bottom that will send us back to last reservation
        //had problems with the transfer of the reservation inputs from TicketsDetailsActivity and back
        //the current sulotion is not ideal and I'm planning to change it
        val intent = getIntent()
        val confirmed = intent.getIntExtra("boughtTicket", 0)
        if(confirmed == 1) {
            val ll_main = findViewById(R.id.ll_main) as LinearLayout
            // creating the button
            val button_dynamic = Button(this)
            // setting layout_width and layout_height using layout parameters
            button_dynamic.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            button_dynamic.text = resources.getString(R.string.ticket_reservation)
            button_dynamic.setOnClickListener {
                var intent = Intent(this, TicketsDetails::class.java)
                intent.putExtra("FromActivity", 1)
                startActivity(intent)
            }
            button_dynamic.background = resources.getDrawable(R.drawable.back)
            button_dynamic.setTextColor(resources.getColor(R.color.btn_text_color))
            // add Button to LinearLayout
            ll_main.addView(button_dynamic)
        }
        //*****************************************
    }
    //*****************************************
    //move to TicketConfermationActivity
    fun showAlert(view: View) {
        val intent = Intent(this, TicketConfermationActivity::class.java)
        startActivity(intent)
    }
    //*****************************************
}