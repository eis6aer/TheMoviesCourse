package com.bobafett.themoviesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        intent?.extras?.let {
            txt_main_name.text = it.getString("name")
        }

        btn_close.setOnClickListener {
            val returnIntent = Intent().apply {
                putExtra(RESULT_KEY, "REGRESO MACHO!!!")
            }

            setResult(RESULT_OK, returnIntent)
            finish()
        }
    }

    companion object {
        val RESULT_KEY = "return_data"
    }
}