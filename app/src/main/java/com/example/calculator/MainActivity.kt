package com.example.calculator

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.calculator.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {
   lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.apply {
            btn0.setOnClickListener{setTextFields("0")}
            btn1.setOnClickListener{setTextFields("1")}
            btn2.setOnClickListener{setTextFields("2")}
            btn3.setOnClickListener{setTextFields("3")}
            btn4.setOnClickListener{setTextFields("4")}
            btn5.setOnClickListener{setTextFields("5")}
            btn6.setOnClickListener{setTextFields("6")}
            btn7.setOnClickListener{setTextFields("7")}
            btn8.setOnClickListener{setTextFields("8")}
            btn9.setOnClickListener{setTextFields("9")}
            btnMinus.setOnClickListener{setTextFields("-")}
            btnPlus.setOnClickListener{setTextFields("+")}
            btnX.setOnClickListener{setTextFields("×")}
            btnSplit.setOnClickListener{setTextFields("/")}
            btnStartBrackets.setOnClickListener{setTextFields("(")}
            btnEndBrackets.setOnClickListener{setTextFields(")")}
            btnTochka.setOnClickListener { (setTextFields(".")) }
        }



        binding.btnAc.setOnClickListener{
            binding.mathOperation.text = ""
            binding.resultText.text = ""
        }
        binding.btnBack.setOnClickListener{
            val str = binding.mathOperation.text.toString()
            if (str.isNotEmpty())
                binding.mathOperation.text = str.substring(0,str.length-1)
            binding.resultText.text = ""

        }

        binding.btnEqually.setOnClickListener{
            try {
                val ex = ExpressionBuilder(binding.mathOperation.text.toString()).build()
                val resul = ex.evaluate()
                val LongRes = resul.toLong()
                if (resul== LongRes.toDouble())
                    binding.resultText.text = LongRes.toString()
                else
                    binding.resultText.text = resul.toString()

            }catch (e:Exception) {
                Log.d("Ошибка", "Сообщение: ${e.message}")
            }
        }
    }

    fun setTextFields(str: String) {
        if (binding.resultText.text != "") {

            binding.mathOperation.text = binding.resultText.text
            binding.resultText.text = ""
        }
        binding.mathOperation.append(str)
    }
}