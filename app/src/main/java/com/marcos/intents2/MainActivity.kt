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

    //RESULTS
    val RESULT_CAMERA = 1
    val RESULT_ACTIVITY = 2

    //Numeros random generados con el metodo randomNumber()
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
            //Creamos un intent que inicia la segunda activity cuando pulsamos el boton
            val intent = Intent(this, MainActivity2::class.java)

            //introducimos los numeros random generados en el intent
            intent.putExtra("firstNumber", firstRandomNumber)
            intent.putExtra("secondNumber", secondRandomNumber)
            //inicamos la activity con su respectivo result
            startActivityForResult(intent, RESULT_ACTIVITY)
        }

    }

    //metodo para generar un numero random entre 0 y 9 (ambos incluidos)
    fun randomNumber(): Int {
        val numeroRandom = Random().nextInt(10)
        return numeroRandom
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val imageView = findViewById<ImageView>(R.id.imageView)
        val resultText = findViewById<TextView>(R.id.resultText)
        //si requestCode es distinto de -1
        if (requestCode != Activity.RESULT_OK) {
            //"switch" en funcion del request code recibido por el método
            when (requestCode) {
                //si requestCode = 1 (CAMARA)
                RESULT_CAMERA -> {
                    //si data no es null...
                    if (data != null) {
                        //recogemos la imagen tomada en una variable de tipo Bitmap
                        val imageBitmap = data.extras!!.get("data") as Bitmap
                        //Le mostramos la foto sacada al usuario mediante el imageView
                        imageView.setImageBitmap(imageBitmap)
                    };
                }
                //si requestCode = 2 (resultado de la MainActivity2)
                RESULT_ACTIVITY -> {
                    //si data no es null...
                    if (data != null) {
                        //recogemos el dato (el resultado) enviado por la MainActivity2
                        val resultString = data.getStringExtra("result")
                        //sumamos los valores para posteriormente compararlo con el resultado proporcionado por el usuario
                        val suma = firstRandomNumber + secondRandomNumber
                        Log.d("CHECK", "RESULTADO 1AC = $resultString")
                        //comprobamos si la suma de los numeros es igual al resultado dado por el usuario
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
