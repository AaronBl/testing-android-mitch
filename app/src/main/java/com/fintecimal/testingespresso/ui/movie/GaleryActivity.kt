package com.fintecimal.testingespresso.ui.movie

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.fintecimal.testingespresso.R

const val GALLERY_REQUEST_CODE = 1234

class GaleryActivity : AppCompatActivity() {
    private val TAG: String = "AppDebug"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_galery)
        val buttonOpen = findViewById<TextView>(R.id.button_open_gallery)
        buttonOpen.setOnClickListener{
            pickFromGallery()
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val image = findViewById<ImageView>(R.id.image)
        if(resultCode == Activity.RESULT_OK){
            Log.d(TAG, "RESULT_OK")
            when(requestCode){

                GALLERY_REQUEST_CODE -> {
                    Log.d(TAG, "GALLERY_REQUEST_CODE detected.")
                    data?.data?.let { uri ->
                        Log.d(TAG, "URI: $uri")
                        Glide.with(this)
                                .load(uri)
                                .into(image)
                    }
                }
            }
        }
    }

    private fun pickFromGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, GALLERY_REQUEST_CODE)
    }

}