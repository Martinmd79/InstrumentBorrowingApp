package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar

class MainActivity2 : AppCompatActivity() {
    @SuppressLint()

    private val instruments = listOf(
        Instrument("Guitar", "A classic acoustic guitar.", 15, 4.5f, listOf("Black", "White", "Brown"), R.drawable.guitarpic),
        Instrument("Piano", "An upright piano for all genres.", 25, 4.8f, listOf("Black", "White", "Red"), R.drawable.pianopic),
        Instrument("Drum Set", "A full drum set for rock and jazz.", 20, 4.0f, listOf("Black", "Blue", "Brown"), R.drawable.drumsetpic)
    )


    private var currentInstrumentIndex = 0
    private var currentCreditScore = 500


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_browsing)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.instrumentDetailsLayout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val creditScore = findViewById<TextView>(R.id.creditScore)
        val nextButton = findViewById<Button>(R.id.nextButton)
        val borrowButton = findViewById<Button>(R.id.borrowButton)
        val instrumentTypeGroup = findViewById<RadioGroup>(R.id.instrumentTypeGroup)



        creditScore.text = "Credit: $currentCreditScore"


        updateInstrumentDetails(instruments[currentInstrumentIndex])


        nextButton.setOnClickListener {
            currentInstrumentIndex = (currentInstrumentIndex + 1) % instruments.size
            updateInstrumentDetails(instruments[currentInstrumentIndex])
        }





        borrowButton.setOnClickListener {
            val selectedRadioButtonId = instrumentTypeGroup.checkedRadioButtonId
            if (selectedRadioButtonId == -1){
                Snackbar.make(borrowButton, "Please select a color before proceeding", Snackbar.LENGTH_LONG).show()
            } else{
                val selectedColor = findViewById<RadioButton>(selectedRadioButtonId).text.toString()
                val intent = Intent(this, BorrowDetailsActivity::class.java).apply {
                    putExtra("instrument", instruments[currentInstrumentIndex])
                    putExtra("currentCreditScore", currentCreditScore.toString())
                    putExtra("sColor", selectedColor)
                }
                startActivityForResult(intent, 1)
            }

        }

    }

    private fun updateInstrumentDetails(instrument: Instrument) {
        val instrumentName = findViewById<TextView>(R.id.instrumentName)
        val instrumentDescription = findViewById<TextView>(R.id.instrumentDescription)
        val instrumentPrice = findViewById<TextView>(R.id.instrumentPrice)
        val instrumentRating = findViewById<RatingBar>(R.id.instrumentRating)
        val instrumentImage = findViewById<ImageView>(R.id.instrumentImage)
        val instrumentTypeGroup = findViewById<RadioGroup>(R.id.instrumentTypeGroup)



        instrumentName.text = instrument.name
        instrumentDescription.text = instrument.description
        instrumentPrice.text = "${instrument.price} credits per month"
        instrumentRating.rating = instrument.rating
        instrumentImage.setImageResource(instrument.image)
        instrumentImage.contentDescription = instrument.name

        instrumentTypeGroup.removeAllViews()

        instrument.colorOptions.forEach { color ->
            val radioButton = RadioButton(this)
            radioButton.text = color
            instrumentTypeGroup.addView(radioButton)
        }

    }





    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1 && resultCode == RESULT_OK) {
            data?.getStringExtra("updatedCreditScore")?.let {

                currentCreditScore = it.toInt()
                findViewById<TextView>(R.id.creditScore).text = "Credit: $currentCreditScore"
            }
        }
    }



}
