package com.example.tictactoe

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return super.onTouchEvent(event)
    }
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // move to second page
        val playBtn = findViewById<Button>(R.id.playButton)
        playBtn.setOnClickListener{
            val intent = Intent(this,play::class.java)
            startActivity(intent)
        }

        val helpBtn = findViewById<Button>(R.id.helpBtn);
        helpBtn.setOnClickListener {
            val helpPage = Intent(this,HelpPage::class.java)
            startActivity(helpPage)
        }
        val aboutBtn = findViewById<Button>(R.id.aboutBtn);
        aboutBtn.setOnClickListener {
            val helpPage = Intent(this,AboutPage::class.java)
            startActivity(helpPage)
        }

    }

}