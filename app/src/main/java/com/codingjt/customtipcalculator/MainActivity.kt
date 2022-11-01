package com.codingjt.customtipcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.codingjt.customtipcalculator.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showTip(0.0)

        binding.calculateButton.setOnClickListener {
            calculateButtonTapped()
        }
    }

    private fun calculateButtonTapped() {
        val costStr = binding.costOfServiceEditText.text.toString()

        if (costStr.isEmpty()) {
            showTip(0.0)
            return
        }

        val cost = costStr.toDoubleOrNull() ?: return

        val checkedOptionId = binding.radioGroup.checkedRadioButtonId
        val tipPercentage = when(checkedOptionId) {
            binding.optionAmazing.id -> 0.20
            binding.optionGood.id -> 0.18
            binding.optionOkay.id -> 0.15
            else -> 0.0
        }

        val tip = cost * tipPercentage
        showTip(tip)
    }

    private fun showTip(tip: Double) {
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipAmountTextView.text = getString(R.string.tip_amount, formattedTip)
    }

}