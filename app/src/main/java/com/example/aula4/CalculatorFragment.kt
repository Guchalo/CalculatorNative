package com.example.aula4

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aula4.databinding.ActivityMainBinding
import com.example.aula4.databinding.FragmentCalculatorBinding
import net.objecthunter.exp4j.ExpressionBuilder

class CalculatorFragment : Fragment() {
    private lateinit var binding: FragmentCalculatorBinding
    private val TAG = MainActivity::class.java.simpleName
    private val operacions = mutableListOf<String>()
    private val adapter = HistoryAdapter(::onOperacionClick)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_calculator, container, false)
        binding = FragmentCalculatorBinding.bind(view)
        return binding.root
    }

    private fun onOperacionClick(operacion: String) {
        Toast.makeText(this.context, operacion, Toast.LENGTH_LONG).show()
    }

    override fun onStart() {
        super.onStart()
        binding.rvHistoric!!.layoutManager = LinearLayoutManager(activity as Context)
        binding.rvHistoric!!.adapter = adapter

        binding.button0.setOnClickListener {
            onClickSymbol("0")
        }

        binding.button1.setOnClickListener {
            onClickSymbol("1")
        }

        binding.button2.setOnClickListener {
            onClickSymbol("2")

        }

        binding.button3.setOnClickListener {
            onClickSymbol("3")
        }

        binding.button4.setOnClickListener {
            onClickSymbol("4")
        }

        binding.button5.setOnClickListener {
            onClickSymbol("5")
        }

        binding.button6.setOnClickListener {
            onClickSymbol("6")
        }

        binding.button7.setOnClickListener {
            onClickSymbol("7")
        }

        binding.button8.setOnClickListener {
            onClickSymbol("8")
        }

        binding.button9.setOnClickListener {
            onClickSymbol("9")
        }

        binding.buttonAdicion?.setOnClickListener {
            Log.i(TAG, "Clique no botão +")
            binding.textVisor.append("+")
        }

        binding.buttonMinus.setOnClickListener {
            Log.i(TAG, "Clique no botão -")
            binding.textVisor.append("-")
        }

        binding.buttonMult.setOnClickListener {
            Log.i(TAG, "Clique no botão *")
            binding.textVisor.append("*")
        }

        binding.buttonBack.setOnClickListener {
            Log.i(TAG, "Clique no botão «")
            if (binding.textVisor.text.isEmpty() || binding.textVisor.text == "0" || binding.textVisor.text == ""
                || binding.textVisor.text.length == 1
            ) {
                binding.textVisor.text = "0"
                return@setOnClickListener
            }
            binding.textVisor.text =
                binding.textVisor.text.substring(0, binding.textVisor.text.length - 1)
        }

        binding.buttonClean.setOnClickListener {
            Log.i(TAG, "Clique no botão C")
            binding.textVisor.text = "0"
        }

        binding.buttonAsk.setOnClickListener {
            Log.i(TAG, "Clique no botão :)")
            binding.textVisor.text = "-_(o.o)_-"
        }

        binding.buttonEx.setOnClickListener {
            Log.i(TAG, "Clique no botão <3")
            binding.textVisor.text = "<3"
        }

        binding.buttonDot.setOnClickListener {
            Log.i(TAG, "Clique no botão .")
            binding.textVisor.append(".")
        }

        binding.buttonEquals.setOnClickListener {
            Log.i(TAG, "Clique no botão =")
            val expression = ExpressionBuilder(binding.textVisor.text.toString()).build()
            val op = binding.textVisor.text.toString()
            binding.textVisor.text = expression.evaluate().toString()
            val expressionF = "$op = ${binding.textVisor.text}"
            Log.i(TAG, "O resultado da expressão é ${binding.textVisor.text}")
            operacions.add(expressionF)
            adapter.updtateItems(operacions)
        }
    }

    private fun onClickSymbol(symbol: String) {
        Log.i(TAG, "Clique no botão $symbol")
        if (binding.textVisor.text == "0") {
            binding.textVisor.text = symbol
        } else {
            binding.textVisor.append(symbol)
        }
    }
}