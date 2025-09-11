// MainActivity.kt
package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.ui.theme.ApiClient
import com.example.myapplication.ui.theme.HeroRequest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etName = findViewById<EditText>(R.id.etName)
        val etAge = findViewById<EditText>(R.id.etAge)
        val etScore = findViewById<EditText>(R.id.etScore)
        val btnAdd = findViewById<Button>(R.id.btnAddHero)
        val btnCount = findViewById<Button>(R.id.btnSeeCount)
        val tvResult = findViewById<TextView>(R.id.tvResult)

        btnAdd.setOnClickListener {
            val name = etName.text.toString().trim()
            val age = etAge.text.toString().toIntOrNull()
            val score = etScore.text.toString().toIntOrNull()
            if (name.isEmpty() || age == null || score == null) {
                Toast.makeText(this, "Fill all fields correctly", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            lifecycleScope.launch {
                // TODO: create Hero agent with name, age, score via ApiClient
            }
        }

        btnCount.setOnClickListener {
            lifecycleScope.launch {
                // TODO: get count via ApiClient

            }
        }
    }
}