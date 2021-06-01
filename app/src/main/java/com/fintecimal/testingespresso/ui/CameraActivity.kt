package com.fintecimal.testingespresso.ui

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.fintecimal.testingespresso.R


const val REQUEST_IMAGE_CAPTURE = 1234
const val KEY_IMAGE_DATA = "data"

class CameraActivity : AppCompatActivity() {

    private val TAG: String = "AppDebug"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)

        val buttonLaunchCamera = findViewById<TextView>(R.id.button_launch_camera)
        buttonLaunchCamera.setOnClickListener {
            dispatchCameraIntent()
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val image = findViewById<ImageView>(R.id.image)

        if (resultCode == Activity.RESULT_OK) {
            Log.d(TAG, "RESULT_OK")
            when (requestCode) {

                REQUEST_IMAGE_CAPTURE -> {
                    Log.d(TAG, "REQUEST_IMAGE_CAPTURE detected.")
                    data?.extras.let { extras ->
                        if (extras == null || !extras.containsKey(KEY_IMAGE_DATA)) {
                            return
                        }
                        val imageBitmap = extras[KEY_IMAGE_DATA] as Bitmap?
                        image.setImageBitmap(imageBitmap)
                    }
                }
            }
        }
    }

    private fun dispatchCameraIntent() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
    }

}