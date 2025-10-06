package com.example.snapdo.ui.addtask

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.snapdo.R
import com.example.snapdo.data.repository.TodoRepository
import kotlinx.coroutines.launch

class AddTaskActivity : AppCompatActivity() {

    private val repository = TodoRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)

        val inputTitle = findViewById<EditText>(R.id.inputTitle)
        val inputDescription = findViewById<EditText>(R.id.inputDescription)
        val inputEvidence = findViewById<EditText>(R.id.inputEvidence)

        val btnSubmit = findViewById<Button>(R.id.btnSubmit)
        val btnBack = findViewById<Button>(R.id.btnBack)

        val btnGenerate = findViewById<Button>(R.id.btnGenerateEvidence)

        btnGenerate.setOnClickListener {
            val title = inputTitle.text.toString()
            val description = inputDescription.text.toString()

            if (title.isBlank()) {
                Toast.makeText(this, "Enter a title first", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            lifecycleScope.launch {
                try {
                    // Call repository to generate evidence
                    val evidenceText = repository.generateEvidence(title, description)
                    inputEvidence.setText(evidenceText)
                    Toast.makeText(this@AddTaskActivity, "Evidence generated", Toast.LENGTH_SHORT).show()
                } catch (e: Exception) {
                    e.printStackTrace()
                    Toast.makeText(this@AddTaskActivity, "Failed to generate evidence", Toast.LENGTH_SHORT).show()
                }
            }
        }


        btnSubmit.setOnClickListener {
            val title = inputTitle.text.toString()
            val description = inputDescription.text.toString()
            val evidence = inputEvidence.text.toString()

            if (title.isBlank()) {
                Toast.makeText(this, "Title is required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            lifecycleScope.launch {
                try {
                    val newTask = repository.addTodo(title, description, evidence)
                    Toast.makeText(
                        this@AddTaskActivity,
                        "Task Added: ${newTask.title}",
                        Toast.LENGTH_SHORT
                    ).show()

                    setResult(RESULT_OK) // notify MainActivity
                    finish()
                } catch (e: Exception) {
                    e.printStackTrace()
                    Toast.makeText(this@AddTaskActivity, "Failed to add task", Toast.LENGTH_SHORT).show()
                }
            }
        }

        btnBack.setOnClickListener { finish() }
    }
}
