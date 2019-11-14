package com.efedaniel.csc514

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.efedaniel.csc514.sample.MainActivity
import com.efedaniel.csc514.tutorialfive.TutorialFiveActivity
import com.efedaniel.csc514.tutorialfour.TutorialFourActivity
import com.efedaniel.csc514.tutorialsix.TutorialSixActivity
import kotlinx.android.synthetic.main.activity_selection.*

class SelectionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selection)
        tut_3.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
        tut_4.setOnClickListener {
            startActivity(Intent(this, TutorialFourActivity::class.java))
        }
        tut_5.setOnClickListener {
            startActivity(Intent(this, TutorialFiveActivity::class.java))
        }
        tut_6.setOnClickListener {
            startActivity(Intent(this, TutorialSixActivity::class.java))
        }
    }
}
