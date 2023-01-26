package com.marcos.intents2

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import java.util.*

class MainActivity : AppCompatActivity() {

    val RESULT_CAMERA = 1
    val RESULT_ACTIVITY = 2

    val firstRandomNumber = randomNumber()
    val secondRandomNumber = randomNumber()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val buttonCamera = findViewById<Button>(R.id.buttonCamera)
        val buttonRandom = findViewById<Button>(R.id.buttonRandom)

        buttonCamera.setOnClickListener {
            //Creamos un intent que inicia la camara cuando pulsamos el boton
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            //llamamos a la activity de la camara
            startActivityForResult(intent, RESULT_CAMERA)
        }

        buttonRandom.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            intent.putExtra("firstNumber", firstRandomNumber)
            intent.putExtra("secondNumber", secondRandomNumber)
            startActivityForResult(intent, RESULT_ACTIVITY)
        }

    }

    fun randomNumber(): Int {
        val numeroRandom = Random().nextInt(10)
        return numeroRandom
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val imageView = findViewById<ImageView>(R.id.imageView)
        val resultText = findViewById<TextView>(R.id.resultText)
        if (requestCode != Activity.RESULT_OK) {
            when (requestCode) {
                RESULT_CAMERA -> {
                    if (data != null) {
                        //recogemos la imagen tomada en una variable de tipo Bitmap
                        val imageBitmap = data.extras!!.get("data") as Bitmap
                        //Le mostramos la foto sacada al usuario mediante el imageView
                        imageView.setImageBitmap(imageBitmap)
                    };
                }
                RESULT_ACTIVITY -> {
                    if (data != null) {
                        val resultString = data.getStringExtra("result")
                        val suma = firstRandomNumber + secondRandomNumber
                        Log.d("CHECK", "RESULTADO 1AC = $resultString")
                        if (suma.toString()==resultString) {
                            resultText.text = "Resultado: ¡CORRECTO!"
                        } else {
                            resultText.text = "Resultado: INCORRECTO..."
                        }
                    };
                }
                else -> {}
            }
        }
    }
}
