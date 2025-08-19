package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.health.connect.datatypes.units.Length
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.NumberPicker
import android.widget.RatingBar
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar


class BorrowDetailsActivity : AppCompatActivity() {
    @SuppressLint()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_borrow_details)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.borrowDetailsActivity)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val instrument = intent.getParcelableExtra<Instrument>("instrument")
        val currentScore = intent.getStringExtra("currentCreditScore")!!.toInt()
        val selectedColor = intent.getStringExtra("sColor")
        var newScore = currentScore

        instrument?.let {
            findViewById<TextView>(R.id.instrumentName).text = it.name
            findViewById<RatingBar>(R.id.instrumentRating).rating = it.rating
            findViewById<TextView>(R.id.instrumentPrice).text = "${it.price} Credit per month"
            findViewById<ImageView>(R.id.instrumentImage).setImageResource(getImageResourceForInstrument(it.name))
        }


        findViewById<Button>(R.id.cancelButton).setOnClickListener {
            Toast.makeText(this, "Booking canceled.", Toast.LENGTH_LONG).show()
            finish()
        }

        val durationEditText = findViewById<EditText>(R.id.borrowDuration)


        findViewById<Button>(R.id.confirmButton).setOnClickListener {
            val durationText = durationEditText.text.toString()
            if(durationText.isNotEmpty() && durationText.toIntOrNull() != null && durationText.toInt() != 0 && durationText.toInt()>0){
                val duration = durationText.toInt()
                val totalCost = duration * instrument!!.price
                if (totalCost > currentScore){
                    val remainderCost = totalCost - currentScore
                    Snackbar.make(durationEditText, "Insufficient credit. You need $remainderCost more credits", Snackbar.LENGTH_LONG).show()
                } else {
                    val newScore = currentScore - totalCost
                    val resultIntent = Intent()
                    resultIntent.putExtra("updatedCreditScore", newScore.toString())
                    setResult(RESULT_OK, resultIntent)

                    Toast.makeText(this, "A $selectedColor ${instrument.name} for $durationText month(s) is now available for pickup.", Toast.LENGTH_LONG).show()
                    finish()

                }
            } else {
                Snackbar.make(durationEditText, "Please enter a valid positive number", Snackbar.LENGTH_LONG).show()
            }




        }
    }

    private fun getImageResourceForInstrument(name: String): Int {
        return when (name) {
            "Guitar" -> R.drawable.guitarpic
            "Piano" -> R.drawable.pianopic
            "Drum Set" -> R.drawable.drumsetpic
            else -> R.drawable.intropic
        }
    }
}
