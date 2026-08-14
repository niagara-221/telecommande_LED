package com.example.showimage

import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.showimage.databinding.ActivityMainBinding

/**
 * Displays an image on screen only after a double-tap anywhere on the root view.
 *
 * The image shown is `R.drawable.your_image`. To use your own image, replace
 * `app/src/main/res/drawable/your_image.xml` (or `your_image.png/.jpg`) with your file,
 * keeping the same resource name `your_image`.
 */
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    // A single-tap toggles the image OFF; a double-tap toggles it ON. This way the user
    // can both reveal and hide the image without needing extra buttons.
    private lateinit var gestureDetector: GestureDetector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        gestureDetector = GestureDetector(this, object : GestureDetector.SimpleOnGestureListener() {
            override fun onDoubleTap(e: MotionEvent): Boolean {
                showImage()
                return true
            }

            override fun onSingleTapConfirmed(e: MotionEvent): Boolean {
                hideImage()
                return true
            }
        })

        binding.root.setOnTouchListener { _, event ->
            gestureDetector.onTouchEvent(event)
            true
        }
    }

    private fun showImage() {
        binding.secretImage.visibility = View.VISIBLE
        binding.hintText.visibility = View.GONE
    }

    private fun hideImage() {
        binding.secretImage.visibility = View.INVISIBLE
        binding.hintText.visibility = View.VISIBLE
    }
}
