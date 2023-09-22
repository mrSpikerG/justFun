package ua.thespiker.exam

import android.R.attr.value
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import ua.thespiker.exam.helper.RetroHelper


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        findViewById<ImageView>(R.id.adnroid).setOnClickListener {
            val myIntent = Intent(this@MainActivity, AndroidPage::class.java)
            this@MainActivity.startActivity(myIntent)
        }

        findViewById<ImageView>(R.id.java).setOnClickListener {
            val myIntent = Intent(this@MainActivity, JavaPage::class.java)
            this@MainActivity.startActivity(myIntent)
        }
    }
}