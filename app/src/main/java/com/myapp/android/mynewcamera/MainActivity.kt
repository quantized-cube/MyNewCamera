package com.myapp.android.mynewcamera

import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private val _requestCodeEat = 1000
    private val _requestCodeBody = 2000
    private val _requestCodeBang = 3000
    private val _requestCodeMusic = 4000

    private var _imageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == RESULT_OK) {
            val ivCamera = findViewById<ImageView>(R.id.ivCamera)
            ivCamera.setImageURI(_imageUri)
            if (requestCode == _requestCodeEat) {
                Toast.makeText(applicationContext, "Eat Saved", Toast.LENGTH_SHORT).show()
            } else if (requestCode == _requestCodeBody) {
                Toast.makeText(applicationContext, "Body Saved", Toast.LENGTH_SHORT).show()
            } else if (requestCode == _requestCodeBang) {
                Toast.makeText(applicationContext, "BanG Saved", Toast.LENGTH_SHORT).show()
            } else if (requestCode == _requestCodeMusic) {
                Toast.makeText(applicationContext, "Music Saved", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun onEatButtonClick(view: View) {
        _imageUri = makeImageUri("Eat")
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        intent.putExtra(MediaStore.EXTRA_OUTPUT, _imageUri)
        startActivityForResult(intent, _requestCodeEat)
    }

    fun onBodyButtonClick(view: View) {
        _imageUri = makeImageUri("Body")
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        intent.putExtra(MediaStore.EXTRA_OUTPUT, _imageUri)
        startActivityForResult(intent, _requestCodeBody)
    }

    fun onBangButtonClick(view: View) {
        _imageUri = makeImageUri("BanG")
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        intent.putExtra(MediaStore.EXTRA_OUTPUT, _imageUri)
        startActivityForResult(intent, _requestCodeBang)
    }

    fun onMusicButtonClick(view: View) {
        _imageUri = makeImageUri("Music")
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        intent.putExtra(MediaStore.EXTRA_OUTPUT, _imageUri)
        startActivityForResult(intent, _requestCodeMusic)
    }

    private fun makeFileName(head: String): String {
        val dateFormat = SimpleDateFormat("yyyyMMddHHmmss")
        val nowStr = dateFormat.format(Date())
        val fileName = "${head}_${nowStr}.jpg"
        return fileName
    }

    private fun makeImageUri(head: String): Uri {
        val fileName = makeFileName(head)
        val resolver = applicationContext.contentResolver
        val collection = MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY)
        val values = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, fileName)
            put(MediaStore.Images.Media.TITLE, fileName)
            put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
        }
        val imageUri = resolver.insert(collection, values)!!
        return imageUri
    }


}