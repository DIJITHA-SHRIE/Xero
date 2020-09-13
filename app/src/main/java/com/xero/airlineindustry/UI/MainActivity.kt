package com.xero.airlineindustry.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.xero.airlineindustry.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        signIn.setOnClickListener{
            val intent = Intent(this,BottomNavigation::class.java)
            startActivity(intent)
        }
    }
}