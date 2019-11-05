package com.efedaniel.csc514

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.efedaniel.csc514.sample.MainActivity
import com.efedaniel.csc514.tutorialguy.TutorialActivity
import kotlinx.android.synthetic.main.activity_selection.*

class SelectionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selection)
        tut_3.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        tutorialguy.setOnClickListener {
            startActivity(Intent(this, TutorialActivity::class.java))
        }
    }
}
