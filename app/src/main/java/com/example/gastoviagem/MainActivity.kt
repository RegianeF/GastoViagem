package com.example.gastoviagem

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.gastoviagem.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity()/*, View.OnClickListener*/ {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        //então não esquecer de inicializar a variavel e também só usar depois de inicializada
        binding.editDistance
        //trazer o setContentView para baixo - depois de instanciado, root é a raiz do layout
        setContentView(binding.root)

        /*binding.buttonCalculate.setOnClickListener(this) esse aqui esta usando o override lá de embaixo*/

        binding.buttonCalculate.setOnClickListener {
            calculate()
            //essa forma aqui é a mais simples e pratica de fazer o botão funcionar,
            //é através da expressão lambda, está chamando a função calculate e executando
        }

        binding.editDistance.setOnFocusChangeListener { v, hasFocus ->
            if (binding.editDistance.text.toString() == "" && !hasFocus) {
                binding.editDistance.error = "Campo obrigatório"
                //aparece uma mensagem e é possivel clicar em cima do erro
                // para entender o que é o erro
            }
        }
    }

    /*override fun onClick(view: View) { //implementação de OnClickListener
        if (view.id == R.id.button_calculate){ //se o paramentro view que herda de view, que view.id
            //for igual ao botão calculate (tem que dar match para funcionar) então faça
            calculate()
        }
    }*/

    private fun isValid(): Boolean {
        if (binding.editDistance.text.toString() == "") {
            binding.editDistance.error = "Campo obrigatório"
            //aparece um icone de erro do lado se o campo não for preenchido - ta pra distancia

        }

        return (binding.editDistance.text.toString() != "" &&
                binding.editPrice.text.toString() != "" &&
                binding.editAutonomy.text.toString() != "" &&
                binding.editAutonomy.text.toString().toFloat() != 0f
                )
    }

    private fun calculate() {
        if (isValid()) {
            val distance = binding.editDistance.text.toString().toFloat()
            val price = binding.editPrice.text.toString().toFloat()
            val autonomy = binding.editAutonomy.text.toString().toFloat()
            //essa é a maneira, para pegar um valor, então tem um val para ser usada;
            //binding é o nome da instancia, edit é forma de pegar o valor,text é o texto informado,
            //não a string, to string nao faço ideia o pq
            //mas é necessario converter para float para usar

            val totalValue = (distance * price) / autonomy
            val totalValueString = "R$ ${"%.2f".format(totalValue)}"
            //totalValue transformado para string formatado com duas casas de float
            //e enviado para Toast

            binding.textValue.text = totalValueString
            //text value é o nome do total lá no layout
            //então esta passando totalValueString para o textValue
        } else {
            Toast.makeText(this, R.string.validation_fill_all_field, Toast.LENGTH_SHORT).show()
            //R.String é uma string com nome, tem no campo de string, e diz preencha seus dados
        }
    }

}