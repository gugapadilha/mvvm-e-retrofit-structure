package com

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.adapter.MainAdapter
import com.example.mvvmprojectstructure.R
import com.example.mvvmprojectstructure.databinding.ActivityMainBinding
import com.repository.MainRepository
import com.rest.RetrofitService
import com.viewmodel.main.MainViewModel
import com.viewmodel.main.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    lateinit var viewModel: MainViewModel

    private val  retrofitService = RetrofitService.getInstance()

    private val adapter = MainAdapter{
        openLink(it.link) //abrir o link do video quando clicar na thumbnail

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, MainViewModelFactory(MainRepository(retrofitService)))
            .get(MainViewModel::class.java)

        binding.recyclerview.adapter = adapter
    }

    override fun onStart() {
        super.onStart()

        viewModel.liveList.observe(this, { lives ->
            Log.i("guga", "livelist.observe") //3 e ultimo, passa aqui
            adapter.setLiveList(lives)
        })

        viewModel.errorMessage.observe(this, { message ->
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        })
    }

    override fun onResume() {
        Log.i("guga", "onResume MainActivity") //1 passa aqui
        super.onResume()

        viewModel.getAllLives()
    }

    private fun openLink(link: String){

        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
        startActivity(browserIntent)

    }

}