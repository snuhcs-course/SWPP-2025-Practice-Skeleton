package com.example.snapdo.ui.camera

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.snapdo.R
import java.io.ByteArrayOutputStream
import java.io.File
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import com.example.snapdo.data.network.RetrofitInstance
import com.example.snapdo.data.network.VerifyRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch



class CameraActivity : AppCompatActivity() {

    private lateinit var previewView: PreviewView
    private lateinit var imageView: ImageView
    private lateinit var imageCapture: ImageCapture
    private lateinit var cameraExecutor: ExecutorService

    private var lensFacing = CameraSelector.LENS_FACING_BACK
    private var capturedFile: File? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)

        val container = findViewById<FrameLayout>(R.id.cameraPreview)
        previewView = PreviewView(this)
        container.addView(previewView)

        imageView = findViewById(R.id.capturedImage)
        val btnCapture = findViewById<Button>(R.id.btnCapture)
        val btnSubmit = findViewById<Button>(R.id.btnSubmitPhoto)
        val btnRetake = findViewById<Button>(R.id.btnRetake)
        val btnSwitch = findViewById<Button>(R.id.btnSwitchCamera)
        val btnBack = findViewById<Button>(R.id.btnGoBack)

        // Permissions
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), 1000)
        } else {
            startCamera()
        }

        // Capture photo
        btnCapture.setOnClickListener {
            val photoFile = File(externalCacheDir, "captured_${System.currentTimeMillis()}.jpg")
            val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()

            imageCapture.takePicture(
                outputOptions,
                ContextCompat.getMainExecutor(this),
                object : ImageCapture.OnImageSavedCallback {
                    override fun onImageSaved(output: ImageCapture.OutputFileResults) {
                        capturedFile = photoFile
                        // Show photo in ImageView
                        imageView.setImageURI(output.savedUri ?: android.net.Uri.fromFile(photoFile))
                        imageView.visibility = ImageView.VISIBLE
                        previewView.visibility = PreviewView.GONE

                        // Toggle buttons
                        btnCapture.visibility = Button.GONE
                        btnSubmit.visibility = Button.VISIBLE
                        btnRetake.visibility = Button.VISIBLE
                    }

                    override fun onError(exc: ImageCaptureException) {
                        Toast.makeText(applicationContext, "Capture failed: ${exc.message}", Toast.LENGTH_SHORT).show()
                    }
                }
            )
        }

        // Retake photo
        btnRetake.setOnClickListener {
            capturedFile = null
            imageView.visibility = ImageView.GONE
            previewView.visibility = PreviewView.VISIBLE

            btnCapture.visibility = Button.VISIBLE
            btnSubmit.visibility = Button.GONE
            btnRetake.visibility = Button.GONE

            startCamera()
        }

        btnSubmit.setOnClickListener {
            capturedFile?.let { file ->
                // immediately finish
                val taskId = intent.getIntExtra("task_id", -1)

                // fire network in background
                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        val bitmap = BitmapFactory.decodeFile(file.absolutePath)
                        val outputStream = ByteArrayOutputStream()
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 60, outputStream)
                        val byteArray = outputStream.toByteArray()

                        val base64Image = "data:image/jpeg;base64," +
                                Base64.encodeToString(byteArray, Base64.NO_WRAP)

                        if (taskId != -1) {
                            val response = RetrofitInstance.api.verifyTask(
                                taskId,
                                VerifyRequest(image_base64 = base64Image)
                            )

                            // Notify MainActivity when response arrives
                            val broadcastIntent = Intent("VERIFY_RESULT")
                            broadcastIntent.putExtra("task_id", taskId)
                            broadcastIntent.putExtra("verdict", response.verdict)
                            sendBroadcast(broadcastIntent)
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }

                // return to MainActivity immediately
                finish()
            }
        }


        btnSwitch.setOnClickListener {
            // TODO - 5 : Switch Camera
            startCamera()
        }

        btnBack.setOnClickListener { finish() }

        cameraExecutor = Executors.newSingleThreadExecutor()
    }

    private fun startCamera() {
        // TODO - 4 : Implement Camera
    }

    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor.shutdown()
    }
}
