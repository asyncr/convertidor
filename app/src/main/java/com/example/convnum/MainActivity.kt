package com.example.convnum

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.convnum.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var edt: EditText?=null

    var mtbList: MutableList<EditText>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        this.supportActionBar?.hide()
        detectedFocus()
        who()
        initEdt()
    }

    fun initEdt(){
        mtbList?.add(binding.edtDecimal)
        mtbList?.add(binding.edtBinary)
        mtbList?.add(binding.edtOctal)
        mtbList?.add(binding.edtHexa)
    }

    fun detectedFocus(){
        binding.edtDecimal.setOnFocusChangeListener { _, f ->
            edt = binding.edtDecimal
            if (f) text(edt!!)
        }
        binding.edtBinary.setOnFocusChangeListener { _, f ->
            edt=binding.edtBinary
            if (f) text(edt!!)
        }
        binding.edtOctal.setOnFocusChangeListener { _, f ->
            edt=binding.edtOctal
            if (f) text(edt!!)
        }
        binding.edtHexa.setOnFocusChangeListener { _, f ->
            edt=binding.edtHexa
            if (f) text(edt!!)
        }
    }

    fun loadData(editText: EditText){
        with(editText) {
            addTextChangedListener(object : TextWatcher{
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = sms(editText.text.toString())
                override fun onTextChanged(newText: CharSequence?, p1: Int, p2: Int, p3: Int) = sms(editText.text.toString())
                override fun afterTextChanged(newText: Editable?) = sms(editText.text.toString())
            })
        }
    }

    var sms = {str:String? -> binding.tvTitle.text = str }

    var text = {edt:EditText? ->
        if(edt!!.text.isNotEmpty()) sms(edt!!.text.toString())
        loadData(edt!!)
        who()
    }

    fun who() {
        when (edt) {
            binding.edtDecimal -> Toast.makeText(this, "Decimal", Toast.LENGTH_SHORT).show() //Llamar su metodo
            binding.edtBinary -> Toast.makeText(this, "Binario", Toast.LENGTH_SHORT).show() //Llamar el metodo
            binding.edtOctal -> Toast.makeText(this, "Octal", Toast.LENGTH_SHORT).show() //LLamar su metodo
            binding.edtHexa -> Toast.makeText(this, "Hexadecimal", Toast.LENGTH_SHORT).show() //LLamar su metodo
            else -> {}
        }
    }

    fun sendData(){

    }



    fun decimaltoAll(base:Int, decimal:Int):String{
        val finalValue = "0123456789abcdef"
        var value  = ""
        val base = base
        var decimal = decimal
        while (decimal > 0) {
            val residuo: Int = decimal % base
            value = finalValue[residuo] + value // Add to left
            decimal /= base
        }
        return value
    }










}