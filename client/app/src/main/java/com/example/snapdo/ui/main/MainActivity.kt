package com.example.snapdo.ui.main


import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.snapdo.R
import com.example.snapdo.data.repository.TodoRepository
import com.example.snapdo.ui.addtask.AddTaskActivity
import com.example.snapdo.ui.camera.CameraActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: TodoAdapter

    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory(TodoRepository())
    }
    private val verifyResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK && result.data != null) {
                val taskId = result.data!!.getIntExtra("task_id", -1)
                val verdict = result.data!!.getStringExtra("verdict")
                if (taskId != -1 && verdict != null) {
                    viewModel.updateVerdict(taskId, verdict)
                }
            }
        }

    private val verifyReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent?.action == "VERIFY_RESULT") {
                val taskId = intent.getIntExtra("task_id", -1)
                val verdict = intent.getStringExtra("verdict")
                if (taskId != -1 && verdict != null) {
                    viewModel.updateVerdict(taskId, verdict)
                }
            }
        }
    }

    private val addTaskLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            // refresh list
            viewModel.refreshTodos()
        }
    }

    @SuppressLint("UnspecifiedRegisterReceiverFlag")
    override fun onStart() {
        super.onStart()
        val filter = IntentFilter("VERIFY_RESULT")
        registerReceiver(verifyReceiver, filter)
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(verifyReceiver)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("MainActivity", "onCreate: MainActivity started")

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val recyclerView = findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.recyclerViewTodos)
        adapter = TodoAdapter(emptyList()) { todo ->
            // When a task is clicked, go to CameraActivity
            val intent = Intent(this, CameraActivity::class.java)
            intent.putExtra("task_id", todo.id)
            verifyResultLauncher.launch(intent)
        }



        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        val fabAddTask = findViewById<FloatingActionButton>(R.id.fabAddTask)

        // TODO - 3: Transition to AddTaskActivity when fabAddTask clicked

        // Observe LiveData
        viewModel.todos.observe(this) { todos ->
            adapter.updateData(todos)
        }
    }
}
