package com.example.tipcalculator

import android.app.Activity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import com.google.android.material.slider.Slider


class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val calculateButton: Button = findViewById(R.id.calculateButton)
        val billAmountEditText: EditText = findViewById(R.id.billAmountEditText)
        val tipPercentageSlider: Slider = findViewById(R.id.tipPercentageSlider)
        val tipResultTextView: TextView = findViewById(R.id.tipResultTextView)

        tipPercentageSlider.setLabelFormatter { value ->
            "$value%"
        }

        billAmountEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                updateTipAmount(billAmountEditText.text.toString(), tipPercentageSlider.value, tipResultTextView)
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        tipPercentageSlider.addOnChangeListener { _, value, _ ->
            updateTipAmount(billAmountEditText.text.toString(), value, tipResultTextView)
        }

        }

    private fun updateTipAmount(billAmountText: String, tipPercentage: Float, tipResultTextView: TextView) {
        val billAmount = billAmountText.toDoubleOrNull() ?: 0.0
        val tipAmount = calculateTip(billAmount, tipPercentage.toDouble())
        tipResultTextView.text = "Tip Amount: ${"%.2f".format(tipAmount)}"
    }

    private fun calculateTip(billAmount: Double, tipPercentage: Double): Double {
        return billAmount * (tipPercentage / 100.0)
    }
}