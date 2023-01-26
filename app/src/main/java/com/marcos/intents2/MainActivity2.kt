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

        //declaramos el intent
        val intent = getIntent()

        //recogemos los valores enviados de la MainActivity
        val firstRandomNumber = intent.getIntExtra("firstNumber",0)
        val secondRandomNunber = intent.getIntExtra("secondNumber",0)
        //los enviamos al TextView para que el usuario pueda verlos
        val sumaText = findViewById<TextView>(R.id.sumaText)
        sumaText.text = "$firstRandomNumber + $secondRandomNunber = ?"

        val sendButton = findViewById<Button>(R.id.sendButton)
        val resultEditText = findViewById<EditText>(R.id.resultEditText)
        sendButton.setOnClickListener{
            //metemos el dato resultado en el intent que recogemos del EditText
            intent.putExtra("result", resultEditText.text.toString())

            Log.d("CHECK", "RESULTADO = ${resultEditText.text}")
            //enviamos los datos
            setResult(Activity.RESULT_OK, intent)
            //cerramos la MainActivity2
            finish()
        }
    }
}