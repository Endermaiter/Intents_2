package com.marcos.intents2

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val intent = getIntent()

        val firstRandomNumber = intent.getIntExtra("firstNumber",0)
        val secondRandomNunber = intent.getIntExtra("secondNumber",0)
        val sumaText = findViewById<TextView>(R.id.sumaText)
        sumaText.text = "$firstRandomNumber + $secondRandomNunber = ?"

        val sendButton = findViewById<Button>(R.id.sendButton)
        val resultEditText = findViewById<EditText>(R.id.resultEditText)
        sendButton.setOnClickListener{

            intent.putExtra("result", resultEditText.text.toString())

            Log.d("CHECK", "RESULTADO = ${resultEditText.text}")

            setResult(Activity.RESULT_OK, intent)

            finish()
        }
    }
}