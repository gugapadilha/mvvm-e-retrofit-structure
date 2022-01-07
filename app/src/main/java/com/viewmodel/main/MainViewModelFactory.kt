package com.viewmodel.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.repository.MainRepository
import java.lang.IllegalArgumentException

class MainViewModelFactory constructor(
    private val repository : MainRepository
    ) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T { //vai criar nossa viewmodel,
        //dependendo da classe que a gente passar pra ela (isso que a Factory vai fazer)
        return if (modelClass.isAssignableFrom(MainViewModel::class.java)){
            MainViewModel(this.repository) as T
        }else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }


}