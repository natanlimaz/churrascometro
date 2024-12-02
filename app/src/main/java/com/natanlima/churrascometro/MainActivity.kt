package com.natanlima.churrascometro

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText

fun showSnackBar(context: android.content.Context, message: String, view: android.view.View) {
    val snackbar: Snackbar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT);
    snackbar.setBackgroundTint(ContextCompat.getColor(context, R.color.blue));
    snackbar.setTextColor(ContextCompat.getColor(context, R.color.white));
    snackbar.show();
}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val edtAdult = findViewById<TextInputEditText>(R.id.edt_adult)
        val edtChild = findViewById<TextInputEditText>(R.id.edt_child);
        val edtDuration = findViewById<TextInputEditText>(R.id.edt_duration);
        val btnCalc = findViewById<Button>(R.id.btn_calc);

        btnCalc.setOnClickListener {
            val adultStr = edtAdult.text.toString();
            val childStr = edtChild.text.toString();
            val durationStr = edtDuration.text.toString();

            if(adultStr.isNotEmpty() && childStr.isNotEmpty() && durationStr.isNotEmpty()) {
                val adult = adultStr.toInt();
                val child = childStr.toInt();
                val duration = durationStr.toInt();

                if(adult > 0 && child > 0 && duration > 0) {
                    val beer = adult * 6;
                    val meat = (adult * 300 + child * 100) / 1000.0f;
                    val softdrink = (adult + child / 2);

                    val intent = Intent(this, ResultActivity::class.java);
                    intent.putExtra(KEY_RESULT_BEER, beer);
                    intent.putExtra(KEY_RESULT_MEAT, meat);
                    intent.putExtra(KEY_RESULT_SOFT_DRINK, softdrink);
                    startActivity(intent);
                }
                else {
                    showSnackBar(this@MainActivity, "Os valores não podem ser 0.", edtAdult);
                }
            }

            else {
                showSnackBar(this@MainActivity, "Preencha todos os campos!", edtAdult);
            }
        }

    }
}