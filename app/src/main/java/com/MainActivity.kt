package com

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.adapter.MainAdapter
import com.example.mvvmprojectstructure.R
import com.example.mvvmprojectstructure.databinding.ActivityMainBinding
import com.rest.RetrofitService
import com.viewmodel.main.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    lateinit var viewModel: MainViewModel

    private val  retrofitService = RetrofitService.getInstance()

    private val adapter = MainAdapter{

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}