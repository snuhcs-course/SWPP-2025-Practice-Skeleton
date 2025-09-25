package com.example.swpp.cloud_code

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var nameInput: TextView
    private lateinit var idInput: TextView
    private lateinit var writeButton: Button
    private lateinit var targetNameInput: TextView
    private lateinit var readButton: Button
    private lateinit var targetNameOutput: TextView
    private lateinit var idText: TextView

    private lateinit var service: ServiceApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nameInput = findViewById(R.id.NameInput)
        idInput = findViewById(R.id.IdInput)
        writeButton = findViewById(R.id.WriteBtn)
        targetNameInput = findViewById(R.id.TargetNameInput)
        readButton = findViewById(R.id.ReadBtn)
        targetNameOutput = findViewById(R.id.TargetNameOutput)
        idText = findViewById(R.id.IdOutput)

        service = RetrofitClient.getClient().create(ServiceApi::class.java)

        writeButton.setOnClickListener {
            val userName = nameInput.text.toString()
            val userId = idInput.text.toString()

            val requestData = NameIdData(userName, userId)

            service.userWrite(requestData).enqueue(object : Callback<CodeMessageResponse> {
                override fun onResponse(
                    call: Call<CodeMessageResponse>,
                    response: Response<CodeMessageResponse>
                ) {
                    val result = response.body()
                    Toast.makeText(this@MainActivity, result?.message, Toast.LENGTH_SHORT).show()
                }

                override fun onFailure(call: Call<CodeMessageResponse>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "Write Error", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}