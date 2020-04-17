package xyz.devura.androidstorageio.ui.serialization

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.serialization_activity.*
import xyz.devura.androidstorageio.R
import xyz.devura.androidstorageio.model.Person

class SerializationActivity : AppCompatActivity() {

    private lateinit var serializationViewModel: SerializationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.serialization_activity)

        initComponents()
        setupClickListeners()
        setupObservers()
    }

    private fun initComponents() {
        serializationViewModel = ViewModelProvider(this).get(SerializationViewModel::class.java)
    }

    private fun setupClickListeners() {

        button_save.setOnClickListener {
            val name = editText_name.text.toString()
            val age = editText_age.text.toString().toInt()
            serializationViewModel.onSaveObject(Person(name, age),"object")
        }

        button_revive.setOnClickListener {
            serializationViewModel.onReviveObject("object")
        }

    }

    private fun setupObservers() {

        serializationViewModel.toastViewModel.observe(this, Observer { message ->
            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        })

        serializationViewModel.reviveViewModel.observe(this, Observer { person ->
            textView_revived_object.text = "Person(${person.name}, ${person.age})"
        })

    }

}
