package com.natanlima.churrascometro

import android.content.Intent
import android.icu.text.DecimalFormat
import android.icu.text.DecimalFormatSymbols
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Locale

const val KEY_RESULT_BEER = "MainActivity.KEY_BEER";
const val KEY_RESULT_MEAT = "MainActivity.KEY_MEAT";
const val KEY_RESULT_SOFT_DRINK = "MainActivity.KEY_SOFT_DRINK";

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val beer = intent.getIntExtra(KEY_RESULT_BEER, 0);
        val meat = intent.getFloatExtra(KEY_RESULT_MEAT, 0f);
        val softDrink = intent.getIntExtra(KEY_RESULT_SOFT_DRINK, 0);

        val tvMeat = findViewById<TextView>(R.id.tv_meat);
        val tvBeer = findViewById<TextView>(R.id.tv_beer);
        val tvCup = findViewById<TextView>(R.id.tv_cup);
        val btnNewCalc = findViewById<Button>(R.id.btn_new_calc);

        val decimalFormatSymbol = DecimalFormatSymbols(Locale.getDefault()).apply {
            decimalSeparator = ',';
        }
        val meatFormat = DecimalFormat("#.000", decimalFormatSymbol);



        tvMeat.text = "Quantidade de carne (Kg): ${meatFormat.format(meat)}";
        tvBeer.text = "Quantidade de cerveja (Latas): $beer";
        tvCup.text = "Quantidade de refrigerante (Litros): $softDrink";

        btnNewCalc.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP;
            }
            startActivity(intent);
        }
    }
}