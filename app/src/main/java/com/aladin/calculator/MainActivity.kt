package com.aladin.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*
import java.text.DecimalFormat
import java.text.NumberFormat
import kotlin.math.abs
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    var op = ""
    var pNum = ""
    var isNewOp = true

    fun buNumberEvent(view: View) {
        if(isNewOp){
            etShowNumber.setText("")
        }
        isNewOp = false

        val entryData = etShowNumber.text
        val buSelected = view as Button
        var buClickValue:String = entryData.toString()
        if(buClickValue == "0") buClickValue = ""
        when(buSelected.id){
            bu0.id -> buClickValue += "0"
            bu1.id -> buClickValue += "1"
            bu2.id -> buClickValue += "2"
            bu3.id -> buClickValue += "3"
            bu4.id -> buClickValue += "4"
            bu5.id -> buClickValue += "5"
            bu6.id -> buClickValue += "6"
            bu7.id -> buClickValue += "7"
            bu8.id -> buClickValue += "8"
            bu9.id -> buClickValue += "9"
            buDot.id -> {
                //todo prevent multiple period
                if( (buClickValue.toDouble() / ceil( buClickValue.toDouble()))  == 1.0){
                    buClickValue +="."
                }

            }
            buSign.id -> {
                if(buClickValue.toDouble() > 0){
                    var negValue = abs(buClickValue.toDouble()) * -1
                    buClickValue = negValue.toString()
                }else{
                    buClickValue = abs(buClickValue.toDouble()).toString()
                }

            }
            buAc.id -> {
                buClickValue = ""
                isNewOp = true
            }
        }
        etShowNumber.setText(buClickValue)
    }

    fun buOpEvent(view: View) {
        val buSelected = view as Button
        when(buSelected.id) {
            buDiv.id -> {
                op ="/"
            }
            buMul.id -> {
                op ="*"
            }
            buMin.id -> {
                op ="-"
            }
            buAdd.id -> {
                op ="+"
            }

        }
        pNum = etShowNumber.text.toString()
        isNewOp = true
    }

    fun buEqualEvent(view: View) {
        var nNum = etShowNumber.text.toString()
        var answer:Double? = null
        when(op){
            "*" -> {
                answer = pNum.toDouble() * nNum.toDouble()
            }
            "/" -> {
                answer = pNum.toDouble() / nNum.toDouble()
            }
            "+" -> {
                answer = pNum.toDouble() + nNum.toDouble()
            }
            "-" -> {
                answer = pNum.toDouble() - nNum.toDouble()
            }

        }

        val df = DecimalFormat("0.####")
        etShowNumber.setText(df.format(answer).toString())
        isNewOp = true
    }

    fun buPercEvent(view: View) {

        val percNum:Double = etShowNumber.text.toString().toDouble() / 100
        etShowNumber.setText(percNum.toString())
    }


}
