package com.bobafett.themoviesapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.bobafett.themoviesapp.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import java.util.*

class MainActivity : AppCompatActivity() {
  private val viewModel: MainViewModel by viewModels()
  private val job = Job()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    btn_send_to_fragment.setOnClickListener {
      startActivityForResult(Intent(this, DetailActivity::class.java).apply {
        putExtra("name", "Harry")
      }, REQUEST_CODE)
    }

    viewModel.nameStream.observe(this, Observer {
      event ->
      when (event) {
        is MainViewModel.UIEvent.success -> {
          btn_send_to_fragment.text = "${event.name} is ${event.age} years old"
        }
        is MainViewModel.UIEvent.failed -> {
          btn_send_to_fragment.text = "Failed with code: ${event.errorCode} and message: ${event.errorMessage}"
        }
        is MainViewModel.UIEvent.create_account -> {
          btn_send_to_fragment.text = "${event.name} is ${event.age} years old and the account was created : ${event.dateOfCreation}"
        }
      }
    })

  }

  override fun onStart() {
    super.onStart()
    Log.d("MainActivity", "onStart")
  }

  override fun onResume() {
    super.onResume()
    Log.d("MainActivity", "onResume")
  }

  override fun onPause() {
    super.onPause()
    Log.d("MainActivity", "onPause")
    job.cancel()
  }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)
    if (REQUEST_CODE == requestCode) {
      if (Activity.RESULT_OK == resultCode) {
        textView.text = data?.getStringExtra(DetailActivity.RESULT_KEY) ?: "NO HAY NADA"
      }
    }

  }

  companion object {
    var REQUEST_CODE = 555
  }
}