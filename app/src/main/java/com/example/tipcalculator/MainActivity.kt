package com.example.tipcalculator

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val calculateButton: Button = findViewById(R.id.calculateButton)
        val billAmountEditText: EditText = findViewById(R.id.billAmountEditText)
        val tipPercentageEditText: EditText = findViewById(R.id.tipPercentageEditText)
        val tipResultTextView: TextView = findViewById(R.id.tipResultTextView)

        calculateButton.setOnClickListener {
            val billAmount = billAmountEditText.text.toString().toDoubleOrNull() ?: 0.0
            val tipPercentage = tipPercentageEditText.text.toString().toDoubleOrNull() ?: 0.0
            val tipAmount = calculateTip(billAmount, tipPercentage)
            tipResultTextView.text = "Tip Amount: $${"%.2f".format(tipAmount)}"
        }
    }

    private fun calculateTip(billAmount: Double, tipPercentage: Double): Double {
        return billAmount * (tipPercentage / 100.0)
    }
}