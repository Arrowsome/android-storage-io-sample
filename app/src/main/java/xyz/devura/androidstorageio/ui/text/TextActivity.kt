package xyz.devura.androidstorageio.ui.text

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.text_activity.*
import xyz.devura.androidstorageio.R

class TextActivity : AppCompatActivity() {

    private lateinit var textViewModel: TextViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.text_activity)

        initComponents()
        initClickListeners()
        setupObservers()
    }

    private fun initComponents() {
        textViewModel = ViewModelProvider(this).get(TextViewModel::class.java)
    }

    private fun initClickListeners() {

        button_save.setOnClickListener {
            val input = editText.text.toString()
            textViewModel.onSaveTextFile(input, "hello.txt")
        }

        button_read.setOnClickListener {
            textViewModel.onReadTextFile("hello.txt")
        }

    }

    private fun setupObservers() {

        textViewModel.clearFormLiveData.observe(this, Observer { isClearCommand ->
            if (isClearCommand) editText.text = null
        })

        textViewModel.contentLiveData.observe(this, Observer { content ->
            editText.setText(content)
        })

        textViewModel.toastViewModel.observe(this, Observer { message ->
            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        })

    }

}
