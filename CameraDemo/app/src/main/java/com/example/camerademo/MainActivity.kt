package com.example.camerademo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.camerademo.ui.theme.CameraDemoTheme

// Exercise 0-1: Uncomment below import statements
// import android.Manifest
// import androidx.annotation.RequiresApi
// import androidx.camera.lifecycle.ProcessCameraProvider
// import androidx.core.content.ContextCompat
// import com.google.common.util.concurrent.ListenableFuture
// import java.util.concurrent.ExecutionException
// import androidx.appcompat.app.AppCompatActivity
// import androidx.core.app.ActivityCompat
// import androidx.camera.view.PreviewView
// import android.content.pm.PackageManager
// import androidx.annotation.NonNull
// import androidx.camera.core.CameraSelector
// import androidx.camera.core.Preview
// import androidx.lifecycle.LifecycleOwner
// import android.view.View
// Exercise 0-1

// Exercise 2: Uncomment below import statements
// import androidx.camera.core.ImageCapture
// import android.content.ContentValues
// import android.os.Environment
// import android.provider.MediaStore
// import java.text.SimpleDateFormat
// import java.util.Date
// import java.util.concurrent.Executor
// import java.util.concurrent.Executors
// import android.media.MediaScannerConnection
// import android.net.Uri
// import android.os.Build
// import android.widget.Toast
// import androidx.camera.core.ImageCaptureException
// import java.util.Locale
// Exercise 2

// Exercise 3: Uncomment below import statements
// import org.tensorflow.lite.Interpreter
// import org.tensorflow.lite.support.common.FileUtil
// import java.nio.MappedByteBuffer
// import androidx.camera.core.ImageAnalysis
// import android.util.Size // TFLite input ByteBuffer
// import java.nio.ByteBuffer
// import java.nio.ByteOrder
// import android.graphics.Bitmap
// import android.graphics.BitmapFactory
// import android.graphics.Rect
// import android.graphics.ImageFormat
// import android.graphics.YuvImage
// import androidx.camera.core.ImageProxy
// import java.io.ByteArrayOutputStream
// import android.widget.TextView
// Exercise 3

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

}

