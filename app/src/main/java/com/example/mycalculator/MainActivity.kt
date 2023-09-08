package com.example.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private var inputType: TextView? = null
    private var btnOne: Button? = null
    var lastNumber: Boolean = false
    var lastDot: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        inputType = findViewById(R.id.inputNumber)
        btnOne = findViewById(R.id.button_1)

        btnOne?.setOnClickListener {
            inputType?.append("1")
        }
    }

    fun onDigitClick(view: View) {
        inputType?.append((view as Button).text)
        lastNumber = true
        lastDot = false
    }

    fun onClear(view: View) {
        inputType?.text = ""
    }

    fun onDecimalClick(view: View) {
        if (!lastDot && lastNumber) {
            inputType?.append(".")
            lastDot = true
            lastNumber = false
        }
    }

    fun onOperator(view: View) {
        inputType?.text.let {
            if (lastNumber && !onIsOperator(it.toString())) {
                inputType?.append((view as Button).text)
                lastNumber = false
                lastDot = false
            }
        }
    }

    private fun onIsOperator(value: String): Boolean {
        return if (value.startsWith("-")) {
            false
        } else {
            value.contains("-")
            value.contains("+")
            value.contains("*")
            value.contains("/")
        }
    }

    fun onEqual(view: View) {
        if (lastNumber) {
            var value = inputType?.text.toString()
            var prefix = ""

            try {
                if (value.startsWith("-")) {
                    prefix = "-"
                    value = value.substring(1)
                }
                else if (value.contains("-")) {
                    var splitValue = value.split("-")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (prefix.isNotEmpty()) {
                        one = prefix + one
                    }
                    var result = one.toDouble() - two.toDouble()
                    inputType?.text = result.toString()
                }else if (value.contains("/")) {
                    var splitValue = value.split("/")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (prefix.isNotEmpty()) {
                        one = prefix + one
                    }
                    var result = one.toDouble() / two.toDouble()
                    inputType?.text = result.toString()
                }else if (value.contains("+")) {
                    var splitValue = value.split("+")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (prefix.isNotEmpty()) {
                        one = prefix + one
                    }
                    var result = one.toDouble() + two.toDouble()
                    inputType?.text = result.toString()
                }else if (value.contains("*")) {
                    var splitValue = value.split("*")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (prefix.isNotEmpty()) {
                        one = prefix + one
                    }
                    var result = one.toDouble() * two.toDouble()
                    inputType?.text = result.toString()
                }

            } catch (e: ArithmeticException) {
                e.printStackTrace()
            }
        }
    }

}