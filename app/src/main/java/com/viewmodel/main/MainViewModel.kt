package com.viewmodel.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.models.Live
import com.repository.MainRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel constructor(
    private val repository: MainRepository
    ) : ViewModel() { //temos argumentos aqui, então precisamos criar o MainViewModelFactory.
/*extendendo nossa classe viewModel do androidx.lifecycle no nosso proprio MainViewModel*/

    val liveList = MutableLiveData<List<Live>>()
    val errorMessage = MutableLiveData<String>()

    fun getAllLives(){

        val request = repository.getAllLives() //chama do RetrofitService
        request.enqueue(object : Callback<List<Live>>{
            //executando nosso RetrofitService através do repositorio, pra pegar a chamada da API
            override fun onResponse(call: Call<List<Live>>, response: Response<List<Live>>) {
                //executar quando houver uma resposta
                Log.i("guga", "onResponse") //2 passa aqui
                liveList.postValue(response.body())

            }

            override fun onFailure(call: Call<List<Live>>, t: Throwable) {
                //quando houver uma falha na chamada
                errorMessage.postValue(t.message)

            }

        })
    }
}