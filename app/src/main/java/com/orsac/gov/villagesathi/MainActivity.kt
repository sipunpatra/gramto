package com.orsac.gov.villagesathi

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.orsac.gov.villagesathi.user.UserLoginActivity
import com.orsac.gov.villagesathi.vender.LoginVenderActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val cardUser: CardView = findViewById(R.id.cardUser)
        val cardVendor: CardView = findViewById(R.id.cardVendor)

        cardUser.setOnClickListener {
            startActivity(Intent(this, UserLoginActivity::class.java))
        }

        cardVendor.setOnClickListener {
            startActivity(Intent(this, LoginVenderActivity::class.java))
        }
    }
}