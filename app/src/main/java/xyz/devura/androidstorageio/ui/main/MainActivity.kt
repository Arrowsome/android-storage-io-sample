package xyz.devura.androidstorageio.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.main_activity.*
import xyz.devura.androidstorageio.R
import xyz.devura.androidstorageio.ui.image.ImageActivity
import xyz.devura.androidstorageio.ui.serialization.SerializationActivity
import xyz.devura.androidstorageio.ui.text.TextActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        setupClickListeners()
    }

    private fun setupClickListeners() {

        button_open_file_saving.setOnClickListener {
            startActivity(Intent(this, TextActivity::class.java))
        }

        button_open_image_saving.setOnClickListener {
            startActivity(Intent(this, ImageActivity::class.java))
        }

        button_open_serialization.setOnClickListener {
            startActivity(Intent(this, SerializationActivity::class.java))
        }

    }

}
