package com.example.zaetsdrawable

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.graphics.drawable.toBitmap
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.zaetsdrawable.databinding.ActivityMainBinding
import java.io.ByteArrayOutputStream

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // BITMAP TO BYTE ARRAY
        val stream = ByteArrayOutputStream()
        binding.bitmap.drawable.toBitmap().compress(Bitmap.CompressFormat.PNG, 100, stream)
        val byteArray = stream.toByteArray()

        setupListener()
    }

    private fun setupListener() {
        binding.btnSelector.setOnClickListener {
            drawLine()
        }
        binding.btnGlide.setOnClickListener {
            loadImage()
        }
    }

    private fun loadImage() {
        Glide.with(this)
            .load("https://images.drive.ru/i/3/5eaaff4fec05c40206000010")
            .circleCrop()
            .transition(DrawableTransitionOptions.withCrossFade(2000))
            .into(binding.imageGlide)
    }

    private fun drawLine(){
        val porsheBitmap = binding.bitmap.drawable
            .toBitmap().
            copy(Bitmap.Config.RGB_565, true)
        val painter = Paint().apply {
            color = getColor(R.color.black)
            strokeWidth = 30f
        }
        Canvas(porsheBitmap).apply {
            drawLine(0f, 0f, 500f, 500f, painter)
        }
        binding.bitmap.setImageBitmap(porsheBitmap)
    }
}